// MyApp.java — simple Java class برای Maven project
// با javac کامپایل می‌شود — بدون dependency
import com.google.common.collect.ImmutableList;

public class MyApp {

    public static void main(String[] args) {
        System.out.println("=== Maven Project ===");

        // استفاده از Guava dependency
        ImmutableList<String> names = ImmutableList.of("Ali", "Sara", "Reza");
        System.out.println("Names: " + names);

        // Simple calculation
        int result = calculate(10, 5);
        System.out.println("10 + 5 = " + result);
    }

    public static int calculate(int a, int b) {
        return a + b;
    }
}
