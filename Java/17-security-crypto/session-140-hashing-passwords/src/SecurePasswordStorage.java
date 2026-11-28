// SecurePasswordStorage.java — complete password storage
// قابل کامپایل با javac — بدون dependency
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class SecurePasswordStorage {

    public static void main(String[] args) throws Exception {
        // Store password
        String password = "mySecretPassword";
        String storedHash = storePassword(password);

        System.out.println("Stored hash: " + storedHash);

        // Verify password
        boolean matches = verifyPassword(password, storedHash);
        System.out.println("Password matches: " + matches);

        // Wrong password
        boolean wrongMatches = verifyPassword("wrongPassword", storedHash);
        System.out.println("Wrong password matches: " + wrongMatches);
    }

    static String storePassword(String password) throws Exception {
        byte[] salt = generateSalt();
        byte[] hash = hashPassword(password, salt);
        // Store as: salt:hash (Base64 encoded)
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
}
