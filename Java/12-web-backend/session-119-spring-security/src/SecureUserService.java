// SecureUserService.java — service با method-level security
// نیازمند: spring-security-core jar برای کامپایل
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class SecureUserService {

    private final List<Map<String, Object>> users = new ArrayList<>();
    private int nextId = 1;

    public SecureUserService() {
        users.add(Map.of("id", 1, "username", "admin", "role", "ADMIN"));
        users.add(Map.of("id", 2, "username", "user1", "role", "USER"));
        nextId = 3;
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(Long id) {
        users.removeIf(u -> (long) u.get("id") == id);
    }

    @PreAuthorize("#id == authentication.principal.id")
    public Map<String, Object> getUserById(Long id) {
        return users.stream().filter(u -> (long) u.get("id") == id).findFirst()
            .orElseThrow(() -> new RuntimeException("Not found"));
    }

    @PreAuthorize("isAuthenticated()")
    public List<Map<String, Object>> getAllUsers() {
        return new ArrayList<>(users);
    }
}
