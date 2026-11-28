// UserService.java — User microservice
// نیازمند: spring-boot-starter-web jar برای کامپایل
// import org.springframework.boot.SpringApplication;
// import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.web.bind.annotation.*;
import java.util.*;

public class UserService {

    private final Map<Long, Map<String, Object>> users = new HashMap<>();
    private long nextId = 1;

    public UserService() {
        users.put(1L, Map.of("id", 1L, "username", "admin", "email", "admin@mail.com"));
        users.put(2L, Map.of("id", 2L, "username", "user", "email", "user@mail.com"));
        nextId = 3;
    }

    // GET /api/users/{id}
    public Map<String, Object> getUser(Long id) {
        return users.getOrDefault(id, Map.of("error", "User not found"));
    }

    // GET /api/users
    public List<Map<String, Object>> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    // POST /api/users
    public Map<String, Object> createUser(String username, String email) {
        Map<String, Object> user = Map.of(
            "id", nextId++,
            "username", username,
            "email", email
        );
        users.put((Long) user.get("id"), user);
        return user;
    }
}
