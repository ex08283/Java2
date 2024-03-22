import be.kdg.stadiumproject.model.FootballStadiumFactory;
import be.kdg.stadiumproject.model.FootballStadiums;
import be.kdg.stadiumproject.model.RoofType;
import be.kdg.stadiumproject.threading.FootballStadiumRunnable;

import java.util.stream.Stream;

public class Demo_12 {


    public static void main(String[] args) throws InterruptedException {
        FootballStadiums footballStadiums = new FootballStadiums(10000);

        Runnable runnable = () -> {
            Stream.generate(FootballStadiumFactory::newRandomFootballStadium).limit(5000).forEach(footballStadiums::add);
        };

        System.out.println("Two threads to add 10000 stadiums to multiclass");
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println();
        System.out.println("After adding 5000 object using two threads: footballstadiums: " + footballStadiums.getSize());
        System.out.println();




    }
}