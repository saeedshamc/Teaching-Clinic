// ChatClient.java — پاسخ پروژهٔ جلسه ۱۰۳
// TCP chat client
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatClient {

    private static final String HOST = "localhost";
    private static final int PORT = 8888;

    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket(HOST, PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            System.out.println("Connected to chat server.");

            // Thread برای دریافت پیام‌ها
            Thread receiver = new Thread(() -> {
                try {
                    String message;
                    while ((message = in.readLine()) != null) {
                        System.out.println("Server: " + message);
                    }
                } catch (IOException e) {}
            });
            receiver.start();

            // Input از کاربر
            Scanner scanner = new Scanner(System.in);
            System.out.println("Type messages (or 'exit' to quit):");
            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("exit")) break;
                out.println(input);
                out.flush();
            }
        }
    }
}
