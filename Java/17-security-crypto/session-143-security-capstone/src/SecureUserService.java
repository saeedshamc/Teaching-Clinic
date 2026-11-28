// SecureUserService.java — secure user service با password hashing
// قابل کامپایل با javac — بدون dependency
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.util.*;

public class SecureUserService {

    private final Map<Long, User> users = new HashMap<>();
    private long nextId = 1;

    public User register(String username, String email, String password) throws Exception {
        // Check if user exists
        for (User u : users.values()) {
            if (u.getUsername().equals(username)) {
                throw new IllegalArgumentException("Username already exists");
            }
        }

        // Hash password
        String hashedPassword = hashPassword(password);

        // Create user
        User user = new User(nextId++, username, email, hashedPassword);
        users.put(user.getId(), user);
        return user;
    }

    public User authenticate(String username, String password) throws Exception {
        for (User u : users.values()) {
            if (u.getUsername().equals(username)) {
                if (verifyPassword(password, u.getPassword())) {
                    return u;
                } else {
                    throw new IllegalArgumentException("Invalid password");
                }
            }
        }
        throw new IllegalArgumentException("User not found");
    }

    public Optional<User> getUserById(Long id) {
        return Optional.ofNullable(users.get(id));
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    // ---------- Password hashing (PBKDF2) ----------
    private String hashPassword(String password) throws Exception {
        byte[] salt = generateSalt();
        byte[] hash = hashPasswordPBKDF2(password, salt);
        return Base64.getEncoder().encodeToString(salt) + ":" +
               Base64.getEncoder().encodeToString(hash);
    }

    private boolean verifyPassword(String password, String storedHash) throws Exception {
        String[] parts = storedHash.split(":");
        byte[] salt = Base64.getDecoder().decode(parts[0]);
        byte[] expectedHash = Base64.getDecoder().decode(parts[1]);
        byte[] actualHash = hashPasswordPBKDF2(password, salt);
        return Arrays.equals(expectedHash, actualHash);
    }

    private byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    private byte[] hashPasswordPBKDF2(String password, byte[] salt) throws Exception {
        PBEKeySpec spec = new PBEKeySpec(
            password.toCharArray(), salt, 10000, 256);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        return factory.generateSecret(spec).getEncoded();
    }
}
