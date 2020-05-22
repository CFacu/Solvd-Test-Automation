package com.solvd.spaceCompany.models;

public class CEO extends Person{
    private String email;
    private SpaceCompany spaceCompany;

    public CEO() {
    }

    public CEO(String firstName, String lastName, Integer age,String email, SpaceCompany spaceCompany) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setAge(age);
        this.email = email;
        this.spaceCompany = spaceCompany;
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

    public SpaceCompany getSpaceCompany() {
        return spaceCompany;
    }

    public void setSpaceCompany(SpaceCompany spaceCompany) {
        this.spaceCompany = spaceCompany;
    }
}
