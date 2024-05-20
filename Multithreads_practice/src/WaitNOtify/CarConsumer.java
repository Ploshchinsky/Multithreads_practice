package WaitNOtify;

public class CarConsumer implements Runnable {
    private Parking parking;

    public CarConsumer(Parking parking) {
        this.parking = parking;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            parking.autoOut();
        }
    }
}
