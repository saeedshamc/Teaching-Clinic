// AuthController.java — JWT authentication controller
// نیازمند: spring-boot-starter-web + jjwt jars برای کامپایل
import java.util.*;

public class AuthController {

    private final JwtUtil jwtUtil = new JwtUtil();
    private final Map<String, String> users = new HashMap<>();

    public AuthController() {
        users.put("admin", "password123");
        users.put("user", "user123");
    }

    // POST /api/auth/login
    public Map<String, Object> login(String username, String password) {
        if (!users.containsKey(username) || !users.get(username).equals(password)) {
            return Map.of("error", "Invalid credentials");
        }

        String token = jwtUtil.generateToken(username);
        return Map.of(
            "token", token,
            "username", username,
            "expiresIn", 86400
        );
    }

    // GET /api/auth/me — validate token
    public Map<String, Object> getCurrentUser(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return Map.of("error", "Missing token");
        }

        String token = authHeader.substring(7);
        if (!jwtUtil.validateToken(token)) {
            return Map.of("error", "Invalid token");
        }

        String username = jwtUtil.extractUsername(token);
        return Map.of(
            "username", username,
            "authenticated", true
        );
    }
}
