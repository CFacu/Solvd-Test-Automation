package com.solvd.spaceCompany.models;

public class Satellite extends Spacecraft{
    private SatelliteMissionDate launchDate;
    private Station station;

    public Satellite() {
    }

    public Satellite(String name, Float weight, Float cargoCapacity, Float fuelCapacity, SatelliteMissionDate launchDate, Station station) {
        this.launchDate = launchDate;
        this.setName(name);
        this.setCargoCapacity(cargoCapacity);
        this.setFuelCapacity(fuelCapacity);
        this.setWeight(weight);
        this.station = station;
    }

    public SatelliteMissionDate getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(SatelliteMissionDate launchDate) {
        this.launchDate = launchDate;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }
}
