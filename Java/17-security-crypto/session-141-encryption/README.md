# جلسه ۱۴۱ — Encryption: Symmetric & Asymmetric

## اهداف یادگیری
- `Symmetric` و `Asymmetric` encryption را بفهمید
- `AES`, `RSA`, `DES`, `3DES` را برای encryption استفاده کنید
- `Key`, `IV`, `Cipher`, `SecretKey`, `KeyPair` را به‌کار ببرید
- `KeyStore`, `Certificate` را برای secure storage استفاده کنید

## پیش‌نیازها
- جلسهٔ ۱۴۰ (Hashing & Passwords)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### Symmetric Encryption — same key

```java
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.util.Base64;

public class SymmetricEncryptionDemo {

    public static void main(String[] args) throws Exception {
        String plaintext = "Hello, World!";
        String key = generateKey();
        String iv = generateIV();
        String encrypted = encrypt(plaintext, key, iv);
        String decrypted = decrypt(encrypted, key, iv);

        System.out.println("Plaintext: " + plaintext);
        System.out.println("Key: " + key);
        System.out.println("IV: " + iv);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);
    }

    static String generateKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256);
        SecretKey secretKey = keyGen.generateKey();
        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }

    static String generateIV() {
        byte[] iv = new byte[16];
        new java.security.SecureRandom().nextBytes(iv);
        return Base64.getEncoder().encodeToString(iv);
    }

    static String encrypt(String plaintext, String key, String iv) throws Exception {
        SecretKey secretKey = javax.crypto.spec.SecretKeySpec(
            Base64.getDecoder().decode(key), "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(Base64.getDecoder().decode(iv));

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);
        byte[] encrypted = cipher.doFinal(plaintext.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    static String decrypt(String encrypted, String key, String iv) throws Exception {
        SecretKey secretKey = javax.crypto.spec.SecretKeySpec(
            Base64.getDecoder().decode(key), "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(Base64.getDecoder().decode(iv));

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);
        byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(encrypted));
        return new String(decrypted);
    }
}
```

### Asymmetric Encryption — public/private keys

```java
import javax.crypto.Cipher;
import java.security.*;
import java.util.Base64;

public class AsymmetricEncryptionDemo {

    public static void main(String[] args) throws Exception {
        String plaintext = "Secret message";

        // Generate key pair
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair keyPair = keyGen.generateKeyPair();

        // Encrypt with public key
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, keyPair.getPublic());
        byte[] encrypted = encryptCipher.doFinal(plaintext.getBytes());

        // Decrypt with private key
        Cipher decryptCipher = Cipher.getInstance("RSA");
        decryptCipher.init(Cipher.DECRYPT_MODE, keyPair.getPrivate());
        byte[] decrypted = decryptCipher.doFinal(encrypted);

        System.out.println("Plaintext: " + plaintext);
        System.out.println("Encrypted: " + Base64.getEncoder().encodeToString(encrypted));
        System.out.println("Decrypted: " + new String(decrypted));
    }
}
```

### Encryption Algorithms

| Algorithm | Type | Key Size | Use Case |
|---|---|---|---|
| **AES** | Symmetric | 128/192/256 | Data encryption |
| **RSA** | Asymmetric | 2048/4096 | Key exchange, signatures |
| **DES** | Symmetric | 56 | Legacy (insecure) |
| **3DES** | Symmetric | 168 | Legacy (insecure) |
| **ChaCha20** | Symmetric | 256 | Modern, fast |

## کد مهم

`src/SymmetricEncryptionDemo.java` — AES encryption.
`src/AsymmetricEncryptionDemo.java` — RSA encryption.
`src/EncryptionUtils.java` — encryption utilities.

## خطاهای رایج
- `DES` را برای new code استفاده کردن → insecure.
- `ECB` mode را بدون `IV` استفاده کردن → predictable encryption.
- `key` را بدون `secure random` استفاده کردن → weak encryption.
- `cipher` را بدون `try-catch` استفاده کردن → `GeneralSecurityException`.

## جمع‌بندی
- Symmetric encryption — same key — `AES`, `ChaCha20`.
- Asymmetric encryption — public/private keys — `RSA`.
- `Cipher` برای encryption/decryption — `AES/CBC/PKCS5Padding`.
- `Key`, `IV` برای secure encryption.

**جلسهٔ بعد:** JWT.
