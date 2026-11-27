// ListDemo.java — List interface، ArrayList، LinkedList، List.of، iteration
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ListDemo {

    public static void main(String[] args) {
        // ---------- List interface + ArrayList ----------
        List<String> names = new ArrayList<>();
        names.add("Ali");
        names.add("Sara");
        names.add(1, "Reza");          // insert در index 1
        names.add("Mina");

        System.out.println("names=" + names);           // [Ali, Reza, Sara, Mina]
        System.out.println("get(0)=" + names.get(0));    // Ali
        System.out.println("size=" + names.size());      // 4
        System.out.println("contains(Ali)=" + names.contains("Ali")); // true
        System.out.println("indexOf(Mina)=" + names.indexOf("Mina")); // 3

        names.set(0, "Ahmed");          // جایگزینی
        System.out.println("after set=" + names);

        names.remove("Sara");           // حذف با مقدار
        System.out.println("after remove=" + names);

        // ---------- LinkedList ----------
        LinkedList<String> linked = new LinkedList<>();
        linked.add("First");
        linked.add("Second");
        linked.addFirst("Zero");        // اضافه به اول
        linked.addLast("Last");         // اضافه به آخر
        System.out.println("linked=" + linked);

        // ---------- List.of (immutable) ----------
        List<String> immutable = List.of("a", "b", "c");
        System.out.println("immutable=" + immutable);
        // immutable.add("d"); // UnsupportedOperationException!

        // ---------- Arrays.asList (fixed-size) ----------
        List<String> fixed = Arrays.asList("x", "y", "z");
        fixed.set(0, "X");              // set مجاز
        // fixed.add("w");              // add مجاز نیست — fixed size
        System.out.println("fixed=" + fixed);

        // ---------- iteration ----------
        System.out.println("\nfor-each:");
        for (String s : names) {
            System.out.print(s + " ");
        }
        System.out.println();

        System.out.println("index loop:");
        for (int i = 0; i < names.size(); i++) {
            System.out.print(names.get(i) + " ");
        }
        System.out.println();

        // ---------- List از اعداد ----------
        List<Integer> numbers = new ArrayList<>();
        numbers.add(10);
        numbers.add(20);
        numbers.add(30);
        int sum = 0;
        for (int n : numbers) sum += n;
        System.out.println("sum=" + sum);   // 60
    }
}
