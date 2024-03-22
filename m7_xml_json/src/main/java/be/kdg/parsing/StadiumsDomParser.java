package be.kdg.parsing;

import be.kdg.model.FootballStadium;
import be.kdg.model.FootballStadiums;
import be.kdg.model.RoofType;
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
import java.time.LocalDate;

public class StadiumsDomParser {

    public static FootballStadiums domReadXML (String fileName) throws RuntimeException{
        FootballStadiums footballStadiums = new FootballStadiums();
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(new File(fileName));

            Element rootElement = doc.getDocumentElement();

            NodeList stadiumsList = rootElement.getChildNodes();
            //<test> blablabla </test>
            for (int i = 0; i < stadiumsList.getLength(); i++) {
                FootballStadium footballStadium = new FootballStadium();
                if (stadiumsList.item(i).getNodeType() != Node.ELEMENT_NODE) {
                    continue;
                }

                Element e = (Element) stadiumsList.item(i);
                footballStadium.setRoofType(Enum.valueOf(RoofType.class, e.getAttribute("rooftype")));
                footballStadium.setName(e.getElementsByTagName("name").item(0).getTextContent());
                footballStadium.setCity(e.getElementsByTagName("city").item(0).getTextContent());
                footballStadium.setOpened(LocalDate.parse(e.getElementsByTagName("opened").item(0).getTextContent()));
                footballStadium.setCost(Double.parseDouble(e.getElementsByTagName("cost").item(0).getTextContent()));
                footballStadium.setCapacity(Integer.parseInt(e.getElementsByTagName("capacity").item(0).getTextContent()));
                footballStadiums.add(footballStadium);
            }
            return footballStadiums;

        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }
    }


}
