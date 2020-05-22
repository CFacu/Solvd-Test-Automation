package com.solvd.spaceCompany.models;

public class Rocket extends Spacecraft {
    private Integer passengerCapacity;
    private RocketMissionDate rocketMissionDate;
    private SpaceCompany spaceCompany;

    public Rocket() {
    }

    public Rocket(String name, Float weight, Float cargoCapacity, Float fuelCapacity, Integer passengerCapacity, RocketMissionDate rocketMissionDate, SpaceCompany spaceCompany) {
        this.passengerCapacity = passengerCapacity;
        this.rocketMissionDate = rocketMissionDate;
        this.setName(name);
        this.setCargoCapacity(cargoCapacity);
        this.setFuelCapacity(fuelCapacity);
        this.setWeight(weight);
        this.spaceCompany = spaceCompany;
    }

    public Integer getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(Integer passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    public RocketMissionDate getRocketMissionDate() {
        return rocketMissionDate;
    }

    public void setRocketMissionDate(RocketMissionDate rocketMissionDate) {
        this.rocketMissionDate = rocketMissionDate;
    }

    public SpaceCompany getSpaceCompany() {
        return spaceCompany;
    }

    public void setSpaceCompany(SpaceCompany spaceCompany) {
        this.spaceCompany = spaceCompany;
    }
}
