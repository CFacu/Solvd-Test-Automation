package com.solvd.spaceCompany.utils.parsers.dom;

import com.solvd.spaceCompany.models.SpaceCompany;
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

public class SpaceCompanyParser {
    private static final Logger LOGGER = LogManager.getLogger(SpaceCompany.class);

    public static List<SpaceCompany> parseCompany (File file) {
        List<SpaceCompany> companies = new ArrayList<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);
            document.getDocumentElement().normalize();
            Element root = document.getDocumentElement();

            NodeList nodeList = document.getElementsByTagName("SpaceCompany");
            Stream.iterate(0, i -> i + 1).limit(nodeList.getLength()).forEach(x -> {
                Node companyNode = nodeList.item(x);
                SpaceCompany company = createCompany((Element) companyNode, file);
                companies.add(company);
            });
        } catch (ParserConfigurationException | IOException | SAXException e) {
            LOGGER.error(e);
        }
        return companies;
    }

    private static SpaceCompany createCompany(Element element, File file) {
        SpaceCompany company = new SpaceCompany();
        company.setId(Long.parseLong(element.getAttribute("id")));
        company.setName(element.getElementsByTagName("name").item(0).getTextContent());
        company.setStations(StationParser.parseStation(file));
        company.setRockets(RocketParser.parseRocket(file));
        company.setEngineers(EngineerParser.parseEngineer(file));

        return company;
    }
}
