// GenericContainer.java — پاسخ پروژهٔ جلسه ۲۹
import java.util.ArrayList;
import java.util.List;

class Container<T> {
    private List<T> items = new ArrayList<>();

    void add(T item) { items.add(item); }
    T get(int index) { return items.get(index); }
    T remove(int index) { return items.remove(index); }
    int size() { return items.size(); }
    List<T> getAll() { return items; }
}

public class GenericContainer {

    static <T extends Comparable<T>> T max(List<T> list) {
        T max = list.get(0);
        for (T item : list) {
            if (item.compareTo(max) > 0) max = item;
        }
        return max;
    }

    static <T extends Number> double sum(List<T> list) {
        double total = 0;
        for (T n : list) total += n.doubleValue();
        return total;
    }

    public static void main(String[] args) {
        // String container
        Container<String> strContainer = new Container<>();
        strContainer.add("Ali");
        strContainer.add("Sara");
        strContainer.add("Reza");
        System.out.println("Strings: " + strContainer.getAll());
        System.out.println("max: " + max(strContainer.getAll()));

        // Integer container
        Container<Integer> intContainer = new Container<>();
        intContainer.add(10);
        intContainer.add(20);
        intContainer.add(30);
        System.out.println("Integers: " + intContainer.getAll());
        System.out.println("sum: " + sum(intContainer.getAll()));

        // Double container
        Container<Double> dblContainer = new Container<>();
        dblContainer.add(1.5);
        dblContainer.add(2.5);
        dblContainer.add(3.5);
        System.out.println("Doubles: " + dblContainer.getAll());
        System.out.println("sum: " + sum(dblContainer.getAll()));

        // remove
        intContainer.remove(0);
        System.out.println("After remove: " + intContainer.getAll());
    }
}
