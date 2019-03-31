package com.monese.payments.model;

import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "acc_details")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "acc_nm", nullable = false, updatable = false)
    private Long accountNumber;

    @Column(name = "acc_bal", nullable = false)
    private Long balance;

    @Column(name = "acc_cre_id", nullable = false)
    private String creatorOperatorId;

    @Column(name = "acc_cre_ts", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @Column(name = "acc_mnt_id", nullable = false)
    private String maintainanceOperatorId;

    @UpdateTimestamp
    @Column(name = "acc_mnt_ts", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date maintananceTime;

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public String getCreatorOperatorId() {
        return creatorOperatorId;
    }

    public void setCreatorOperatorId(String creatorOperatorId) {
        this.creatorOperatorId = creatorOperatorId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getMaintainanceOperatorId() {
        return maintainanceOperatorId;
    }

    public void setMaintainanceOperatorId(String maintainanceOperatorId) {
        this.maintainanceOperatorId = maintainanceOperatorId;
    }

    public Date getMaintananceTime() {
        return maintananceTime;
    }

    public void setMaintananceTime(Date maintananceTime) {
        this.maintananceTime = maintananceTime;
    }
}
