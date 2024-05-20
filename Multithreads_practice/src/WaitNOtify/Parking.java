package WaitNOtify;

public class Parking {
    private int parkingLimit;
    private int currentCounter;

    public Parking(int parkingLimit) {
        this.parkingLimit = parkingLimit;
        this.currentCounter = 0;
    }

    public synchronized void autoIn() {
        while (currentCounter >= parkingLimit) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        currentCounter++;
        System.out.println("New car. Current counter: " + currentCounter);
        notify();
    }

    public synchronized void autoOut() {
        while (currentCounter < 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        currentCounter--;
        System.out.println("Car out. Current counter: " + currentCounter);
        notify();
    }
}
