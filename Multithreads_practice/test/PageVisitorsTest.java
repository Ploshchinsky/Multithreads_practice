import ConcurrentCollections.PageVisitors.PageVisitorCounter;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@TestMethodOrder(MethodOrderer.DisplayName.class)
public class PageVisitorsTest {

    @Test
    @DisplayName("Test #1")
    public void test1() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(4);
        String[] urls = {"www.first.com", "www.second.com",
                "www.third.com", "www.google.com",
                "www.microsoft.com", "www.apple.com"};

        int pageCount = 10_000;
        for (int i = 0; i < pageCount; i++) {
            service.submit(() -> {
                int randomInt = new Random().nextInt(urls.length);
                System.out.println(Thread.currentThread().getName() + " added new url - " + urls[randomInt]);
                PageVisitorCounter.incrementPageVisit(urls[randomInt]);
            });
        }
        service.shutdown();
        service.awaitTermination(5_000, TimeUnit.MILLISECONDS);

        PageVisitorCounter.getStats();
    }

    @Test
    @DisplayName("Test #2")
    public void test2() throws InterruptedException {
        int expected = 10_000;
        int actual = PageVisitorCounter.getPageCount();
        Assertions.assertEquals(expected, actual, "Assertions Failed!");
    }
}
