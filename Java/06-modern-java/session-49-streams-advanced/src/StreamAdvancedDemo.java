// StreamAdvancedDemo.java — Collectors، groupingBy، reduce، flatMap، toMap
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamAdvancedDemo {

    static class Person {
        String name;
        int age;
        String department;
        Person(String name, int age, String department) {
            this.name = name; this.age = age; this.department = department;
        }
        String getName() { return name; }
        int getAge() { return age; }
        String getDepartment() { return department; }
        @Override public String toString() { return name + "(" + age + "," + department + ")"; }
    }

    public static void main(String[] args) {
        List<Person> people = List.of(
            new Person("Ali", 20, "IT"),
            new Person("Sara", 25, "HR"),
            new Person("Reza", 30, "IT"),
            new Person("Mina", 22, "HR"),
            new Person("Ahmed", 35, "Finance")
        );

        // ---------- joining ----------
        String names = people.stream()
            .map(Person::getName)
            .collect(Collectors.joining(", "));
        System.out.println("Joined: " + names);

        // ---------- groupingBy ----------
        Map<String, List<Person>> byDept = people.stream()
            .collect(Collectors.groupingBy(Person::getDepartment));
        System.out.println("\nBy department: " + byDept.keySet());
        byDept.forEach((dept, list) -> System.out.println(dept + ": " + list.size() + " people"));

        // ---------- groupingBy با downstream ----------
        Map<String, Double> avgAgeByDept = people.stream()
            .collect(Collectors.groupingBy(
                Person::getDepartment,
                Collectors.averagingInt(Person::getAge)
            ));
        System.out.println("\nAvg age by dept: " + avgAgeByDept);

        // ---------- partitioningBy ----------
        Map<Boolean, List<Person>> adults = people.stream()
            .collect(Collectors.partitioningBy(p -> p.getAge() >= 25));
        System.out.println("\nAdults (>=25): " + adults.get(true).size());
        System.out.println("Young (<25): " + adults.get(false).size());

        // ---------- reduce ----------
        int totalAge = people.stream()
            .mapToInt(Person::getAge)
            .reduce(0, Integer::sum);
        System.out.println("\nTotal age: " + totalAge);

        // ---------- flatMap ----------
        List<List<Integer>> nested = List.of(
            List.of(1, 2), List.of(3, 4), List.of(5, 6)
        );
        List<Integer> flat = nested.stream()
            .flatMap(List::stream)
            .collect(Collectors.toList());
        System.out.println("Flat: " + flat);

        // flatMap برای string → words
        List<String> sentences = List.of("Hello world", "Java is fun");
        List<String> words = sentences.stream()
            .flatMap(s -> Arrays.stream(s.split(" ")))
            .collect(Collectors.toList());
        System.out.println("Words: " + words);

        // ---------- toMap ----------
        Map<String, Integer> nameToAge = people.stream()
            .collect(Collectors.toMap(
                Person::getName,
                Person::getAge,
                (a, b) -> a + b   // merge برای duplicate
            ));
        System.out.println("\nName to age: " + nameToAge);

        // ---------- summarizingInt ----------
        var stats = people.stream()
            .mapToInt(Person::getAge)
            .summaryStatistics();
        System.out.println("\nAge stats: count=" + stats.getCount() +
                          " sum=" + stats.getSum() +
                          " avg=" + stats.getAverage() +
                          " max=" + stats.getMax());
    }
}
