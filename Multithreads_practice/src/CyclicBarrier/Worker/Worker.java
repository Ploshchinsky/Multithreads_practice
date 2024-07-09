package CyclicBarrier.Worker;

import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;

public class Worker implements Callable<Integer> {
    private CyclicBarrier barrier;

    public Worker(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            Thread.sleep(20);
            sum++;
        }
        barrier.await();
        return sum;
    }
}
