package be.kdg.model;

import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FootballStadiums  {
    private static final Logger logger = Logger.getLogger("be.kdg.model.FootballStadiums");




    private final Set<FootballStadium> footballStadiums = new TreeSet<>();

    public boolean add(FootballStadium footballStadium){
        boolean added  =footballStadiums.add(footballStadium);
        if (added) {
            logger.log(Level.FINER, "Added: " + footballStadium);
        }
        return added;
    }

    public boolean remove(String stadiumName){
        boolean found = false;
        for (Iterator<FootballStadium> it = footballStadiums.iterator(); it.hasNext();) {
            FootballStadium foundFootballStadium = it.next();;
            if (foundFootballStadium.getName().equals(stadiumName)) {
                it.remove();
                logger.log(Level.FINER, "Removed: " + foundFootballStadium);
                found = true;
            };
        }

        return found;

    }

    public FootballStadium search(String name){
        Iterator<FootballStadium> itr = footballStadiums.iterator();
        int currentIndex = 0;
        while (itr.hasNext()){
            FootballStadium foundFootballStadium = itr.next();
            if (foundFootballStadium.getName().equals(name)){
                return foundFootballStadium;
            }
            currentIndex++;
        }

        return null;
    }

    public List<FootballStadium> sortedOnName(){

        List<FootballStadium> footballStadiumsSortedOnName = new ArrayList<FootballStadium>(this.footballStadiums);

        footballStadiumsSortedOnName.sort(new Comparator<FootballStadium>() {
            @Override
            public int compare(FootballStadium stadium1, FootballStadium stadium2) {
                return stadium1.getName().compareTo(stadium2.getName());
            }
        });

        return footballStadiumsSortedOnName;

    }
    public List<FootballStadium> sortedOnOpening(){
        List<FootballStadium> footballStadiumsSortedOnOpening = new ArrayList<FootballStadium>(this.footballStadiums);

        footballStadiumsSortedOnOpening.sort(new Comparator<FootballStadium>() {
            @Override
            public int compare(FootballStadium stadium1, FootballStadium stadium2) {
                return stadium1.getOpened().compareTo(stadium2.getOpened());
            }
        });

        return footballStadiumsSortedOnOpening;

    }
    public List<FootballStadium> sortedOnCapacity(){
        List<FootballStadium> footballStadiumsSortedOnCapacity = new ArrayList<FootballStadium>(this.footballStadiums);

        footballStadiumsSortedOnCapacity.sort(new Comparator<FootballStadium>() {
            @Override
            public int compare(FootballStadium stadium1, FootballStadium stadium2) {
                return stadium1.getCapacity() - stadium2.getCapacity();
            }
        });

        return footballStadiumsSortedOnCapacity;

    }

    public int getSize(){
        return footballStadiums.size();
    }








}
