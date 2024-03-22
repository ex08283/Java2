package be.kdg.model;

import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FootballStadium implements Comparable<FootballStadium>{
    private static final Logger logger = Logger.getLogger("be.kdg.model.FootballStadium");
    private String Name;
    private String City;
    private LocalDate Opened;
    private RoofType roofType;
    double Cost;
    int Capacity;

    public String getName() {
        return Name;
    }

    public FootballStadium(){
        this("Example","ExampleCity",LocalDate.now().minusDays(1), RoofType.RETRACTABLE,1e8,100000);

    }

    public FootballStadium(String name, String city, LocalDate opened, RoofType roofType, double cost, int capacity) {
        setName(name);
        setCity(city);
        setOpened(opened);
        setRoofType(roofType);
        setCost(cost);
        setCapacity(capacity);
    }



    public void setName(String name) {
//        try {
            if (name == null || name.isEmpty()){
                logger.log(Level.SEVERE, String.format("Stadium name is %s, cannnot be empty", "\"empty or null\""));
//                throw  new IllegalArgumentException("Name cannot be empty");
            } else {Name = name;}
//        } catch (IllegalArgumentException e){
//            System.out.println(e.getMessage());
//        }
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
//        try {
            if (city == null || city.isEmpty()){
                logger.log(Level.SEVERE, String.format("City of stadium of %s is configured as %s, it cannot be null or empty", getName(), city.toString()));
                //throw  new IllegalArgumentException("City cannot be empty");
            } else {City = city;}
//        } catch (IllegalArgumentException e){
//            System.out.println(e.getMessage());
//        }

    }

    public LocalDate getOpened() {
        return Opened;
    }

    public void setOpened(LocalDate opened) {
//        try {
            if (opened.isAfter(LocalDate.now())){
                System.out.println(LocalDate.now());
                logger.log(Level.SEVERE, String.format("Stadium opening date is configured as %s for %s, cannot in the future", opened.toString(), getName()));

//                throw  new IllegalArgumentException("Stadium opening date has to be in the past");
            } else {
                Opened = opened;
            }
//        } catch (IllegalArgumentException e){
//            System.out.println(e.getMessage());
//        }

    }

    public RoofType getRoofType() {
        return roofType;
    }

    public void setRoofType(RoofType roofType) {
        this.roofType = roofType;
    }

    public double getCost() {
        return Cost;
    }

    public void setCost(double cost) {

//        try {
            if (cost < 0 || cost > 1e12){
                String errorMessage = "Stadium cost " + getCost() + " cannot be negative or larger than 1 million for stadium with name: " + (getName() == null ? "null": "empty string");
                logger.log(Level.SEVERE, errorMessage);
//                throw  new IllegalArgumentException("Stadium cost cannot be negative or larger than 1 trillion");
            } else {
                this.Cost = cost;
            }
//        } catch (IllegalArgumentException e){
//            System.out.println(e.getMessage());
//        }
    }

    public int getCapacity() {
        return Capacity;
    }

    public void setCapacity(int capacity) {
//        try {
            if (capacity < 0 || capacity > 1e6){
                String error = String.format("Stadium capacity %d of %s, annot be negative or larger than 1 million\n", capacity, getName());
                System.out.println(error);
                logger.log(Level.SEVERE, error);
                //throw  new IllegalArgumentException("Stadium capacity cannot be negative or larger than 1 milltion");
            } else {
                this.Capacity = capacity;
            }
//        } catch (IllegalArgumentException e){
//            System.out.println(e.getMessage());
//        };;
    }

    @Override
    public int compareTo(FootballStadium otherStadium) {
        return this.getName().compareTo(otherStadium.Name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FootballStadium stadium = (FootballStadium) o;

        return Name.equals(stadium.Name);
    }

    @Override
    public int hashCode() {
        return Name.hashCode();
    }


    @Override
    public String toString() {
        return String.format( "%1$-30s City: %2$-15s  Opened:%3$-15s Roof:%4$-25s Cost:\u20ac %5$-13.1fs  Capacity:%6$-15s", this.Name, this.City, this.Opened, roofType, Cost, Capacity);
    }
}
