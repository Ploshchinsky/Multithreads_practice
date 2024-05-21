package ReentrantLock;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FileManager {
    private static Lock lock = new ReentrantLock();
    private static Path filePath;
    private static FileManager fileManager;

    private FileManager() {
    }

    public static FileManager getInstance(String filePath) {
        if (fileManager == null) {
            fileManager = new FileManager();
            setFilePath(filePath);
        }
        return fileManager;
    }

    private static void setFilePath(String filePath) {
        FileManager.filePath = Path.of(filePath);
    }

    public void write(String[] strings) {
        lock.lock();
        List<String> updatedList;
        try {
            System.out.println(Thread.currentThread().getName() + " start writing..");
            if (Files.readAllLines(filePath).toArray() != strings) {
                updatedList = Files.readAllLines(filePath);
                updatedList.addAll(Arrays.asList(strings));
                Files.write(filePath, updatedList);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        lock.unlock();
        System.out.println(Thread.currentThread().getName() + " end writing..");
    }

    public void write(String string) {
        lock.lock();
        List<String> updatedList;
        try {
            System.out.println(Thread.currentThread().getName() + " start writing..");
            if (!Files.readAllLines(filePath).contains(string)) {
                updatedList = Files.readAllLines(filePath);
                updatedList.add(string);
                Files.write(filePath, updatedList);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        lock.unlock();
        System.out.println(Thread.currentThread().getName() + " end writing..");
    }

    public List<String> read() {
        lock.lock();
        List<String> strings;
        try {
            System.out.println(Thread.currentThread().getName() + " start reading..");
            strings = Files.readAllLines(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        lock.unlock();
        System.out.println(Thread.currentThread().getName() + " end reading..");
        return strings;
    }

    public void delete(String[] stringsToBeDeleted) {
        lock.lock();
        List<String> updatedList;
        try {
            System.out.println(Thread.currentThread().getName() + " start deleting...");
            updatedList = Files.readAllLines(filePath);
            updatedList.removeAll(List.of(stringsToBeDeleted));
            write((String[]) updatedList.toArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        lock.unlock();
        System.out.println(Thread.currentThread().getName() + " end deleting...");
    }
}
