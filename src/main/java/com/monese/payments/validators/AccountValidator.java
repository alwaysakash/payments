package com.monese.payments.validators;

import com.monese.payments.exception.AccountException;
import com.monese.payments.exception.AmountException;
import com.monese.payments.model.request.TransactionRequest;
import com.monese.payments.repositories.AccountsRepository;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountValidator {

    @Autowired
    private AccountsRepository accountsRepository;

    private Logger logger = LoggerFactory.getLogger(AccountValidator.class);

    public void validateTransaction(TransactionRequest request) throws AccountException, AmountException {
        String fromAccount = request.getFromAccount();
        String toAccount = request.getToAccount();
        validateAccount(fromAccount, "From account  exists", "From Account doesn't exist");
        validateAccount(toAccount, "To account  exists", "To Account doesn't exist");
        if (null == request.getAmount() || request.getAmount() <= 0) {
            throw new AmountException(("Invalid Amount"));
        }

    }

    public void validateAccount(String fromAccount, String successMessage, String failureMessage) throws AccountException {
        if (fromAccount != null && StringUtils.isNotBlank(fromAccount) && StringUtils.isNumeric(fromAccount) && accountsRepository.exists(Long.parseLong(fromAccount))) {
            logger.debug(successMessage, fromAccount);
        } else {
            throw new AccountException(failureMessage);
        }
    }

}
