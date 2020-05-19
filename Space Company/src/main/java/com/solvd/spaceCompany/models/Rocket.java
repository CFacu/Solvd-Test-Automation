package com.solvd.spaceCompany.models;

public class Rocket extends Spacecraft {
    private Integer passengerCapacity;
    private RocketMissionDate rocketMissionDate;

    public Rocket() {
    }

    public Rocket(String name, Float weight, Float cargoCapacity, Float fuelCapacity, Integer passengerCapacity, RocketMissionDate rocketMissionDate) {
        this.passengerCapacity = passengerCapacity;
        this.rocketMissionDate = rocketMissionDate;
        this.setName(name);
        this.setCargoCapacity(cargoCapacity);
        this.setFuelCapacity(fuelCapacity);
        this.setWeight(weight);
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
}
