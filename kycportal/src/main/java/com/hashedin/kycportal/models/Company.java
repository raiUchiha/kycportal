package com.hashedin.kycportal.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.AUTO;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = AUTO)
    private long id;
    @Column(unique=true)
    private String companyName;
    @Column(unique=true)
    private String companyPan;
    @Column(unique=true)
    private String companyGst;
    private String industry;
    @Column(unique=true)
    private String email;
    private String password;
    private int phone;
    private String Address;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyPan() {
        return companyPan;
    }

    public void setCompanyPan(String companyPan) {
        this.companyPan = companyPan;
    }

    public String getCompanyGst() {
        return companyGst;
    }

    public void setCompanyGst(String companyGst) {
        this.companyGst = companyGst;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
