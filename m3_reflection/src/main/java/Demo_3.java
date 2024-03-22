import be.kdg.stadiumproject.model.FootballStadium;
import be.kdg.stadiumproject.model.FootballStadiums;
import be.kdg.stadiumproject.model.Structure;
import be.kdg.stadiumproject.reflection.ReflectionTools;

public class Demo_3 {
    public static void main(String[] args) {
        ReflectionTools.classAnalysis(Structure.class, FootballStadium.class, FootballStadiums.class);

        try {
            Object annotated = ReflectionTools.runAnnotated(FootballStadium.class);
            System.out.println(annotated);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
