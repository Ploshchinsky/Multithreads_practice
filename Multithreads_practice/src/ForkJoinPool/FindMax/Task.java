package ForkJoinPool.FindMax;

import java.util.Arrays;
import java.util.concurrent.RecursiveTask;

class Task extends RecursiveTask<Integer> {
    private static int THRESHOLD = 0;
    private int[] numbers;
    private int l, r, mid;


    public Task(int[] numbers) {
        this.numbers = numbers;
        this.l = 0;
        this.r = numbers.length;

        if (THRESHOLD == 0) {
            THRESHOLD = numbers.length / Runtime.getRuntime().availableProcessors() * 50;
        }
    }

    @Override
    protected Integer compute() {
        if (numbers.length <= THRESHOLD) {
            return Arrays.stream(numbers).max().orElse(0);
        }

        mid = (r - l) >> 2;

        Task left = new Task(Arrays.copyOfRange(numbers, l, mid));
        Task right = new Task(Arrays.copyOfRange(numbers, mid, r));

        invokeAll(left, right);
        return Math.max(right.join(), left.join());
    }
}