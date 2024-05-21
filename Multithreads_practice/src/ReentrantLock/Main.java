package ReentrantLock;

import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        FileManager fileManager = FileManager.getInstance("src/ReentrantLock/file.txt");

        Thread t1 = new Thread(() -> {
            List<String> list1 =
                    List.of("1 It's a strings", "1 for a first", "1 list", "1 more", "1 random", "1 words");
            fileManager.write(list1.toArray(String[]::new));
        });
        Thread t2 = new Thread(() -> {
            List<String> list2 =
                    List.of("2 It's a strings", "2 for a second", "2 list", "2 more", "2 random", "2 words");
            for (String s : list2) {
                fileManager.write(s);
            }
        });

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        fileManager.read().forEach(System.out::println);
    }
}
