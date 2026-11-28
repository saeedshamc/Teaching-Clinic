// ObjectServer.java — object serialization echo server
// با javac کامپایل و اجرا می‌شود — فقط JDK
import java.io.*;
import java.net.*;

public class ObjectServer {

    private static final int PORT = 9000;

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(PORT)) {
            System.out.println("Object Server on port " + PORT);
            while (true) {
                Socket client = server.accept();
                new Thread(() -> handleClient(client)).start();
            }
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }

    static void handleClient(Socket socket) {
        try (ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream())) {

            Message msg;
            while ((msg = (Message) ois.readObject()) != null) {
                System.out.println("Received: " + msg);
                // echo back
                oos.writeObject(msg);
                oos.flush();
            }

        } catch (ClassNotFoundException | IOException e) {
            System.out.println("Client error: " + e.getMessage());
        } finally {
            try { socket.close(); } catch (IOException e) {}
        }
    }
}
