// SetDemo.java — HashSet، LinkedHashSet، TreeSet، set operations
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class SetDemo {

    public static void main(String[] args) {
        // ---------- HashSet — بدون ترتیب ----------
        Set<String> hash = new HashSet<>();
        hash.add("Ali");
        hash.add("Sara");
        hash.add("Ali");          // duplicate — نادیده گرفته می‌شود
        hash.add("Reza");
        System.out.println("hash=" + hash + " size=" + hash.size());

        // ---------- LinkedHashSet — ترتیب درج ----------
        Set<String> linked = new LinkedHashSet<>();
        linked.add("First");
        linked.add("Second");
        linked.add("Third");
        System.out.println("linked=" + linked);   // [First, Second, Third]

        // ---------- TreeSet — مرتب ----------
        Set<String> tree = new TreeSet<>();
        tree.add("Zebra");
        tree.add("Apple");
        tree.add("Mango");
        System.out.println("tree=" + tree);   // [Apple, Mango, Zebra]

        // ---------- Set operations ----------
        Set<Integer> a = new HashSet<>(Set.of(1, 2, 3, 4));
        Set<Integer> b = new HashSet<>(Set.of(3, 4, 5, 6));

        // union
        Set<Integer> union = new HashSet<>(a);
        union.addAll(b);
        System.out.println("union=" + union);   // [1, 2, 3, 4, 5, 6]

        // intersection
        Set<Integer> inter = new HashSet<>(a);
        inter.retainAll(b);
        System.out.println("intersection=" + inter);   // [3, 4]

        // difference
        Set<Integer> diff = new HashSet<>(a);
        diff.removeAll(b);
        System.out.println("difference=" + diff);   // [1, 2]

        // ---------- Set از String ----------
        Set<String> unique = new HashSet<>();
        String[] words = {"hello", "world", "hello", "java", "world"};
        for (String w : words) unique.add(w);
        System.out.println("unique words=" + unique);   // [hello, world, java]
    }
}
