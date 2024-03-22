package be.kdg.model;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.time.LocalDate;

@XmlType(propOrder = {"name", "city", "opened", "cost","capacity"})
public class FootballStadium implements Comparable<FootballStadium>{
    private String name;
    private String city;
    private LocalDate opened;
    private RoofType roofType;
    double cost;
    int capacity;

    public String getName() {
        return name;
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
        try {
            if (name == null || name.isEmpty()){
                throw  new IllegalArgumentException("name cannot be empty");
            } else {
                this.name = name;}
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        try {
            if (city == null || city.isEmpty()){
                throw  new IllegalArgumentException("City cannot be empty");
            } else {
                this.city = city;}
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

    }

    public LocalDate getOpened() {
        return opened;
    }

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public void setOpened(LocalDate opened) {
        try {
            if (opened.isAfter(LocalDate.now())){
                throw  new IllegalArgumentException("Stadium opening date has to be in the past");
            } else {
                this.opened = opened;
            }
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

    }

    public RoofType getRoofType() {
        return roofType;
    }

    @XmlAttribute(name="roofType")
    public void setRoofType(RoofType roofType) {
        this.roofType = roofType;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {

        try {
            if (cost < 0 || cost > 1e12){
                throw  new IllegalArgumentException("Stadium cost cannot be negative or larger than 1 trillion");
            } else {
                this.cost = cost;
            }
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        try {
            if (capacity < 0 || capacity > 1e6){
                throw  new IllegalArgumentException("Stadium capacity cannot be negative or larger than 1 milltion");
            } else {
                this.capacity = capacity;
            }
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        };;
    }

    @Override
    public int compareTo(FootballStadium otherStadium) {
        return this.getName().compareTo(otherStadium.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FootballStadium stadium = (FootballStadium) o;

        return name.equals(stadium.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }


    @Override
    public String toString() {
        return String.format( "%1$-30s City: %2$-15s  opened:%3$-15s Roof:%4$-25s Cost:\u20ac %5$-13.1fs  Capacity:%6$-15s", this.name, this.city, this.opened, roofType, cost, capacity);
    }
}
