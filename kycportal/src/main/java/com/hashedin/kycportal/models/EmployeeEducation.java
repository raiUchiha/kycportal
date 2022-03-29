package com.hashedin.kycportal.models;

import javax.persistence.*;

@Entity
public class EmployeeEducation {
    @Id
    private long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="userId")
    private User userId;
    private String highestEducationDegree;
    private int yearOfPassing;
    private int cpi;
    private String college;
    private String University;

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

    public String getHighestEducationDegree() {
        return highestEducationDegree;
    }

    public void setHighestEducationDegree(String highestEducationDegree) {
        this.highestEducationDegree = highestEducationDegree;
    }

    public int getYearOfPassing() {
        return yearOfPassing;
    }

    public void setYearOfPassing(int yearOfPassing) {
        this.yearOfPassing = yearOfPassing;
    }

    public int getCpi() {
        return cpi;
    }

    public void setCpi(int cpi) {
        this.cpi = cpi;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getUniversity() {
        return University;
    }

    public void setUniversity(String university) {
        University = university;
    }
}
