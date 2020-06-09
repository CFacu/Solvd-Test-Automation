package com.solvd.spaceCompany.utils.parsers.jaxb;

import com.solvd.spaceCompany.models.SpaceCompany;
import com.solvd.spaceCompany.services.SpaceCompanyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class JaxbParser {
    private static final Logger LOGGER = LogManager.getLogger(JaxbParser.class);
    public static void main(String[] args) {
        SpaceCompanyService service = new SpaceCompanyService();
        SpaceCompany spaceCompany = service.getSpaceCompany(2L);
        try {
            JAXBContext context = JAXBContext.newInstance(SpaceCompany.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            File file = new File("src/main/resources/jaxbOut.xml");
            marshaller.marshal(spaceCompany, file);
        } catch (JAXBException e) {
            LOGGER.error(e);
        }

        File fileIn = new File("src/main/resources/spaceCompany.xml");

        try {
            JAXBContext context = JAXBContext.newInstance(SpaceCompany.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            SpaceCompany spaceCompanyIn = (SpaceCompany) unmarshaller.unmarshal(fileIn);
            LOGGER.info("Space Company: " + spaceCompanyIn.getName());
            LOGGER.info("Stations: " + spaceCompanyIn.getStations());
            LOGGER.info("Rockets: " + spaceCompanyIn.getRockets());
            LOGGER.info("Engineers: " + spaceCompanyIn.getEngineers());
        } catch (JAXBException e) {
            LOGGER.error(e);
        }
    }
}