package com.monese.payments.service;

import com.monese.payments.exception.AccountException;
import com.monese.payments.exception.AmountException;
import com.monese.payments.exception.PaymentsGlobalException;
import com.monese.payments.model.Account;
import com.monese.payments.model.request.TransactionRequest;
import com.monese.payments.model.response.TransactionResponse;
import com.monese.payments.repositories.AccountsRepository;
import com.monese.payments.validators.AccountValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceHandler {
    @Autowired
    private AccountValidator accountValidator;

    @Autowired
    private AccountsRepository accountsRepository;

    @Autowired
    private TransactionService transactionService;

    private Logger logger = LoggerFactory.getLogger(TransactionServiceHandler.class);

    public TransactionResponse fundTransfer(TransactionRequest transactionRequest) {
        logger.info("Entered into method fundTransfer()");
        try {

            validateRequest(transactionRequest);
        } catch (AccountException e) {
            logger.error("Account Validation Error:", e.getMessage());
            return new TransactionResponse(transactionRequest.getFromAccount(), transactionRequest.getToAccount(), 500, "F", e.getMessage());
        } catch (AmountException e) {
            logger.error("Amount Validation Error:", e.getMessage());
            return new TransactionResponse(transactionRequest.getFromAccount(), transactionRequest.getToAccount(), 500, "F", e.getMessage());

        }
        Account fromAccount = accountsRepository.findOne(Long.parseLong(transactionRequest.getFromAccount()));
        Account toAccount = accountsRepository.findOne(Long.parseLong(transactionRequest.getToAccount()));
        try {
            return transactionService.fundTransfer(fromAccount, toAccount, transactionRequest.getAmount());
        } catch (PaymentsGlobalException e) {
            logger.error("Exception in Fund Transfer:", e.getMessage());
            return new TransactionResponse(transactionRequest.getFromAccount(), transactionRequest.getToAccount(), 500, "F", e.getMessage());
        }

    }

    private void validateRequest(TransactionRequest transactionRequest) throws AccountException, AmountException {
        accountValidator.validateTransaction(transactionRequest);
    }
}
