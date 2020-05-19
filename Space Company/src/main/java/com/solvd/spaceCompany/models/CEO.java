package com.solvd.spaceCompany.models;

public class CEO extends Person{
    private String email;

    public CEO() {
    }

    public CEO(String firstName, String lastName, Integer age,String email) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setAge(age);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return this.getFirstName() + " " + this.getLastName();
    }
}
