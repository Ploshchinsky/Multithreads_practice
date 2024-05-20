package MonitorPractice;

public class Incrementor implements Runnable {
    @Override
    public void run() {
        synchronized (Main.lock) {
            for (int i = 0; i < 5_000; i++) {
                Main.setCounter(Main.getCounter() + 1);
            }
        }
    }
}
