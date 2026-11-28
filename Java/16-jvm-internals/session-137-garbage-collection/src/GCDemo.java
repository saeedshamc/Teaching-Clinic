// GCDemo.java — Garbage Collection behavior demo
// قابل کامپایل با javac — بدون dependency
public class GCDemo {

    public static void main(String[] args) {
        // Create objects — eligible for GC
        for (int i = 0; i < 100; i++) {
            createGarbage(i);
        }

        // Force GC (not recommended in production)
        System.gc();

        // Memory info
        Runtime runtime = Runtime.getRuntime();
        long usedMemory = (runtime.totalMemory() - runtime.freeMemory()) / 1024 / 1024;
        System.out.println("Used memory after GC: " + usedMemory + " MB");
    }

    static void createGarbage(int i) {
        // Object becomes unreachable immediately
        String garbage = "Garbage " + i;
        // garbage is eligible for GC here
    }
}
