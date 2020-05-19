package com.solvd.spaceCompany.models;

import java.util.List;

public class Mission {
    private long id;
    private String name;
    private String objective;
    private Integer span;
    private List<Rocket> rockets;
    private List<Satellite> satellites;

    public Mission() {
    }

    public Mission(String name, String objective, List<Rocket> rockets, Integer span) {
        this.name = name;
        this.objective = objective;
        this.span = span;
        this.rockets = rockets;
    }

    public Mission(String name, String objective, Integer span, List<Satellite> satellites) {
        this.name = name;
        this.objective = objective;
        this.span = span;
        this.satellites = satellites;
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

    public List<Rocket> getRockets() {
        return rockets;
    }

    public void setRockets(List<Rocket> rockets) {
        this.rockets = rockets;
    }

    public List<Satellite> getSatellites() {
        return satellites;
    }

    public void setSatellites(List<Satellite> satellites) {
        this.satellites = satellites;
    }
}
