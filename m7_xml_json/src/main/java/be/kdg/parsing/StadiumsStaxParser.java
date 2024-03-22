package be.kdg.parsing;

import be.kdg.model.FootballStadium;
import be.kdg.model.FootballStadiums;
import com.sun.xml.txw2.output.IndentingXMLStreamWriter;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


//Writes data to XML file using a xmlstream writes
public class StadiumsStaxParser {
    private FootballStadiums footballStadiums;

    private XMLStreamWriter xmlStreamWriter;

    public StadiumsStaxParser(FootballStadiums footballStadiums, String path) throws IOException, XMLStreamException {
        FileWriter fileWriter =new FileWriter(path, StandardCharsets.UTF_8);
        this.xmlStreamWriter = XMLOutputFactory.newInstance().createXMLStreamWriter(fileWriter);
        this.xmlStreamWriter = new IndentingXMLStreamWriter(this.xmlStreamWriter);

        this.footballStadiums = footballStadiums;
    }

    public void staxWriteXML() throws XMLStreamException {
        xmlStreamWriter.writeStartDocument();
        xmlStreamWriter.writeStartElement("footballstadiums");
        for (FootballStadium footballStadium : footballStadiums.getFootballStadiumsList()) {
            writeElement(footballStadium);
        }
        xmlStreamWriter.writeEndElement();
        xmlStreamWriter.writeEndDocument();
        xmlStreamWriter.flush();
        xmlStreamWriter.close();
        System.out.println("XML bestand: \"" + "\" opgeslagen!");

    }

    private void writeElement(FootballStadium f) throws XMLStreamException {
        xmlStreamWriter.writeStartElement("footballstadium");
        xmlStreamWriter.writeAttribute("rooftype", f.getRoofType().name());

        xmlStreamWriter.writeStartElement("name");
        xmlStreamWriter.writeCharacters(f.getName());
        xmlStreamWriter.writeEndElement();

        xmlStreamWriter.writeStartElement("city");
        xmlStreamWriter.writeCharacters(f.getCity());
        xmlStreamWriter.writeEndElement();

        xmlStreamWriter.writeStartElement("opened");
        xmlStreamWriter.writeCharacters(f.getOpened().toString());
        xmlStreamWriter.writeEndElement();

        xmlStreamWriter.writeStartElement("cost");
        xmlStreamWriter.writeCharacters(String.valueOf(f.getCost()));
        xmlStreamWriter.writeEndElement();

        xmlStreamWriter.writeStartElement("capacity");
        xmlStreamWriter.writeCharacters(Integer.toString(f.getCapacity()));
        xmlStreamWriter.writeEndElement();

        xmlStreamWriter.writeEndElement();
    }


}
