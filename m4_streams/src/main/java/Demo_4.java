import be.kdg.stadiumproject.data.Data;
import be.kdg.stadiumproject.model.FootballStadium;
import be.kdg.stadiumproject.model.FootballStadiums;
import be.kdg.stadiumproject.model.RoofType;
import be.kdg.stadiumproject.util.FootballStadiumFunctions;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Demo_4 {
    public static void main(String[] args) {

        FootballStadiums footballStadiums = new FootballStadiums();

        Data data = new Data();
        List<FootballStadium> footballStadiumData = Data.getData();

//        for (FootballStadium footballStadium : footballStadiumData) {
//            System.out.println(footballStadium.toString());
//            footballStadiums.add(footballStadium);
//        }

        footballStadiumData.forEach(footballStadiums::add);


        //2. LAMBDA EXPRESSIONS EN METHOD REFERENCES
        System.out.println("\n");
        System.out.println("*******************2.1 \n");
        // Sorting of Stadiums
        System.out.println("FootballStadiums sorted on name:");
//        for (FootballStadium footballStadium : footballStadiums.sortedBy(FootballStadium::getName)) {
//            System.out.println(footballStadium);
//        }
        footballStadiums.sortedBy(FootballStadium::getName).forEach(System.out::println);

        System.out.println("\n");
        System.out.println("FootballStadiums sorted on capacity:");
//        for (FootballStadium footballStadium : footballStadiums.sortedBy(FootballStadium::getCapacity)) {
//            System.out.println(footballStadium);
//        }
        footballStadiums.sortedBy(FootballStadium::getCapacity).forEach(System.out::println);


        System.out.println("\n");
//        System.out.println("FootballStadiums sorted on opening date");
//        for (FootballStadium footballStadium : footballStadiums.sortedBy(FootballStadium::getOpened)) {
//            System.out.println(footballStadium);
//        }
        footballStadiums.sortedBy(FootballStadium::getOpened).forEach(System.out::println);



        //2.4
        System.out.println("\n");
        System.out.println("*******************2.4 \n");
        System.out.println("print predicate using price as functions");
        List<FootballStadium> footballStadiumsFilteredOnPrice = FootballStadiumFunctions.filteredList(footballStadiumData,
                f -> f.getCost() < 100000.0);

        footballStadiumsFilteredOnPrice.forEach(System.out::println);

        System.out.println("\n");
        System.out.println("Football stadiums in the city of London");
        List<FootballStadium> footballStadiumsFileredOnCity = FootballStadiumFunctions.filteredList(footballStadiumData,
                f -> f.getCity().equals("London"));

        footballStadiumsFileredOnCity.forEach(System.out::println);

        System.out.println("\n");
        System.out.println("Print predicate using Capacity as parameter, stadiums with capacity greater then 90.000");
        List<FootballStadium> footballStadiumsFileredOnCapacity = FootballStadiumFunctions.filteredList(footballStadiumData,
                f -> f.getCapacity() > 90000);

        footballStadiumsFileredOnCapacity.forEach(System.out::println);

        //2.6
        System.out.println();
        System.out.printf("Average stadium capacity %.1f \n", FootballStadiumFunctions.average(footballStadiumData,FootballStadium::getCapacity));
        System.out.printf("Average stadium cost %.1f \n", FootballStadiumFunctions.average(footballStadiumData,FootballStadium::getCost));

        //2.8
        System.out.println();
        System.out.printf("Number of stadiums with capacity > 90000:  %d\n", FootballStadiumFunctions.countIf(footballStadiumData,f -> f.getCapacity() > 90000));
        System.out.printf("Number of stadiums with cost < 1000.000,00:  %d\n", FootballStadiumFunctions.countIf(footballStadiumData,f -> f.getCost() < 1e6));


        //3. streams

        //3.1
        long countBeforeDate = footballStadiumData.stream()
                .filter(f -> f.getOpened().isBefore(LocalDate.of(1900,1,1))).count();

        System.out.println("//Steams 3.1");
        System.out.printf("Number of stadiums build before 1900: #%d\n",countBeforeDate);

        System.out.println();
        System.out.println("//Steams 3.2");
        System.out.println("Stadiums corted on city then by name:");
        footballStadiumData.stream()
                .sorted(Comparator.comparing(FootballStadium::getCity).thenComparing(FootballStadium::getName))
                .forEach(System.out::println);


        System.out.println();
        System.out.println("//Steams 3.3");
        System.out.println("Stadiums cities, inversely sorted without duplicates:");

        footballStadiumData.stream()
                .map(FootballStadium::getCity)
                .distinct()
                .sorted(Comparator.reverseOrder())
                .forEach(System.out::println);

        System.out.println();
        System.out.println("//Steams 3.4");
        System.out.println("Filter on city of London:");
        Optional<FootballStadium> foundStadium = footballStadiumData.parallelStream()
                .filter(f -> f.getCity().equals("London"))
                .findAny();

        if (foundStadium.isPresent()){
            System.out.println(foundStadium.get());
        } else {
            System.out.println("Stadium not found!");
        }

        System.out.println("The result is the same because we are using .stream(picks first in list) instead of parallelstream(where the same result is not guaranteed).");


        System.out.println();
        System.out.println("//Steams 3.5");
        FootballStadium mostCapacity = footballStadiumData.stream()
                .max(Comparator.comparing(FootballStadium::getCapacity))
                .get();
        System.out.printf("Footbal stadium with highest capacity: Filter on city of London: %s\n",mostCapacity.getName() );



        System.out.println();
        System.out.println("//Steams 3.6");
        System.out.println("A list of city names as strings, sorted alphabetically:\n");
        List<String> stringStadiumNames = footballStadiumData.stream()
                .map(FootballStadium::getName)
                .filter(s -> s.charAt(0) == 'E')
                .sorted()
                .collect(Collectors.toList());
        System.out.println(stringStadiumNames);

        System.out.println();
        System.out.println("//Steams 3.7");
        Map<Boolean, List<FootballStadium>> map = footballStadiumData.stream()
                        .collect(Collectors.partitioningBy(f -> f.getOpened().isBefore(LocalDate.of(2000,1,1))));

        System.out.println("Football stadiums opened before 2000:\n");
        List<FootballStadium> stadiumsOpenedBefore = map.get(true);
        stadiumsOpenedBefore.forEach(System.out::println);
        System.out.println("Football stadiums opened after 2000:\n");
        List<FootballStadium> stadiumsOpenedAfter = map.get(false);
        stadiumsOpenedAfter.forEach(System.out::println);

        System.out.println();
        System.out.println("//Steams 3.7");
        Map<RoofType, List<FootballStadium>> mapCategory = footballStadiumData.stream()
                .collect(Collectors.groupingBy(FootballStadium::getRoofType));

        mapCategory.forEach((k,v) -> System.out.printf("%-25s --> %s\n",k, v.stream()
                .map(f -> f.getName()).collect(Collectors.joining(", "))
        ));

        System.out.println();
        System.out.println("//Steams 4.3 and 4.4");
        //4.3. Test de methode remove en getSize uit (zie 3.2)
        String stringToSearch = "Emirates Stadium";
        FootballStadium searchStadiumOnName = footballStadiums.search(stringToSearch);
        System.out.println("Did we find stadium with name " +  stringToSearch +  ": " + (searchStadiumOnName == null ? "no":"yes"));

        //remove first stadium from list
        System.out.println("How many stadiums are there before removal: " +  footballStadiums.getSize());

        System.out.println("Did we remove stadium with  name " +  searchStadiumOnName.getName() +  ": " + footballStadiums.remove(stringToSearch));
        System.out.println("How many stadiums are there after removal: " +  footballStadiums.getSize());
        FootballStadium searchStadiumAfterRemoval = footballStadiums.search(stringToSearch);
        System.out.println("Did we find stadium with name " +  stringToSearch +  ": " + (searchStadiumAfterRemoval == null ? "no":"yes"));























    }
}