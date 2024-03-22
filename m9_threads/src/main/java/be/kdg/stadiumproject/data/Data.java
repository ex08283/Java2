package be.kdg.stadiumproject.data;

import be.kdg.stadiumproject.model.FootballStadium;
import be.kdg.stadiumproject.model.RoofType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Data
{
    public static List<FootballStadium> getData(){
        FootballStadium CampNou = new FootballStadium("Camp Nou", "Barcelona", LocalDate.of(1957, 9, 24), RoofType.OPEN,1.73e9, 99354);
        FootballStadium WembleyStadium = new FootballStadium("Wembley Stadium", "London", LocalDate.of(2007, 3, 9), RoofType.PARTIALLYRETRACTABLE,.92213e9, 90000);
        FootballStadium SantiagoBernabeu = new FootballStadium("Santiago Bernabéu", "Madrid", LocalDate.of(1957, 9, 9), RoofType.RETRACTABLE,0.893e9, 81044);
        FootballStadium CrokePark = new FootballStadium("Croke Park", "Dublin", LocalDate.of(1884, 1, 1), RoofType.OPEN,0.26e9, 82300);
        FootballStadium StadedeFrance = new FootballStadium("Stade de France", "Saint-Denis", LocalDate.of(1995, 5, 2), RoofType.OPEN,0.364e9, 80000);
        FootballStadium Westfalenstadion = new FootballStadium("Westfalenstadion", "Dortmmund", LocalDate.of(1974, 4, 2), RoofType.OPEN,0.237e9, 81365);
        FootballStadium SanSiro = new FootballStadium("San Siro", "Milan", LocalDate.of(1926, 9, 19), RoofType.OPEN,2500, 75817);
        FootballStadium OldTrafford = new FootballStadium("Old Trafford", "Manchester", LocalDate.of(1910, 2, 19), RoofType.OPEN,90000, 74310);
        FootballStadium AtaturkOlympicStadium = new FootballStadium("Atatürk Olympic Stadium", "Istanbul", LocalDate.of(2002, 7, 31), RoofType.OPEN,0.28e9, 74753);
        FootballStadium Olympiastadion = new FootballStadium("Olympiastadion", "Berlin", LocalDate.of(1936, 8, 1), RoofType.OPEN,0.297e9, 74667);
        FootballStadium PuskasArena = new FootballStadium("Puskás Aréna", "Budapest", LocalDate.of(2019, 9, 15), RoofType.OPEN,0.533e9, 74753);
        FootballStadium LondonStadium = new FootballStadium("London Stadium", "London", LocalDate.of(2008, 5, 22), RoofType.OPEN,0.274e9, 66000);
        FootballStadium EstadiodaLuz = new FootballStadium("Estádio da Luz", "Lisbon", LocalDate.of(2003, 10, 25), RoofType.OPEN,0.162e9, 64642);
        FootballStadium EmiratesStadium = new FootballStadium("Emirates Stadium", "London", LocalDate.of(2006, 7, 22), RoofType.OPEN,0.39e9, 60704);
        FootballStadium CelticPark = new FootballStadium("Celtic Park", "Glasgow", LocalDate.of(1892, 8, 20), RoofType.OPEN,0.04e9, 83500);

        List<FootballStadium> footballStadiums = new ArrayList<>();
        footballStadiums.add(CampNou);
        footballStadiums.add(WembleyStadium);
        footballStadiums.add(SantiagoBernabeu);
        footballStadiums.add(CrokePark);
        footballStadiums.add(StadedeFrance);
        footballStadiums.add(Westfalenstadion);
        footballStadiums.add(SanSiro);
        footballStadiums.add(OldTrafford);
        footballStadiums.add(AtaturkOlympicStadium);
        footballStadiums.add(Olympiastadion);
        footballStadiums.add(PuskasArena);
        footballStadiums.add(LondonStadium);
        footballStadiums.add(EstadiodaLuz);
        footballStadiums.add(EmiratesStadium);
        footballStadiums.add(CelticPark);

        return footballStadiums;

    }
}
