package problem1;

import java.util.concurrent.atomic.AtomicBoolean;

public class Guest implements Runnable {
    private final boolean isCounter;
    private final Labyrinth labyrinth;
    private int guestCounter = 0;
    private final int numGuests;
    private boolean hasEatenCupcake = false;
    private final AtomicBoolean visitedFlag;

    public Guest(Labyrinth labyrinth, AtomicBoolean visitedFlag, boolean isCounter, int numGuests){
        this.isCounter = isCounter;
        this.labyrinth = labyrinth;
        this.numGuests = numGuests;
        this.visitedFlag = visitedFlag;
    }

    public void enterLabyrinth(){
        if(isCounter){
            if(!labyrinth.isCupcakePresent()){
                guestCounter++;
                labyrinth.requestCupcake();
            }
            if(guestCounter >= numGuests-1){
                System.out.println("Counter is announcing victory");
                visitedFlag.set(true);
            }
        }
        else{
            if(labyrinth.isCupcakePresent() && !hasEatenCupcake){
                labyrinth.eatCupcake();
                hasEatenCupcake = true;
            }
        }
    }

    @Override
    public void run() {
        labyrinth.acquireLock();
        enterLabyrinth();
        labyrinth.releaseLock();
    }
}
