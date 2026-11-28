// JwtAuthSystem.java — پاسخ کامل پروژهٔ جلسه ۱۴۲
// JWT authentication system
import java.util.*;

public class JwtAuthSystem {

    public static void main(String[] args) {
        JwtAuthSystem system = new JwtAuthSystem();

        // Login
        Map<String, Object> loginResult = system.login("admin", "password123");
        System.out.println("Login: " + loginResult);

        String token = (String) loginResult.get("token");

        // Get current user
        Map<String, Object> userResult = system.getCurrentUser("Bearer " + token);
        System.out.println("User: " + userResult);

        // Invalid token
        Map<String, Object> invalidResult = system.getCurrentUser("Bearer invalid");
        System.out.println("Invalid: " + invalidResult);
    }

    private final JwtUtil jwtUtil = new JwtUtil();
    private final Map<String, String> users = new HashMap<>();

    public JwtAuthSystem() {
        users.put("admin", "password123");
        users.put("user", "user123");
    }

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

    public Map<String, Object> getCurrentUser(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return Map.of("error", "Missing token");
        }
        String token = authHeader.substring(7);
        if (!jwtUtil.validateToken(token)) {
            return Map.of("error", "Invalid token");
        }
        String username = jwtUtil.extractUsername(token);
        return Map.of("username", username, "authenticated", true);
    }
}
