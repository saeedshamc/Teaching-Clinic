// MethodRefDemo.java — ۴ نوع method reference، lambda vs method ref
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class MethodRefDemo {

    static String greet(String name) {
        return "Hello, " + name;
    }

    static class Person {
        String name;
        int age;
        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
        int getAge() { return age; }
        String getName() { return name; }
        @Override public String toString() { return name + "(" + age + ")"; }
    }

    public static void main(String[] args) {
        // ---------- ۱. Static method reference ----------
        Function<String, String> greetRef = MethodRefDemo::greet;
        System.out.println(greetRef.apply("Ali"));   // Hello, Ali

        // ---------- ۲. Instance method reference (object) ----------
        String text = "hello";
        Supplier<String> upperRef = text::toUpperCase;
        System.out.println(upperRef.get());   // HELLO

        // ---------- ۳. Instance method of arbitrary object ----------
        Function<String, Integer> lengthRef = String::length;
        System.out.println("length: " + lengthRef.apply("hello"));   // 5

        Function<String, Boolean> emptyRef = String::isEmpty;
        System.out.println("isEmpty: " + emptyRef.apply(""));   // true

        // ---------- ۴. Constructor reference ----------
        Supplier<ArrayList<String>> listSupplier = ArrayList::new;
        ArrayList<String> list = listSupplier.get();
        list.add("item");
        System.out.println("List: " + list);

        // ---------- با List ----------
        List<Person> people = new ArrayList<>();
        people.add(new Person("Ali", 20));
        people.add(new Person("Sara", 18));
        people.add(new Person("Reza", 22));

        // lambda vs method reference
        System.out.println("\n=== Lambda ===");
        people.forEach(p -> System.out.println(p.getName()));

        System.out.println("=== Method reference ===");
        people.forEach(System.out::println);   // toString

        // method reference برای sort
        people.sort((a, b) -> a.getAge() - b.getAge());
        System.out.println("Sorted by age: " + people);

        // با Comparator.comparing + method reference
        people.sort(java.util.Comparator.comparingInt(Person::getAge));
        System.out.println("Sorted by age (method ref): " + people);

        people.sort(java.util.Comparator.comparing(Person::getName));
        System.out.println("Sorted by name: " + people);

        // ---------- this:: و super:: ----------
        MethodRefDemo demo = new MethodRefDemo();
        Runnable r1 = demo::instanceMethod;
        r1.run();
    }

    void instanceMethod() {
        System.out.println("Instance method via this::");
    }
}
