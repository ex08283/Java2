import be.kdg.kollections.lists.ArrayList;
import be.kdg.kollections.Kollections;
import be.kdg.kollections.lists.LinkedList;
import be.kdg.kollections.lists.List;
import be.kdg.kollections.maps.HashMap;
import be.kdg.kollections.maps.ListMap;
import be.kdg.kollections.maps.Map;
import be.kdg.kollections.sets.ArraySet;
import be.kdg.kollections.sets.TreeSet;
import be.kdg.model.FootballStadium;
import be.kdg.model.FootballStadiumFactory;

public class PerformanceTester {

    public static List<FootballStadium> randomList(int n) {
        List<FootballStadium> myList = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            myList.add(FootballStadiumFactory.newRandomFootballStadium());
        }
        return myList;
    }

    public static void compareArrayListAndLinkedList(int n) {
        //add at beginning
        List<FootballStadium> myLinkedList = new LinkedList<>();
        List<FootballStadium> myArrayList = new ArrayList<>();

        long startTimeAddToArray = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            myArrayList.add(0,FootballStadiumFactory.newRandomFootballStadium());
        }
        //new Random().ints(n).forEach(i -> myArrayList.add(FootballStadiumFactory.newRandomFootballStadium()));
        long durationAddToArrayList = System.currentTimeMillis() - startTimeAddToArray;
        System.out.println("Adding 20000 to ArrayList: " + durationAddToArrayList + " ms");

        long startTimeAddToLinked = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            myLinkedList.add(0,FootballStadiumFactory.newRandomFootballStadium());
        }
        //new Random().ints(n).forEach(i -> myLinkedList.add(FootballStadiumFactory.newRandomFootballStadium()));
        long durationAddToLinkedList = System.currentTimeMillis() - startTimeAddToLinked;
        System.out.println("Adding 20000 to LinkedList: " + durationAddToLinkedList + " ms");


        //get at end


        long startTimeGetArray = System.currentTimeMillis();
        FootballStadium getStadiumFromArrayList = myArrayList.get(n-1);
        long durationGetArrayList = System.currentTimeMillis() - startTimeGetArray;
        System.out.println("Getting 20000 from ArrayList: " + durationGetArrayList + " ms");

        long startTimeGetLinked = System.currentTimeMillis();
        FootballStadium getStadiumFromLinkedList = myLinkedList.get(n-1);
        long durationGetLinkedList = System.currentTimeMillis() - startTimeGetLinked;
        System.out.println("Getting 20000 from LinkedList: " + durationGetLinkedList + " ms");
        System.out.println(getStadiumFromLinkedList);

    }

    public static void testSelectionSort() {

        List<FootballStadium> randomCustomListForSelectionSort;

        for (int i = 100; i < 2000; i +=100) {

            //Create a random list for  testing
            randomCustomListForSelectionSort = PerformanceTester.randomList(i);

            Kollections.selectionSort(randomCustomListForSelectionSort);
            System.out.printf("%d;%d\n", i, randomCustomListForSelectionSort.get(i-1).getCounter());
        }


    }

    public static void testMergeSort() {
        List<FootballStadium> randomCustomListForMergeSort;

        for (int i = 100; i < 2000; i +=100) {

            randomCustomListForMergeSort = PerformanceTester.randomList(i);

            Kollections.mergeSort(randomCustomListForMergeSort);
            System.out.printf("%d;%d\n", i, FootballStadium.getCounter());
        }

    }

    public static void compareListMapToHasMap(int mapSize){

        ListMap<FootballStadium, String>  listMap= new ListMap<>();
        fillMap(listMap, mapSize);

        long startTime = System.nanoTime();
        for (int i = 0; i < mapSize; i++) {
            FootballStadium footballStadium = FootballStadiumFactory.newEmptyFootballStadium();
            footballStadium.setName("Stadium"+i);
            listMap.get(footballStadium);
        }
        long duration = System.nanoTime() - startTime;

        int equalsCounterListMap = FootballStadium.getEqualsCounter();

        System.out.printf("Listmap --> n: %d equalscounter:%d  nanosec: %d\n",mapSize,equalsCounterListMap, duration);


        HashMap<FootballStadium, String> hashMap= new HashMap<>();
        fillMap(hashMap, mapSize);


        startTime = System.nanoTime();

        for (int i = 0; i < mapSize; i++) {
            FootballStadium footballStadium = FootballStadiumFactory.newEmptyFootballStadium();
            footballStadium.setName("Stadium"+i);
            hashMap.get(footballStadium);
        }
        duration = System.nanoTime() - startTime;


        int equalsCounterHashMap = FootballStadium.getEqualsCounter()-equalsCounterListMap;



        System.out.printf("Hashmap --> n: %d equalscounter:%d  nanosec: %d\n",mapSize, equalsCounterHashMap, duration);

    }

    private static void fillMap(Map<FootballStadium, String> map, int N){
        for (int i = 0; i <N; i++) {
            FootballStadium footballStadium = FootballStadiumFactory.newEmptyFootballStadium();
            footballStadium.setName("Stadium"+i);
            map.put(footballStadium, " Value for FootballStadium" + i);
        }
    }

    public static void compareArraySetToTreeSet(int mapSize){
        ArraySet<FootballStadium> arraySet= new ArraySet<>();

        long startTimeA = System.nanoTime();
        for (int i = 0; i < mapSize; i++) {
            arraySet.add(FootballStadiumFactory.newRandomFootballStadium());
        }
        long durationA = System.nanoTime() - startTimeA;

        int equalsCounterArraySet = FootballStadium.getEqualsCounter();
        int compareCountArraySet = FootballStadium.getCounter();

        System.out.printf("ArraySey --> n: %d equalscounter:%d  \n",mapSize, equalsCounterArraySet);
        System.out.printf("ArraySey --> n: %d comparecounter:%d  \n",mapSize, compareCountArraySet);
        System.out.printf("ArraySey --> n: %d nanosec: %d\n",mapSize, durationA);


        TreeSet<FootballStadium> treeSet= new TreeSet<>();

        long startTimet = System.nanoTime();
        for (int i = 0; i < mapSize; i++) {
            treeSet.add(FootballStadiumFactory.newRandomFootballStadium());
        }
        //Test to see if duplicate is added
//        FootballStadium stadium =FootballStadiumFactory.newEmptyFootballStadium();
//        treeSet.add(stadium);
//        treeSet.add(stadium);
        long durationt = System.nanoTime() - startTimet;

        int equalsCounterTreeSet = FootballStadium.getEqualsCounter()-equalsCounterArraySet;
        int compareCountTreeSet = FootballStadium.getCounter()-compareCountArraySet;

        System.out.printf("Treeset --> n: %d equalscounter:%d  \n",mapSize, equalsCounterTreeSet);
        System.out.printf("Treeset --> n: %d comparecounter:%d  \n",mapSize, compareCountTreeSet);
        System.out.printf("Treeset --> n: %d nanosec: %d\n",mapSize, durationt);



        System.out.println();
        System.out.println("TODO, complete remove");

        TreeSet<FootballStadium> treeSetRemove= new TreeSet<>();



        FootballStadium stadium1 =FootballStadiumFactory.newEmptyFootballStadium();
        stadium1.setName("anom1");
        FootballStadium stadium2 =FootballStadiumFactory.newEmptyFootballStadium();
        stadium2.setName("banom");
        FootballStadium stadium3 =FootballStadiumFactory.newEmptyFootballStadium();
        stadium3.setName("cnom3");
        treeSetRemove.add(stadium1);
        treeSetRemove.add(stadium2);
        treeSetRemove.add(stadium3);


        List<FootballStadium> list1 = treeSetRemove.toList();
        System.out.println("We added the following three stadiums and will remove the first stadium");
        for (int i = 0; i < list1.size(); i++) {
            System.out.println(list1.get(i));
        }

        treeSetRemove.remove(stadium1);

        System.out.println();
        System.out.println("We can see the stadium1 was removed successfully:");
        List<FootballStadium> list2 = treeSetRemove.toList();
        for (int i = 0; i < list2.size(); i++) {
            System.out.println(list2.get(i));
        }








//        HashMap<FootballStadium, String> hashMap= new HashMap<>();
//        fillMap(hashMap, mapSize);
//
//
//        startTimeA = System.nanoTime();
//
//        for (int i = 0; i < mapSize; i++) {
//            FootballStadium footballStadium = FootballStadiumFactory.newEmptyFootballStadium();
//            footballStadium.setName("Stadium"+i);
//            hashMap.get(footballStadium);
//        }
//        duration = System.nanoTime() - startTimeA;
//
//
//        int equalsCounterHashMap = FootballStadium.getEqualsCounter()- equalsCounterArraySet;
//
//
//
//        System.out.printf("Hashmap --> n: %d equalscounter:%d  nanosec: %d\n",mapSize, equalsCounterHashMap, duration);
    }


}
