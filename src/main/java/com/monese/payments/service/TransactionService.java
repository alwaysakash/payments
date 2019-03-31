package com.monese.payments.service;

import com.monese.payments.exception.AmountException;
import com.monese.payments.exception.PaymentsGlobalException;
import com.monese.payments.model.Account;
import com.monese.payments.model.Transaction;
import com.monese.payments.model.response.TransactionResponse;
import com.monese.payments.repositories.AccountsRepository;
import com.monese.payments.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountsRepository accountsRepository;

    public TransactionResponse fundTransfer(Account fromAccount, Account toAccount, Long amount) throws PaymentsGlobalException {
        if (fromAccount.getAccountNumber() < toAccount.getAccountNumber()) {
            synchronized (fromAccount) {
                return doTransfer(fromAccount, toAccount, amount);
            }
        } else if (fromAccount.getAccountNumber() > toAccount.getAccountNumber()) {
            synchronized (toAccount) {
                return doTransfer(fromAccount, toAccount, amount);
            }
        } else {
            throw new PaymentsGlobalException("From and To accounts cannot be same");
        }
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    private TransactionResponse doTransfer(Account fromAccount, Account toAccount, Long amount) throws AmountException {
        Transaction tx = transactionRepository.save(new Transaction(fromAccount.getAccountNumber(), toAccount.getAccountNumber(), amount, "I", "INITIATED"));
        try {
            doDebit(fromAccount, amount);
            tx.setTransactionStatusCode("D");
            tx.setTransactionStatusMessage("DEBITED");
            transactionRepository.save(tx);
            doCredit(toAccount, amount);
            tx.setTransactionStatusCode("C");
            tx.setTransactionStatusMessage("CREDITED");
            transactionRepository.save(tx);

        } catch (AmountException e) {
            tx.setTransactionStatusCode("F");
            tx.setTransactionStatusMessage("FAILED");
            transactionRepository.save(tx);
            throw e;
        }
        tx.setTransactionStatusCode("S");
        tx.setTransactionStatusMessage("SUCCESS");
        transactionRepository.save(tx);
        return new TransactionResponse(fromAccount.getAccountNumber(), toAccount.getAccountNumber(), 200, "S", "SUCCESS");
    }

    private void doCredit(Account toAccount, Long amount) {
        toAccount.setBalance(toAccount.getBalance() + amount);
        accountsRepository.save(toAccount);

    }

    private void doDebit(Account fromAccount, Long amount) throws AmountException {
        if (fromAccount.getBalance() < amount) {
            throw new AmountException("INSUFFICIENT FUNDS");
        }
        fromAccount.setBalance(fromAccount.getBalance() - amount);
        accountsRepository.save(fromAccount);
    }
}
