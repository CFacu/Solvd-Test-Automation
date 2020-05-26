package com.solvd.spaceCompany.services;

import com.solvd.spaceCompany.daos.mysqlImpl.EngineerDAO;
import com.solvd.spaceCompany.daos.mysqlImpl.RocketDAO;
import com.solvd.spaceCompany.daos.mysqlImpl.SpaceCompanyDAO;
import com.solvd.spaceCompany.daos.mysqlImpl.StationDAO;
import com.solvd.spaceCompany.models.SpaceCompany;

import java.util.ArrayList;
import java.util.List;

public class SpaceCompanyService {
    private SpaceCompanyDAO spaceCompanyDAO;
    private EngineerDAO engineerDAO;
    private RocketDAO rocketDAO;
    private StationDAO stationDAO;

    public SpaceCompanyService() {
        this.spaceCompanyDAO = new SpaceCompanyDAO();
        this.engineerDAO = new EngineerDAO();
        this.rocketDAO = new RocketDAO();
        this.stationDAO = new StationDAO();
    }

    public SpaceCompany getSpaceCompany(Long id) {
        SpaceCompany spaceCompany = spaceCompanyDAO.get(id);
        this.updateSpaceCompany(spaceCompany);
        return spaceCompany;
    }

    public void updateSpaceCompany(SpaceCompany spaceCompany) {
        spaceCompany.setEngineers(engineerDAO.getAllBySpaceCompanyId(spaceCompany.getId()));
        spaceCompany.setRockets(rocketDAO.getAllBySpaceCompanyId(spaceCompany.getId()));
        spaceCompany.setStations(stationDAO.getAllBySpaceCompanyId(spaceCompany.getId()));
    }

    public List<SpaceCompany> getAllSpaceCompany() {
        List<SpaceCompany> spaceCompanies = new ArrayList<>();
        spaceCompanies = spaceCompanyDAO.getAll();
        spaceCompanies.forEach(comp -> this.updateSpaceCompany(comp));
        return spaceCompanies;
    }
}