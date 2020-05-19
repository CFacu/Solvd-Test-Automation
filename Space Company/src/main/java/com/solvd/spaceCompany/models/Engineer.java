package com.solvd.spaceCompany.models;

import com.solvd.spaceCompany.models.enums.Speciality;

public class Engineer extends Person{
    private String speciality;

    public Engineer() {
    }

    public Engineer(String firstName, String lastName, Integer age, String speciality) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setAge(age);
        this.speciality = speciality;
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
}