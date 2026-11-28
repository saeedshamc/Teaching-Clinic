// MemoryPressure.java — Memory pressure example
// قابل کامپایل با javac — بدون dependency
import java.util.*;

public class MemoryPressure {

    public static void main(String[] args) {
        List<byte[]> list = new ArrayList<>();

        // Create memory pressure
        for (int i = 0; i < 100; i++) {
            byte[] data = new byte[1024 * 1024];   // 1MB each
            Arrays.fill(data, (byte) i);
            list.add(data);

            Runtime runtime = Runtime.getRuntime();
            long usedMemory = (runtime.totalMemory() - runtime.freeMemory()) / 1024 / 1024;
            System.out.println("Iteration " + i + " — Used: " + usedMemory + " MB");

            if (usedMemory > 500) {
                System.out.println("Memory pressure detected — clearing list");
                list.clear();   // make objects unreachable
                System.gc();
            }
        }
    }
}
