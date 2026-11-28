# جلسه ۱۰۳ — Sockets: TCP Client/Server

## اهداف یادگیری
- `Socket` و `ServerSocket` را برای TCP networking بشناسید
- `InputStream`/`OutputStream` برای data transfer را به‌کار ببرید
- Multi-threaded server را برای concurrent connections پیاده کنید
- `InetAddress` و port management را بفهمید

## پیش‌نیازها
- جلسهٔ ۱۰۲ (JDBC Capstone)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### TCP Socket — اتصال مستقیم

```
Client                          Server
  |                               |
  |--- connect() ---------------->|  ServerSocket.accept()
  |<-- connection established ---|
  |--- send data ---------------->|
  |<-- receive data --------------|
  |--- close() ------------------>|
```

### ServerSocket — سمت سرور

```java
ServerSocket serverSocket = new ServerSocket(8080);
System.out.println("Server listening on port 8080...");

while (true) {
    Socket clientSocket = serverSocket.accept();  // wait for connection
    // handle client — در thread جدا برای concurrent connections
    new Thread(() -> handleClient(clientSocket)).start();
}
```

### Socket — سمت کلاینت

```java
Socket socket = new Socket("localhost", 8080);
OutputStream out = socket.getOutputStream();
InputStream in = socket.getInputStream();

// send
out.write("Hello Server".getBytes());
out.flush();

// receive
byte[] buffer = new byte[1024];
int bytesRead = in.read(buffer);
String response = new String(buffer, 0, bytesRead);

socket.close();
```

### Multi-threaded Server — برای concurrent clients

```java
while (true) {
    Socket client = serverSocket.accept();
    new Thread(() -> {
        try {
            handleClient(client);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }).start();
}
```

### Echo Server — ساده‌ترین demo

```java
static void handleClient(Socket client) throws IOException {
    try (InputStream in = client.getInputStream();
         OutputStream out = client.getOutputStream()) {
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = in.read(buffer)) != -1) {
            out.write(buffer, 0, bytesRead);  // echo back
            out.flush();
        }
    }
}
```

## کد مهم

`src/EchoServer.java` — TCP echo server با multi-threading.
`src/EchoClient.java` — TCP client.

## خطاهای رایج
- `accept()` را روی main thread بلاک کردن → server نمی‌تواند concurrent connections بپذیرد — thread لازم است.
- `socket.close()` را فراموش کردن → resource leak.
- `read()` را بدون buffer استفاده کردن → data loss.
- Port را بدون permission استفاده کردن → `BindException`.

## جمع‌بندی
- `ServerSocket` برای accept، `Socket` برای connect.
- `InputStream`/`OutputStream` برای data transfer.
- Thread برای هر client — concurrent connections.

**جلسهٔ بعد:** HTTP Client — java.net.http.
