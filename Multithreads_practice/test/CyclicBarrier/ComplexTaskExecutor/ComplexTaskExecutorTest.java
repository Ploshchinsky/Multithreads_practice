package CyclicBarrier.ComplexTaskExecutor;

import CyclicBarrier.ComplexTaskExecutor.ComplexTaskExecutor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ComplexTaskExecutorTest {
    @Test
    public void taskExecutorTest() {
        int expected = 4_000;
        int actual = 0;

        ComplexTaskExecutor complexTaskExecutor = new ComplexTaskExecutor(4);
        actual = complexTaskExecutor.executeTasks();

        Assertions.assertEquals(expected, actual);
    }
}
