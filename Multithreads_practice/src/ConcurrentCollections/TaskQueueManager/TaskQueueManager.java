package ConcurrentCollections.TaskQueueManager;

import java.util.concurrent.LinkedBlockingQueue;

public class TaskQueueManager {
    private static LinkedBlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>();

    public static void addTask(String task) {
        try {
            blockingQueue.put(task);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getTask() {
        try {
            return blockingQueue.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void stats() {
        System.out.println("Queue Manager Stats:\nElements in queue - " + blockingQueue.size());
        System.out.println(blockingQueue);
    }
}
