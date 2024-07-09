package CyclicBarrier.ComplexTaskExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ComplexTaskExecutor {
    private ExecutorService service;
    private CyclicBarrier barrier;
    private int numberOfTasks;
    private List<Future<Integer>> futureList;

    public ComplexTaskExecutor(int numberOfTasks) {
        this.service = Executors.newFixedThreadPool(numberOfTasks);
        this.barrier = new CyclicBarrier(numberOfTasks);
        this.numberOfTasks = numberOfTasks;
        this.futureList = new ArrayList<>();
    }

    public synchronized int executeTasks() {
        for (int i = 0; i < numberOfTasks; i++) {
            futureList.add(service.submit(new ComplexTask(barrier)));
        }

        service.shutdown();
        try {
            service.awaitTermination(5_000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        int res = 0;
        for (Future<Integer> future : futureList) {
            try {
                res += future.get();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
        return res;
    }

}
