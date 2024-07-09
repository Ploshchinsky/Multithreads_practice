package Callable.ComputationTask;

import Callable.ComputationTask.ComputationTask;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ComputationTaskTest {
    @Test
    public void computationTaskTest() throws InterruptedException {
        int expected, actual;
        ExecutorService service = Executors.newFixedThreadPool(10);

        List<Callable<Integer>> callableList = new ArrayList<>();
        List<Future<Integer>> futureList = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            callableList.add(new ComputationTask(i));
        }
        for (Callable<Integer> callable : callableList) {
            futureList.add(service.submit(callable));
        }

        service.shutdown();
        service.awaitTermination(5_000, TimeUnit.MILLISECONDS);

        expected = (1 * 1) + (2 * 2) + (3 * 3) + (4 * 4) + (5 * 5);
        actual = 0;
        for (Future<Integer> future : futureList) {
            try {
                actual += future.get();
            } catch (ExecutionException e) {
                Thread.currentThread().interrupt();
            }
        }
        Assertions.assertEquals(expected, actual);
    }
}
