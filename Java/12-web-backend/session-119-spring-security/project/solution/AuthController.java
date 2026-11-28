// AuthController.java — پاسخ کامل پروژهٔ جلسه ۱۱۹
// نیازمند: spring-boot-starter-web + spring-security jars برای کامپایل
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public AuthController(JwtUtil jwtUtil, UserRepository userRepository) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        // Validate user (simplified — use UserDetailsService in production)
        String token = jwtUtil.generateToken(username);
        return Map.of(
            "token", token,
            "username", username,
            "expiresIn", 86400
        );
    }

    @GetMapping("/me")
    public Map<String, Object> getCurrentUser(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        String username = jwtUtil.extractUsername(token);
        return Map.of("username", username, "authenticated", true);
    }
}
