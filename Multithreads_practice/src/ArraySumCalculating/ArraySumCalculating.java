package ArraySumCalculating;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ArraySumCalculating {
    public static void main(String[] args) {
        int size = 790_720;
        int[] array = arrayGenerator(size);
        MultithreadedArraySummarizer summarizer = new MultithreadedArraySummarizer(array);
    }

    private static int[] arrayGenerator(int size) {
        int randomInt;
        int[] resultInt = new int[size];
        for (int i = 0; i < size; i++) {
            randomInt = new Random().nextInt(1_000) + 1;
            resultInt[i] = randomInt;
        }
        return resultInt;
    }
}
