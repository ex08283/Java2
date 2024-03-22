import be.kdg.stadiumproject.data.Data;
import be.kdg.stadiumproject.model.FootballStadium;
import be.kdg.stadiumproject.model.FootballStadiums;
import be.kdg.stadiumproject.model.RoofType;

import java.time.LocalDate;
import java.util.List;

public class Demo_1 {
    public static void main(String[] args) {

        FootballStadiums footballStadiums = new FootballStadiums();

        Data data = new Data();
        List<FootballStadium> footballStadiumData = Data.getData();

        for (FootballStadium footballStadium : footballStadiumData) {
            System.out.println(footballStadium.toString());
            footballStadiums.add(footballStadium);
        }

        //c. Voeg ook eens een dubbel object toe (dat zou niet mogen lukken; zie 2.3)
        System.out.println("Can we add a duplicate: " + footballStadiums.add(footballStadiumData.get(0)));
        //d. Test de methoden search, remove en getSize uit (zie 3.2)
        String stringToSearch = "Emirates Stadium";
        FootballStadium searchStadiumOnName = footballStadiums.search(stringToSearch);
        System.out.println("Did we find stadium with name " +  stringToSearch +  ": " + searchStadiumOnName.getName().equals(stringToSearch));

        //remove first stadium from list
        System.out.println("How many stadiums are there before removal: " +  footballStadiums.getSize());

        System.out.println("Did we remove stadium with  name " +  searchStadiumOnName.getName() +  ": " + footballStadiums.remove(stringToSearch));
        System.out.println("How many stadiums are there after removal: " +  footballStadiums.getSize());

        FootballStadium searchStadiumAfterRemoval = footballStadiums.search(stringToSearch);
        System.out.println("Did we find stadium with name " +  stringToSearch +  ": " + (searchStadiumAfterRemoval == null ? "no":"yes"));


        //e. Druk de 3 gesorteerde listen af (zie 3.2/d)
        System.out.println("\n");
        // Sorting of Stadiums
        System.out.println("FootballStadiums sorted on name");
        List<FootballStadium> sortedOnName =  footballStadiums.sortedOnName();
        for (FootballStadium footballStadium : sortedOnName) {
            System.out.println(footballStadium);
        }

        System.out.println("\n");
        System.out.println("FootballStadiums sorted on capacity");
        List<FootballStadium> sortedOnCapacity =  footballStadiums.sortedOnCapacity();
        for (FootballStadium footballStadium : sortedOnCapacity) {
            System.out.println(footballStadium);
        }

        System.out.println("\n");
        System.out.println("FootballStadiums sorted on opening date");
        List<FootballStadium> sortedOnOpening =  footballStadiums.sortedOnOpening();
        for (FootballStadium footballStadium : sortedOnOpening) {
            System.out.println(footballStadium);
        }

        //f. Test beide constructors uit en ook de IllegalArgumentException (zie 2.2)
        FootballStadium testNameandCity = new FootballStadium("", "", LocalDate.of(1957, 9, 24), RoofType.OPEN,1.73e9, 99354);
        FootballStadium testDate = new FootballStadium("TestStadium", "Barcelona", LocalDate.of(2024, 9, 24), RoofType.OPEN,5000, 99354);
        FootballStadium testCost = new FootballStadium("TestStadium", "Barcelona", LocalDate.of(2022, 9, 24), RoofType.OPEN,-1.0, 99354);
        FootballStadium testCapacity = new FootballStadium("TestStadium", "Barcelona", LocalDate.of(2022, 9, 24), RoofType.OPEN,1200, 10000000);
        FootballStadium defaultConstructor = new FootballStadium();
        System.out.println(defaultConstructor);




    }
}