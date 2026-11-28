# جلسه ۱۰۴ — HTTP Client: java.net.http

## اهداف یادگیری
- `HttpClient` را برای HTTP requests بشناسید
- `HttpRequest`, `HttpResponse` را بفهمید
- GET, POST, PUT, DELETE را با headers و body پیاده کنید
- Async requests با `sendAsync` را به‌کار ببرید

## پیش‌نیازها
- جلسهٔ ۱۰۳ (Sockets)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### HttpClient — Java 11+ built-in

```java
HttpClient client = HttpClient.newBuilder()
    .connectTimeout(Duration.ofSeconds(10))
    .build();
```

### GET Request

```java
HttpRequest request = HttpRequest.newBuilder()
    .uri(URI.create("https://api.example.com/users"))
    .header("Accept", "application/json")
    .GET()
    .build();

HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
System.out.println(response.statusCode());   // 200
System.out.println(response.body());          // JSON string
```

### POST Request با JSON body

```java
String json = """
    {"name": "Ali", "email": "ali@mail.com"}
    """;

HttpRequest request = HttpRequest.newBuilder()
    .uri(URI.create("https://api.example.com/users"))
    .header("Content-Type", "application/json")
    .POST(HttpRequest.BodyPublishers.ofString(json))
    .build();

HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
```

### Async Request — sendAsync

```java
client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
    .thenApply(HttpResponse::body)
    .thenAccept(System.out::println)
    .exceptionally(e -> {
        System.out.println("Error: " + e.getMessage());
        return null;
    });
```

### Response Body Handlers

```java
HttpResponse.BodyHandlers.ofString()      // String
HttpResponse.BodyHandlers.ofFile(path)    // save to file
HttpResponse.BodyHandlers.ofInputStream() // stream
HttpResponse.BodyHandlers.ofByteArray()   // byte[]
HttpResponse.BodyHandlers.discarding()    // ignore body
```

## کد مهم

`src/HttpClientDemo.java` — GET, POST, async, error handling.

## خطاهای رایج
- `send` را روی main thread بلاک کردن → برای async از `sendAsync` استفاده کنید.
- `Content-Type` را فراموش کردن → server نمی‌فهمد body چیست.
- `URI.create` را با URL ناقص استفاده کردن → `IllegalArgumentException`.
- `response.body()` را بدون null check استفاده کردن → NPE.

## جمع‌بندی
- `HttpClient` برای HTTP — built-in در Java 11+.
- `HttpRequest` با URI, headers, method, body.
- `send` برای sync، `sendAsync` برای async.
- `BodyHandlers` برای response parsing.

**جلسهٔ بعد:** WebSocket — real-time communication.
