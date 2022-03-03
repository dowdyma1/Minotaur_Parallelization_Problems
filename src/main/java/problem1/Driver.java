package problem1;

import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Driver {
    public static boolean driver(){
        Labyrinth labyrinth = new Labyrinth();
        AtomicBoolean visitedFlag = new AtomicBoolean(false);

        Random rng = new Random();
        int numGuests = rng.nextInt(50) + 5;

        ArrayList<Guest> guests = new ArrayList<>(numGuests);

        // Generate guests. One of them is the counter
        guests.add(new Guest(0, labyrinth, visitedFlag, true, numGuests));
        for(int i = 1; i < numGuests; i++){
            Guest curGuest = new Guest(i, labyrinth, visitedFlag, false, numGuests);
            guests.add(curGuest);
        }
        Collections.shuffle(guests);

        boolean allVisited = false;

        System.out.println("Number of guests: " + numGuests);

        Set<Guest> unvisited = new HashSet<>(guests);

        int i = 0;
        while(!allVisited || !visitedFlag.get()){

            // Minotaur picks guest at random to go in labyrinth
            int curGuestIndex = rng.nextInt(numGuests);
            Guest curGuest = guests.get(curGuestIndex);
            unvisited.remove(curGuest);

            // Minotaur knows that all guests have gone in labyrinth at least once
            if(unvisited.isEmpty()){
                allVisited = true;
            }

            // Minotaur tells guest to go into labyrinth
            System.out.println(LocalTime.now() + ": Minotaur tells guest " + curGuest.getID() + " to enter labyrinth.");
            curGuest.run();

            boolean curFlag = visitedFlag.get();
            if(curFlag && allVisited){
                System.out.println("\nNumber of times guests went into labyrinth: " + i);
                System.out.println("Minotaur agrees that all guests went into labyrinth at least once.");
                return true;
            }
            else if(curFlag && !allVisited){
                System.out.println("Failure: All guests did not go into labyrinth at least once, but guests said they did.");
                return false;
            }

            i++;
        }
        if(!visitedFlag.get()){
            System.out.println("Failure: Guests never announced victory.");
        }

        return false;
    }
    public static void main(String[] args){
        driver();
    }
}
