package problem2;

import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicReference;

public class CLHLock {

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
        System.out.println(LocalTime.now() + ": " + " Lock ACQUIRED.");
    }

    public void unlock(){
        QNode qnode = myNode.get();

        System.out.println(LocalTime.now() + ": " + " Lock RELEASED.");
        qnode.locked = false;

        myNode.set(myPred.get());
    }
}
