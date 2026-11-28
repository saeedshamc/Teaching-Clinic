// ApiUserController.java — پاسخ کامل پروژهٔ جلسه ۱۱۷
// نیازمند: spring-boot-starter-web jar برای کامپایل
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/users")
public class ApiUserController {

    private final List<Map<String, Object>> users = new ArrayList<>();
    private int nextId = 1;

    public ApiUserController() {
        users.add(Map.of("id", 1, "name", "Ali", "email", "ali@mail.com", "age", 25));
        users.add(Map.of("id", 2, "name", "Sara", "email", "sara@mail.com", "age", 30));
        users.add(Map.of("id", 3, "name", "Reza", "email", "reza@mail.com", "age", 28));
        nextId = 4;
    }

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getAll() {
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable int id) {
        return users.stream().filter(u -> (int) u.get("id") == id).findFirst()
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> create(@RequestBody Map<String, Object> data) {
        Map<String, Object> user = new HashMap<>(data);
        user.put("id", nextId++);
        users.add(user);
        return ResponseEntity.status(201).body(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(
            @PathVariable int id, @RequestBody Map<String, Object> data) {
        return users.stream().filter(u -> (int) u.get("id") == id).findFirst()
            .map(u -> { u.putAll(data); return ResponseEntity.ok(u); })
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        boolean removed = users.removeIf(u -> (int) u.get("id") == id);
        return removed ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Map<String, Object>>> search(@RequestParam String name) {
        List<Map<String, Object>> results = users.stream()
            .filter(u -> ((String) u.get("name")).toLowerCase().contains(name.toLowerCase()))
            .toList();
        return ResponseEntity.ok(results);
    }

    @GetMapping("/age/{minAge}")
    public ResponseEntity<List<Map<String, Object>>> getByMinAge(@PathVariable int minAge) {
        List<Map<String, Object>> results = users.stream()
            .filter(u -> (int) u.get("age") >= minAge)
            .toList();
        return ResponseEntity.ok(results);
    }
}
