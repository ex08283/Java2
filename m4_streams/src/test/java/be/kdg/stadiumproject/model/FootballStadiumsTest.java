package be.kdg.stadiumproject.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FootballStadiumsTest {

    private FootballStadium CampNou;
    private FootballStadium WembleyStadium;
    private FootballStadium SantiagoBernabeu;
    private FootballStadium CrokePark;
    private FootballStadium StadedeFrance;
    FootballStadiums footballStadiums;


    @BeforeEach
    void setUp() {

        CampNou = new FootballStadium("Camp Nou", "Barcelona", LocalDate.of(1957, 9, 24), RoofType.OPEN,1.73e9, 99354);
        WembleyStadium = new FootballStadium("Wembley Stadium", "London", LocalDate.of(2007, 3, 9), RoofType.PARTIALLYRETRACTABLE,.92213e9, 90000);
        SantiagoBernabeu = new FootballStadium("Santiago Bernabéu", "Madrid", LocalDate.of(1957, 9, 9), RoofType.RETRACTABLE,0.893e9, 81044);
        CrokePark=  new FootballStadium("Croke Park", "Dublin", LocalDate.of(1884, 1, 1), RoofType.OPEN,0.26e9, 82300);
        StadedeFrance =new FootballStadium("Stade de France", "Saint-Denis", LocalDate.of(1995, 5, 2), RoofType.OPEN,0.364e9, 80000);

        footballStadiums = new FootballStadiums();
        footballStadiums.add(CampNou);
        footballStadiums.add(WembleyStadium);
        footballStadiums.add(SantiagoBernabeu);
        footballStadiums.add(CrokePark);
        footballStadiums.add(StadedeFrance);
    }

    @Test
    void addStadiumTest() {
        FootballStadium CelticPark = new FootballStadium("Celtic Park", "Glasgow", LocalDate.of(1892, 8, 20), RoofType.OPEN,0.04e9, 83500);

        assertTrue(footballStadiums.add(CelticPark),"Adding a stadium that's not a duplicate should not give an error");
        assertFalse(footballStadiums.add(CelticPark),"Adding a duplicate should give false");
    }

    @Test
    void removeStadiumTest() {
        assertEquals(5, footballStadiums.getSize(), "The size before removal should be 5");
        assertTrue(footballStadiums.remove("Camp Nou"), "A stadium removed succesfully should give tru");
        assertFalse(footballStadiums.remove("Does not exist"), "A stadium which could not be removed should return False");
        assertEquals(4, footballStadiums.getSize(), "The size after removal should be 4");
    }

    @Test
    void sortByCapacityTest() {
        List<Integer> intsCapacity = footballStadiums.sortedBy(FootballStadium::getCapacity).stream()
                        .map(FootballStadium::getCapacity)
                .toList();

        assertAll
                (
                        ()-> assertEquals(80000, intsCapacity.get(0)),
                        ()-> assertEquals(81044, intsCapacity.get(1)),
                        ()-> assertEquals(82300, intsCapacity.get(2)),
                        ()-> assertEquals(90000, intsCapacity.get(3)),
                        ()-> assertEquals(99354, intsCapacity.get(4))
                );
    }

    @Test
    void sortByCostTest() {
        CampNou = new FootballStadium("Camp Nou", "Barcelona", LocalDate.of(1957, 9, 24), RoofType.OPEN,1.73e9, 99354);
        WembleyStadium = new FootballStadium("Wembley Stadium", "London", LocalDate.of(2007, 3, 9), RoofType.PARTIALLYRETRACTABLE,.92213e9, 90000);
        SantiagoBernabeu = new FootballStadium("Santiago Bernabéu", "Madrid", LocalDate.of(1957, 9, 9), RoofType.RETRACTABLE,0.893e9, 81044);
        CrokePark=  new FootballStadium("Croke Park", "Dublin", LocalDate.of(1884, 1, 1), RoofType.OPEN,0.26e9, 82300);
        StadedeFrance =new FootballStadium("Stade de France", "Saint-Denis", LocalDate.of(1995, 5, 2), RoofType.OPEN,0.364e9, 80000);

        FootballStadium[] expectedStadiumList = new FootballStadium[]{CrokePark, StadedeFrance, SantiagoBernabeu, WembleyStadium, CampNou};
        assertArrayEquals(expectedStadiumList, footballStadiums.sortedBy(FootballStadium::getCost).toArray(), "The stadium array is not sorted properly by Cost");
    }
}