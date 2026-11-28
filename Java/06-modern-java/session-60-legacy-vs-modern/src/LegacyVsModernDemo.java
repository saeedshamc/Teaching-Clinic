// LegacyVsModernDemo.java — مقایسهٔ کامل legacy و modern
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class LegacyVsModernDemo {

    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        System.out.println("=== Legacy vs Modern Comparison ===\n");

        // ---------- Collections ----------
        System.out.println("1. Collections:");
        Vector<String> oldVec = new Vector<>();
        oldVec.add("Ali"); oldVec.add("Sara");
        ArrayList<String> newList = new ArrayList<>(oldVec);
        System.out.println("  Vector: " + oldVec + " (synchronized — slower)");
        System.out.println("  ArrayList: " + newList + " (faster — better)");

        Hashtable<String, Integer> oldHt = new Hashtable<>();
        oldHt.put("Ali", 20);
        HashMap<String, Integer> newMap = new HashMap<>(oldHt);
        System.out.println("  Hashtable: " + oldHt + " (synchronized — slower)");
        System.out.println("  HashMap: " + newMap + " (faster — better)");

        // ---------- Iteration ----------
        System.out.println("\n2. Iteration:");
        Enumeration<String> oldEnum = oldVec.elements();
        Iterator<String> newIter = newList.iterator();
        System.out.println("  Enumeration: legacy — no remove, forward only");
        System.out.println("  Iterator: modern — remove, ListIterator for bidirectional");

        // ---------- Date/Time ----------
        System.out.println("\n3. Date/Time:");
        Date oldDate = new Date();
        LocalDateTime newDate = LocalDateTime.now();
        System.out.println("  Date: " + oldDate + " (mutable, deprecated)");
        System.out.println("  LocalDateTime: " + newDate + " (immutable, thread-safe)");

        // ---------- String ----------
        System.out.println("\n4. String:");
        StringBuffer oldSb = new StringBuffer("Hello");
        StringBuilder newSb = new StringBuilder("Hello");
        System.out.println("  StringBuffer: " + oldSb + " (synchronized — slower)");
        System.out.println("  StringBuilder: " + newSb + " (faster — better)");

        // ---------- GUI ----------
        System.out.println("\n5. GUI:");
        System.out.println("  AWT: native components — platform-specific");
        System.out.println("  Swing: pure Java — consistent");
        System.out.println("  JavaFX: modern — Scene Builder, CSS-like");
        System.out.println("  Web (JSF/Spring): enterprise — browser-based");

        // ---------- Web Services ----------
        System.out.println("\n6. Web Services:");
        System.out.println("  RMI: complex, Java-only, firewall-unfriendly");
        System.out.println("  REST: simple, HTTP, language-agnostic");
        System.out.println("  gRPC: high-performance, Protocol Buffers");

        // ---------- Summary ----------
        System.out.println("\n=== Summary ===");
        System.out.println("For new code: Always use modern APIs");
        System.out.println("For legacy code: Understand and maintain");
        System.out.println("Migration: Incremental — not all at once");
    }
}
