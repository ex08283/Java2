import be.kdg.stadiumproject.model.FootballStadium;
import be.kdg.stadiumproject.model.FootballStadiumFactory;
import be.kdg.stadiumproject.model.RoofType;
import be.kdg.stadiumproject.threading.FootballStadiumAttacker;
import be.kdg.stadiumproject.threading.FootballStadiumRunnable;

import java.util.List;
import java.util.stream.Stream;

public class Demo_10 {


    public static void main(String[] args) {
        List<FootballStadium> listToBeAttacked = new java.util.ArrayList<>(Stream.generate(FootballStadiumFactory::newRandomFootballStadium).limit(1000).toList());

//        long countBeforeRemoveOpenStadiums = listToBeAttacked.stream().filter((f) -> f.getRoofType() == RoofType.OPEN).count();




        Thread removeOpenStadiums = new Thread(new FootballStadiumAttacker(listToBeAttacked, (f) -> f.getRoofType() == RoofType.OPEN ));
        Thread removeOpenedBefore = new Thread(new FootballStadiumAttacker(listToBeAttacked, (f) -> f.getOpened().getYear() < 2000));
        Thread removeCapcityLessThan = new Thread(new FootballStadiumAttacker(listToBeAttacked, (f) -> f.getCapacity() < 100000));

        removeOpenStadiums.start();
        removeOpenedBefore.start();
        removeCapcityLessThan.start();

        try {
            removeOpenStadiums.join();
            removeOpenedBefore.join();
            removeCapcityLessThan.join();
        } catch (InterruptedException e) {
            // ...
        }

        System.out.println();
        System.out.println("Na uitzuivering:");

        long countRemoveOpenStadiums = listToBeAttacked.stream().filter((f) -> f.getRoofType() == RoofType.OPEN).count();
        long countRemoveOpenedBefore = listToBeAttacked.stream().filter((f) -> f.getOpened().getYear() < 2000).count();
        long countRemoveCapcityLessThan = listToBeAttacked.stream().filter((f) -> f.getCapacity() < 100000).count();
        System.out.println("Number of Stadiums with Open Rooftype: " + countRemoveOpenStadiums );
        System.out.println("Number of Stadiums opened before year 2000: " + countRemoveOpenedBefore );
        System.out.println("Number of Stadiums with capacity less than 100000: " + countRemoveCapcityLessThan);






    }
}