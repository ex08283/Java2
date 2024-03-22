import be.kdg.kollections.Kollections;
import be.kdg.kollections.lists.List;
import be.kdg.model.FootballStadium;
import be.kdg.model.FootballStadiumFactory;
import be.kdg.model.RoofType;

import java.time.LocalDate;
import java.util.stream.Stream;

public class Demo_8 {
    public static void main(String[] args) {


        System.out.println("Groeiproject week 8:");
        System.out.println("Empty stadium:");
        System.out.println(FootballStadiumFactory.newEmptyFootballStadium() + "\n");

        System.out.println("Filled stadium:");
        System.out.println(FootballStadiumFactory.newFilledFoobtallStadium("Dela Stadium", "London", LocalDate.now(), RoofType.OPEN, 0.0, 5) + "\n");
        System.out.println();

        System.out.println("30 randomly generated stadium sorted by name:");
        Stream.generate(FootballStadiumFactory::newRandomFootballStadium)
                .limit(30)
                .forEach(System.out::println);
//
//
//        System.out.println();
//        System.out.println("3.2 Instantie eigen arraylist");
//        System.out.println();
//
//
//        //Create a random list for  testing
//        List<FootballStadium> randomCustomList = PerformanceTester.randomList(5);
//
//
//        System.out.println("all");
//        for (int i = 0; i < randomCustomList.size(); i++) {
//            System.out.println(randomCustomList.get(i));
//        }
//
//        randomCustomList.add(0,FootballStadiumFactory.newEmptyFootballStadium());
//
//        System.out.println("It still works afte we do an add:");
//        //System.out.println(randomCustomList.get(0));
//        for (int i = 0; i < randomCustomList.size(); i++) {
//            System.out.println(randomCustomList.get(i));
//        }
//
//        System.out.println();
//        System.out.println("3.6 compary performance of array list vs linkedlist using get and add methods");
//
//        PerformanceTester.compareArrayListAndLinkedList(200000);
//
//        3.6 compary performance of array list vs linkedlist using get and add methods
//        Adding 20000 to ArrayList: 4868 ms
//        Adding 20000 to LinkedList: 3394 ms
//        Getting 20000 from ArrayList: 0 ms
//        Getting 20000 from LinkedList: 9 ms

//        System.out.println();
//        System.out.println("4.3 search and sort algo's, after applying the selections sort algoritm");
//
//        Kollections.selectionSort(randomCustomList);
//        for (int i = 0; i < randomCustomList.size(); i++) {
//            System.out.println(randomCustomList.get(i));
//        }
//
//
//        System.out.println();
//        System.out.println("4.5 search and sort algo's, after applying the selections sort algoritm");
//
//        //Create a random list for  testing
//        List<FootballStadium> randomCustomListForMergeSort = PerformanceTester.randomList(5);
//
//        Kollections.mergeSort(randomCustomListForMergeSort);
//        for (int i = 0; i < randomCustomListForMergeSort.size(); i++) {
//            System.out.println(randomCustomListForMergeSort.get(i));
//        }
//        4.7 Test sort algos
//
//        PerformanceTester.testSelectionSort();
//        PerformanceTester.testMergeSort();
//
//        // 4.9
//
//        //Create a random list for  testing
//        List<FootballStadium> randomCustomListForQuickSort = PerformanceTester.randomList(30);
//
//
//        System.out.println();
//        System.out.println("4.9 Quick sort");
//        System.out.println("Quick sort : before sort");
//        for (int i = 0; i < randomCustomListForQuickSort.size(); i++) {
//            System.out.println(randomCustomListForQuickSort.get(i));
//        }
//
//        Kollections.quickSort(randomCustomListForQuickSort);
//
//        System.out.println();
//        System.out.println();
//        System.out.println("Quick sort : after sort");
//        //System.out.println(randomCustomList.get(0));
//        for (int i = 0; i < randomCustomListForQuickSort.size(); i++) {
//            System.out.println(randomCustomListForQuickSort.get(i));
//        }
//
//        System.out.println();
//        System.out.println("4.11 binary bs linear search");
//
//        //Add an empty stadium
//        FootballStadium footballStadiumToSearchFor =  FootballStadiumFactory.newEmptyFootballStadium();
//        FootballStadium nonExistent =  FootballStadiumFactory.newEmptyFootballStadium();
//        footballStadiumToSearchFor.setName("zzzzz");
//        nonExistent.setName("nonexistent");
//        randomCustomListForQuickSort.add(footballStadiumToSearchFor);
//
//        //Sort the List
//        Kollections.quickSort(randomCustomListForQuickSort);
//
//        //Print the list again
//        System.out.println();
//        System.out.println("The added stadium starts with an A and was added and sorted successfully");
//        //System.out.println(randomCustomList.get(0));
//        for (int i = 0; i < randomCustomListForQuickSort.size(); i++) {
//            System.out.println(randomCustomListForQuickSort.get(i));
//        }
//
//        System.out.println("Index of Stadium with name " + footballStadiumToSearchFor.getName() + ": " + Kollections.lineairSearch(randomCustomListForQuickSort, footballStadiumToSearchFor));
//        System.out.println("Index of Stadium with name " + footballStadiumToSearchFor.getName() + ": " + Kollections.binarySearch(randomCustomListForQuickSort, footballStadiumToSearchFor));
//        System.out.println("Index of Stadium with name " + footballStadiumToSearchFor.getName() + ": " + Kollections.lineairSearch(randomCustomListForQuickSort, nonExistent));
//        System.out.println("Index of Stadium with name " + footballStadiumToSearchFor.getName() + ": " + Kollections.binarySearch(randomCustomListForQuickSort, nonExistent));

        //5.8  In de klasse PerformanceTester schrijf je een nieuwe methode compareListMapToHasMap:

        //PerformanceTester.compareListMapToHasMap(1000);
        PerformanceTester.compareArraySetToTreeSet(1000);

//        PerformanceTester.testMapImplementation();


















    }



}
