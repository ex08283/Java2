package be.kdg.stadiumproject.model;

import java.util.*;
import java.util.function.Function;

public class FootballStadiums  {
    private Set<FootballStadium> footballStadiums = new TreeSet<>();

    public boolean add(FootballStadium footballStadium){
        return footballStadiums.add(footballStadium);
    }

    public boolean remove(String stadiumName){
              return footballStadiums.removeIf(f -> f.getName().equals(stadiumName));
    }

    public FootballStadium search(String name){

        return footballStadiums.stream()
                .filter(f -> f.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public List<FootballStadium> sortedBy(Function<FootballStadium, Comparable> function){

        List<FootballStadium> footballStadiumsSortedBy = new ArrayList<FootballStadium>(this.footballStadiums);
        Collections.sort(footballStadiumsSortedBy, Comparator.comparing(function));

        return footballStadiumsSortedBy;

    }


    public int getSize(){
        return footballStadiums.size();
    }








}
