package be.kdg.stadiumproject.threading;

import be.kdg.stadiumproject.model.FootballStadium;

import java.util.List;
import java.util.function.Predicate;

public class FootballStadiumAttacker implements Runnable{
    private final List<FootballStadium> footballStadiumList;

    Predicate<FootballStadium> predicate;

    public FootballStadiumAttacker(List<FootballStadium> list, Predicate<FootballStadium> predicate) {
        this.footballStadiumList=list;
        this.predicate= predicate;
    }

    @Override
    public void run() {
        synchronized (footballStadiumList){
        footballStadiumList.removeIf(predicate);}
    }
}
