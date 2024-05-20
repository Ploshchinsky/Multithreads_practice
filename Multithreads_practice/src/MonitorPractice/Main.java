package MonitorPractice;

public class Main {
    private static int counter = 0;
    public static final Object lock = new Object();

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Main.counter = counter;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Incrementor());
        Thread t2 = new Thread(new Incrementor());

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(counter);
    }
}
