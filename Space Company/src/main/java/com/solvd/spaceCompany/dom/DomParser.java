package com.solvd.spaceCompany.dom;

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
import java.io.IOException;
import java.util.stream.Stream;

public class DomParser {
    private static final Logger LOGGER = LogManager.getLogger(DomParser.class);

    public static void main(String[] args) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse("src/main/resources/spaceCompany.xml");
            document.getDocumentElement().normalize();
            Element root = document.getDocumentElement();

            NodeList nodeList = document.getElementsByTagName("spaceCompany");
            Stream.iterate(0, i -> i + 1).limit(nodeList.getLength()).forEach(x -> {
                Node companyNode = nodeList.item(x);

                if (companyNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element companyElement = (Element) companyNode;
                    LOGGER.info("Space Company id: " + companyElement.getAttribute("id"));
                    LOGGER.info("Name: " + companyElement.getElementsByTagName("name").item(0).getTextContent());
                    LOGGER.info("Stations: ");

                    NodeList stationsList = document.getElementsByTagName("station");
                    Stream.iterate(0, j -> j + 1).limit(stationsList.getLength()).forEach(z -> {
                        Node stationNode = stationsList.item(z);

                        if (stationNode.getNodeType() == Node.ELEMENT_NODE) {

                            Element stationElement = (Element) stationNode;
                            LOGGER.info("\tStation id: " + stationElement.getAttribute("stationId"));
                            LOGGER.info("\tName: " + stationElement.getElementsByTagName("name").item(0).getTextContent());
                            LOGGER.info("\tSatellites: ");

                            NodeList satelliteList = document.getElementsByTagName("satellite");
                            Stream.iterate(0, k -> k + 1).limit(satelliteList.getLength()).forEach(c -> {
                                Node satelliteNode = satelliteList.item(c);

                                if (satelliteNode.getNodeType() == Node.ELEMENT_NODE) {

                                    Element satelliteElement = (Element) satelliteNode;
                                    LOGGER.info("\t\tSatellite id: " + satelliteElement.getAttribute("satelliteId"));
                                    LOGGER.info("\t\tName: " + satelliteElement.getElementsByTagName("name").item(0).getTextContent());
                                    LOGGER.info("\t\tWeight: " + satelliteElement.getElementsByTagName("weight").item(0).getTextContent());
                                    LOGGER.info("\t\tCargo capacity: " + satelliteElement.getElementsByTagName("cargoCapacity").item(0).getTextContent());
                                    LOGGER.info("\t\tFuel capacity: " + satelliteElement.getElementsByTagName("fuelCapacity").item(0).getTextContent());
                                    LOGGER.info("\t\tLaunch date: " + satelliteElement.getElementsByTagName("launchDate").item(0).getTextContent());
                                    LOGGER.info("\n");
                                }
                            });

                            LOGGER.info("\tRockets: ");

                            NodeList rocketList = document.getElementsByTagName("rocket");
                            Stream.iterate(0, l -> l + 1).limit(rocketList.getLength()).forEach(v -> {
                               Node rocketNode = rocketList.item(v);

                               if (rocketNode.getNodeType() == Node.ELEMENT_NODE) {
                                   Element rocketElement = (Element) rocketNode;
                                   LOGGER.info("\t\tRocket id: " + rocketElement.getAttribute("rocketId"));
                                   LOGGER.info("\t\tName: " + rocketElement.getElementsByTagName("name").item(0).getTextContent());
                                   LOGGER.info("\t\tWeight: " + rocketElement.getElementsByTagName("weight").item(0).getTextContent());
                                   LOGGER.info("\t\tCargo capacity: " + rocketElement.getElementsByTagName("cargoCapacity").item(0).getTextContent());
                                   LOGGER.info("\t\tFuel capacity: " + rocketElement.getElementsByTagName("fuelCapacity").item(0).getTextContent());
                                   LOGGER.info("\t\tPassenger capacity: " + rocketElement.getElementsByTagName("passengerCapacity").item(0).getTextContent());
                                   LOGGER.info("\t\tLaunch date: " + rocketElement.getElementsByTagName("launchDate").item(0).getTextContent());
                                   LOGGER.info("\n");
                               }
                            });
                        }

                    });
                    LOGGER.info("Engineers: ");
                    NodeList engineerList = document.getElementsByTagName("engineer");
                    Stream.iterate(0, h -> h + 1).limit(engineerList.getLength()).forEach(b -> {
                       Node engineerNode = engineerList.item(b);
                       if (engineerNode.getNodeType() == Node.ELEMENT_NODE) {
                           Element engineerElement = (Element) engineerNode;
                           LOGGER.info("\tEngineer id: " + engineerElement.getAttribute("engineerId"));
                           LOGGER.info("\tFirst name: " + engineerElement.getElementsByTagName("firstName").item(0).getTextContent());
                           LOGGER.info("\tLast name: " + engineerElement.getElementsByTagName("lastName").item(0).getTextContent());
                           LOGGER.info("\tAge: " + engineerElement.getElementsByTagName("age").item(0).getTextContent());
                           LOGGER.info("\tSpeciality: " + engineerElement.getElementsByTagName("speciality").item(0).getTextContent());
                           LOGGER.info("\n");
                       }
                    });
                }
            });

        } catch (ParserConfigurationException | SAXException | IOException e) {
            LOGGER.error(e);
        }



    }
}