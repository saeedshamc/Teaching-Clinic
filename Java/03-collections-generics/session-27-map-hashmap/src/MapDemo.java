// MapDemo.java — HashMap، iteration، LinkedHashMap، TreeMap، getOrDefault/merge
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class MapDemo {

    public static void main(String[] args) {
        // ---------- HashMap ----------
        Map<String, Integer> ages = new HashMap<>();
        ages.put("Ali", 20);
        ages.put("Sara", 22);
        ages.put("Reza", 25);
        ages.put("Ali", 30);          // key تکراری — value به‌روز می‌شود

        System.out.println("ages=" + ages);              // {Sara=22, Reza=25, Ali=30}
        System.out.println("get(Ali)=" + ages.get("Ali")); // 30
        System.out.println("get(NotExist)=" + ages.get("NotExist")); // null
        System.out.println("size=" + ages.size());        // 3
        System.out.println("containsKey(Sara)=" + ages.containsKey("Sara")); // true
        System.out.println("containsValue(22)=" + ages.containsValue(22));   // true

        ages.remove("Reza");
        System.out.println("after remove=" + ages);

        // ---------- iteration ----------
        System.out.println("\nentrySet:");
        for (Map.Entry<String, Integer> entry : ages.entrySet()) {
            System.out.println("  " + entry.getKey() + " = " + entry.getValue());
        }

        System.out.println("keySet:");
        for (String key : ages.keySet()) {
            System.out.println("  " + key);
        }

        System.out.println("values:");
        for (Integer val : ages.values()) {
            System.out.println("  " + val);
        }

        // ---------- LinkedHashMap — ترتیب درج ----------
        Map<String, Integer> linked = new LinkedHashMap<>();
        linked.put("First", 1);
        linked.put("Second", 2);
        linked.put("Third", 3);
        System.out.println("\nlinked=" + linked);   // [First=1, Second=2, Third=3]

        // ---------- TreeMap — مرتب بر اساس key ----------
        Map<String, Integer> tree = new TreeMap<>();
        tree.put("Zebra", 1);
        tree.put("Apple", 2);
        tree.put("Mango", 3);
        System.out.println("tree=" + tree);   // [Apple=2, Mango=3, Zebra=1]

        // ---------- getOrDefault و merge ----------
        Map<String, Integer> counts = new HashMap<>();
        counts.put("java", counts.getOrDefault("java", 0) + 1);
        counts.put("java", counts.getOrDefault("java", 0) + 1);
        counts.put("python", counts.getOrDefault("python", 0) + 1);
        System.out.println("\ncounts=" + counts);   // {java=2, python=1}

        counts.merge("java", 1, Integer::sum);   // java += 1 → 3
        System.out.println("after merge=" + counts);

        // ---------- Map از Integer به String ----------
        Map<Integer, String> idToName = new HashMap<>();
        idToName.put(1, "Ali");
        idToName.put(2, "Sara");
        System.out.println("idToName=" + idToName);
    }
}
