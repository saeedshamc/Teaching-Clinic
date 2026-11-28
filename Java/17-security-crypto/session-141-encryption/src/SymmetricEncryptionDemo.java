// SymmetricEncryptionDemo.java — AES encryption demo
// قابل کامپایل با javac — بدون dependency
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
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
        SecretKey secretKey = new SecretKeySpec(
            Base64.getDecoder().decode(key), "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(Base64.getDecoder().decode(iv));

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);
        byte[] encrypted = cipher.doFinal(plaintext.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    static String decrypt(String encrypted, String key, String iv) throws Exception {
        SecretKey secretKey = new SecretKeySpec(
            Base64.getDecoder().decode(key), "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(Base64.getDecoder().decode(iv));

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);
        byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(encrypted));
        return new String(decrypted);
    }
}
