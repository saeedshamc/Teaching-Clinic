// HttpClientDemo.java — HTTP Client demo با java.net.http
// با javac کامپایل و اجرا می‌شود — فقط JDK (نیازمند Java 11+)
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class HttpClientDemo {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    public static void main(String[] args) throws Exception {
        HttpClient client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .build();

        // ---------- GET ----------
        getUsers(client);

        // ---------- GET single ----------
        getUser(client, 1);

        // ---------- POST ----------
        createUser(client);

        // ---------- Async ----------
        asyncGet(client);
    }

    // ---------- GET all users ----------
    static void getUsers(HttpClient client) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(BASE_URL + "/users"))
            .header("Accept", "application/json")
            .GET()
            .build();

        HttpResponse<String> response = client.send(request,
            HttpResponse.BodyHandlers.ofString());

        System.out.println("=== GET /users ===");
        System.out.println("Status: " + response.statusCode());
        System.out.println("Body (first 200 chars): " +
            response.body().substring(0, Math.min(200, response.body().length())));
    }

    // ---------- GET single user ----------
    static void getUser(HttpClient client, int id) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(BASE_URL + "/users/" + id))
            .GET()
            .build();

        HttpResponse<String> response = client.send(request,
            HttpResponse.BodyHandlers.ofString());

        System.out.println("\n=== GET /users/" + id + " ===");
        System.out.println("Status: " + response.statusCode());
        System.out.println("Body: " + response.body());
    }

    // ---------- POST create user ----------
    static void createUser(HttpClient client) throws Exception {
        String json = """
            {"name": "New User", "email": "new@example.com", "role": "user"}
            """;

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(BASE_URL + "/users"))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(json))
            .build();

        HttpResponse<String> response = client.send(request,
            HttpResponse.BodyHandlers.ofString());

        System.out.println("\n=== POST /users ===");
        System.out.println("Status: " + response.statusCode());
        System.out.println("Body: " + response.body());
    }

    // ---------- Async GET ----------
    static void asyncGet(HttpClient client) {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(BASE_URL + "/posts"))
            .GET()
            .build();

        System.out.println("\n=== Async GET /posts ===");
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
            .thenApply(response -> {
                System.out.println("Status: " + response.statusCode());
                String body = response.body();
                System.out.println("Body (first 200 chars): " +
                    body.substring(0, Math.min(200, body.length())));
                return response;
            })
            .exceptionally(e -> {
                System.out.println("Error: " + e.getMessage());
                return null;
            })
            .join();  // wait for completion
    }
}
