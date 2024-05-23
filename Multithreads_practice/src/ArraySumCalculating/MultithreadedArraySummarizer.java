package ArraySumCalculating;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MultithreadedArraySummarizer {
    private static int threadsCount = 10;
    private static ExecutorService executorService = Executors.newFixedThreadPool(threadsCount);
    private int[] array;
    private int result;

    public MultithreadedArraySummarizer(int[] array) {
        this.array = array;
        calculatingSum(array);
        singleThreadCalc(array);
    }

    private void singleThreadCalc(int[] array) {
        long start = System.currentTimeMillis();
        int res = 0;
        for (int i = 0; i < array.length; i++) {
            res += array[i];
        }
        System.out.println("-------------");
        System.out.println("Single thread calculation\nResult = " + res);
        System.out.println(System.currentTimeMillis() - start + " ms");
    }

    private void calculatingSum(int[] array) {
        long start = System.currentTimeMillis();
        int step = stepCalculating(array.length);
        result = 0;
        for (int i = 0; i < array.length; i += step) {
            int[] temp = Arrays.copyOfRange(array, i, i + step);
            executorService.execute(() -> {
                for (int number : temp) {
                    result += number;
                }
            });
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(1_000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("-------------");
        System.out.println("Multi threads calculation" + "\nResult = " + result);
        System.out.println(System.currentTimeMillis() - start + " ms");
    }

    private int stepCalculating(int arraySize) {
        for (int i = arraySize; i > 1; i--) {
            if (arraySize % 2 == 0) {
                return i;
            }
        }
        return 1;
    }

    public int getResult() {
        return result;
    }
}
