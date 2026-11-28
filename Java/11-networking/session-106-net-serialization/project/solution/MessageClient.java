// MessageClient.java — پاسخ پروژهٔ جلسه ۱۰۶
// object serialization client
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class MessageClient {

    private static final String HOST = "localhost";
    private static final int PORT = 9100;

    public static void main(String[] args) throws Exception {
        try (Socket socket = new Socket(HOST, PORT);
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {

            System.out.println("Connected to message server.");

            // Thread برای دریافت
            Thread receiver = new Thread(() -> {
                try {
                    ChatMessage msg;
                    while ((msg = (ChatMessage) ois.readObject()) != null) {
                        System.out.println("Received: " + msg);
                    }
                } catch (ClassNotFoundException | IOException e) {}
            });
            receiver.start();

            // Send messages
            Scanner scanner = new Scanner(System.in);
            System.out.println("Type messages (or 'exit' to quit):");
            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("exit")) break;
                oos.writeObject(new ChatMessage("Client", input, ChatMessage.MessageType.TEXT));
                oos.flush();
            }
        }
    }
}
