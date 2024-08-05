package ForkJoinPool.FindMax;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class FJPFindMax {
    public static ForkJoinPool forkJoinPool = new ForkJoinPool();
    public static int[] numbers;

    public static void main(String[] args) {
        int size = 100_000_000;
        numbers = numberGenerator(size);

        long start = System.currentTimeMillis();
        System.out.print("Seq result: " + findMax(numbers) + " | ");
        System.out.print(System.currentTimeMillis() - start + " ms\n");

        start = System.currentTimeMillis();
        System.out.print("FJP result: " + forkJoinPool.invoke(new Task(numbers)).intValue() + " | ");
        System.out.print(System.currentTimeMillis() - start + " ms");
    }

    private static int[] numberGenerator(int size) {
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            int randomInt = new Random().nextInt(100_000_000);
            result[i] = randomInt;
        }
        return result;
    }

    private static int findMax(int[] arr) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        return max;
    }
}
