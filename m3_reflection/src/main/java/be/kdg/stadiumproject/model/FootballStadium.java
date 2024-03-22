package be.kdg.stadiumproject.model;

import java.time.LocalDate;


public class FootballStadium extends Structure {

    private RoofType roofType;
    int Capacity;


    public FootballStadium(){
        this("Example","ExampleCity",LocalDate.now().minusDays(1), RoofType.RETRACTABLE,1e8,100000);
    }


    public FootballStadium(String name, String city, LocalDate opened, RoofType roofType, double cost, int capacity) {
        super(name, city, opened, cost);
        setRoofType(roofType);
        setCapacity(capacity);
    }



    public RoofType getRoofType() {
        return roofType;
    }


    public void setRoofType(RoofType roofType) {
        this.roofType = roofType;
    }

    public int getCapacity() {
        return Capacity;
    }


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

    @Override
    public String toString() {

        return super.toString() + String.format( "Roof:%1$-25s Capacity:%2$-15s", roofType,  Capacity);
    }
}
