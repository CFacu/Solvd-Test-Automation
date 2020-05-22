package com.solvd.spaceCompany.models;

import java.util.List;

public class Mission {
    private long id;
    private String name;
    private String objective;
    private Integer span;
    private List<RocketMissionDate> rockets;
    private List<SatelliteMissionDate> satellites;

    public Mission() {
    }

    public Mission(String name, String objective, List<RocketMissionDate> rockets,  Integer span) {
        this.name = name;
        this.objective = objective;
        this.span = span;
        this.rockets = rockets;
        this.satellites = null;
    }

    public Mission(String name, String objective, Integer span, List<SatelliteMissionDate> satellites) {
        this.name = name;
        this.objective = objective;
        this.span = span;
        this.satellites = satellites;
        this.rockets = null;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public Integer getSpan() {
        return span;
    }

    public void setSpan(Integer span) {
        this.span = span;
    }

    public List<RocketMissionDate> getRockets() {
        return rockets;
    }

    public void setRockets(List<RocketMissionDate> rockets) {
        this.rockets = rockets;
    }

    public List<SatelliteMissionDate> getSatellites() {
        return satellites;
    }

    public void setSatellites(List<SatelliteMissionDate> satellites) {
        this.satellites = satellites;
    }
}
