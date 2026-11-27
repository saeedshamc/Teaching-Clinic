// TagSystem.java — پاسخ پروژهٔ جلسه ۲۶
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TagSystem {

    static Set<String> tags = new HashSet<>();
    static Map<String, Integer> tagCounts = new HashMap<>();

    static void addTag(String tag) {
        tags.add(tag);
        tagCounts.put(tag, tagCounts.getOrDefault(tag, 0) + 1);
    }

    static void removeTag(String tag) {
        tags.remove(tag);
        tagCounts.remove(tag);
    }

    static int getTagCount(String tag) {
        return tagCounts.getOrDefault(tag, 0);
    }

    static void displayAll() {
        System.out.println("\n=== Tags ===");
        System.out.println("Unique tags: " + tags);
        System.out.println("Tag counts:");
        for (Map.Entry<String, Integer> entry : tagCounts.entrySet()) {
            System.out.println("  " + entry.getKey() + " = " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        addTag("java");
        addTag("python");
        addTag("web");
        addTag("java");   // duplicate — count افزایش می‌یابد
        addTag("java");
        addTag("python");

        displayAll();

        removeTag("python");
        System.out.println("\n=== After removing python ===");
        displayAll();
    }
}
