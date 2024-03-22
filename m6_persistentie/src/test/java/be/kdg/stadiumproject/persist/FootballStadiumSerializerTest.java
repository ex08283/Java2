package be.kdg.stadiumproject.persist;

import be.kdg.stadiumproject.data.Data;
import be.kdg.stadiumproject.model.FootballStadium;
import be.kdg.stadiumproject.model.FootballStadiums;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FootballStadiumSerializerTest {
    private static FootballStadiums footballStadiums;
    FootballStadiumSerializer footballStadiumSerializer;


    @BeforeEach
    public void init(){
        footballStadiumSerializer = new FootballStadiumSerializer("db/footballstadiums.ser");
        footballStadiums = new FootballStadiums();
        Data.getData().forEach(f -> footballStadiums.add(f));

    }

    @Test
    void testSerialize() {
        assertDoesNotThrow(() -> footballStadiumSerializer.serialize(footballStadiums), "The serialization did not work and threw an exception");
    }

    @Test
    void testDeserialize() {
        assertDoesNotThrow(() ->assertEquals(footballStadiums, footballStadiumSerializer.deserialize(), "Deserialization did not work"), "Deserialization threw an exception");
    }
}



