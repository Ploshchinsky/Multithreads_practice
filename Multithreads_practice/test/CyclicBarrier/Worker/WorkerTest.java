package CyclicBarrier.Worker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class WorkerTest {
    @Test
    public void workerTest() {
        int expeted = 500;
        ExecutorService service = Executors.newFixedThreadPool(5);
        List<Future<Integer>> futureList = new ArrayList<>();

        CyclicBarrier barrier = new CyclicBarrier(5, () -> System.out.println("All Threads Are Done!"));
        Worker worker = new Worker(barrier);

        for (int i = 0; i < 5; i++) {
            futureList.add(service.submit(worker));
        }

        service.shutdown();
        try {
            service.awaitTermination(10_000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        int actual = 0;
        for (Future<Integer> future : futureList) {
            try {
                actual += future.get();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }

        Assertions.assertEquals(expeted, actual);
    }
}
