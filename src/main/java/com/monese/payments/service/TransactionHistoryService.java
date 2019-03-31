package com.monese.payments.service;

import com.monese.payments.exception.AccountException;
import com.monese.payments.model.Transaction;
import com.monese.payments.repositories.AccountsRepository;
import com.monese.payments.repositories.TransactionRepository;
import com.monese.payments.validators.AccountValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TransactionHistoryService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountsRepository accountsRepository;

    @Autowired
    private AccountValidator accountValidator;

    public Page<Transaction> getTransactionHistory(Pageable pageable, String accountNumber) throws AccountException {
        accountValidator.validateAccount(accountNumber, "Account Number Exist", "Account Number doesn't exist");
        return transactionRepository.findByFromAccountOrToAccount(pageable, accountNumber, accountNumber);
    }
}
