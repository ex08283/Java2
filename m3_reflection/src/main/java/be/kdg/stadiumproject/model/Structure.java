package be.kdg.stadiumproject.model;

import be.kdg.stadiumproject.reflection.Canrun;

import java.time.LocalDate;

public class Structure implements Comparable<Structure> {
    double Cost;
    private String Name;
    private String City;
    private LocalDate Opened;

    public Structure(){
        this("Example","ExampleCity",LocalDate.now().minusDays(1),1e8);
    }

    public Structure(String name, String city, LocalDate opened, double cost) {
        setName(name);
        setCity(city);
        setOpened(opened);
        setCost(cost);
    }


    public String getName() {
        return Name;
    }


    @Canrun("Emirates Stadium")
    public void setName(String name) {
        try {
            if (name == null || name.isEmpty()) {
                throw new IllegalArgumentException("Name cannot be empty");
            } else {
                Name = name;
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }


    public String getCity() {
        return City;
    }


    @Canrun
    public void setCity(String city) {
        try {
            if (city == null || city.isEmpty()) {
                throw new IllegalArgumentException("City cannot be empty");
            } else {
                City = city;
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }


    public LocalDate getOpened() {
        return Opened;
    }


    public void setOpened(LocalDate opened) {
        try {
            if (opened.isAfter(LocalDate.now())) {
                throw new IllegalArgumentException("Stadium opening date has to be in the past");
            } else {
                Opened = opened;
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }


    public double getCost() {
        return Cost;
    }


    public void setCost(double cost) {

        try {
            if (cost < 0 || cost > 1e12) {
                throw new IllegalArgumentException("Stadium cost cannot be negative or larger than 1 trillion");
            } else {
                this.Cost = cost;
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public int compareTo(Structure otherStructure) {
        return this.getName().compareTo(otherStructure.Name);
    }

    /**
     * Check if two stadium are equal
     *
     * @param o Object to be compared
     * @return True if the stadiums are equal, false if the stadiums are not equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Structure structure = (FootballStadium) o;

        return Name.equals(structure.Name);
    }


    @Override
    public int hashCode() {
        return Name.hashCode();
    }


    @Override
    public String toString() {
        return String.format("%1$-30s City: %2$-15s  Opened:%3$-15s Cost:\u20ac %4$-13.1fs", this.Name, this.City, this.Opened, Cost);
    }
}
