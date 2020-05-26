package com.solvd.spaceCompany.services;

import com.solvd.spaceCompany.daos.mysqlImpl.AstronautDAO;
import com.solvd.spaceCompany.daos.mysqlImpl.SatelliteDAO;
import com.solvd.spaceCompany.daos.mysqlImpl.StationDAO;
import com.solvd.spaceCompany.models.Station;

import java.util.ArrayList;
import java.util.List;

public class StationService {
    private StationDAO stationDAO;
    private SatelliteDAO satelliteDAO;
    private AstronautDAO astronautDAO;

    public StationService() {
        this.stationDAO = new StationDAO();
        this.satelliteDAO = new SatelliteDAO();
        this.astronautDAO = new AstronautDAO();
    }

    public Station getStation(Long id) {
        Station station = stationDAO.get(id);
        this.updateStation(station);
        return station;
    }

    public void updateStation(Station station) {
        station.setAstronauts(astronautDAO.getAllByStationId(station.getId()));
        station.setSatellites(satelliteDAO.getAllByStationId(station.getId()));
    }

    public List<Station> getAllStations() {
        List<Station> stations = new ArrayList<>();
        stations = stationDAO.getAll();
        stations.forEach(station -> this.updateStation(station));
        return stations;
    }
}