// FullMigration.java — پاسخ پروژهٔ جلسه ۶۰
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class FullMigration {

    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        System.out.println("=== Legacy Code ===");
        legacyDemo();
        System.out.println("\n=== Modern Code ===");
        modernDemo();
        System.out.println("\n=== Migration Complete ===");
    }

    @SuppressWarnings("deprecation")
    static void legacyDemo() {
        // Vector
        Vector<String> vec = new Vector<>();
        vec.add("Ali"); vec.add("Sara"); vec.add("Reza");
        System.out.println("Vector: " + vec);

        // Hashtable
        Hashtable<String, Integer> ht = new Hashtable<>();
        ht.put("Ali", 20); ht.put("Sara", 25);
        System.out.println("Hashtable: " + ht);

        // Enumeration
        Enumeration<String> e = vec.elements();
        System.out.print("Enumeration: ");
        while (e.hasMoreElements()) System.out.print(e.nextElement() + " ");
        System.out.println();

        // Date
        Date date = new Date();
        System.out.println("Date: " + date);

        // StringBuffer
        StringBuffer sb = new StringBuffer("Hello ");
        sb.append("World");
        System.out.println("StringBuffer: " + sb);
    }

    static void modernDemo() {
        // ArrayList
        ArrayList<String> list = new ArrayList<>();
        list.add("Ali"); list.add("Sara"); list.add("Reza");
        System.out.println("ArrayList: " + list);

        // HashMap
        HashMap<String, Integer> map = new HashMap<>();
        map.put("Ali", 20); map.put("Sara", 25);
        System.out.println("HashMap: " + map);

        // Iterator
        Iterator<String> it = list.iterator();
        System.out.print("Iterator: ");
        while (it.hasNext()) System.out.print(it.next() + " ");
        System.out.println();

        // LocalDateTime
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("LocalDateTime: " + now.format(fmt));

        // StringBuilder
        StringBuilder sb = new StringBuilder("Hello ");
        sb.append("World");
        System.out.println("StringBuilder: " + sb);
    }
}
