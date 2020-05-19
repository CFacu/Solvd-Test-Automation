package com.solvd.spaceCompany.models;

public class Satellite extends Spacecraft{
    private SatelliteMissionDate launchDate;

    public Satellite() {
    }

    public Satellite(String name, Float weight, Float cargoCapacity, Float fuelCapacity, SatelliteMissionDate launchDate) {
        this.launchDate = launchDate;
        this.setName(name);
        this.setCargoCapacity(cargoCapacity);
        this.setFuelCapacity(fuelCapacity);
        this.setWeight(weight);
    }

    public SatelliteMissionDate getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(SatelliteMissionDate launchDate) {
        this.launchDate = launchDate;
    }
}
