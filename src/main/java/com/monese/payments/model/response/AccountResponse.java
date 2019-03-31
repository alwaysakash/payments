package com.monese.payments.model.response;

public class AccountResponse {

    private Long accountNumber;
    private Long accountBalance;

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Long getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Long accountBalance) {
        this.accountBalance = accountBalance;
    }

    public AccountResponse(Long accountNumber, Long accountBalance) {
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
    }
}
