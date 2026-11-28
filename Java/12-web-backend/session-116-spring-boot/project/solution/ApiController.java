// ApiController.java — پاسخ کامل پروژهٔ جلسه ۱۱۶
// نیازمند: spring-boot-starter-web jar برای کامپایل
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final List<Map<String, Object>> items = new ArrayList<>();
    private int nextId = 1;

    public ApiController() {
        items.add(Map.of("id", 1, "name", "Item1", "price", 100));
        items.add(Map.of("id", 2, "name", "Item2", "price", 200));
        nextId = 3;
    }

    @GetMapping("/items")
    public List<Map<String, Object>> getAll() { return items; }

    @GetMapping("/items/{id}")
    public Map<String, Object> getById(@PathVariable int id) {
        return items.stream().filter(i -> (int) i.get("id") == id).findFirst()
            .orElseThrow(() -> new RuntimeException("Not found"));
    }

    @PostMapping("/items")
    public Map<String, Object> create(@RequestBody Map<String, Object> data) {
        Map<String, Object> item = new HashMap<>(data);
        item.put("id", nextId++);
        items.add(item);
        return item;
    }

    @DeleteMapping("/items/{id}")
    public void delete(@PathVariable int id) {
        items.removeIf(i -> (int) i.get("id") == id);
    }
}
