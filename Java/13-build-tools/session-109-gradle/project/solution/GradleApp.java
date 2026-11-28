// GradleApp.java — پاسخ کامل پروژهٔ جلسه ۱۰۹
import com.google.common.collect.ImmutableList;

public class GradleApp {

    public static void main(String[] args) {
        System.out.println("=== Gradle Demo App ===");
        ImmutableList<String> items = ImmutableList.of("X", "Y", "Z");
        System.out.println("Items: " + items);
        System.out.println("Multiply: " + multiply(4, 5));
        System.out.println("Greet: " + greet("World"));
    }

    public static int multiply(int a, int b) { return a * b; }
    public static String greet(String name) { return "Hello, " + name; }
}
