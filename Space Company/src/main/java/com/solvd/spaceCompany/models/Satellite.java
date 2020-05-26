package com.solvd.spaceCompany.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "satellite")
@XmlAccessorType(XmlAccessType.FIELD)
public class Satellite extends Spacecraft{
    private Station station;

    public Satellite() {
    }

    public Satellite(String name, Float weight, Float cargoCapacity, Float fuelCapacity, SatelliteMissionDate launchDate, Station station) {
        super(name, weight, cargoCapacity, fuelCapacity);
        this.station = station;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }
}
