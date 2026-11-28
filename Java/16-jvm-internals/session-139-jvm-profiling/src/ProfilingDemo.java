// ProfilingDemo.java — Profiling demo
// قابل کامپایل با javac — بدون dependency
public class ProfilingDemo {

    public static void main(String[] args) {
        // CPU-intensive task
        long start = System.currentTimeMillis();
        int result = computeIntensive(1000000);
        long duration = System.currentTimeMillis() - start;

        System.out.println("Result: " + result);
        System.out.println("Duration: " + duration + " ms");

        // Memory info
        Runtime runtime = Runtime.getRuntime();
        long usedMemory = (runtime.totalMemory() - runtime.freeMemory()) / 1024 / 1024;
        System.out.println("Used memory: " + usedMemory + " MB");
    }

    static int computeIntensive(int n) {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += i * i;
        }
        return sum;
    }
}
