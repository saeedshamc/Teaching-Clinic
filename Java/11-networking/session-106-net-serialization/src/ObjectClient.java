// ObjectClient.java — object serialization client
// با javac کامپایل و اجرا می‌شود — فقط JDK
import java.io.*;
import java.net.*;

public class ObjectClient {

    private static final String HOST = "localhost";
    private static final int PORT = 9000;

    public static void main(String[] args) throws Exception {
        try (Socket socket = new Socket(HOST, PORT);
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {

            // ---------- Send objects ----------
            for (int i = 1; i <= 3; i++) {
                Message msg = new Message("Client-" + i, "Hello from client " + i);
                oos.writeObject(msg);
                oos.flush();
                System.out.println("Sent: " + msg);

                // ---------- Receive echo ----------
                Message response = (Message) ois.readObject();
                System.out.println("Echo: " + response);
            }

        } catch (ClassNotFoundException | IOException e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }
}
