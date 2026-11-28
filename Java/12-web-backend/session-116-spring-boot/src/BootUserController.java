// BootUserController.java — REST controller برای Spring Boot
// نیازمند: spring-boot-starter-web jar برای کامپایل
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/users")
public class BootUserController {

    private final List<Map<String, Object>> users = new ArrayList<>();
    private int nextId = 1;

    public BootUserController() {
        users.add(Map.of("id", 1, "name", "Ali", "email", "ali@mail.com"));
        users.add(Map.of("id", 2, "name", "Sara", "email", "sara@mail.com"));
        nextId = 3;
    }

    @GetMapping
    public List<Map<String, Object>> getAll() {
        return users;
    }

    @GetMapping("/{id}")
    public Map<String, Object> getById(@PathVariable int id) {
        return users.stream()
            .filter(u -> (int) u.get("id") == id)
            .findFirst()
            .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @PostMapping
    public Map<String, Object> create(@RequestBody Map<String, Object> userData) {
        Map<String, Object> user = new HashMap<>(userData);
        user.put("id", nextId++);
        users.add(user);
        return user;
    }

    @PutMapping("/{id}")
    public Map<String, Object> update(@PathVariable int id, @RequestBody Map<String, Object> userData) {
        return users.stream()
            .filter(u -> (int) u.get("id") == id)
            .findFirst()
            .map(u -> {
                u.putAll(userData);
                return u;
            })
            .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        users.removeIf(u -> (int) u.get("id") == id);
    }
}
