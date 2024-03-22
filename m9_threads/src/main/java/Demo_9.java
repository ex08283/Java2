import be.kdg.stadiumproject.data.Data;
import be.kdg.stadiumproject.model.FootballStadium;
import be.kdg.stadiumproject.model.FootballStadiumFactory;
import be.kdg.stadiumproject.model.FootballStadiums;
import be.kdg.stadiumproject.model.RoofType;
import be.kdg.stadiumproject.threading.FootballStadiumRunnable;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;

public class Demo_9 {

    private static final int TESTCOUNTER = 3;

    public static void main(String[] args) {
        FootballStadiumRunnable runnableStadium1 = new FootballStadiumRunnable(f -> f.getName().startsWith("A"));
        //FootballStadiumRunnable runnableStadium2 = new FootballStadiumRunnable(f -> f.getName().startsWith("B"));
        FootballStadiumRunnable runnableStadium2 = new FootballStadiumRunnable(f -> f.getRoofType() == RoofType.OPEN);
        FootballStadiumRunnable runnableStadium3 = new FootballStadiumRunnable(f -> f.getCapacity() < 1000);


        long[] times = new long[TESTCOUNTER];
        for (int i = 0; i < TESTCOUNTER; i++) {
            Thread[] threads = new Thread[3];
            threads[0]= new Thread(runnableStadium1);
            threads[1]= new Thread(runnableStadium2);
            threads[2]= new Thread(runnableStadium3);
            long startTime = System.currentTimeMillis();
            for (Thread value : threads) {
                value.start();
            }

            for (Thread thread : threads) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    // ...
                }
            }

            times[i] = System.currentTimeMillis()-startTime;

        }

        long totalTimeAverage = 0;

        for (long time : times) {
            totalTimeAverage += time;
            System.out.println(time);
        }

        long averageTime = totalTimeAverage/TESTCOUNTER;

        System.out.println("3 threads verzamelen elk 1000 Stadiums (gemiddelde uit 100 runs): " + averageTime + " ms");





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