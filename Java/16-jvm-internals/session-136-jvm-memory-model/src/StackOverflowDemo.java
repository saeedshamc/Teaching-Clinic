// StackOverflowDemo.java — Stack overflow example
// قابل کامپایل با javac — بدون dependency
public class StackOverflowDemo {

    public static void main(String[] args) {
        try {
            infiniteRecursion(0);
        } catch (StackOverflowError e) {
            System.out.println("Caught StackOverflowError!");
            System.out.println("Message: " + e.getMessage());
        }
    }

    static void infiniteRecursion(int depth) {
        if (depth % 1000 == 0) {
            System.out.println("Depth: " + depth);
        }
        infiniteRecursion(depth + 1);   // no base case → stack overflow
    }
}
