package com.solvd.spaceCompany.models;

public class Astronaut extends Person {
    private String duty;
    private Station station;

    public Astronaut() {
    }

    public Astronaut(String firstName, String lastName, Integer age, String duty, Station station) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setAge(age);
        this.duty = duty;
        this.station = station;
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

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }
}
