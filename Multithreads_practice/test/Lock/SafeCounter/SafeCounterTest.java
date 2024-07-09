package Lock.SafeCounter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SafeCounterTest {
    @Test
    public void safeCounterTest() {
        int expected = 100;
        int actual;
        SafeCounter safeCounter = new SafeCounter();
        Runnable runnable1 = () -> {
            System.out.println(Thread.currentThread().getName() + " start 85 increments...");
            for (int i = 0; i < 85; i++) {
                try {
                    Thread.sleep(15);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                safeCounter.increment();
            }
            System.out.println(Thread.currentThread().getName() + " end 85 increments!");
        };
        Runnable runnable2 = () -> {
            System.out.println(Thread.currentThread().getName() + " start 15 increments...");
            for (int i = 0; i < 15; i++) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                safeCounter.increment();
            }
            System.out.println(Thread.currentThread().getName() + " end 15 increments!");
        };

        Thread t1 = new Thread(runnable1);
        Thread t2 = new Thread(runnable2);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        actual = safeCounter.getCounter();
        Assertions.assertEquals(expected, actual);
    }
}
