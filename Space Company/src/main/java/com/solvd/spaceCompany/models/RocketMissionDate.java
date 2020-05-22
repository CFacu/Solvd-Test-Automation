package com.solvd.spaceCompany.models;

import java.sql.Date;
import java.util.List;

public class RocketMissionDate {
    private Long id;
    private Date launchDate;
    private Rocket rocket;
    private Mission mission;

    public RocketMissionDate() {
    }

    public RocketMissionDate(Long id, Date launchDate, Rocket rocket, Mission mission) {
        this.id = id;
        this.launchDate = launchDate;
        this.rocket = rocket;
        this.mission = mission;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(Date launchDate) {
        this.launchDate = launchDate;
    }

    public Rocket getRocket() {
        return rocket;
    }

    public void setRocket(Rocket rocket) {
        this.rocket = rocket;
    }

    public Mission getMission() {
        return mission;
    }

    public void setMission(Mission mission) {
        this.mission = mission;
    }
}
