// HeapDemo.java — Heap usage demo
// قابل کامپایل با javac — بدون dependency
import java.util.*;

public class HeapDemo {

    public static void main(String[] args) {
        // Create objects in heap
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            list.add("String " + i);
        }
        System.out.println("List size: " + list.size());

        // Memory info
        Runtime runtime = Runtime.getRuntime();
        long maxMemory = runtime.maxMemory() / 1024 / 1024;
        long totalMemory = runtime.totalMemory() / 1024 / 1024;
        long freeMemory = runtime.freeMemory() / 1024 / 1024;
        long usedMemory = (totalMemory - freeMemory) / 1024 / 1024;

        System.out.println("Max memory: " + maxMemory + " MB");
        System.out.println("Total memory: " + totalMemory + " MB");
        System.out.println("Used memory: " + usedMemory + " MB");
        System.out.println("Free memory: " + freeMemory + " MB");
    }
}
