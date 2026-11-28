// EncryptionSystem.java — پاسخ کامل پروژهٔ جلسه ۱۴۱
// Symmetric + Asymmetric encryption
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.util.Base64;

public class EncryptionSystem {

    public static void main(String[] args) throws Exception {
        // Symmetric encryption
        String plaintext = "Hello, World!";
        String key = generateSymmetricKey();
        String iv = generateIV();
        String encrypted = encryptSymmetric(plaintext, key, iv);
        String decrypted = decryptSymmetric(encrypted, key, iv);

        System.out.println("=== Symmetric ===");
        System.out.println("Plaintext: " + plaintext);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);

        // Asymmetric encryption
        KeyPair keyPair = generateAsymmetricKeyPair();
        String asymmetricEncrypted = encryptAsymmetric(plaintext, keyPair.getPublic());
        String asymmetricDecrypted = decryptAsymmetric(asymmetricEncrypted, keyPair.getPrivate());

        System.out.println("\n=== Asymmetric ===");
        System.out.println("Plaintext: " + plaintext);
        System.out.println("Encrypted: " + asymmetricEncrypted);
        System.out.println("Decrypted: " + asymmetricDecrypted);
    }

    // ---------- Symmetric ----------
    static String generateSymmetricKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256);
        return Base64.getEncoder().encodeToString(keyGen.generateKey().getEncoded());
    }

    static String generateIV() {
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        return Base64.getEncoder().encodeToString(iv);
    }

    static String encryptSymmetric(String plaintext, String key, String iv) throws Exception {
        SecretKey secretKey = new SecretKeySpec(
            Base64.getDecoder().decode(key), "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(Base64.getDecoder().decode(iv));
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);
        return Base64.getEncoder().encodeToString(cipher.doFinal(plaintext.getBytes()));
    }

    static String decryptSymmetric(String encrypted, String key, String iv) throws Exception {
        SecretKey secretKey = new SecretKeySpec(
            Base64.getDecoder().decode(key), "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(Base64.getDecoder().decode(iv));
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);
        return new String(cipher.doFinal(Base64.getDecoder().decode(encrypted)));
    }

    // ---------- Asymmetric ----------
    static KeyPair generateAsymmetricKeyPair() throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        return keyGen.generateKeyPair();
    }

    static String encryptAsymmetric(String plaintext, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return Base64.getEncoder().encodeToString(cipher.doFinal(plaintext.getBytes()));
    }

    static String decryptAsymmetric(String encrypted, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return new String(cipher.doFinal(Base64.getDecoder().decode(encrypted)));
    }
}
