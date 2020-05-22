package com.solvd.spaceCompany;

import com.solvd.spaceCompany.daos.EngineerDAO;
import com.solvd.spaceCompany.daos.SpaceCompanyDAO;
import com.solvd.spaceCompany.daos.StationDAO;
import com.solvd.spaceCompany.models.Engineer;
import com.solvd.spaceCompany.models.SpaceCompany;
import com.solvd.spaceCompany.models.Station;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Runner {
    private static final Logger LOGGER = LogManager.getLogger(Runner.class);
    public static void main(String[] args) {
        SpaceCompany spaceCompany = new SpaceCompany();
        spaceCompany.setName("NASA");
        SpaceCompanyDAO scDao = new SpaceCompanyDAO();
        scDao.insert(spaceCompany);
        LOGGER.info("Space company id:" + spaceCompany.getId());

        Station station = new Station();
        station.setName("Kennedy Space Center");
        station.setSpaceCompany(spaceCompany);

        StationDAO staDao = new StationDAO();
        staDao.insert(station);
        LOGGER.info("Station id: " + station.getId() + " Name: " + station.getName());
    }
}
