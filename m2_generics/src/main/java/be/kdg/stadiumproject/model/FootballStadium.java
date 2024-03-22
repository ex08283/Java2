package be.kdg.stadiumproject.model;

import java.time.LocalDate;

/**
 * The class FootballStadium describes european football stadiums

 * @author Delawar Jalat
 * @version 1.0, 14 okt 2023
 * @see <a href="https://en.wikipedia.org/wiki/List_of_European_stadiums_by_capacity">Link where data was obtained</a>
 */
public class FootballStadium implements Comparable<FootballStadium>{
    private String Name;
    private String City;
    private LocalDate Opened;

    /**
     *
     */
    private RoofType roofType;
    double Cost;
    int Capacity;

    /**
     * Default Constructor with prefilled values for all arguments
     */
    public FootballStadium(){
        this("Example","ExampleCity",LocalDate.now().minusDays(1), RoofType.RETRACTABLE,1e8,100000);

    }

    /**
     * Constructor which will enable create a custom stadium object

     * @param name The name of the city
     * @param city The city of the Stadium
     * @param opened The date on which the stadium was openened
     * @param roofType The roof type
     * @param cost Cost of the stadium to build it
     * @param capacity Capacity of the stadium
     */
    public FootballStadium(String name, String city, LocalDate opened, RoofType roofType, double cost, int capacity) {
        setName(name);
        setCity(city);
        setOpened(opened);
        setRoofType(roofType);
        setCost(cost);
        setCapacity(capacity);
    }


    /**
     * @return The name of the stadium
     */
    public String getName() {
        return Name;
    }

    /**
     * Sets the name of the Stadium, the name of the city cannot be empty
     * @param name Name of the stadium
     * @throws IllegalArgumentException if the argument name is empty string
     */
    public void setName(String name) {
        try {
            if (name == null || name.isEmpty()){
                throw  new IllegalArgumentException("Name cannot be empty");
            } else {Name = name;}
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     *
     * @return Name of the city
     */
    public String getCity() {
        return City;
    }

    /**
     * Sets the name of city
     * @param city Name of the city, exception will be thown if argument is empty
     * @throws IllegalArgumentException if the argument is empty
     */
    public void setCity(String city) {
        try {
            if (city == null || city.isEmpty()){
                throw  new IllegalArgumentException("City cannot be empty");
            } else {City = city;}
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

    }

    /**
     *
     * @return The date the stadium was opened
     */
    public LocalDate getOpened() {
        return Opened;
    }

    /**
     *
     * @param opened Set the date the stadium was opened
     * @throws IllegalArgumentException if the stadium opening date is the future
     */
    public void setOpened(LocalDate opened) {
        try {
            if (opened.isAfter(LocalDate.now())){
                throw  new IllegalArgumentException("Stadium opening date has to be in the past");
            } else {
                Opened = opened;
            }
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

    }

    /**
     *
     * @return The roof type
     * @see RoofType an enum defning the rooftype
     */
    public RoofType getRoofType() {
        return roofType;
    }

    /**
     *
     * @param roofType Rooftype of the stadium
     * @see RoofType an enum defning the rooftype
     */
    public void setRoofType(RoofType roofType) {
        this.roofType = roofType;
    }

    /**
     *
     * @return Cost to build the stadium
     */
    public double getCost() {
        return Cost;
    }

    /**
     *
     * @param cost To build the stadium
     * @throws IllegalArgumentException if the cost of the stadium exceeds 1 trillion euro
     */
    public void setCost(double cost) {

        try {
            if (cost < 0 || cost > 1e12){
                throw  new IllegalArgumentException("Stadium cost cannot be negative or larger than 1 trillion");
            } else {
                this.Cost = cost;
            }
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     *
     * @return The capacity of the stadium
     */
    public int getCapacity() {
        return Capacity;
    }

    /**
     *
     * @param capacity Of the stadium
     * @throws IllegalArgumentException if the capacity of the stadium is negative or greater than 1 million
     */
    public void setCapacity(int capacity) {
        try {
            if (capacity < 0 || capacity > 1e6){
                throw  new IllegalArgumentException("Stadium capacity cannot be negative or larger than 1 milltion");
            } else {
                this.Capacity = capacity;
            }
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        };;
    }

    /**
     *
     * @param otherStadium the object to be compared.
     * @return and integer, zero if equal, positive int if this > that, a negative int if this < that
     */
    @Override
    public int compareTo(FootballStadium otherStadium) {
        return this.getName().compareTo(otherStadium.Name);
    }

    /**
     * Check if two stadium are equal
     * @param o Object to be compared
     * @return True if the stadiums are equal, false if the stadiums are not equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FootballStadium stadium = (FootballStadium) o;

        return Name.equals(stadium.Name);
    }

    /**
     * Hash is set based on name
     * @return an integer
     */
    @Override
    public int hashCode() {
        return Name.hashCode();
    }

    /**
     *
     * @return as string of object created using the paramaters
     */
    @Override
    public String toString() {
        return String.format( "%1$-30s City: %2$-15s  Opened:%3$-15s Roof:%4$-25s Cost:\u20ac %5$-13.1fs  Capacity:%6$-15s", this.Name, this.City, this.Opened, roofType, Cost, Capacity);
    }
}
