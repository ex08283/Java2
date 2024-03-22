package be.kdg.model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.*;

@XmlRootElement(name="footballstadiums")
public class FootballStadiums  {
    private List<FootballStadium> footballStadiumsList = new ArrayList<>();

    public boolean add(FootballStadium footballStadium){

        return footballStadiumsList.add(footballStadium);
    }

    public boolean remove(String stadiumName){
        boolean found = false;
        for (Iterator<FootballStadium> it = footballStadiumsList.iterator(); it.hasNext();) {
            if (it.next().getName().equals(stadiumName)) {
                it.remove();
                found = true;
            };
        }

        return found;

    }

    public List<FootballStadium> getFootballStadiumsList() {
        return footballStadiumsList;
    }

    @XmlElement(name = "footballstadium")
    public void setFootballStadiumsList(List<FootballStadium> footballStadiumsList) {
        this.footballStadiumsList = footballStadiumsList;
    }

    public FootballStadium search(String name){
        Iterator<FootballStadium> itr = footballStadiumsList.iterator();
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

        List<FootballStadium> footballStadiumsSortedOnName = new ArrayList<FootballStadium>(this.footballStadiumsList);

        footballStadiumsSortedOnName.sort(new Comparator<FootballStadium>() {
            @Override
            public int compare(FootballStadium stadium1, FootballStadium stadium2) {
                return stadium1.getName().compareTo(stadium2.getName());
            }
        });

        return footballStadiumsSortedOnName;

    }
    public List<FootballStadium> sortedOnOpening(){
        List<FootballStadium> footballStadiumsSortedOnOpening = new ArrayList<FootballStadium>(this.footballStadiumsList);

        footballStadiumsSortedOnOpening.sort(new Comparator<FootballStadium>() {
            @Override
            public int compare(FootballStadium stadium1, FootballStadium stadium2) {
                return stadium1.getOpened().compareTo(stadium2.getOpened());
            }
        });

        return footballStadiumsSortedOnOpening;

    }
    public List<FootballStadium> sortedOnCapacity(){
        List<FootballStadium> footballStadiumsSortedOnCapacity = new ArrayList<FootballStadium>(this.footballStadiumsList);

        footballStadiumsSortedOnCapacity.sort(new Comparator<FootballStadium>() {
            @Override
            public int compare(FootballStadium stadium1, FootballStadium stadium2) {
                return stadium1.getCapacity() - stadium2.getCapacity();
            }
        });

        return footballStadiumsSortedOnCapacity;

    }

    public int getSize(){
        return footballStadiumsList.size();
    }








}
