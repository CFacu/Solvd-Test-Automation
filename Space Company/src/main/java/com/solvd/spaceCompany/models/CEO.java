package com.solvd.spaceCompany.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "CEO")
@XmlAccessorType(XmlAccessType.FIELD)
public class CEO extends Person{
    @XmlElement
    private String email;
    private SpaceCompany spaceCompany;

    public CEO() {
    }

    public CEO(String firstName, String lastName, Integer age,String email, SpaceCompany spaceCompany) {
        super(firstName, lastName, age);
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
