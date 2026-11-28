// MemoryAnalyzer.java — پاسخ کامل پروژهٔ جلسه ۱۳۶
// JVM memory analysis demo
public class MemoryAnalyzer {

    public static void main(String[] args) {
        // Stack demo
        int stackVar = 42;
        System.out.println("Stack variable: " + stackVar);

        // Heap demo
        String heapString = new String("Hello Heap");
        int[] heapArray = new int[1000];
        System.out.println("Heap string: " + heapString);

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

        // Stack overflow demo
        try {
            infiniteRecursion(0);
        } catch (StackOverflowError e) {
            System.out.println("StackOverflowError caught!");
        }
    }

    static void infiniteRecursion(int depth) {
        if (depth % 10000 == 0) {
            System.out.println("Depth: " + depth);
        }
        infiniteRecursion(depth + 1);
    }
}
