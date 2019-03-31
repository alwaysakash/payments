package com.monese.payments.exception;

public class AccountException extends PaymentsGlobalException {
    AccountException() {
        super();
    }

    public AccountException(String message) {
        super(message);
    }


}
