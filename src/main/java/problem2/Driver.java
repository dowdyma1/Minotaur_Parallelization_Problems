package problem2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Driver {
    public static void main(String[] args){
        Random rng = new Random();
        int numGuests = rng.nextInt(30)+5;
        System.out.println("Number of guests: " + numGuests);

        ArrayList<problem2.Guest> guests = new ArrayList<>(numGuests);

        CLHLock lock = new CLHLock();

        for(int i = 0; i < numGuests; i++){
            problem2.Guest curGuest = new problem2.Guest(i, lock);
            guests.add(curGuest);
        }
        Collections.shuffle(guests);

        for(problem2.Guest guest : guests){
            Thread thread = new Thread(guest);
            thread.start();
        }
    }
}
