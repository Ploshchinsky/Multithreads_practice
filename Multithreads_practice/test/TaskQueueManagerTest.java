import ConcurrentCollections.TaskQueueManager.TaskQueueManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskQueueManagerTest {
    @Test
    public void test1() {
        ExecutorService producer = Executors.newFixedThreadPool(3);
        ExecutorService consumer = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 10; i++) {
            final int number = i;
            producer.submit(() -> {
                try {
                    Thread.sleep(500);
                    TaskQueueManager.addTask("Task #" + number);
                    System.out.println(Thread.currentThread().getName() + " added new task!");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        for (int i = 0; i < 10; i++) {
            consumer.submit(() -> {
                try {
                    Thread.sleep(1500);
                    String task = TaskQueueManager.getTask();
                    System.out.println(Thread.currentThread().getName() + " get new task - " + task);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        try {
            Thread.sleep(1000);
            System.out.println("----------");
            TaskQueueManager.stats();
            System.out.println("----------");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        producer.shutdown();
        consumer.shutdown();
    }
}
