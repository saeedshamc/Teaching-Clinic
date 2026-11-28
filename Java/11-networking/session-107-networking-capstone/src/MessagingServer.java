// MessagingServer.java — TCP messaging server با broadcast
// با javac کامپایل و اجرا می‌شود — فقط JDK
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class MessagingServer {

    private static final int PORT = 9200;
    private static final Set<ClientHandler> clients = ConcurrentHashMap.newKeySet();

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(PORT)) {
            System.out.println("Messaging Server on port " + PORT);
            while (true) {
                Socket socket = server.accept();
                ClientHandler handler = new ClientHandler(socket);
                clients.add(handler);
                new Thread(handler).start();
            }
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }

    // ---------- Client Handler ----------
    static class ClientHandler implements Runnable {
        private final Socket socket;
        private ObjectOutputStream oos;
        private ObjectInputStream ois;
        private String username;

        ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                oos = new ObjectOutputStream(socket.getOutputStream());
                ois = new ObjectInputStream(socket.getInputStream());

                // First message = username
                username = (String) ois.readObject();
                broadcast(new ChatMessage("System", username + " joined", ChatMessage.MessageType.SYSTEM));
                System.out.println("User connected: " + username);

                // Message loop
                ChatMessage msg;
                while ((msg = (ChatMessage) ois.readObject()) != null) {
                    System.out.println("[" + username + "]: " + msg.getContent());
                    broadcast(msg);
                }

            } catch (Exception e) {
                System.out.println("Client error: " + e.getMessage());
            } finally {
                clients.remove(this);
                try {
                    broadcast(new ChatMessage("System", username + " left", ChatMessage.MessageType.SYSTEM));
                } catch (Exception e) {}
                try { socket.close(); } catch (IOException e) {}
            }
        }

        void send(ChatMessage msg) throws IOException {
            oos.writeObject(msg);
            oos.flush();
        }
    }

    static void broadcast(ChatMessage msg) {
        for (ClientHandler client : clients) {
            try {
                client.send(msg);
            } catch (IOException e) {
                clients.remove(client);
            }
        }
    }

    // ---------- ChatMessage ----------
    public static class ChatMessage implements java.io.Serializable {
        private static final long serialVersionUID = 1L;
        public enum MessageType { TEXT, SYSTEM }
        private String sender;
        private String content;
        private MessageType type;
        private long timestamp;

        public ChatMessage(String sender, String content, MessageType type) {
            this.sender = sender;
            this.content = content;
            this.type = type;
            this.timestamp = System.currentTimeMillis();
        }

        public String getContent() { return content; }
        public MessageType getType() { return type; }
        public long getTimestamp() { return timestamp; }

        @Override
        public String toString() {
            return String.format("[%s] %s (%s): %s",
                new java.util.Date(timestamp), sender, type, content);
        }
    }
}
