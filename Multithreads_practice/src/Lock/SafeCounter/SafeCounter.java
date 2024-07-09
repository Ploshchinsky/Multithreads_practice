package Lock.SafeCounter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SafeCounter {
    private volatile int counter;
    private Lock lock;

    public SafeCounter() {
        this.counter = 0;
        this.lock = new ReentrantLock();
    }

    public void increment() {
        lock.lock();
        counter++;
        lock.unlock();
    }

    public int getCounter() {
        return counter;
    }
}
