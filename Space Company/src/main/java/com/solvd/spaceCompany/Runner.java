package com.solvd.spaceCompany;

import com.solvd.spaceCompany.daos.mysqlImpl.*;
import com.solvd.spaceCompany.models.*;
import com.solvd.spaceCompany.services.SpaceCompanyService;
import com.solvd.spaceCompany.services.StationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Runner {
    private static final Logger LOGGER = LogManager.getLogger(Runner.class);
    public static void main(String[] args) {

        SpaceCompanyDAO spaceCompanyDAO = new SpaceCompanyDAO();
        StationDAO stationDAO = new StationDAO();
        CEODAO ceoDAO = new CEODAO();
        EngineerDAO engineerDAO = new EngineerDAO();
        RocketDAO rocketDAO = new RocketDAO();
        SatelliteDAO satelliteDAO = new SatelliteDAO();
        AstronautDAO astronautDAO = new AstronautDAO();
        AddressDAO addressDAO = new AddressDAO();

        SpaceCompany spaceCompany = new SpaceCompany();
        spaceCompany.setName("SpaceX");

        CEO ceo = new CEO();
        ceo.setFirstName("Elon");
        ceo.setLastName("Musk");
        ceo.setEmail("robertjhonson@nasa.com");
        ceo.setAge(48);
        ceo.setSpaceCompany(spaceCompany);

        Engineer engineerOne = new Engineer();
        engineerOne.setFirstName("John");
        engineerOne.setLastName("Robertson");
        engineerOne.setAge(42);
        engineerOne.setSpeciality("Flight Engineer");
        engineerOne.setSpaceCompany(spaceCompany);

        Engineer engineerTwo = new Engineer();
        engineerTwo.setFirstName("Robert");
        engineerTwo.setLastName("Johnson");
        engineerTwo.setAge(38);
        engineerTwo.setSpeciality("Hardware Engineer");
        engineerTwo.setSpaceCompany(spaceCompany);

        Station station = new Station();
        station.setName("Kennedy Space Center");
        station.setSpaceCompany(spaceCompany);

        Astronaut astronautOne = new Astronaut();
        astronautOne.setFirstName("Mike");
        astronautOne.setLastName("Smith");
        astronautOne.setAge(35);
        astronautOne.setDuty("Captain");
        astronautOne.setStation(station);

        Astronaut astronautTwo = new Astronaut();
        astronautTwo.setFirstName("Joe");
        astronautTwo.setLastName("Doe");
        astronautTwo.setAge(38);
        astronautTwo.setDuty("Flight specialist");
        astronautTwo.setStation(station);

        Address address = new Address();
        address.setStreet("Kennedy Pkwy");
        address.setNumber(32899);
        address.setCity("Orlando");
        address.setStation(station);

        Rocket rocketOne = new Rocket();
        rocketOne.setName("Titan");
        rocketOne.setWeight(5600f);
        rocketOne.setPassengerCapacity(5);
        rocketOne.setCargoCapacity(3000f);
        rocketOne.setFuelCapacity(2500f);
        rocketOne.setSpaceCompany(spaceCompany);

        Rocket rocketTwo = new Rocket();
        rocketTwo.setName("Jupiter");
        rocketTwo.setWeight(7800f);
        rocketTwo.setPassengerCapacity(6);
        rocketTwo.setCargoCapacity(4250f);
        rocketTwo.setFuelCapacity(3000f);
        rocketTwo.setSpaceCompany(spaceCompany);

        Satellite satelliteOne = new Satellite();
        satelliteOne.setName("Skylab");
        satelliteOne.setWeight(8000f);
        satelliteOne.setFuelCapacity(3000f);
        satelliteOne.setCargoCapacity(1200f);
        satelliteOne.setStation(station);

        Satellite satelliteTwo = new Satellite();
        satelliteTwo.setName("International Space Station");
        satelliteTwo.setWeight(30000f);
        satelliteTwo.setFuelCapacity(9000f);
        satelliteTwo.setCargoCapacity(12000f);
        satelliteTwo.setStation(station);

//        spaceCompanyDAO.insert(spaceCompany);
//        stationDAO.insert(station);
//        ceoDAO.insert(ceo);
//        engineerDAO.insert(engineerOne);
//        engineerDAO.insert(engineerTwo);
//        rocketDAO.insert(rocketOne);
//        rocketDAO.insert(rocketTwo);
//        satelliteDAO.insert(satelliteOne);
//        satelliteDAO.insert(satelliteTwo);
//        astronautDAO.insert(astronautOne);
//        astronautDAO.insert(astronautTwo);
//        addressDAO.insert(address);

        SpaceCompanyService spService = new SpaceCompanyService();
        StationService satService = new StationService();

        SpaceCompany spCompany = spService.getSpaceCompany(2L);
        Station stationTwo = satService.getStation(2L);
        LOGGER.info(spCompany.getRockets());
        LOGGER.info(stationTwo.getAstronauts());
    }
}
