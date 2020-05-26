package com.solvd.spaceCompany.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "engineer")
@XmlAccessorType(XmlAccessType.FIELD)
public class Engineer extends Person{
    @XmlElement
    private String speciality;
    private SpaceCompany spaceCompany;

    public Engineer() {
    }

    public Engineer(String firstName, String lastName, Integer age, String speciality, SpaceCompany spaceCompany) {
        super(firstName, lastName, age);
        this.speciality = speciality;
        this.spaceCompany = spaceCompany;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public SpaceCompany getSpaceCompany() {
        return spaceCompany;
    }

    public void setSpaceCompany(SpaceCompany spaceCompany) {
        this.spaceCompany = spaceCompany;
    }
}