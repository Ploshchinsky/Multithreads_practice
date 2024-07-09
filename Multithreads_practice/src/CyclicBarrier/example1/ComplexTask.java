package CyclicBarrier.example1;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

public class ComplexTask implements Callable<Integer> {
    private volatile int counter = 0;
    private final CyclicBarrier barrier;

    public ComplexTask(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    @Override
    public synchronized Integer call() throws Exception {
        Thread.sleep(500);
        barrier.await();
        System.out.println(Thread.currentThread().getName() + " - First stage complete!");
        for (int i = 0; i <= 500; i++) {
            counter = i;
        }
        barrier.await();
        System.out.println(Thread.currentThread().getName() + " - Second stage complete! [" + counter + "]");
        Thread.sleep(500);
        counter *= 2;
        barrier.await();
        System.out.println(Thread.currentThread().getName() + " - Third stage complete! [" + counter + "]");
        return counter;
    }
}
