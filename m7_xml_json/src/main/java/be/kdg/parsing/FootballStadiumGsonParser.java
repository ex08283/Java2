package be.kdg.parsing;

import be.kdg.model.FootballStadium;
import be.kdg.model.FootballStadiums;
import be.kdg.model.LocalDateGsonAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class FootballStadiumGsonParser {


    public static void writeJson(FootballStadiums footballStadiums, String fileName) throws FileNotFoundException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateGsonAdapter().nullSafe())
                .create();

        String jsonString = gson.toJson(footballStadiums.getFootballStadiumsList());

        System.out.printf("Serialised:\n\t%s\n", jsonString);

        try ( PrintWriter jsonWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(fileName)))) {
            jsonWriter.write(jsonString);
            System.out.println("Json file saved");
        }

    }

    public static FootballStadiums readJson(String fileName) throws FileNotFoundException, IOException{
        GsonBuilder builder = new GsonBuilder();

        Gson gson = builder
                .registerTypeAdapter(LocalDate.class, new LocalDateGsonAdapter())
                .create();

        List<FootballStadium> footballStadiumsList= null;
        FootballStadiums stadiums = new FootballStadiums();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            footballStadiumsList = Arrays.asList(gson.fromJson(reader, FootballStadium[].class));
            footballStadiumsList.forEach(f -> stadiums.add(f));
            System.out.println();
            System.out.println("Deserialised:");
            stadiums.getFootballStadiumsList().forEach(System.out::println);
        }

//        System.out.println("Deserialised: LLLLLLLLLLLLLLLLLLLLLLLL");
//
//        stadiums.getFootballStadiumsList().forEach(System.out::println);
        return stadiums;

    }


}
