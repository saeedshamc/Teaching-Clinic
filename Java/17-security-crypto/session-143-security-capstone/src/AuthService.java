// AuthService.java — Authentication service با JWT
// نیازمند: jjwt-api, jjwt-impl, jjwt-jackson jars برای کامپایل
import java.util.*;

public class AuthService {

    private final SecureUserService userService = new SecureUserService();

    // Login — returns JWT token
    public Map<String, Object> login(String username, String password) {
        try {
            User user = userService.authenticate(username, password);
            String token = generateToken(user);
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

    // Register — create new user
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

    // Validate token — return user info
    public Map<String, Object> validateToken(String token) {
        try {
            // Parse JWT and extract username
            String username = extractUsernameFromToken(token);
            // Find user
            for (User u : userService.getAllUsers()) {
                if (u.getUsername().equals(username)) {
                    return Map.of(
                        "username", u.getUsername(),
                        "role", u.getRole(),
                        "authenticated", true
                    );
                }
            }
            return Map.of("error", "User not found");
        } catch (Exception e) {
            return Map.of("error", "Invalid token");
        }
    }

    private String generateToken(User user) {
        // Placeholder — uncomment when jjwt is available
        return "Bearer " + user.getUsername() + "." + System.currentTimeMillis();
    }

    private String extractUsernameFromToken(String token) {
        // Placeholder — uncomment when jjwt is available
        return token.replace("Bearer ", "").split("\\.")[0];
    }
}
