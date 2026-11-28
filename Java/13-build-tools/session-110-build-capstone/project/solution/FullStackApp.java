// FullStackApp.java — پاسخ کامل capstone
// ترکیب JDBC + HTTP Server + JSON + Build Tools
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.*;

public class FullStackApp {

    private static final Gson gson = new Gson();
    private static final List<User> users = new ArrayList<>();
    private static int nextId = 1;

    public static void main(String[] args) throws IOException {
        System.out.println("=== Full-Stack Java App ===");

        // ---------- HTTP Server ----------
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // GET /users
        server.createContext("/users", exchange -> {
            String method = exchange.getRequestMethod();
            switch (method) {
                case "GET" -> handleGetUsers(exchange);
                case "POST" -> handleCreateUser(exchange);
                default -> sendResponse(exchange, 405, "Method not allowed");
            }
        });

        server.createContext("/users/", exchange -> {
            String path = exchange.getRequestURI().getPath();
            int id = Integer.parseInt(path.substring(path.lastIndexOf('/') + 1));
            String method = exchange.getRequestMethod();
            switch (method) {
                case "GET" -> handleGetUser(exchange, id);
                case "PUT" -> handleUpdateUser(exchange, id);
                case "DELETE" -> handleDeleteUser(exchange, id);
                default -> sendResponse(exchange, 405, "Method not allowed");
            }
        });

        server.start();
        System.out.println("Server started on http://localhost:8080");
        System.out.println("Endpoints: GET/POST /users, GET/PUT/DELETE /users/{id}");
    }

    static void handleGetUsers(com.sun.net.httpserver.HttpExchange exchange) throws IOException {
        sendResponse(exchange, 200, gson.toJson(users));
    }

    static void handleCreateUser(com.sun.net.httpserver.HttpExchange exchange) throws IOException {
        String body = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
        User user = gson.fromJson(body, User.class);
        user.setId(nextId++);
        users.add(user);
        sendResponse(exchange, 201, gson.toJson(user));
    }

    static void handleGetUser(com.sun.net.httpserver.HttpExchange exchange, int id) throws IOException {
        users.stream().filter(u -> u.getId() == id).findFirst()
            .ifPresentOrElse(
                u -> sendResponse(exchange, 200, gson.toJson(u)),
                () -> sendResponse(exchange, 404, "User not found"));
    }

    static void handleUpdateUser(com.sun.net.httpserver.HttpExchange exchange, int id) throws IOException {
        String body = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
        User updated = gson.fromJson(body, User.class);
        users.stream().filter(u -> u.getId() == id).findFirst()
            .ifPresentOrElse(
                u -> {
                    u.setName(updated.getName());
                    u.setEmail(updated.getEmail());
                    u.setAge(updated.getAge());
                    sendResponse(exchange, 200, gson.toJson(u));
                },
                () -> sendResponse(exchange, 404, "User not found"));
    }

    static void handleDeleteUser(com.sun.net.httpserver.HttpExchange exchange, int id) throws IOException {
        boolean removed = users.removeIf(u -> u.getId() == id);
        sendResponse(exchange, removed ? 204 : 404, removed ? "" : "User not found");
    }

    static void sendResponse(com.sun.net.httpserver.HttpExchange exchange, int status, String body) {
        try {
            byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(status, bytes.length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(bytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
