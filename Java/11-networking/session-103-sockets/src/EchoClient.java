// EchoClient.java — TCP client برای echo server
// با javac کامپایل و اجرا می‌شود — فقط JDK
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class EchoClient {

    private static final String HOST = "localhost";
    private static final int PORT = 8080;

    public static void main(String[] args) {
        try (Socket socket = new Socket(HOST, PORT)) {
            System.out.println("Connected to server: " + socket.getInetAddress());

            OutputStream out = socket.getOutputStream();
            InputStream in = socket.getInputStream();

            // ---------- Send message ----------
            String message = "Hello from Java Client!";
            out.write(message.getBytes(StandardCharsets.UTF_8));
            out.flush();
            System.out.println("Sent: " + message);

            // ---------- Receive echo ----------
            byte[] buffer = new byte[1024];
            int bytesRead = in.read(buffer);
            String response = new String(buffer, 0, bytesRead, StandardCharsets.UTF_8);
            System.out.println("Received: " + response);

            // ---------- Send another ----------
            out.write("Second message".getBytes(StandardCharsets.UTF_8));
            out.flush();
            bytesRead = in.read(buffer);
            response = new String(buffer, 0, bytesRead, StandardCharsets.UTF_8);
            System.out.println("Received: " + response);

        } catch (IOException e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }
}
