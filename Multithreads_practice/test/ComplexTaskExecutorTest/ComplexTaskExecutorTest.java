package ComplexTaskExecutorTest;

import CyclicBarrier.example1.ComplexTaskExecutor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

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
