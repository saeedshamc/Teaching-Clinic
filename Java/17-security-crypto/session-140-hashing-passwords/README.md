# جلسه ۱۴۰ — Hashing & Passwords: Secure Storage

## اهداف یادگیری
- `Hashing` را برای one-way transformation بشناسید
- `BCrypt`, `PBKDF2`, `Argon2` را برای password hashing استفاده کنید
- `Salt` و `Pepper` را در نظر بگیرید
- `MessageDigest` برای hashing algorithms استفاده کنید

## پیش‌نیازها
- فازهای ۱–۹ دورهٔ جاوا

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### Hashing vs Encryption

| Hashing | Encryption |
|---|---|
| One-way | Two-way |
| Cannot decrypt | Can decrypt with key |
| For passwords | For data |
| `BCrypt`, `SHA-256` | `AES`, `RSA` |

### MessageDigest — SHA-256

```java
import java.security.MessageDigest;

public class HashDemo {
    public static void main(String[] args) throws Exception {
        String password = "myPassword123";
        String hash = hashPassword(password);
        System.out.println("Password: " + password);
        System.out.println("Hash: " + hash);
    }

    static String hashPassword(String password) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(password.getBytes());
        return bytesToHex(hash);
    }

    static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
```

### BCrypt — secure password hashing

```java
// نیازمند: spring-security-crypto یا jbcrypt jar
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptDemo {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String rawPassword = "myPassword123";
        String encodedPassword = encoder.encode(rawPassword);

        System.out.println("Raw: " + rawPassword);
        System.out.println("Encoded: " + encodedPassword);

        // Verify
        boolean matches = encoder.matches(rawPassword, encodedPassword);
        System.out.println("Matches: " + matches);
    }
}
```

**BCrypt characteristics:**
- `cost` factor — higher = slower = more secure
- `salt` — random salt per password
- `adaptive` — can increase cost over time

### PBKDF2 — Password-Based Key Derivation

```java
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class PBKDF2Demo {
    public static void main(String[] args) throws Exception {
        String password = "myPassword123";
        byte[] salt = generateSalt();
        byte[] hash = hashPassword(password, salt);

        System.out.println("Salt: " + Base64.getEncoder().encodeToString(salt));
        System.out.println("Hash: " + Base64.getEncoder().encodeToString(hash));
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
```

## کد مهم

`src/HashDemo.java` — SHA-256 hashing.
`src/BcryptDemo.java` — BCrypt password hashing.
`src/PBKDF2Demo.java` — PBKDF2 password hashing.
`src/SecurePasswordStorage.java` — complete password storage.

## خطاهای رایج
- `SHA-256` را برای passwords استفاده کردن → vulnerable to rainbow tables.
- `salt` را بدون random استفاده کردن → predictable hashes.
- `BCrypt` را بدون `cost` factor استفاده کردن → weak hashing.
- `password` را بدون `hash` ذخیره کردن → security breach.

## جمع‌بندی
- Hashing برای one-way transformation — `SHA-256`, `BCrypt`, `PBKDF2`.
- `Salt` برای randomization — prevent rainbow tables.
- `BCrypt` برای password hashing — adaptive cost factor.
- `PBKDF2` برای key derivation — configurable iterations.

**جلسهٔ بعد:** Encryption.
