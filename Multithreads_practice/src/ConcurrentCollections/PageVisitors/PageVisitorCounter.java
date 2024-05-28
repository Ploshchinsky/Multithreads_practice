package ConcurrentCollections.PageVisitors;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PageVisitorCounter {
    private static ConcurrentHashMap<String, Integer> pageCounter = new ConcurrentHashMap<>();

    public static void incrementPageVisit(String url) {
        pageCounter.merge(url, 1, Integer::sum);
    }

    public static void getStats() {
        System.out.println("\nPage stats:");
        System.out.println("All pages - " + pageCounter.values().stream().reduce(Integer::sum).get());
        for (Map.Entry<String, Integer> entry : pageCounter.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }

    public static int getPageCount() {
        return pageCounter.values().stream().reduce(Integer::sum).get();
    }
}
