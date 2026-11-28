// MessagingClient.java — TCP messaging client
// با javac کامپایل و اجرا می‌شود — فقط JDK
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class MessagingClient {

    private static final String HOST = "localhost";
    private static final int PORT = 9200;

    public static void main(String[] args) throws Exception {
        try (Socket socket = new Socket(HOST, PORT);
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter username: ");
            String username = scanner.nextLine();

            // Send username first
            oos.writeObject(username);
            oos.flush();

            System.out.println("Connected as: " + username);

            // Thread برای دریافت پیام‌ها
            Thread receiver = new Thread(() -> {
                try {
                    MessagingServer.ChatMessage msg;
                    while ((msg = (MessagingServer.ChatMessage) ois.readObject()) != null) {
                        System.out.println("Received: " + msg);
                    }
                } catch (Exception e) {}
            });
            receiver.start();

            // Send messages
            System.out.println("Type messages (or 'exit' to quit):");
            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("exit")) break;
                oos.writeObject(new MessagingServer.ChatMessage(
                    username, input, MessagingServer.ChatMessage.MessageType.TEXT));
                oos.flush();
            }
        }
    }
}
