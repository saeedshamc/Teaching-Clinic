// FullUserService.java — پاسخ کامل پروژهٔ جلسه ۱۱۳
// نیازمند: jakarta.inject-api jar برای کامپایل
import jakarta.enterprise.context.ApplicationScoped;
import java.util.*;

@ApplicationScoped
public class FullUserService {

    private final List<Map<String, Object>> users = new ArrayList<>();
    private int nextId = 1;

    public FullUserService() {
        users.add(Map.of("id", 1, "name", "Ali", "email", "ali@mail.com"));
        users.add(Map.of("id", 2, "name", "Sara", "email", "sara@mail.com"));
        nextId = 3;
    }

    public List<Map<String, Object>> findAll() { return new ArrayList<>(users); }

    public Optional<Map<String, Object>> findById(int id) {
        return users.stream().filter(u -> (int) u.get("id") == id).findFirst();
    }

    public Map<String, Object> create(String name, String email) {
        Map<String, Object> user = new HashMap<>();
        user.put("id", nextId++); user.put("name", name); user.put("email", email);
        users.add(user); return user;
    }

    public boolean delete(int id) { return users.removeIf(u -> (int) u.get("id") == id); }
}
