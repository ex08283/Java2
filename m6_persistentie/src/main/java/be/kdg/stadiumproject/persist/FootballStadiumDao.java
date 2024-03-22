package be.kdg.stadiumproject.persist;

import be.kdg.stadiumproject.model.FootballStadium;

import java.util.List;

public interface FootballStadiumDao {
    boolean insert(FootballStadium dictator);
    boolean delete(String naam);
    boolean update(FootballStadium dictator);
    FootballStadium retrieve(String naam);
    List<FootballStadium> sortedOn(String query);
}
