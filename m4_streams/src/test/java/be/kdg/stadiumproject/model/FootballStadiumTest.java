package be.kdg.stadiumproject.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class FootballStadiumTest {
    private FootballStadium footballStadium1;
    private FootballStadium footballStadium2;

    @BeforeEach
    void setUp() {
        footballStadium1 = new FootballStadium("Camp Nou", "Barcelona", LocalDate.of(1957, 9, 24), RoofType.OPEN,1.73e9, 99354);
        footballStadium2 = new FootballStadium("Wembley Stadium", "London", LocalDate.of(2007, 3, 9), RoofType.PARTIALLYRETRACTABLE,.92213e9, 90000);
    }

    @Test
    void testEquals() {
        FootballStadium sameNameDiff = new FootballStadium("Camp Nou", "Barcelona",LocalDate.of(1957, 9, 29),RoofType.RETRACTABLE,100000,50000);
        assertFalse(footballStadium1.equals(footballStadium2),
                "The stadiums should be different");
        assertEquals(footballStadium1, sameNameDiff,
                String.format("The name of the objects is not the same: name of object 1: %s, name of object 2: %s",footballStadium1.getName(), sameNameDiff.getName()));
        assertFalse(footballStadium1.equals(null),
                "Comparing with null should give false");
        assertFalse(footballStadium1.equals(Integer.valueOf(1)),
                "Comparing a stadium object with a different class type should give false");
    }

    @Test
    void testIllegalCost() {
        assertThrows(IllegalArgumentException.class, () -> footballStadium1.setCost(-1),"Stadium cost cannot be negative or larger than 1 trillion");
    }

    @Test
    void testLegalCost() {
        assertDoesNotThrow(() -> footballStadium1.setCost(200), "The stadium threw an exception although the cost is cost < 0 || cost > 1e12");
    }

    @Test
    void testCompareTo() {
        FootballStadium footballStadiumComparable = new FootballStadium("Camp Nou","Barcelona", LocalDate.of(1970, 9, 24), RoofType.RETRACTABLE,1.73e8, 99300);
        assertNotEquals(0,footballStadium1.compareTo(footballStadium2),"Stadium with different name cannot be comparable");
        assertEquals(0,footballStadium1.compareTo(footballStadiumComparable),"Stadium with different name cannot be comparable");
    }

    @Test
    void testCost() {
        assertEquals(1730000000.01, footballStadium1.getCost(), 0.02, "The cost should be a double and as configured");
    }
}