package com.example.mainapp;

public class Agent {
    private int UserId;
    private String compName;
    private String name;
    private String password;
    private String domain;
    private String email;
    private String number;

    @Override
    public String toString() {
        return "databass2{" +
                "UserId=" + UserId +
                ", compName='" + compName + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", domain='" + domain + '\'' +
                ", email='" + email + '\'' +
                ", number='" + number + '\'' +
                '}';
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
