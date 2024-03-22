import be.kdg.stadiumproject.data.Data;
import be.kdg.stadiumproject.generics.PriorityQueue;

import java.util.Random;

public class Demo_2 {
    public static void main(String[] args) {
        //2.6
        var myQueue = new PriorityQueue<>();
        myQueue.enqueue("Tokio", 2);
        myQueue.enqueue("Denver", 5);
        myQueue.enqueue("Rio", 2);
        myQueue.enqueue("Oslo", 3);
        System.out.println("Overzicht van de PriorityQueue:");
        System.out.println(myQueue.toString());
        System.out.println("aantal: " + myQueue.getSize());
        System.out.println("positie van Tokio: " + myQueue.search("Tokio"));
        System.out.println("positie van Nairobi: " + myQueue.search("Nairobi"));
        for(int i = 0; i < 4; i++) {
            System.out.println("Dequeue: " + myQueue.dequeue());
        }
        System.out.println("Size na dequeue: " + myQueue.getSize());

        //2.7
        var footballStadiumPriorityQueue = new PriorityQueue<>();
        var random = new Random();
        var footballStadiums = Data.getData();
        for (var footballStadium : Data.getData()) {
            footballStadiumPriorityQueue.enqueue(footballStadium, random.nextInt(5) + 1);
        }

        System.out.println("Overzicht van de PriorityQueue:");
        System.out.println(footballStadiumPriorityQueue.toString());
        System.out.println("aantal stadiums: " + footballStadiumPriorityQueue.getSize());
        System.out.println("positie van " + footballStadiums.get(0).getName() + ": " + footballStadiumPriorityQueue.search(footballStadiums.get(0)));
        System.out.println("positie van " + footballStadiums.get(3).getName() + ": " + footballStadiumPriorityQueue.search(footballStadiums.get(3)));
        for(int i = 0; i < 4; i++) {

            System.out.println("Dequeue: " + footballStadiumPriorityQueue.dequeue());
        }
        System.out.println("Size na dequeue: " + footballStadiumPriorityQueue.getSize());

    }
}
