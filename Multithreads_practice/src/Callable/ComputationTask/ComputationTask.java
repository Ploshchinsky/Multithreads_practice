package Callable.ComputationTask;

import java.util.concurrent.Callable;

public class ComputationTask implements Callable<Integer> {
    private int numberToPow;

    public ComputationTask(int numberToPow) {
        this.numberToPow = numberToPow;
    }

    @Override
    public Integer call() throws Exception {
        return (int) Math.pow(numberToPow, 2);
    }
}
