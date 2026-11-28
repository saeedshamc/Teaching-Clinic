// BcryptDemo.java — BCrypt password hashing demo
// نیازمند: spring-security-crypto یا jbcrypt jar برای کامپایل
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptDemo {

    public static void main(String[] args) {
        // Uncomment when spring-security-crypto is available:
        // BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        //
        // String rawPassword = "myPassword123";
        // String encodedPassword = encoder.encode(rawPassword);
        //
        // System.out.println("Raw: " + rawPassword);
        // System.out.println("Encoded: " + encodedPassword);
        // System.out.println("Matches: " + encoder.matches(rawPassword, encodedPassword));

        System.out.println("BCrypt demo — uncomment code when spring-security-crypto is available");
        System.out.println("BCrypt characteristics:");
        System.out.println("  - Salt: random per password");
        System.out.println("  - Cost: adaptive (default 10)");
        System.out.println("  - Output: $2a$10$... (60 chars)");
    }
}
