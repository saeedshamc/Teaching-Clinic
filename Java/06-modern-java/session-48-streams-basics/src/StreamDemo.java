// StreamDemo.java — filter/map/collect، intermediate/terminal، عملیات رایج
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StreamDemo {

    public static void main(String[] args) {
        List<String> names = List.of("Ali", "Sara", "Reza", "Mina", "Ahmed");
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // ---------- filter ----------
        List<String> filtered = names.stream()
            .filter(name -> name.startsWith("A"))
            .collect(Collectors.toList());
        System.out.println("Filtered (starts with A): " + filtered);   // [Ali, Ahmed]

        // ---------- map ----------
        List<String> upper = names.stream()
            .map(String::toUpperCase)
            .collect(Collectors.toList());
        System.out.println("Uppercase: " + upper);

        // ---------- forEach ----------
        System.out.println("\nforEach:");
        names.stream()
            .filter(name -> name.length() > 3)
            .forEach(System.out::println);

        // ---------- count ----------
        long count = names.stream().count();
        System.out.println("\nCount: " + count);   // 5

        // ---------- findFirst ----------
        Optional<String> first = names.stream().findFirst();
        System.out.println("First: " + first.orElse("none"));

        // ---------- anyMatch / allMatch ----------
        boolean hasA = names.stream().anyMatch(s -> s.startsWith("A"));
        boolean allLong = names.stream().allMatch(s -> s.length() > 2);
        System.out.println("hasA=" + hasA + " allLong=" + allLong);

        // ---------- min / max ----------
        Optional<String> longest = names.stream()
            .max(Comparator.comparingInt(String::length));
        System.out.println("Longest: " + longest.orElse("none"));

        // ---------- sorted ----------
        List<String> sorted = names.stream()
            .sorted()
            .collect(Collectors.toList());
        System.out.println("Sorted: " + sorted);

        // ---------- distinct ----------
        List<String> withDupes = Arrays.asList("Ali", "Sara", "Ali", "Reza", "Sara");
        List<String> unique = withDupes.stream()
            .distinct()
            .collect(Collectors.toList());
        System.out.println("Unique: " + unique);

        // ---------- limit / skip ----------
        List<String> top3 = names.stream()
            .limit(3)
            .collect(Collectors.toList());
        System.out.println("Top 3: " + top3);

        List<String> skip2 = names.stream()
            .skip(2)
            .collect(Collectors.toList());
        System.out.println("Skip 2: " + skip2);

        // ---------- اعداد ----------
        List<Integer> even = numbers.stream()
            .filter(n -> n % 2 == 0)
            .collect(Collectors.toList());
        System.out.println("Even: " + even);

        List<Integer> squared = numbers.stream()
            .map(n -> n * n)
            .collect(Collectors.toList());
        System.out.println("Squared: " + squared);

        int sum = numbers.stream()
            .mapToInt(Integer::intValue)
            .sum();
        System.out.println("Sum: " + sum);   // 55

        Optional<Integer> max = numbers.stream().max(Integer::compareTo);
        System.out.println("Max: " + max.orElse(0));   // 10
    }
}
