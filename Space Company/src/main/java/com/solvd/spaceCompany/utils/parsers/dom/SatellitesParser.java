package com.solvd.spaceCompany.utils.parsers.dom;

import com.solvd.spaceCompany.models.Satellite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class SatellitesParser {
    private static final Logger LOGGER = LogManager.getLogger(SatellitesParser.class);

    public static List<Satellite> parseSatellite(File file){
        List<Satellite> satellites = new ArrayList<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);
            document.getDocumentElement().normalize();

            NodeList nodeList = document.getElementsByTagName("satellite");
            Stream.iterate(0, i -> i + 1).limit(nodeList.getLength()).forEach(x -> {
                Node satelliteNode = nodeList.item(x);
                Satellite satellite = createSatellite((Element) satelliteNode);
                satellites.add(satellite);
            });
        } catch (ParserConfigurationException | IOException | SAXException e) {
            LOGGER.error(e);
        }
        return satellites;
    }

    private static Satellite createSatellite(Element element) {
        Satellite satellite = new Satellite();
        satellite.setId(Long.parseLong(element.getAttribute("id")));
        satellite.setName(element.getElementsByTagName("name").item(0).getTextContent());
        satellite.setCargoCapacity(Float.parseFloat(element.getElementsByTagName("cargoCapacity").item(0).getTextContent()));
        satellite.setFuelCapacity(Float.parseFloat(element.getElementsByTagName("fuelCapacity").item(0).getTextContent()));
        satellite.setWeight(Float.parseFloat(element.getElementsByTagName("weight").item(0).getTextContent()));

        return satellite;
    }
}
