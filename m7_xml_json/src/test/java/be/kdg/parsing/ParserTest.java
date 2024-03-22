package be.kdg.parsing;

import be.kdg.data.Data;
import be.kdg.model.FootballStadium;
import be.kdg.model.FootballStadiums;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
    String pathStax;
    String pathGson;
    String pathJaxb;
    private static FootballStadiums footballStadiums;


    @BeforeEach
    public void init(){
        footballStadiums = new FootballStadiums();
        pathStax = "datafiles/stadiumStax.xml";
        pathJaxb = "datafiles/stadiumJaxb.xml";
        pathGson = "datafiles/stadiumGson.xml";
        Data.getData().forEach(f ->footballStadiums.add(f));
    }

    @Test
    void testStaxDom() {
        StadiumsStaxParser parser  = assertDoesNotThrow(() -> new StadiumsStaxParser(footballStadiums, pathStax), "issue with parser");
        assertDoesNotThrow(parser::staxWriteXML, "issue with writing to xml");

        FootballStadiums footballStadiumsAfterParsing = assertDoesNotThrow(() -> StadiumsDomParser.domReadXML(pathStax), "issue with domreadXml");
        assertEquals(footballStadiums.sortedOnName(), footballStadiumsAfterParsing.sortedOnName(), "object are not equal");
    }

    @Test
    void testJaxb(){
        assertDoesNotThrow(() -> FootballStadiumsJaxbParser.JaxbWriteXml(pathJaxb, footballStadiums), "issue with Jaxb parser");
        FootballStadiums footballStadiums1 = assertDoesNotThrow(() -> FootballStadiumsJaxbParser.JaxbReadXml(pathJaxb, FootballStadiums.class), "issue with writing to xml");

        assertEquals(footballStadiums.sortedOnName(), footballStadiums1.sortedOnName(), "object are not equal");

    }

    @Test
    void testGson(){
        assertDoesNotThrow(() -> FootballStadiumGsonParser.writeJson(footballStadiums, pathGson), "Issue writing XML using json");
        FootballStadiums footballStadiumsFromGson =  assertDoesNotThrow(() ->FootballStadiumGsonParser.readJson(pathGson), "Issue");

        assertEquals( footballStadiums.getFootballStadiumsList(), footballStadiumsFromGson.getFootballStadiumsList(), "Not the same");
    }
}