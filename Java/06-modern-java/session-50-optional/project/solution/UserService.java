// UserService.java — پاسخ پروژهٔ جلسه ۵۰
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

class User {
    String id;
    String name;
    String email;
    User(String id, String name, String email) {
        this.id = id; this.name = name; this.email = email;
    }
    String getName() { return name; }
    String getEmail() { return email; }
}

public class UserService {

    private Map<String, User> users = new HashMap<>();

    UserService() {
        users.put("1", new User("1", "Ali", "ali@mail.com"));
        users.put("2", new User("2", "Sara", "sara@mail.com"));
    }

    // findById — Optional برمی‌گرداند
    Optional<User> findById(String id) {
        return Optional.ofNullable(users.get(id));
    }

    // findByEmail — Optional برمی‌گرداند
    Optional<User> findByEmail(String email) {
        return users.values().stream()
            .filter(u -> u.getEmail().equals(email))
            .findFirst();
    }

    // getOrCreate — اگر نبود ایجاد کن
    Optional<User> getOrCreate(String id, String name, String email) {
        return findById(id)
            .map(Optional::of)
            .orElseGet(() -> {
                User newUser = new User(id, name, email);
                users.put(id, newUser);
                return Optional.of(newUser);
            });
    }

    // getNameOrDefault — String با default
    String getNameOrDefault(String id) {
        return findById(id)
            .map(User::getName)
            .orElse("Unknown User");
    }

    // getEmailOrThrow — fail-fast
    String getEmailOrThrow(String id) {
        return findById(id)
            .map(User::getEmail)
            .orElseThrow(() -> new IllegalArgumentException("User not found: " + id));
    }

    public static void main(String[] args) {
        UserService service = new UserService();

        // findById
        System.out.println("findById(1): " + service.findById("1").map(User::getName).orElse("none"));
        System.out.println("findById(999): " + service.findById("999").isPresent());

        // findByEmail
        System.out.println("findByEmail: " + service.findByEmail("sara@mail.com").map(User::getName).orElse("none"));

        // getOrCreate
        System.out.println("getOrCreate(3): " + service.getOrCreate("3", "Reza", "reza@mail.com").map(User::getName).orElse("none"));
        System.out.println("getOrCreate(1): " + service.getOrCreate("1", "Ali2", "ali2@mail.com").map(User::getName).orElse("none"));

        // getNameOrDefault
        System.out.println("getNameOrDefault(1): " + service.getNameOrDefault("1"));
        System.out.println("getNameOrDefault(999): " + service.getNameOrDefault("999"));

        // getEmailOrThrow
        try {
            System.out.println("getEmailOrThrow(999): " + service.getEmailOrThrow("999"));
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
