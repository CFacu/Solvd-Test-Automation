package com.solvd.spaceCompany.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "rocket")
@XmlAccessorType(XmlAccessType.FIELD)
public class Rocket extends Spacecraft {
    @XmlElement
    private Integer passengerCapacity;
    private SpaceCompany spaceCompany;

    public Rocket() {
    }

    public Rocket(String name, Float weight, Float cargoCapacity, Float fuelCapacity, Integer passengerCapacity, SpaceCompany spaceCompany) {
        super(name, weight, cargoCapacity, fuelCapacity);
        this.passengerCapacity = passengerCapacity;
        this.spaceCompany = spaceCompany;
    }

    public Integer getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(Integer passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    public SpaceCompany getSpaceCompany() {
        return spaceCompany;
    }

    public void setSpaceCompany(SpaceCompany spaceCompany) {
        this.spaceCompany = spaceCompany;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + this.getId() +
                ", name='" + this.getName() + '\'' +
                ", weight=" + this.getWeight() +
                ", cargoCapacity=" + this.getCargoCapacity() +
                ", fuelCapacity=" + this.getFuelCapacity() +
                ", passengerCapacity=" + passengerCapacity +
                '}';
    }
}
