package com.solvd.spaceCompany.models;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "SpaceCompany")
@XmlAccessorType(XmlAccessType.FIELD)
public class SpaceCompany {
    @XmlAttribute(name = "id")
    private Long id;
    @XmlElement
    private String name;
    @XmlElementWrapper(name = "stations")
    @XmlElement(name = "station", type = Station.class)
    private List<Station> stations;
    @XmlElementWrapper(name = "engineers")
    @XmlElement(name = "engineer", type = Engineer.class)
    private List<Engineer> engineers;
    @XmlElementWrapper(name = "rockets")
    @XmlElement(name = "rocket", type = Rocket.class)
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

    @Override
    public String toString() {
        return this.name;
    }
}
