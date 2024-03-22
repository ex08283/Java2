package be.kdg.stadiumproject.threading;

import be.kdg.stadiumproject.model.FootballStadium;
import be.kdg.stadiumproject.model.FootballStadiumFactory;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class FootballStadiumRunnable implements Runnable{
    Predicate<FootballStadium> predicate;
    private List<FootballStadium> footballStadiums;

    public FootballStadiumRunnable(Predicate<FootballStadium> predicate) {
        this.predicate =  predicate;
    }

    @Override
    public void run() {

        this.footballStadiums = Stream.generate(FootballStadiumFactory::newRandomFootballStadium)
                .filter(predicate).limit(1000)
                .toList();
    }

    public List<FootballStadium> getFootballStadiums() {
        return footballStadiums;
    }
}
