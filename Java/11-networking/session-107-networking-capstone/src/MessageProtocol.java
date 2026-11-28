// MessageProtocol.java — length-prefixed message protocol
// با javac کامپایل می‌شود — فقط JDK
import java.io.*;

public class MessageProtocol {

    // ---------- Send object با length prefix ----------
    public static void sendObject(ObjectOutputStream oos, Object obj) throws IOException {
        oos.writeObject(obj);
        oos.flush();
    }

    // ---------- Receive object ----------
    public static Object receiveObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        return ois.readObject();
    }

    // ---------- Send string با length prefix ----------
    public static void sendString(DataOutputStream dos, String message) throws IOException {
        byte[] bytes = message.getBytes(java.nio.charset.StandardCharsets.UTF_8);
        dos.writeInt(bytes.length);   // length prefix
        dos.write(bytes);             // message bytes
        dos.flush();
    }

    // ---------- Receive string با length prefix ----------
    public static String receiveString(DataInputStream dis) throws IOException {
        int length = dis.readInt();   // read length
        byte[] buffer = new byte[length];
        dis.readFully(buffer);        // read exact bytes
        return new String(buffer, java.nio.charset.StandardCharsets.UTF_8);
    }

    // ---------- Send bytes ----------
    public static void sendBytes(DataOutputStream dos, byte[] data) throws IOException {
        dos.writeInt(data.length);
        dos.write(data);
        dos.flush();
    }

    // ---------- Receive bytes ----------
    public static byte[] receiveBytes(DataInputStream dis) throws IOException {
        int length = dis.readInt();
        byte[] buffer = new byte[length];
        dis.readFully(buffer);
        return buffer;
    }
}
