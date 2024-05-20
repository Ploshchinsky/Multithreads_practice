package WaitNOtify;

public class Main {
    public static void main(String[] args) {
        Parking parking = new Parking(50);

        CarProducer producer = new CarProducer(parking);
        CarConsumer consumer = new CarConsumer(parking);

        Thread threadProducer = new Thread(producer);
        Thread threadConsumer = new Thread(consumer);
        threadProducer.start();
        threadConsumer.start();

    }
}
