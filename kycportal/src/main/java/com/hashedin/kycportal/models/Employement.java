package com.hashedin.kycportal.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.AUTO;

@Entity
public class Employement {
    @Id
    @GeneratedValue(strategy = AUTO)
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="userId")
    private User userId;
    private String previousCompany;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date joiningDate;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date exitDate;
    private int totalYearsOfExperience;
    private int uan;

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

    public String getPreviousCompany() {
        return previousCompany;
    }

    public void setPreviousCompany(String previousCompany) {
        this.previousCompany = previousCompany;
    }

    public Date getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(Date joiningDate) {
        this.joiningDate = joiningDate;
    }

    public Date getExitDate() {
        return exitDate;
    }

    public void setExitDate(Date exitDate) {
        this.exitDate = exitDate;
    }

    public int getTotalYearsOfExperience() {
        return totalYearsOfExperience;
    }

    public void setTotalYearsOfExperience(int totalYearsOfExperience) {
        this.totalYearsOfExperience = totalYearsOfExperience;
    }

    public int getUan() {
        return uan;
    }

    public void setUan(int uan) {
        this.uan = uan;
    }
}
