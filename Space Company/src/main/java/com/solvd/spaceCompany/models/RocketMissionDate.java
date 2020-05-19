package com.solvd.spaceCompany.models;

import java.sql.Date;

public class RocketMissionDate {
    private Long id;
    private Date launchDate;


    public RocketMissionDate() {
    }

    public RocketMissionDate(Long id, Date launchDate) {
        this.id = id;
        this.launchDate = launchDate;
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
}
