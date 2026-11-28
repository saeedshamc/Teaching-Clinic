// MemoryDemo.java — Heap vs Stack demo
// قابل کامپایل با javac — بدون dependency
public class MemoryDemo {

    public static void main(String[] args) {
        // Stack — primitive types, method calls
        int stackVar = 42;
        System.out.println("Stack variable: " + stackVar);

        // Heap — objects
        String heapString = new String("Hello Heap");
        int[] heapArray = new int[1000];
        System.out.println("Heap string: " + heapString);
        System.out.println("Heap array length: " + heapArray.length);

        // Method calls — stack frames
        calculate(10, 20);
    }

    static void calculate(int a, int b) {
        int result = a + b;   // local variable on stack
        System.out.println("Result: " + result);
    }
}
