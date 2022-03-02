package problem2;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.DefaultConfiguration;

import java.util.Random;

public class Guest implements Runnable{
    private static Logger log = (Logger) LogManager.getLogger(Guest.class);

    private final CLHLock lock;
    private final int id;
    private final Random rng;

    public Guest(int id, CLHLock lock){
        Configurator.initialize(new DefaultConfiguration());
        Configurator.setRootLevel(Level.INFO);

        this.lock = lock;
        this.id = id;
        rng = new Random();
    }

    @Override
    public void run() {
        int numVisits = rng.nextInt(5)+1;
        for(int i = 0; i < numVisits; i++){
            lock.lock();
            log.info(id + " is in the room.");

            // sleep for 1 - 20 ms
            try {
                Thread.sleep(rng.nextInt(20));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            log.info(id + " is leaving room.");
            lock.unlock();

        }
    }
}
