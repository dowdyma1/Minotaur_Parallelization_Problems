package problem2;

import java.time.LocalTime;
import java.util.Random;

public class Guest implements Runnable{

    private final CLHLock lock;
    private final int id;
    private final Random rng;

    public Guest(int id, CLHLock lock){

        this.lock = lock;
        this.id = id;
        rng = new Random();
    }

    @Override
    public void run() {
        int numVisits = rng.nextInt(5)+1;
        for(int i = 0; i < numVisits; i++){
            lock.lock();
            System.out.println(LocalTime.now() + ": " + id + " ENTERED the room.");

            // sleep for 1 - 20 ms
            try {
                Thread.sleep(rng.nextInt(20));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(LocalTime.now() + ": " + id + " LEFT the room.");
            lock.unlock();

        }
    }
}
