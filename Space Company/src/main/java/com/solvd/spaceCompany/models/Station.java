package com.solvd.spaceCompany.models;

import java.util.List;

public class Station {
    private long id;
    private String name;
    private Address address;
    private List<Astronaut> astronauts;

    public Station() {
    }

    public Station(String name, Address address, List<Astronaut> astronauts) {
        this.name = name;
        this.address = address;
        this.astronauts = astronauts;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Astronaut> getAstronauts() {
        return astronauts;
    }

    public void setAstronauts(List<Astronaut> astronauts) {
        this.astronauts = astronauts;
    }
}
