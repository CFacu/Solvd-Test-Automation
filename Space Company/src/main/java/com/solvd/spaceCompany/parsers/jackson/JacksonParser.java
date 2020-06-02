package com.solvd.spaceCompany.parsers.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.spaceCompany.models.SpaceCompany;
import com.solvd.spaceCompany.services.SpaceCompanyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class JacksonParser {
    private final static Logger LOGGER = LogManager.getLogger(JacksonParser.class);
    public static void main(String[] args){
        SpaceCompanyService spService = new SpaceCompanyService();
        SpaceCompany spCompany = spService.getSpaceCompany(2L);
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("src/main/resources/jacksonParser.json");
        try{
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, spCompany);
            SpaceCompany spaceCompany = mapper.readValue(file, SpaceCompany.class);
            LOGGER.info(spaceCompany);
            LOGGER.info("Stations: " + spaceCompany.getStations());
            LOGGER.info("Rockets: " + spaceCompany.getRockets());
            LOGGER.info("Engineers: " + spaceCompany.getEngineers());
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }
}