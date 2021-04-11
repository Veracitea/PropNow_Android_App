package com.example.mainapp;

public class NonAgent {
    private int UserId;
    private String name;
    private int age;
    private int salary;
    private String password;
    private String domain;
    private String email;
    private String number;
    private String secondbuyer_name;
    private String secondbuyer_age;
    private int secondbuyer_salary;
    private String relationship; //altins

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
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

    public String getSecondbuyer_name() {
        return secondbuyer_name;
    }

    public void setSecondbuyer_name(String secondbuyer_name) {
        this.secondbuyer_name = secondbuyer_name;
    }

    public String getSecondbuyer_age() {
        return secondbuyer_age;
    }

    public void setSecondbuyer_age(String secondbuyer_age) {
        this.secondbuyer_age = secondbuyer_age;
    }

    public int getSecondbuyer_salary() {
        return secondbuyer_salary;
    }

    public void setSecondbuyer_salary(int secondbuyer_salary) {
        this.secondbuyer_salary = secondbuyer_salary;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    @Override
    public String toString() {
        return "nonagent{" +
                "UserId=" + UserId +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", password='" + password + '\'' +
                ", domain='" + domain + '\'' +
                ", email='" + email + '\'' +
                ", number='" + number + '\'' +
                ", secondbuyer_name='" + secondbuyer_name + '\'' +
                ", secondbuyer_age='" + secondbuyer_age + '\'' +
                ", secondbuyer_salary=" + secondbuyer_salary +
                ", relationship='" + relationship + '\'' +
                '}';
    }
}
