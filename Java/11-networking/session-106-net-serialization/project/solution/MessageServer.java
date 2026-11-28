// MessageServer.java — پاسخ پروژهٔ جلسه ۱۰۶
// object serialization server با broadcast
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class MessageServer {

    private static final int PORT = 9100;
    private static final Set<ObjectOutputStream> clients = ConcurrentHashMap.newKeySet();

    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(PORT)) {
            System.out.println("Message Server on port " + PORT);
            while (true) {
                Socket socket = server.accept();
                new Thread(() -> handleClient(socket)).start();
            }
        }
    }

    static void handleClient(Socket socket) {
        ObjectOutputStream oos = null;
        try (ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
             ObjectOutputStream writer = new ObjectOutputStream(socket.getOutputStream())) {

            oos = writer;
            clients.add(oos);
            broadcast(new ChatMessage("System", "Client joined", ChatMessage.MessageType.SYSTEM));

            ChatMessage msg;
            while ((msg = (ChatMessage) ois.readObject()) != null) {
                System.out.println("Received: " + msg);
                broadcast(msg);
            }

        } catch (ClassNotFoundException | IOException e) {
            System.out.println("Client error: " + e.getMessage());
        } finally {
            if (oos != null) clients.remove(oos);
            try { socket.close(); } catch (IOException e) {}
        }
    }

    static void broadcast(ChatMessage msg) {
        for (ObjectOutputStream client : clients) {
            try {
                client.writeObject(msg);
                client.flush();
            } catch (IOException e) {
                clients.remove(client);
            }
        }
    }
}
