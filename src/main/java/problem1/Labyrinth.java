package problem1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Labyrinth {
    protected Lock lock;
    private boolean cupcakePresent;

    public Labyrinth(){
        lock = new ReentrantLock();
        cupcakePresent = true;
    }

    public void acquireLock(){
        lock.lock();
    }

    public void releaseLock(){
        lock.unlock();
    }

    public boolean isCupcakePresent(){
        return cupcakePresent;
    }

    public void requestCupcake(){
        cupcakePresent = true;
    }

    public void eatCupcake(){
        cupcakePresent = false;
    }

}
