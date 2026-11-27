// GenericsDemo.java — generic class، method، bounded، wildcard
import java.util.ArrayList;
import java.util.List;

public class GenericsDemo {

    // ---------- generic class ----------
    static class Box<T> {
        private T value;
        void set(T value) { this.value = value; }
        T get() { return value; }
    }

    // ---------- generic method ----------
    static <T> T first(List<T> list) {
        return list.isEmpty() ? null : list.get(0);
    }

    // ---------- bounded type ----------
    static <T extends Number> double sum(List<T> list) {
        double total = 0;
        for (T n : list) total += n.doubleValue();
        return total;
    }

    // ---------- wildcard ----------
    static void printList(List<?> list) {
        for (Object obj : list) System.out.println("  " + obj);
    }

    static void addNumber(List<? super Integer> list, int n) {
        list.add(n);
    }

    static void addDouble(List<? super Double> list, double d) {
        list.add(d);
    }

    public static void main(String[] args) {
        // generic class
        Box<String> stringBox = new Box<>();
        stringBox.set("Hello");
        System.out.println("stringBox=" + stringBox.get());

        Box<Integer> intBox = new Box<>();
        intBox.set(42);
        System.out.println("intBox=" + intBox.get());

        // generic method
        List<String> names = List.of("Ali", "Sara", "Reza");
        System.out.println("first name=" + first(names));

        List<Integer> nums = List.of(10, 20, 30);
        System.out.println("first num=" + first(nums));

        // bounded type
        List<Integer> ints = List.of(1, 2, 3);
        List<Double> doubles = List.of(1.5, 2.5, 3.5);
        System.out.println("sum ints=" + sum(ints));       // 6.0
        System.out.println("sum doubles=" + sum(doubles)); // 7.5

        // wildcard — read
        System.out.println("\nprintList:");
        printList(names);
        printList(ints);
        printList(doubles);

        // wildcard — write
        List<Number> numbers = new ArrayList<>();
        addNumber(numbers, 42);   // ? super Integer — Integer یا supertype
        List<Double> dblList = new ArrayList<>();
        addDouble(dblList, 3.14);
        System.out.println("\nnumbers=" + numbers);
        System.out.println("dblList=" + dblList);
    }
}
