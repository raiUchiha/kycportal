package com.hashedin.kycportal.models;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

@Entity
public class EmployeeBankDetails {
    @Id
    @GeneratedValue(strategy = AUTO)
    private long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="userId")
    private User userId;
    private String bankName;
    private int accountNo;
    private String IFCS;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public int getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }

    public String getIFCS() {
        return IFCS;
    }

    public void setIFCS(String IFCS) {
        this.IFCS = IFCS;
    }
}
