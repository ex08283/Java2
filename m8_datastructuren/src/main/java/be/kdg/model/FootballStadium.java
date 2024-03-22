package be.kdg.model;

import javax.management.OperationsException;
import java.time.LocalDate;
import java.util.Objects;

public final class FootballStadium implements Comparable<FootballStadium>{
    private final String Name;
    private final String City;
    private final LocalDate Opened;
    private final RoofType roofType;

    private final double Cost;

    private final int Capacity;

    public static int getEqualsCounter() {
        return equalsCounter;
    }

    public static int getCounter() {
        return Counter;
    }

    private static int Counter;
    private static int equalsCounter;

    public String getName() {
        return Name;
    }

    FootballStadium(){
        this("Anonymous","Unknown",LocalDate.now().minusDays(1), RoofType.UNKNOWN,0.0,0);

    }

   FootballStadium(String name, String city, LocalDate opened, RoofType roofType, double cost, int capacity) {
        Name=name;
        City=city;
        Opened= opened;
        this.roofType=roofType;
        this.Cost=cost;
        this.Capacity=capacity;
    }



//    public void setName(String name) {
//        try {
//            if (name == null || name.isEmpty()){
//                throw  new IllegalArgumentException("Name cannot be empty");
//            } else {Name = name;}
//        } catch (IllegalArgumentException e){
//            System.out.println(e.getMessage());
//        }
//    }

    public String getCity() {
        return City;
    }

//    public void setCity(String city) {
//        try {
//            if (city == null || city.isEmpty()){
//                throw  new IllegalArgumentException("City cannot be empty");
//            } else {City = city;}
//        } catch (IllegalArgumentException e){
//            System.out.println(e.getMessage());
//        }
//
//    }

    public LocalDate getOpened() {
        return Opened;
    }

//    public void setOpened(LocalDate opened) {
//        try {
//            if (opened.isAfter(LocalDate.now())){
//                throw  new IllegalArgumentException("Stadium opening date has to be in the past");
//            } else {
//                Opened = opened;
//            }
//        } catch (IllegalArgumentException e){
//            System.out.println(e.getMessage());
//        }
//
//    }

    public RoofType getRoofType() {
        RoofType roofType1 = RoofType.valueOf(this.roofType.name());
        return roofType1;
    }

//    public void setRoofType(RoofType roofType) {
//        this.roofType = roofType;
//    }

    public double getCost() {
        return Cost;
    }

//    public void setCost(double cost) {
//
//        try {
//            if (cost < 0 || cost > 1e12){
//                throw  new IllegalArgumentException("Stadium cost cannot be negative or larger than 1 trillion");
//            } else {
//                this.Cost = cost;
//            }
//        } catch (IllegalArgumentException e){
//            System.out.println(e.getMessage());
//        }
//    }

    public int getCapacity() {
        return Capacity;
    }

//    public void setCapacity(int capacity) {
//        try {
//            if (capacity < 0 || capacity > 1e6){
//                throw  new IllegalArgumentException("Stadium capacity cannot be negative or larger than 1 milltion");
//            } else {
//                this.Capacity = capacity;
//            }
//        } catch (IllegalArgumentException e){
//            System.out.println(e.getMessage());
//        };;
//    }

    @Override
    public int compareTo(FootballStadium otherStadium) {
        Counter++;
        return this.getName().compareTo(otherStadium.Name);
    }

    @Override
    public boolean equals(Object o) {
        equalsCounter++;
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FootballStadium stadium = (FootballStadium) o;

        return Objects.equals(Name, stadium.Name);
    }

    @Override
    public int hashCode() {
        return Name.hashCode();
    }


    @Override
    public String toString() {
        return String.format( "%1$-30s City: %2$-15s  Opened:%3$-15s Roof:%4$-25s Cost:\u20ac %5$-13.2f  Capacity:%6$-15s", this.Name, this.City, this.Opened, roofType, Cost, Capacity);
    }
}
