package be.kdg.stadiumproject.persist;

import be.kdg.stadiumproject.model.FootballStadiums;

import java.io.*;

public class FootballStadiumSerializer {
    private final String FILENAME;

    public FootballStadiumSerializer(String filename) {
        FILENAME = filename;
    }

    public void serialize(FootballStadiums footballStadiums) throws IOException{
        FileOutputStream fileOutputStream = new FileOutputStream(FILENAME);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(footballStadiums);
    }
    public FootballStadiums deserialize() throws IOException, ClassNotFoundException{
        FileInputStream fileInputStream = new FileInputStream(FILENAME);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        FootballStadiums footballStadiums = (FootballStadiums) objectInputStream.readObject();
        System.out.println("deserializing");
        footballStadiums.sortedOnName().forEach(System.out::println);
        return footballStadiums;
    }
}
