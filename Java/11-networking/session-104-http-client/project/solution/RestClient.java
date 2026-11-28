// RestClient.java — پاسخ پروژهٔ جلسه ۱۰۴
// HTTP client برای REST API با java.net.http
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class RestClient {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private final HttpClient client;

    public RestClient() {
        this.client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .build();
    }

    // ---------- GET all ----------
    public HttpResponse<String> get(String path) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(BASE_URL + path))
            .header("Accept", "application/json")
            .GET()
            .build();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    // ---------- GET by id ----------
    public HttpResponse<String> getById(String path, int id) throws Exception {
        return get(path + "/" + id);
    }

    // ---------- POST ----------
    public HttpResponse<String> post(String path, String json) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(BASE_URL + path))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(json))
            .build();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    // ---------- PUT ----------
    public HttpResponse<String> put(String path, int id, String json) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(BASE_URL + path + "/" + id))
            .header("Content-Type", "application/json")
            .PUT(HttpRequest.BodyPublishers.ofString(json))
            .build();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    // ---------- DELETE ----------
    public HttpResponse<String> delete(String path, int id) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(BASE_URL + path + "/" + id))
            .DELETE()
            .build();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    // ---------- Async GET ----------
    public void getAsync(String path) {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(BASE_URL + path))
            .GET()
            .build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
            .thenApply(r -> "Status: " + r.statusCode())
            .thenAccept(System.out::println)
            .exceptionally(e -> { System.out.println("Error: " + e.getMessage()); return null; });
    }

    public static void main(String[] args) throws Exception {
        RestClient api = new RestClient();

        System.out.println("=== REST API Client ===");
        System.out.println("GET /users: " + api.get("/users").statusCode());
        System.out.println("GET /users/1: " + api.getById("/users", 1).statusCode());
        System.out.println("POST /users: " + api.post("/users",
            "{\"name\":\"New\",\"email\":\"new@mail.com\"}").statusCode());
        System.out.println("PUT /users/1: " + api.put("/users", 1,
            "{\"name\":\"Updated\"}").statusCode());
        System.out.println("DELETE /users/1: " + api.delete("/users", 1).statusCode());
        api.getAsync("/posts");
    }
}
