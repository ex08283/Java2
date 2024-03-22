package be.kdg.stadiumproject.threading;

import be.kdg.stadiumproject.model.FootballStadium;
import be.kdg.stadiumproject.model.FootballStadiumFactory;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class FootballStadiumCallable implements Callable<List<FootballStadium>> {
    Predicate<FootballStadium> predicate;

    public FootballStadiumCallable(Predicate<FootballStadium> predicate) {
        this.predicate = predicate;
    }

    @Override
    public List<FootballStadium> call() throws Exception {
        return Stream.generate(FootballStadiumFactory::newRandomFootballStadium)
                .filter(predicate).limit(1000)
                .toList();
    }
}
