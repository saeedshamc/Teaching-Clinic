// OptionalDemo.java — Optional creation، map/flatMap، orElse/orElseThrow، ifPresent
import java.util.Optional;

public class OptionalDemo {

    // متد که ممکن است null برگرداند
    static String findUser(String id) {
        if (id.equals("1")) return "Ali";
        if (id.equals("2")) return "Sara";
        return null;   // ممکن است null باشد
    }

    // متد که Optional برمی‌گرداند — بهتر
    static Optional<String> findUserSafe(String id) {
        if (id.equals("1")) return Optional.of("Ali");
        if (id.equals("2")) return Optional.of("Sara");
        return Optional.empty();
    }

    public static void main(String[] args) {
        // ---------- ساخت Optional ----------
        Optional<String> opt = Optional.of("Hello");
        Optional<String> empty = Optional.empty();
        Optional<String> nullable = Optional.ofNullable(null);

        System.out.println("opt.isPresent=" + opt.isPresent());       // true
        System.out.println("empty.isPresent=" + empty.isPresent());   // false
        System.out.println("nullable.isPresent=" + nullable.isPresent()); // false

        // ---------- get (خطرناک) ----------
        System.out.println("opt.get()=" + opt.get());   // Hello
        // empty.get() → NoSuchElementException

        // ---------- map ----------
        Optional<String> upper = opt.map(String::toUpperCase);
        System.out.println("upper=" + upper.orElse("none"));   // HELLO

        // ---------- flatMap ----------
        Optional<String> flat = opt.flatMap(s -> Optional.of(s + " World"));
        System.out.println("flat=" + flat.orElse("none"));   // Hello World

        // ---------- زنجیره ----------
        Optional<String> processed = opt
            .map(String::trim)
            .map(String::toUpperCase)
            .filter(s -> s.length() > 3)
            .map(s -> s + "!");
        System.out.println("processed=" + processed.orElse("none"));   // HELLO!

        // ---------- orElse ----------
        String value = empty.orElse("default");
        System.out.println("orElse=" + value);   // default

        // ---------- orElseGet ----------
        String value2 = empty.orElseGet(() -> {
            System.out.println("Computing default...");
            return "computed";
        });
        System.out.println("orElseGet=" + value2);   // computed

        // ---------- orElseThrow ----------
        try {
            String value3 = empty.orElseThrow(() -> new IllegalStateException("No value"));
        } catch (IllegalStateException e) {
            System.out.println("orElseThrow: " + e.getMessage());
        }

        // ---------- ifPresent ----------
        opt.ifPresent(v -> System.out.println("Present: " + v));
        empty.ifPresent(v -> System.out.println("Should not print"));

        // ---------- Optional با null safety ----------
        String user1 = findUser("1");
        String user2 = findUser("3");   // null
        System.out.println("user1=" + user1);
        System.out.println("user2=" + user2);   // null — خطرناک

        // با Optional
        Optional<String> safe1 = findUserSafe("1");
        Optional<String> safe2 = findUserSafe("3");
        System.out.println("safe1=" + safe1.orElse("unknown"));
        System.out.println("safe2=" + safe2.orElse("unknown"));
    }
}
