package problem2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.atomic.AtomicReference;

public class CLHLock {
    private static Logger log = LogManager.getLogger(CLHLock.class);

    class QNode {
        volatile boolean locked = false;
    }

    public AtomicReference<QNode> tail;
    public ThreadLocal<QNode> myPred;
    public ThreadLocal<QNode> myNode;

    public CLHLock(){
        tail = new AtomicReference<>(new QNode());

        myNode = ThreadLocal.withInitial(() -> new QNode());

        myPred = ThreadLocal.withInitial(() -> null);
    }

    public void lock(){
        QNode qnode = myNode.get();
        qnode.locked = true;

        QNode pred = tail.getAndSet(qnode);
        myPred.set(pred);

        while(pred.locked){}
        log.info("Lock acquired");
    }

    public void unlock(){
        QNode qnode = myNode.get();

        log.info("Lock released");
        qnode.locked = false;

        myNode.set(myPred.get());
    }
}
