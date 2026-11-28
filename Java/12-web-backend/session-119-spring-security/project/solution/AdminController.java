// AdminController.java — پاسخ کامل پروژهٔ جلسه ۱۱۹
// نیازمند: spring-boot-starter-web + spring-security jars برای کامپایل
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Map<String, Object>> getAllUsers() {
        return List.of(
            Map.of("id", 1, "username", "admin", "role", "ADMIN"),
            Map.of("id", 2, "username", "user1", "role", "USER")
        );
    }

    @DeleteMapping("/users/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(@PathVariable Long id) {
        // delete logic
    }

    @GetMapping("/stats")
    @PreAuthorize("isAuthenticated()")
    public Map<String, Object> getStats() {
        return Map.of("totalUsers", 2, "activeUsers", 1);
    }
}
