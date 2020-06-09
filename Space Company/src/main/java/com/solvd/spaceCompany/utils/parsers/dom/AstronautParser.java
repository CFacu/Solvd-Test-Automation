package com.solvd.spaceCompany.utils.parsers.dom;

import com.solvd.spaceCompany.models.Astronaut;
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

public class AstronautParser {
    private static final Logger LOGGER = LogManager.getLogger(AstronautParser.class);

    public static List<Astronaut> parseAstronaut(File file){
        List<Astronaut> astronauts = new ArrayList<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);
            document.getDocumentElement().normalize();

            NodeList nodeList = document.getElementsByTagName("astronaut");
            Stream.iterate(0, i -> i + 1).limit(nodeList.getLength()).forEach(x -> {
                Node astronautNode = nodeList.item(x);
                Astronaut astronaut = createAstronaut((Element) astronautNode);
                astronauts.add(astronaut);
            });
        } catch (ParserConfigurationException | IOException | SAXException e) {
            LOGGER.error(e);
        }
        return astronauts;
    }

    private static Astronaut createAstronaut(Element element) {
        Astronaut astronaut = new Astronaut();
        astronaut.setId(Long.parseLong(element.getAttribute("id")));
        astronaut.setFirstName(element.getElementsByTagName("firstName").item(0).getTextContent());
        astronaut.setLastName(element.getElementsByTagName("lastName").item(0).getTextContent());
        astronaut.setDuty(element.getElementsByTagName("duty").item(0).getTextContent());
        astronaut.setAge(Integer.parseInt(element.getElementsByTagName("age").item(0).getTextContent()));

        return astronaut;
    }
}
