package com.solvd.spaceCompany.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "astronaut")
@XmlAccessorType(XmlAccessType.FIELD)
public class Astronaut extends Person {
    @XmlElement
    private String duty;
    private Station station;

    public Astronaut() {
    }

    public Astronaut(String firstName, String lastName, Integer age, String duty, Station station) {
        super(firstName, lastName, age);
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
