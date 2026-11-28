// MavenApp.java — پاسخ کامل پروژهٔ جلسه ۱۰۸
import com.google.common.collect.ImmutableList;

public class MavenApp {

    public static void main(String[] args) {
        System.out.println("=== Maven Demo App ===");
        ImmutableList<String> items = ImmutableList.of("A", "B", "C");
        System.out.println("Items: " + items);
        System.out.println("Sum: " + add(10, 20));
    }

    public static int add(int a, int b) { return a + b; }
    public static String greet(String name) { return "Hello, " + name; }
}
