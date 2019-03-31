package com.monese.payments.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tx_log")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tx_id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "tx_from_acc_nm", nullable = false, updatable = false)
    private String fromAccount;

    @Column(name = "tx_to_acc_nm", nullable = false, updatable = false)
    private String toAccount;

    @Column(name = "tx_to_amt", nullable = false, updatable = false)
    private Long amount;

    @Column(name = "tx_st_cd", nullable = false)
    private String transactionStatusCode;

    @Column(name = "tx_st_mg", nullable = false)
    private String transactionStatusMessage;


    @Column(name = "tx_og_ts", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transactionOriginationTime;

    public Transaction(Long fromAccount, Long toAccount, Long amount, String code, String message) {
        this.fromAccount = String.valueOf(fromAccount);
        this.toAccount = String.valueOf(toAccount);
        this.amount = amount;
        this.transactionStatusCode = code;
        this.transactionStatusMessage = message;
    }

    public Transaction(Long id, String fromAccount, String toAccount, Long amount, String transactionStatusCode, String transactionStatusMessage, Date transactionOriginationTime) {
        this.id = id;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.transactionStatusCode = transactionStatusCode;
        this.transactionStatusMessage = transactionStatusMessage;
        this.transactionOriginationTime = transactionOriginationTime;
    }

    public Transaction() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getTransactionStatusCode() {
        return transactionStatusCode;
    }

    public void setTransactionStatusCode(String transactionStatusCode) {
        this.transactionStatusCode = transactionStatusCode;
    }

    public String getTransactionStatusMessage() {
        return transactionStatusMessage;
    }

    public void setTransactionStatusMessage(String transactionStatusMessage) {
        this.transactionStatusMessage = transactionStatusMessage;
    }

    public Date getTransactionOriginationTime() {
        return transactionOriginationTime;
    }

    public void setTransactionOriginationTime(Date transactionOriginationTime) {
        this.transactionOriginationTime = transactionOriginationTime;
    }
}
