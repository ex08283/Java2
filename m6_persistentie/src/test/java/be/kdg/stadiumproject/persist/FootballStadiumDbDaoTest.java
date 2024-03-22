package be.kdg.stadiumproject.persist;

import be.kdg.stadiumproject.data.Data;
import be.kdg.stadiumproject.model.FootballStadium;
import be.kdg.stadiumproject.model.FootballStadiums;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class FootballStadiumDbDaoTest {
    static FootballStadiumDbDao footballStadiumDbDao;

    @BeforeAll
    public static void firstOfAll(){
        footballStadiumDbDao= new FootballStadiumDbDao("db/dbstadiums");
    }

    @BeforeEach
    public void init(){
        Data.getData().forEach(f -> footballStadiumDbDao.insert(f));
    }

    @AfterEach
    public void teardown(){
        footballStadiumDbDao.delete("*");
    }



    @Test
    public void testInsert(){
        assertEquals(Data.getData().size(), footballStadiumDbDao.sortedOn("name").size());
    }

    @Test
    public void testSort(){
        FootballStadiums footballStadiums = new FootballStadiums();
        Data.getData().forEach(footballStadiums::add);
        assertEquals(footballStadiumDbDao.sortedOn("name"), footballStadiums.sortedOnName(), "The sorted on method of the Dao based on name was not implemented correctly");
        assertEquals(footballStadiumDbDao.sortedOn("capacity"), footballStadiums.sortedOnCapacity(),"The sorted on method of the Dao based on capacity was not implemented correctly");
        assertEquals(footballStadiumDbDao.sortedOn("opened"), footballStadiums.sortedOnOpening(),"The sorted on method of the Dao based on opening date was not implemented correctly");
    }
    @Test
    public void testRetrieveUpdate(){
        FootballStadium retrieved = footballStadiumDbDao.retrieve("Camp Nou");
        retrieved.setName("Camp Nou modified");
        footballStadiumDbDao.update(retrieved);
        FootballStadium afterudpate = footballStadiumDbDao.retrieve("Camp Nou");
        assertNull(afterudpate, "The tow object are equal after an update, check the retrieve or update methods");
    }

    @Test
    void testDelete() {
        int countBeforeDelete = footballStadiumDbDao.sortedOn("name").size();
        assertTrue(footballStadiumDbDao.delete("Camp Nou"), "We did not delete the satdium succesfully");
        int countAfterDelete = footballStadiumDbDao.sortedOn("name").size();
        assertNotEquals(countBeforeDelete, countAfterDelete, "The number of elements in the DB should not be the same, delete was not successful");
        assertFalse(footballStadiumDbDao.delete("Camp Nou"), "We should not be able to delete stadium with the same name twice");

    }

    @AfterAll
    public static void afterAll(){
        footballStadiumDbDao.close();
    }

}