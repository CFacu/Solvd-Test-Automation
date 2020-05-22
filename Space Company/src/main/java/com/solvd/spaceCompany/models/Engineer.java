package com.solvd.spaceCompany.models;

public class Engineer extends Person{
    private String speciality;
    private SpaceCompany spaceCompany;

    public Engineer() {
    }

    public Engineer(String firstName, String lastName, Integer age, String speciality, SpaceCompany spaceCompany) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setAge(age);
        this.speciality = speciality;
        this.spaceCompany = spaceCompany;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
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