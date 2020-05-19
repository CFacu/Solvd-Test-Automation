package com.solvd.spaceCompany.models;

import com.solvd.spaceCompany.models.enums.Duty;

public class Astronaut extends Person {
    private String duty;

    public Astronaut() {
    }

    public Astronaut(String firstName, String lastName, Integer age, String duty) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setAge(age);
        this.duty = duty;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    @Override
    public String toString() {
        return this.getFirstName() + " " + this.getLastName();
    }
}
