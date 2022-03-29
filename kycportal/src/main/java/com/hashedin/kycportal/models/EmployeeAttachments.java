package com.hashedin.kycportal.models;

import javax.persistence.*;

@Entity
public class EmployeeAttachments {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="userId")
    private User userId;
    private String aadharUrl;
    private String panUrl;
    private String highschoolUrl;
    private String interUrl;
    private String graduationUrl;
    private String postGraduationUrl;

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

    public String getAadharUrl() {
        return aadharUrl;
    }

    public void setAadharUrl(String aadharUrl) {
        this.aadharUrl = aadharUrl;
    }

    public String getPanUrl() {
        return panUrl;
    }

    public void setPanUrl(String panUrl) {
        this.panUrl = panUrl;
    }

    public String getHighschoolUrl() {
        return highschoolUrl;
    }

    public void setHighschoolUrl(String highschoolUrl) {
        this.highschoolUrl = highschoolUrl;
    }

    public String getInterUrl() {
        return interUrl;
    }

    public void setInterUrl(String interUrl) {
        this.interUrl = interUrl;
    }

    public String getGraduationUrl() {
        return graduationUrl;
    }

    public void setGraduationUrl(String graduationUrl) {
        this.graduationUrl = graduationUrl;
    }

    public String getPostGraduationUrl() {
        return postGraduationUrl;
    }

    public void setPostGraduationUrl(String postGraduationUrl) {
        this.postGraduationUrl = postGraduationUrl;
    }
}
