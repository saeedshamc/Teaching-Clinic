// VectorHashtableDemo.java — Vector، Hashtable، Enumeration، مقایسه با ArrayList/HashMap
import java.util.*;

public class VectorHashtableDemo {

    public static void main(String[] args) {
        // ---------- Vector ----------
        System.out.println("=== Vector ===");
        Vector<String> vec = new Vector<>();
        vec.add("Ali");
        vec.add("Sara");
        vec.add("Reza");

        System.out.println("Size: " + vec.size());
        System.out.println("First: " + vec.get(0));
        System.out.println("Contains 'Sara': " + vec.contains("Sara"));

        // Enumeration — legacy iteration
        System.out.println("Enumeration:");
        Enumeration<String> e = vec.elements();
        while (e.hasMoreElements()) {
            System.out.println("  " + e.nextElement());
        }

        // Iterator — modern iteration روی Vector
        System.out.println("Iterator:");
        Iterator<String> it = vec.iterator();
        while (it.hasNext()) {
            System.out.println("  " + it.next());
        }

        // ---------- Hashtable ----------
        System.out.println("\n=== Hashtable ===");
        Hashtable<String, Integer> table = new Hashtable<>();
        table.put("Ali", 20);
        table.put("Sara", 25);
        table.put("Reza", 30);

        System.out.println("Ali's age: " + table.get("Ali"));
        System.out.println("Size: " + table.size());

        // Enumeration برای keys
        System.out.println("Keys:");
        Enumeration<String> keys = table.keys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            System.out.println("  " + key + " = " + table.get(key));
        }

        // Enumeration برای values
        System.out.println("Values:");
        Enumeration<Integer> values = table.elements();
        while (values.hasMoreElements()) {
            System.out.println("  " + values.nextElement());
        }

        // ---------- مقایسه با ArrayList/HashMap ----------
        System.out.println("\n=== Comparison ===");
        ArrayList<String> list = new ArrayList<>(vec);
        HashMap<String, Integer> map = new HashMap<>(table);
        System.out.println("ArrayList: " + list);
        System.out.println("HashMap: " + map);

        // ---------- synchronized demo ----------
        System.out.println("\n=== Synchronized ===");
        Vector<Integer> syncVec = new Vector<>();
        // Vector متدها synchronized هستند — thread-safe
        // ولی برای single-thread کندتر از ArrayList
    }
}
