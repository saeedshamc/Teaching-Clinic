// ChatServer.java — پاسخ پروژهٔ جلسه ۱۰۳
// TCP chat server با broadcast
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class ChatServer {

    private static final int PORT = 8888;
    private static final Set<PrintWriter> clients = ConcurrentHashMap.newKeySet();

    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Chat Server on port " + PORT);
            while (true) {
                Socket client = serverSocket.accept();
                new Thread(() -> handleClient(client)).start();
            }
        }
    }

    static void handleClient(Socket socket) {
        PrintWriter out = null;
        try (Socket s = socket;
             BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
             PrintWriter writer = new PrintWriter(s.getOutputStream(), true)) {

            out = writer;
            clients.add(out);
            broadcast("New client joined. Total: " + clients.size());

            String message;
            while ((message = in.readLine()) != null) {
                broadcast("[" + s.getInetAddress().getHostAddress() + "]: " + message);
            }

        } catch (IOException e) {
            System.out.println("Client error: " + e.getMessage());
        } finally {
            if (out != null) clients.remove(out);
            broadcast("Client left. Total: " + clients.size());
        }
    }

    static void broadcast(String message) {
        for (PrintWriter client : clients) {
            client.println(message);
            client.flush();
        }
    }
}
