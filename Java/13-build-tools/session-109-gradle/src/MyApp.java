// MyApp.java — simple Java class برای Gradle project
// با javac کامپایل می‌شود — بدون dependency
import com.google.common.collect.ImmutableList;

public class MyApp {

    public static void main(String[] args) {
        System.out.println("=== Gradle Project ===");

        // استفاده از Guava dependency
        ImmutableList<String> items = ImmutableList.of("A", "B", "C");
        System.out.println("Items: " + items);

        // Simple calculation
        System.out.println("Result: " + multiply(5, 3));
    }

    public static int multiply(int a, int b) {
        return a * b;
    }
}
