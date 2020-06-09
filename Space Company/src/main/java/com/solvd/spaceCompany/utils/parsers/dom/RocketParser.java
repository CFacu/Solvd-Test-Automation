package com.solvd.spaceCompany.utils.parsers.dom;

import com.solvd.spaceCompany.models.Rocket;
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

public class RocketParser {
    private static final Logger LOGGER = LogManager.getLogger(RocketParser.class);

    public static List<Rocket> parseRocket(File file){
        List<Rocket> rockets = new ArrayList<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);
            document.getDocumentElement().normalize();

            NodeList nodeList = document.getElementsByTagName("rocket");
            Stream.iterate(0, i -> i + 1).limit(nodeList.getLength()).forEach(x -> {
                Node rocketNode = nodeList.item(x);
                Rocket rocket = createRocket((Element) rocketNode);
                rockets.add(rocket);
            });
        } catch (ParserConfigurationException | IOException | SAXException e) {
            LOGGER.error(e);
        }
        return rockets;
    }

    private static Rocket createRocket(Element element) {
        Rocket rocket = new Rocket();
        rocket.setId(Long.parseLong(element.getAttribute("id")));
        rocket.setName(element.getElementsByTagName("name").item(0).getTextContent());
        rocket.setCargoCapacity(Float.parseFloat(element.getElementsByTagName("cargoCapacity").item(0).getTextContent()));
        rocket.setFuelCapacity(Float.parseFloat(element.getElementsByTagName("fuelCapacity").item(0).getTextContent()));
        rocket.setWeight(Float.parseFloat(element.getElementsByTagName("weight").item(0).getTextContent()));
        rocket.setPassengerCapacity(Integer.parseInt(element.getElementsByTagName("passengerCapacity").item(0).getTextContent()));

        return rocket;
    }
}
