package BlockingQueue;

import java.util.LinkedList;

public class CustomQueue<T> {
    private int limit;
    private LinkedList<T> queue;

    public CustomQueue(int limit) {
        this.limit = limit;
        this.queue = new LinkedList<>();
    }

    public synchronized void enqueue(T element) {
        while (queue.size() >= limit) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        queue.add(element);
        notifyAll();
    }

    public synchronized T dequeue() {
        while (queue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        T temp = queue.removeFirst();
        notifyAll();
        return temp;
    }

    public int getSize() {
        return queue.size();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
