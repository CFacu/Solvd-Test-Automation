package com.solvd.spaceCompany.models;

import javax.xml.bind.annotation.*;
import java.sql.Date;

@XmlRootElement(name = "satelliteMissionDate")
@XmlAccessorType(XmlAccessType.FIELD)
public class SatelliteMissionDate {
    @XmlAttribute
    private Long id;
    @XmlElement
    private Date launchDate;
    private Satellite satellite;
    private Mission mission;


    public SatelliteMissionDate() {
    }

    public SatelliteMissionDate(Long id, Date launchDate, Satellite satellite, Mission mission) {
        this.id = id;
        this.launchDate = launchDate;
        this.satellite = satellite;
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

    public Satellite getSatellite() {
        return satellite;
    }

    public void setSatellite(Satellite satellite) {
        this.satellite = satellite;
    }

    public Mission getMission() {
        return mission;
    }

    public void setMission(Mission mission) {
        this.mission = mission;
    }
}
