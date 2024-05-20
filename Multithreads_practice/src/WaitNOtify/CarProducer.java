package WaitNOtify;

public class CarProducer implements Runnable {
    private Parking parking;

    public CarProducer(Parking parking) {
        this.parking = parking;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            parking.autoIn();
        }
    }
}
