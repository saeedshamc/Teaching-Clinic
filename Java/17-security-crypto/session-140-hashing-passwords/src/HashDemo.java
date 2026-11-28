// HashDemo.java — SHA-256 hashing demo
// قابل کامپایل با javac — بدون dependency
import java.security.MessageDigest;

public class HashDemo {

    public static void main(String[] args) throws Exception {
        String password = "myPassword123";
        String hash = hashPassword(password);

        System.out.println("Password: " + password);
        System.out.println("Hash: " + hash);
        System.out.println("Hash length: " + hash.length());

        // Verify — same input produces same hash
        String hash2 = hashPassword(password);
        System.out.println("Same hash: " + hash.equals(hash2));
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
