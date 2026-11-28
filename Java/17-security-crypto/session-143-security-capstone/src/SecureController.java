// SecureController.java — REST controller با JWT auth
// نیازمند: spring-boot-starter-web + jjwt jars برای کامپایل
import java.util.*;

public class SecureController {

    private final AuthService authService = new AuthService();
    private final SecureUserService userService = new SecureUserService();

    // POST /api/auth/register
    public Map<String, Object> register(String username, String email, String password) {
        return authService.register(username, email, password);
    }

    // POST /api/auth/login
    public Map<String, Object> login(String username, String password) {
        return authService.login(username, password);
    }

    // GET /api/auth/validate — validate JWT token
    public Map<String, Object> validateToken(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return Map.of("error", "Missing token");
        }
        String token = authHeader.substring(7);
        return authService.validateToken(token);
    }

    // GET /api/users — requires auth
    public Map<String, Object> getAllUsers(String authHeader) {
        // Validate token first
        Map<String, Object> authResult = validateToken(authHeader);
        if (authResult.containsKey("error")) {
            return authResult;
        }

        // Return users (without passwords)
        List<Map<String, Object>> users = new ArrayList<>();
        for (User u : userService.getAllUsers()) {
            users.add(Map.of(
                "id", u.getId(),
                "username", u.getUsername(),
                "email", u.getEmail(),
                "role", u.getRole()
            ));
        }
        return Map.of("users", users);
    }

    // GET /api/users/{id} — requires auth
    public Map<String, Object> getUserById(Long id, String authHeader) {
        Map<String, Object> authResult = validateToken(authHeader);
        if (authResult.containsKey("error")) {
            return authResult;
        }

        Optional<Map<String, Object>> result = userService.getUserById(id)
            .map(u -> Map.<String, Object>of(
                "id", u.getId(),
                "username", u.getUsername(),
                "email", u.getEmail(),
                "role", u.getRole()
            ));
        return result.orElse(Map.of("error", "User not found"));
    }
}
