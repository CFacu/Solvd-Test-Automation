package com.solvd.spaceCompany.services;

import com.solvd.spaceCompany.daos.IAstronautDAO;
import com.solvd.spaceCompany.daos.ISatelliteDAO;
import com.solvd.spaceCompany.daos.IStationDAO;
import com.solvd.spaceCompany.daos.mysqlImpl.AstronautDAO;
import com.solvd.spaceCompany.daos.mysqlImpl.SatelliteDAO;
import com.solvd.spaceCompany.daos.mysqlImpl.StationDAO;
import com.solvd.spaceCompany.models.Station;
import com.solvd.spaceCompany.utils.ConnectionFactory;

import java.util.List;

public class StationService {
    private IStationDAO stationDAO;
    private ISatelliteDAO satelliteDAO;
    private IAstronautDAO astronautDAO;

    public StationService() {
        this.stationDAO = ConnectionFactory.getStationMapper();
        this.satelliteDAO = ConnectionFactory.getSatelliteMapper();
        this.astronautDAO = ConnectionFactory.getAstronautMapper();
    }

    public Station getStation(Long id) {
        Station station = stationDAO.get(id);
        this.updateStation(station);
        return station;
    }

    public List<Station> getStationsByCompanyId(Long id) {
        List<Station> stations = stationDAO.getAllBySpaceCompanyId(id);
        stations.forEach(station -> this.updateStation(station));
        return stations;
    }

    public void updateStation(Station station) {
        station.setAstronauts(astronautDAO.getAllByStationId(station.getId()));
        station.setSatellites(satelliteDAO.getAllByStationId(station.getId()));
    }

    public List<Station> getAllStations() {
        List<Station> stations = stationDAO.getAll();
        stations.forEach(station -> this.updateStation(station));
        return stations;
    }
}