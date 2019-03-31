package com.monese.payments.model.response;

public class TransactionResponse {

    int code;
    private String status;
    private String message;
    private String fromAccount;
    private String toAccount;


    public TransactionResponse(Long fromAccount, Long toAccount, int code, String status, String message) {
        this.fromAccount = String.valueOf(fromAccount);
        this.toAccount = String.valueOf(toAccount);
        this.code = code;
        this.status = status;
        this.message = message;

    }

    public TransactionResponse(String fromAccount, String toAccount, int code, String status, String message) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.code = code;
        this.status = status;
        this.message = message;

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }


}
