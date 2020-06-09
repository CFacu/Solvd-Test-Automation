package com.solvd.spaceCompany.services;

import com.solvd.spaceCompany.daos.*;
import com.solvd.spaceCompany.daos.mysqlImpl.EngineerDAO;
import com.solvd.spaceCompany.daos.mysqlImpl.RocketDAO;
import com.solvd.spaceCompany.daos.mysqlImpl.StationDAO;
import com.solvd.spaceCompany.models.SpaceCompany;
import com.solvd.spaceCompany.utils.ConnectionFactory;

import java.util.List;

public class SpaceCompanyService {
    private ISpaceCompanyDAO spaceCompanyDAO;
    private IEngineerDAO engineerDAO;
    private IRocketDAO rocketDAO;
    private IStationDAO stationDAO;
    private StationService statService;

    public SpaceCompanyService() {
        this.spaceCompanyDAO = ConnectionFactory.getSpaceCompanyMapper();
        this.engineerDAO = ConnectionFactory.getEngineerMapper();
        this.rocketDAO = ConnectionFactory.getRocketMapper();
        this.stationDAO = ConnectionFactory.getStationMapper();
        this.statService = new StationService();
    }

    public SpaceCompany getSpaceCompany(Long id) {
        SpaceCompany spaceCompany = spaceCompanyDAO.get(id);
        this.updateSpaceCompany(spaceCompany);
        return spaceCompany;
    }

    public void updateSpaceCompany(SpaceCompany spaceCompany) {
        spaceCompany.setEngineers(engineerDAO.getAllBySpaceCompanyId(spaceCompany.getId()));
        spaceCompany.setRockets(rocketDAO.getAllBySpaceCompanyId(spaceCompany.getId()));
        spaceCompany.setStations(statService.getStationsByCompanyId(spaceCompany.getId()));
    }

    public List<SpaceCompany> getAllSpaceCompany() {
        List<SpaceCompany> spaceCompanies = spaceCompanyDAO.getAll();
        spaceCompanies.forEach(comp -> this.updateSpaceCompany(comp));
        return spaceCompanies;
    }
}