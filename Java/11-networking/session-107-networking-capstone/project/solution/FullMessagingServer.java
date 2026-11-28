// FullMessagingServer.java — پاسخ کامل پروژهٔ جلسه ۱۰۷
// TCP messaging server با broadcast و user management
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class FullMessagingServer {

    private static final int PORT = 9300;
    private static final Map<String, ClientHandler> clients = new ConcurrentHashMap<>();
    private static final Set<ClientHandler> allClients = ConcurrentHashMap.newKeySet();

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(PORT)) {
            System.out.println("=== Messaging Server ===");
            System.out.println("Listening on port " + PORT);
            while (true) {
                Socket socket = server.accept();
                new Thread(new ClientHandler(socket)).start();
            }
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }

    static class ClientHandler implements Runnable {
        private final Socket socket;
        private ObjectOutputStream oos;
        private ObjectInputStream ois;
        private String username;

        ClientHandler(Socket socket) { this.socket = socket; }

        @Override
        public void run() {
            try {
                oos = new ObjectOutputStream(socket.getOutputStream());
                ois = new ObjectInputStream(socket.getInputStream());

                // First message = username
                username = (String) ois.readObject();
                clients.put(username, this);
                allClients.add(this);

                broadcast(new ChatMessage("System", username + " joined", ChatMessage.MessageType.SYSTEM));
                System.out.println("[+] " + username + " connected");

                // Message loop
                ChatMessage msg;
                while ((msg = (ChatMessage) ois.readObject()) != null) {
                    if (msg.getType() == ChatMessage.MessageType.COMMAND) {
                        handleCommand(msg.getContent());
                    } else {
                        broadcast(msg);
                    }
                }

            } catch (Exception e) {
                System.out.println("[-] " + username + " error: " + e.getMessage());
            } finally {
                cleanup();
            }
        }

        void handleCommand(String command) {
            switch (command) {
                case "/users" -> sendUserList();
                case "/help" -> sendHelp();
                default -> send(new ChatMessage("System", "Unknown command", ChatMessage.MessageType.SYSTEM));
            }
        }

        void sendUserList() {
            StringBuilder sb = new StringBuilder("Users: ");
            clients.keySet().forEach(name -> sb.append(name).append(", "));
            send(new ChatMessage("System", sb.toString(), ChatMessage.MessageType.SYSTEM));
        }

        void sendHelp() {
            send(new ChatMessage("System", "Commands: /users, /help", ChatMessage.MessageType.SYSTEM));
        }

        void send(ChatMessage msg) {
            try {
                oos.writeObject(msg);
                oos.flush();
            } catch (IOException e) {}
        }

        void cleanup() {
            clients.remove(username);
            allClients.remove(this);
            broadcast(new ChatMessage("System", username + " left", ChatMessage.MessageType.SYSTEM));
            try { socket.close(); } catch (IOException e) {}
        }
    }

    static void broadcast(ChatMessage msg) {
        for (ClientHandler client : allClients) {
            client.send(msg);
        }
    }

    public static class ChatMessage implements Serializable {
        private static final long serialVersionUID = 1L;
        public enum MessageType { TEXT, SYSTEM, COMMAND }
        private String sender, content;
        private MessageType type;
        private long timestamp;

        public ChatMessage(String sender, String content, MessageType type) {
            this.sender = sender; this.content = content;
            this.type = type; this.timestamp = System.currentTimeMillis();
        }

        public String getContent() { return content; }
        public MessageType getType() { return type; }

        @Override
        public String toString() {
            return String.format("[%s] %s (%s): %s",
                new java.util.Date(timestamp), sender, type, content);
        }
    }
}
