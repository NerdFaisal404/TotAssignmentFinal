package com.teamcreative.totassignment.data.model;

import java.io.Serializable;

public class StudentInfo implements Serializable {
    private String id;
    private String name;
    private String institution;
    private String email;
    private String phone;
    private String gender;

    public StudentInfo() {
    }

    public StudentInfo(String name, String institution, String email, String phone, String gender) {
        this.name = name;
        this.institution = institution;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
