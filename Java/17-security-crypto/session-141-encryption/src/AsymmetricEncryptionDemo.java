// AsymmetricEncryptionDemo.java — RSA encryption demo
// قابل کامپایل با javac — بدون dependency
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

        System.out.println("Public key: " + Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded()));
        System.out.println("Private key: " + Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded()));

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
