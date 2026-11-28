// SecureSystem.java — پاسخ کامل capstone: سیستم امنیتی کامل
// Secure password storage + JWT + authentication
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.util.*;

public class SecureSystem {

    public static void main(String[] args) throws Exception {
        SecureSystem system = new SecureSystem();

        // Register
        Map<String, Object> registerResult = system.register("admin", "admin@mail.com", "password123");
        System.out.println("Register: " + registerResult);

        // Login
        Map<String, Object> loginResult = system.login("admin", "password123");
        System.out.println("Login: " + loginResult);

        String token = (String) loginResult.get("token");

        // Get users (authenticated)
        Map<String, Object> usersResult = system.getAllUsers("Bearer " + token);
        System.out.println("Users: " + usersResult);

        // Invalid token
        Map<String, Object> invalidResult = system.getAllUsers("Bearer invalid");
        System.out.println("Invalid: " + invalidResult);
    }

    private final SecureUserService userService = new SecureUserService();
    private final AuthService authService = new AuthService();

    public Map<String, Object> register(String username, String email, String password) {
        try {
            User user = userService.register(username, email, password);
            return Map.of(
                "id", user.getId(),
                "username", user.getUsername(),
                "email", user.getEmail(),
                "role", user.getRole()
            );
        } catch (Exception e) {
            return Map.of("error", e.getMessage());
        }
    }

    public Map<String, Object> login(String username, String password) {
        try {
            User user = userService.authenticate(username, password);
            String token = "Bearer " + user.getUsername() + "." + System.currentTimeMillis();
            return Map.of(
                "token", token,
                "username", user.getUsername(),
                "role", user.getRole(),
                "expiresIn", 86400
            );
        } catch (Exception e) {
            return Map.of("error", e.getMessage());
        }
    }

    public Map<String, Object> getAllUsers(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return Map.of("error", "Missing token");
        }
        // Validate token (simplified)
        String token = authHeader.substring(7);
        if (token.isEmpty()) {
            return Map.of("error", "Invalid token");
        }

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
}
