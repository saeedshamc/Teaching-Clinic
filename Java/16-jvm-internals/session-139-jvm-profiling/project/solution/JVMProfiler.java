// JVMProfiler.java — پاسخ کامل پروژهٔ جلسه ۱۳۹
// JVM profiling demo
public class JVMProfiler {

    public static void main(String[] args) throws InterruptedException {
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

        // Thread demo
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(() -> {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            });
            thread.setDaemon(true);
            thread.start();
        }

        System.out.println("Threads created — use jstack to dump");
        Thread.sleep(3000);
    }

    static int computeIntensive(int n) {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += i * i;
        }
        return sum;
    }
}
