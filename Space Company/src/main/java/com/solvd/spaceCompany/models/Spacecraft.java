package com.solvd.spaceCompany.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Spacecraft {
    @XmlAttribute
    private long id;
    @XmlElement
    private String name;
    @XmlElement
    private Float weight;
    @XmlElement
    private Float cargoCapacity;
    @XmlElement
    private Float fuelCapacity;

    public Spacecraft() {
    }

    public Spacecraft(String name, Float weight, Float cargoCapacity, Float fuelCapacity) {
        this.name = name;
        this.weight = weight;
        this.cargoCapacity = cargoCapacity;
        this.fuelCapacity = fuelCapacity;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Float getCargoCapacity() {
        return cargoCapacity;
    }

    public void setCargoCapacity(Float cargoCapacity) {
        this.cargoCapacity = cargoCapacity;
    }

    public Float getFuelCapacity() {
        return fuelCapacity;
    }

    public void setFuelCapacity(Float fuelCapacity) {
        this.fuelCapacity = fuelCapacity;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", cargoCapacity=" + cargoCapacity +
                ", fuelCapacity=" + fuelCapacity +
                '}';
    }
}
