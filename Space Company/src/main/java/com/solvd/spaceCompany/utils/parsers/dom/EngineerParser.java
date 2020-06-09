package com.solvd.spaceCompany.utils.parsers.dom;

import com.solvd.spaceCompany.models.Engineer;
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

public class EngineerParser {
    private static final Logger LOGGER = LogManager.getLogger(EngineerParser.class);

    public static List<Engineer> parseEngineer(File file){
        List<Engineer> engineers = new ArrayList<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);
            document.getDocumentElement().normalize();

            NodeList nodeList = document.getElementsByTagName("engineer");
            Stream.iterate(0, i -> i + 1).limit(nodeList.getLength()).forEach(x -> {
                Node engineerNode = nodeList.item(x);
                Engineer engineer = createEngineer((Element) engineerNode, file);
                engineers.add(engineer);
            });
        } catch (ParserConfigurationException | IOException | SAXException e) {
            LOGGER.error(e);
        }
        return engineers;
    }

    private static Engineer createEngineer(Element element, File file) {
        Engineer engineer = new Engineer();
        engineer.setId(Long.parseLong(element.getAttribute("id")));
        engineer.setFirstName(element.getElementsByTagName("firstName").item(0).getTextContent());
        engineer.setLastName(element.getElementsByTagName("lastName").item(0).getTextContent());
        engineer.setSpeciality(element.getElementsByTagName("speciality").item(0).getTextContent());
        engineer.setAge(Integer.parseInt(element.getElementsByTagName("age").item(0).getTextContent()));

        return engineer;
    }
}
