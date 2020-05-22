package com.solvd.spaceCompany.models;

import java.util.List;

public class SpaceCompany {
    private Long id;
    private String name;
    private List<Station> stations;
    private List<Engineer> engineers;
    private List<Rocket> rockets;

    public SpaceCompany() {
    }

    public SpaceCompany(String name, List<Station> stations, List<Engineer> engineers, List<Rocket> rockets) {
        this.name = name;
        this.stations = stations;
        this.engineers = engineers;
        this.rockets = rockets;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }

    public List<Engineer> getEngineers() {
        return engineers;
    }

    public void setEngineers(List<Engineer> engineers) {
        this.engineers = engineers;
    }

    public List<Rocket> getRockets() {
        return rockets;
    }

    public void setRockets(List<Rocket> rockets) {
        this.rockets = rockets;
    }
}
