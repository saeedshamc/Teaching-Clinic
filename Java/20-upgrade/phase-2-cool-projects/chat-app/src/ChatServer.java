// ChatServer.java — سرور چت real-time با Socket
// قابل کامپایل و اجرا با javac — بدون dependency
// اجرا: java ChatServer  (پورت 5000)
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class ChatServer {

    private static final int PORT = 5000;
    private static final Set<ClientHandler> clients = ConcurrentHashMap.newKeySet();

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(PORT);
        System.out.println("ChatServer روی پورت " + PORT + " آماده است...");

        while (true) {
            Socket socket = server.accept();
            ClientHandler client = new ClientHandler(socket);
            clients.add(client);
            new Thread(client).start();
            broadcast("System", client.getName() + " به چت پیوست", client);
        }
    }

    static void broadcast(String sender, String message, ClientHandler exclude) {
        String formatted = "[" + sender + "]: " + message;
        for (ClientHandler c : clients) {
            if (c != exclude) c.send(formatted);
        }
    }

    static class ClientHandler implements Runnable {
        private final Socket socket;
        private final BufferedReader in;
        private final PrintWriter out;
        private String name;

        ClientHandler(Socket socket) throws IOException {
            this.socket = socket;
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.out = new PrintWriter(socket.getOutputStream(), true);
        }

        String getName() { return name; }
        void send(String msg) { out.println(msg); }

        @Override
        public void run() {
            try {
                out.println("به چت خوش آمدید! نام خود را وارد کنید:");
                name = in.readLine();
                if (name == null || name.isBlank()) name = "Anonymous";
                broadcast("System", name + " آماده است", this);

                String line;
                while ((line = in.readLine()) != null) {
                    if (line.equalsIgnoreCase("/quit")) break;
                    if (line.equalsIgnoreCase("/list")) {
                        send("کاربران: " + clients.size());
                    } else {
                        broadcast(name, line, null);
                    }
                }
            } catch (IOException e) {
                // کلاینت قطع شد
            } finally {
                clients.remove(this);
                try { socket.close(); } catch (IOException ignored) {}
                broadcast("System", name + " چت را ترک کرد", null);
            }
        }
    }
}
