package com.solvd.spaceCompany.models;

import java.sql.Date;

public class SatelliteMissionDate {
    private Long id;
    private Date launchDate;


    public SatelliteMissionDate() {
    }

    public SatelliteMissionDate(Long id, Date launchDate) {
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
