// PBKDF2Demo.java — PBKDF2 password hashing demo
// قابل کامپایل با javac — بدون dependency
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class PBKDF2Demo {

    public static void main(String[] args) throws Exception {
        String password = "myPassword123";
        byte[] salt = generateSalt();
        byte[] hash = hashPassword(password, salt);

        System.out.println("Password: " + password);
        System.out.println("Salt: " + Base64.getEncoder().encodeToString(salt));
        System.out.println("Hash: " + Base64.getEncoder().encodeToString(hash));

        // Verify
        byte[] hash2 = hashPassword(password, salt);
        System.out.println("Same hash: " + java.util.Arrays.equals(hash, hash2));
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
}
