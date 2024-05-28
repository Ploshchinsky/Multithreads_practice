package ConcurrentCollections.PageVisitors;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    private static ExecutorService service = Executors.newFixedThreadPool(3);

    public static void main(String[] args) throws InterruptedException {
        String[] urls = {"www.first.com", "www.second.com",
                "www.third.com", "www.google.com",
                "www.microsoft.com", "www.apple.com"};

        for (int i = 0; i < 10_000; i++) {
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
}
