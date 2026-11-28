# جلسه ۱۰۵ — WebSocket: Real-time Communication

## اهداف یادگیری
- `WebSocket` را برای full-duplex communication بشناسید
- `javax.websocket` یا `Jakarta WebSocket` API را بفهمید
- `@ServerEndpoint` و `@ClientEndpoint` را به‌کار ببرید
- `onOpen`, `onMessage`, `onClose`, `onError` را یاد بگیرید

## پیش‌نیازها
- جلسهٔ ۱۰۴ (HTTP Client)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### WebSocket vs HTTP

| HTTP | WebSocket |
|---|---|
| Request/Response | Full-duplex |
| Half-duplex | Bidirectional |
| Stateless | Persistent connection |
| Higher overhead | Lower overhead |

### WebSocket Server — با javax.websocket

```java
@ServerEndpoint("/chat")
public class ChatEndpoint {

    private static Set<Session> sessions = ConcurrentHashMap.newKeySet();

    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
        broadcast("New client joined");
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        broadcast("[" + session.getId() + "]: " + message);
    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
        broadcast("Client left");
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        throwable.printStackTrace();
    }

    private static void broadcast(String message) {
        for (Session s : sessions) {
            try { s.getBasicRemote().sendText(message); }
            catch (IOException e) { e.printStackTrace(); }
        }
    }
}
```

### WebSocket Client

```java
@ClientEndpoint
public class ChatClient {

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        System.out.println("Connected!");
    }

    @OnMessage
    public void onMessage(String message) {
        System.out.println("Received: " + message);
    }

    @OnClose
    public void onClose(Session session, CloseReason reason) {
        System.out.println("Closed: " + reason);
    }

    public static void main(String[] args) {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        try {
            Session session = container.connectToServer(
                ChatClient.class,
                new URI("ws://localhost:8080/chat"));
            session.getBasicRemote().sendText("Hello WebSocket!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

### Dependencies

```gradle
dependencies {
    implementation 'jakarta.websocket:jakarta.websocket-api:2.1.0'
    implementation 'org.glassfish.tyrus.bundles:tyrus-standalone-client:2.1.3'
    // برای server: tyrus-standalone-server
}
```

## کد مهم

`src/ChatEndpoint.java` — WebSocket server endpoint.
`src/ChatClient.java` — WebSocket client.

## خطاهای رایج
- `javax.websocket` vs `jakarta.websocket` — Jakarta EE 9+ از `jakarta` استفاده می‌کند.
- `session.close()` را فراموش کردن → connection leak.
- `sendText` را روی wrong thread صدا زدن → `IllegalStateException`.
- `ContainerProvider.getWebSocketContainer()` را بدون implementation استفاده کردن → NPE.

## جمع‌بندی
- WebSocket برای real-time bidirectional communication.
- `@ServerEndpoint`/`@ClientEndpoint` برای endpoints.
- `Session` برای connection management.
- Tyrus برای client/server implementation.

**جلسهٔ بعد:** Network Serialization.
