

import be.kdg.model.FootballStadium;
import be.kdg.model.FootballStadiums;
import be.kdg.model.RoofType;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.LogManager;

public class Demo_5 {
    public static void main(String[] args) {

//        FootballStadiums footballStadiums = new FootballStadiums();
//
//        Data data = new Data();
//        List<FootballStadium> footballStadiumData = Data.getData();
//
//        for (FootballStadium footballStadium : footballStadiumData) {
//            System.out.println(footballStadium.toString());
//            footballStadiums.add(footballStadium);
//        }
//
//        //c. Voeg ook eens een dubbel object toe (dat zou niet mogen lukken; zie 2.3)
//        System.out.println("Can we add a duplicate: " + footballStadiums.add(footballStadiumData.get(0)));
//        //d. Test de methoden search, remove en getSize uit (zie 3.2)
//        String stringToSearch = "Emirates Stadium";
//        FootballStadium searchStadiumOnName = footballStadiums.search(stringToSearch);
//        System.out.println("Did we find stadium with name " +  stringToSearch +  ": " + searchStadiumOnName.getName().equals(stringToSearch));
//
//        //remove first stadium from list
//        System.out.println("How many stadiums are there before removal: " +  footballStadiums.getSize());
//
//        System.out.println("Did we remove stadium with  name " +  searchStadiumOnName.getName() +  ": " + footballStadiums.remove(stringToSearch));
//        System.out.println("How many stadiums are there after removal: " +  footballStadiums.getSize());
//
//        FootballStadium searchStadiumAfterRemoval = footballStadiums.search(stringToSearch);
//        System.out.println("Did we find stadium with name " +  stringToSearch +  ": " + (searchStadiumAfterRemoval == null ? "no":"yes"));
//
//
//        //e. Druk de 3 gesorteerde listen af (zie 3.2/d)
//        System.out.println("\n");
//        // Sorting of Stadiums
//        System.out.println("FootballStadiums sorted on name");
//        List<FootballStadium> sortedOnName =  footballStadiums.sortedOnName();
//        for (FootballStadium footballStadium : sortedOnName) {
//            System.out.println(footballStadium);
//        }
//
//        System.out.println("\n");
//        System.out.println("FootballStadiums sorted on capacity");
//        List<FootballStadium> sortedOnCapacity =  footballStadiums.sortedOnCapacity();
//        for (FootballStadium footballStadium : sortedOnCapacity) {
//            System.out.println(footballStadium);
//        }
//
//        System.out.println("\n");
//        System.out.println("FootballStadiums sorted on opening date");
//        List<FootballStadium> sortedOnOpening =  footballStadiums.sortedOnOpening();
//        for (FootballStadium footballStadium : sortedOnOpening) {
//            System.out.println(footballStadium);
//        }

        Loadlogging();

        System.out.println();
        System.out.println("//Logging 3.2");
        FootballStadium testNameandCity = new FootballStadium("", "", LocalDate.of(1957, 9, 24), RoofType.OPEN,1.73e9, 99354);
        FootballStadium testDate = new FootballStadium("TestStadium", "Barcelona", LocalDate.of(2024, 9, 24), RoofType.OPEN,5000, 99354);
        FootballStadium testCost = new FootballStadium("TestStadium", "Barcelona", LocalDate.of(2022, 9, 24), RoofType.OPEN,-1.0, 99354);
        FootballStadium testCapacity = new FootballStadium("TestStadium", "Barcelona", LocalDate.of(2022, 9, 24), RoofType.OPEN,1200, 10000000);
        FootballStadium defaultConstructor = new FootballStadium();
        System.out.println(defaultConstructor);


        System.out.println();
        System.out.println("//Logging 3.6");
        FootballStadiums footballStadiums = new FootballStadiums();

        FootballStadium CampNou = new FootballStadium("Camp Nou", "Barcelona", LocalDate.of(1957, 9, 24), RoofType.OPEN,1.73e9, 99354);
        FootballStadium WembleyStadium = new FootballStadium("Wembley Stadium", "London", LocalDate.of(2007, 3, 9), RoofType.PARTIALLYRETRACTABLE,.92213e9, 90000);
        FootballStadium SantiagoBernabeu = new FootballStadium("Santiago Bernabéu", "Madrid", LocalDate.of(1957, 9, 9), RoofType.RETRACTABLE,0.893e9, 81044);
        FootballStadium CrokePark = new FootballStadium("Croke Park", "Dublin", LocalDate.of(1884, 1, 1), RoofType.OPEN,0.26e9, 82300);


        footballStadiums.add(CampNou);
        footballStadiums.add(WembleyStadium);
        footballStadiums.add(SantiagoBernabeu);
        footballStadiums.add(CrokePark);
        footballStadiums.remove(CampNou.getName());
        footballStadiums.remove(SantiagoBernabeu.getName());





    }

    private static void Loadlogging(){
        InputStream inputStream = Demo_5.class.getResourceAsStream("/logging.properties");

        try {
            LogManager.getLogManager().readConfiguration(inputStream);
        } catch (IOException e){
            System.err.println("Config bestand is corrupt");
        }
    }


}