package Volatile;

public class Main {
    public static final int CONST = 15;
    public static volatile boolean isStop = true;

    public static boolean isIsStop() {
        return isStop;
    }

    public static void setIsStop(boolean isStop) {
        Main.isStop = isStop;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Algorithm());
        Thread t2 = new Thread(new Algorithm());

        t1.start();
        t2.start();

        for (int i = 0; i < 5; i++) {
            Thread.sleep(1_000);
            System.out.println(i + 1 + " sec");
        }

        setIsStop(false);
    }
}
