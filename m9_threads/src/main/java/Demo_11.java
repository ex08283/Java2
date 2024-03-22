import be.kdg.stadiumproject.model.FootballStadium;
import be.kdg.stadiumproject.model.RoofType;
import be.kdg.stadiumproject.threading.FootballStadiumCallable;
import be.kdg.stadiumproject.threading.FootballStadiumRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Demo_11 {

    private static final int TESTCOUNTER = 1;

    public static void main(String[] args) throws Exception {

        FootballStadiumCallable callableStadium1 = new FootballStadiumCallable(f -> f.getName().startsWith("A"));
        //FootballStadiumRunnable runnableStadium2 = new FootballStadiumRunnable(f -> f.getName().startsWith("B"));
        FootballStadiumCallable callableStadium2 = new FootballStadiumCallable(f -> f.getRoofType() == RoofType.OPEN);
        FootballStadiumCallable callableStadium3 = new FootballStadiumCallable(f -> f.getCapacity() < 1000);

        List<FootballStadiumCallable> footballStadiumCallables = new ArrayList<>();
        footballStadiumCallables.add(callableStadium1);
        footballStadiumCallables.add(callableStadium2);
        footballStadiumCallables.add(callableStadium3);





        long[] times = new long[TESTCOUNTER];
        for (int i = 0; i < TESTCOUNTER; i++) {
            ExecutorService pool = Executors.newFixedThreadPool(3);
            List<Future<List<FootballStadium>>> futureListStadiums = new ArrayList<>();
            long startTime = System.currentTimeMillis();
            for (FootballStadiumCallable footballStadiumCallable : footballStadiumCallables) {
                Future<List<FootballStadium>> stadiumFuture = pool.submit(footballStadiumCallable);
                futureListStadiums.add(stadiumFuture);
            }

            for (Future<List<FootballStadium>> future : futureListStadiums) {
                List<FootballStadium> list = future.get();
//                list.stream().limit(5).forEach(System.out::println);
            }
            pool.shutdown();

            times[i] = System.currentTimeMillis()-startTime;

        }

        long totalTimeAverage = 0;

        for (long time : times) {
            totalTimeAverage += time;
//            System.out.println(time);
        }

        long averageTime = totalTimeAverage/TESTCOUNTER;

        System.out.println("3 threads verzamelen elk 1000 Stadiums (gemiddelde uit " + TESTCOUNTER + " runs): " + averageTime + " ms");





//        racerEen.setDaemon(false);
//        thread1.start();
//        thread2.start();
//        thread3.start();
//        try {
//            thread1.join();
//            thread2.join();
//            thread3.join();
//
//        } catch (InterruptedException ie){
//            ie.printStackTrace();
//        }
//         long totTime = System.currentTimeMillis()-starttime;

//        System.out.println("Starts with A");
//       runnableStadium1.getFootballStadiums().stream().limit(5).forEach(System.out::println);
//        System.out.println();
//        System.out.println("Rooftype open");
//       runnableStadium2.getFootballStadiums().stream().limit(5).forEach(System.out::println);
//        System.out.println();
//        System.out.println("Capacity < 1000");
//       runnableStadium3.getFootballStadiums().stream().limit(5).forEach(System.out::println);

        System.out.println();
       System.out.println("Main end");




    }
}