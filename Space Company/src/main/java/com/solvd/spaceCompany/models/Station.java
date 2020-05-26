package com.solvd.spaceCompany.models;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "station")
@XmlAccessorType(XmlAccessType.FIELD)
public class Station {
    @XmlAttribute
    private long id;
    @XmlElement
    private String name;
    @XmlElementWrapper(name = "satellites")
    @XmlElement(name = "satellite", type = Satellite.class)
    private List<Satellite> satellites;
    @XmlElementWrapper(name = "astronauts")
    @XmlElement(name = "astronaut", type = Astronaut.class)
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

    @Override
    public String toString() {
        return "Station{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", satellites=" + satellites +
                ", astronauts=" + astronauts +
                ", spaceCompany=" + spaceCompany +
                '}';
    }
}
