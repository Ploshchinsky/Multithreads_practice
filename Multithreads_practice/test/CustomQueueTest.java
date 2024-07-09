import BlockingQueue.CustomQueue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CustomQueueTest {
    @Test
    public void customQueueTest() {
        ExecutorService service = Executors.newFixedThreadPool(2);
        String[] words = {"apple", "orange", "melon", "cherry", "pine", "watermelon", "lemon"};
        List<String> result = new ArrayList<>();
        CustomQueue<String> queue = new CustomQueue<>(4);

        Runnable producer = () -> {
            for (String s : words) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                queue.enqueue(s);
            }
            System.out.println("=Producer is dead x_x");
        };

        Runnable consumer = () -> {
            while (result.size() < words.length) {
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                result.add(queue.dequeue());
            }
            System.out.println("=Consumer is dead x_x");
        };

        long start = System.currentTimeMillis();
        service.submit(producer);
        service.submit(consumer);
        try {
            service.awaitTermination(2_000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Result: " + result);

        Assertions.assertEquals(words.length, result.size());
    }
}
