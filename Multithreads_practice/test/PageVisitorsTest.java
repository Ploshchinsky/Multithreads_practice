import ConcurrentCollections.PageVisitors.PageVisitorCounter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PageVisitorsTest {
    @Test
    public void mainTest() throws InterruptedException {
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

        int expected = pageCount;
        int actual = PageVisitorCounter.getPageCount();
        Assertions.assertEquals(expected, actual, "Assertions Failed!");
    }
}
