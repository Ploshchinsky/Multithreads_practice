package ConcurrentBankTest;

import ConcurrnetBank.BankAccount;
import ConcurrnetBank.ConcurrentBank;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class ConcurrentBankTest {
    @Test
    public void concurrentBankTest() {
        BigDecimal expected = BigDecimal.valueOf(1_500);
        BigDecimal actual;
        ConcurrentBank bank = new ConcurrentBank("P-Bank");
        BankAccount acc1 = bank.addAccount("Ivan", BigDecimal.valueOf(1_000));
        BankAccount acc2 = bank.addAccount("Maria", BigDecimal.valueOf(500));

        Runnable r1 = () -> bank.transfer(acc1, acc2, BigDecimal.valueOf(800));
        Runnable r2 = () -> bank.transfer(acc2, acc1, BigDecimal.valueOf(500));

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);

        System.out.println("Total Balance Before: " + bank.getTotalBalance().toString());
        System.out.println("\tAcc 1:" + acc1.getBalance() + "\n\tAcc 2: " + acc2.getBalance());

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        actual = bank.getTotalBalance();
        System.out.println("Total Balance After: " + bank.getTotalBalance().toString());
        System.out.println("\tAcc 1:" + acc1.getBalance() + "\n\tAcc 2: " + acc2.getBalance());
        Assertions.assertEquals(expected, actual);
    }
}
