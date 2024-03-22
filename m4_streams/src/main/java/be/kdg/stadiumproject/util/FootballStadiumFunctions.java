package be.kdg.stadiumproject.util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.ToLongFunction;

public class FootballStadiumFunctions {
    public static <T> List<T> filteredList(List<T> footbalStaidumsList, Predicate<T> predicate){
        List<T> tList = new ArrayList<>();

//        for (T match : footbalStaidumsList) {
//            if (predicate.test(match)) tList.add(match);
//        }
        footbalStaidumsList.stream()
                .filter(predicate::test)
                .forEach(tList::add);

        return tList;
    }

    public static <T> Double average(List<T> footbalStaidumsList, ToDoubleFunction<T> mapper){
//        double sum = 0;
//        for (T entry : footbalStaidumsList) {
//            sum += mapper.applyAsDouble(entry);
//        }
//
//        return sum/ (long) footbalStaidumsList.size();

        return footbalStaidumsList.stream().mapToDouble(mapper).average().getAsDouble();

    }

    public static <T> long countIf(List<T> stadiumList, Predicate<T> predicate){
//        long count = 0;
//
//        for (T match : stadiumList) {
//            if (predicate.test(match)) count +=1;
//        }
//        return count;


        return stadiumList.stream()
                .filter(predicate)
                .count();
    }
}
