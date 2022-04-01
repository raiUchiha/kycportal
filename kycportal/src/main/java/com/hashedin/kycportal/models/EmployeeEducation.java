package com.hashedin.kycportal.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.Generated;

import javax.persistence.*;

import java.util.Date;

import static javax.persistence.GenerationType.AUTO;

@Entity
public class EmployeeEducation {
    @Id
    @GeneratedValue(strategy = AUTO)
    private long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="userId")
    private User userId;
    private String highestEducationDegree;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date yearOfPassing;
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

    public Date getYearOfPassing() {
        return yearOfPassing;
    }

    public void setYearOfPassing(Date yearOfPassing) {
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
