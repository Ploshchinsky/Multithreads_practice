package Volatile;

public class Algorithm implements Runnable {
    private int result;

    public int calculating() {
        while (Main.isIsStop()) {
            result = Main.CONST * 2;
        }
        return result;
    }

    @Override
    public void run() {
        System.out.println("Thread [" + Thread.currentThread().getName() + "] is started");
        calculating();
        System.out.println("Thread [" + Thread.currentThread().getName() + "] is finished");
    }
}
