// GCAnalyzer.java — پاسخ کامل پروژهٔ جلسه ۱۳۷
// GC analysis demo
public class GCAnalyzer {

    public static void main(String[] args) {
        // Create garbage
        for (int i = 0; i < 1000; i++) {
            createGarbage(i);
        }

        // Force GC
        System.gc();

        // Memory info
        Runtime runtime = Runtime.getRuntime();
        long usedMemory = (runtime.totalMemory() - runtime.freeMemory()) / 1024 / 1024;
        long maxMemory = runtime.maxMemory() / 1024 / 1024;

        System.out.println("Used memory: " + usedMemory + " MB");
        System.out.println("Max memory: " + maxMemory + " MB");
        System.out.println("GC completed — memory reclaimed");
    }

    static void createGarbage(int i) {
        String garbage = "Garbage " + i;
        // garbage becomes unreachable — eligible for GC
    }
}
