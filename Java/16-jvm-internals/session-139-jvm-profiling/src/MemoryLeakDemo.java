// MemoryLeakDemo.java — Memory leak example
// قابل کامپایل با javac — بدون dependency
import java.util.*;

public class MemoryLeakDemo {

    public static void main(String[] args) {
        // Memory leak — objects never released
        List<byte[]> list = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            byte[] data = new byte[1024 * 1024];   // 1MB
            list.add(data);   // never removed → memory leak

            Runtime runtime = Runtime.getRuntime();
            long usedMemory = (runtime.totalMemory() - runtime.freeMemory()) / 1024 / 1024;
            System.out.println("Iteration " + i + " — Used: " + usedMemory + " MB");

            if (usedMemory > 500) {
                System.out.println("Memory leak detected!");
                list.clear();   // fix leak
                System.gc();
            }
        }
    }
}
