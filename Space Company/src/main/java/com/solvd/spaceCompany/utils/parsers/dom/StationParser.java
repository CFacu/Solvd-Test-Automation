package com.solvd.spaceCompany.utils.parsers.dom;

import com.solvd.spaceCompany.models.Station;
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

public class StationParser {
    private static final Logger LOGGER = LogManager.getLogger(StationParser.class);

    public static List<Station> parseStation(File file){
        List<Station> stations = new ArrayList<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);
            document.getDocumentElement().normalize();

            NodeList nodeList = document.getElementsByTagName("station");
            Stream.iterate(0, i -> i + 1).limit(nodeList.getLength()).forEach(x -> {
                Node stationsNode = nodeList.item(x);
                Station station = createStation((Element) stationsNode, file);
                stations.add(station);
            });
        } catch (ParserConfigurationException | IOException | SAXException e) {
            LOGGER.error(e);
        }
        return stations;
    }

    private static Station createStation(Element element, File file) {
        Station station = new Station();
        station.setId(Long.parseLong(element.getAttribute("id")));
        station.setName(element.getElementsByTagName("name").item(0).getTextContent());
        station.setSatellites(SatellitesParser.parseSatellite(file));
        station.setAstronauts(AstronautParser.parseAstronaut(file));
        return station;
    }
}
