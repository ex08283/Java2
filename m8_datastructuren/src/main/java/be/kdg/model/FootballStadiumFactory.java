package be.kdg.model;

import java.time.LocalDate;
import java.util.GregorianCalendar;
import java.util.Random;

public class FootballStadiumFactory {

    private FootballStadiumFactory() {
    }

    public static FootballStadium newEmptyFootballStadium(){
        return new FootballStadium();
    }

    public static FootballStadium newFilledFoobtallStadium(String name, String city, LocalDate date, RoofType roofType, Double cost, int capacity){
        return new FootballStadium(name, city, date, roofType,cost, capacity);
    }

    public static FootballStadium newRandomFootballStadium(){
        return new FootballStadium(
                generateString(11,3)
                ,generateString(11, 1)
                ,generateDate()
                , RoofType.values()[new Random().nextInt(RoofType.values().length)]
                ,new Random().nextDouble() * 1e12
                , new Random().nextInt((int) 1e6)
        );
    }

    private static String generateString(int maxWordLength, int wordCount){
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int nrOfWords = new Random().nextInt(wordCount) +1;
        int randomNrOfCharsPerWord = new Random().nextInt(maxWordLength) + 3;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < nrOfWords; i++) {
            String generatedString = new Random().ints(leftLimit, rightLimit + 1)
                    .limit(randomNrOfCharsPerWord)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
            builder.append(generatedString.substring(0,1).toUpperCase() +generatedString.substring(1)).append(" ");
        }

        return builder.toString().trim();
    }

    private static LocalDate generateDate(){
        int minDay = (int) LocalDate.of(1800, 1,1).toEpochDay();
        int maxDay = (int) LocalDate.now().toEpochDay();
        long randomDay =  minDay + new Random().nextInt(maxDay - minDay);
        return LocalDate.ofEpochDay(randomDay);
    }

}
