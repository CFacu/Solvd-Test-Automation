package com.solvd.spaceCompany.parsers.dom;

import com.solvd.spaceCompany.models.SpaceCompany;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.List;
import java.util.stream.Stream;

public class DomParser {
    private static final Logger LOGGER = LogManager.getLogger(DomParser.class);

    public static void main(String[] args) {
        List<SpaceCompany> companies = SpaceCompanyParser.parseCompany(new File("src/main/resources/spaceCompany.xml"));
        Stream.iterate(0, i -> i + 1).limit(companies.size()).forEach(x -> {
            SpaceCompany company = companies.get(x);
            LOGGER.info("Company: " + company.getName());
            LOGGER.info("Stations: ");
            Stream.iterate(0, i -> i + 1).limit(company.getStations().size()).forEach(c -> {
                LOGGER.info(company.getStations().get(c).toString());
            });
            LOGGER.info("Engineers: " + company.getEngineers());
            LOGGER.info("Rockets: " + company.getRockets());
        });
    }
}