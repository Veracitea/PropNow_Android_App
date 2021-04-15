package com.example.mainapp;

public class Agent {
    private int UserId;
    private String Company;
    private String Username;
    private String Password;
    private String Domain;
    private String Email;
    private String Number;
    private String compName;
    private String name;

    public Agent(int agentID, String company, String username, String password, String domain, String email, String number) {
        this.UserId = agentID;
        this.Company = company;
        this.Username = username;
        this.Password = password;
        this.Domain = domain;
        this.Email = email;
        this.Number = number;
    }
    public Agent(){};

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
    }

    public String getCompany(){
        return Company;
    }

    public void setCompany(String Company){
        this.Company =Company;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getDomain() {
        return Domain;
    }

    public void setDomain(String Domain) {
        this.Domain = Domain;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String Number) {
        this.Number = Number;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public void setName(String name) {
        this.name = name;
    }
}




