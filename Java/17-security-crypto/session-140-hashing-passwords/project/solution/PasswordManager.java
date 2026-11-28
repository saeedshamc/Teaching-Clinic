// PasswordManager.java — پاسخ کامل پروژهٔ جلسه ۱۴۰
// Secure password storage with PBKDF2
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordManager {

    public static void main(String[] args) throws Exception {
        // Store password
        String password = "MySecurePassword123!";
        String storedHash = storePassword(password);

        System.out.println("Password: " + password);
        System.out.println("Stored: " + storedHash);

        // Verify
        System.out.println("Valid: " + verifyPassword(password, storedHash));
        System.out.println("Invalid: " + verifyPassword("wrong", storedHash));

        // Password strength
        System.out.println("Strength: " + checkStrength(password));
    }

    static String storePassword(String password) throws Exception {
        byte[] salt = generateSalt();
        byte[] hash = hashPassword(password, salt);
        return Base64.getEncoder().encodeToString(salt) + ":" +
               Base64.getEncoder().encodeToString(hash);
    }

    static boolean verifyPassword(String password, String storedHash) throws Exception {
        String[] parts = storedHash.split(":");
        byte[] salt = Base64.getDecoder().decode(parts[0]);
        byte[] expectedHash = Base64.getDecoder().decode(parts[1]);
        byte[] actualHash = hashPassword(password, salt);
        return java.util.Arrays.equals(expectedHash, actualHash);
    }

    static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    static byte[] hashPassword(String password, byte[] salt) throws Exception {
        PBEKeySpec spec = new PBEKeySpec(
            password.toCharArray(), salt, 10000, 256);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        return factory.generateSecret(spec).getEncoded();
    }

    static String checkStrength(String password) {
        if (password.length() < 8) return "WEAK";
        if (password.length() < 12) return "MEDIUM";
        if (password.matches(".*[A-Z].*") && password.matches(".*[a-z].*") &&
            password.matches(".*[0-9].*") && password.matches(".*[!@#$%^&*].*")) {
            return "STRONG";
        }
        return "MEDIUM";
    }
}
