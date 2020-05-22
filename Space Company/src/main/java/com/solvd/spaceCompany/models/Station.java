package com.solvd.spaceCompany.models;

import java.util.List;

public class Station {
    private long id;
    private String name;
    private List<Satellite> satellites;
    private List<Astronaut> astronauts;
    private SpaceCompany spaceCompany;

    public Station() {
    }

    public Station(String name, List<Satellite> satellites, List<Astronaut> astronauts, SpaceCompany spaceCompany) {
        this.name = name;
        this.satellites = satellites;
        this.astronauts = astronauts;
        this.spaceCompany = spaceCompany;
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

    public List<Astronaut> getAstronauts() {
        return astronauts;
    }

    public void setAstronauts(List<Astronaut> astronauts) {
        this.astronauts = astronauts;
    }

    public SpaceCompany getSpaceCompany() {
        return spaceCompany;
    }

    public void setSpaceCompany(SpaceCompany spaceCompany) {
        this.spaceCompany = spaceCompany;
    }

    public List<Satellite> getSatellites() {
        return satellites;
    }

    public void setSatellites(List<Satellite> satellites) {
        this.satellites = satellites;
    }
}
