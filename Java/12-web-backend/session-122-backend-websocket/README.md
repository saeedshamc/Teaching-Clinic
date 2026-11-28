# جلسه ۱۲۲ — Backend WebSocket: Real-time در Spring Boot

## اهداف یادگیری
- `Spring WebSocket` را برای real-time communication بشناسید
- `@MessageMapping`, `@SendTo`, `SimpMessagingTemplate` را بفهمید
- `WebSocketConfig` با `registerStompEndpoints` را به‌کار ببرید
- `STOMP` protocol را برای messaging استفاده کنید

## پیش‌نیازها
- جلسهٔ ۱۲۱ (Actuator & Profiles)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### Spring WebSocket چیست؟

`Spring WebSocket` — WebSocket support در Spring Boot. `STOMP` (Simple Text Oriented Messaging Protocol) روی WebSocket.

### Dependencies

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-websocket</artifactId>
</dependency>
```

### WebSocketConfig — STOMP endpoints

```java
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic", "/queue");
        config.setApplicationDestinationPrefixes("/app");
        config.setUserDestinationPrefix("/user");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").setAllowedOrigins("*").withSockJS();
    }
}
```

### @MessageMapping — message handler

```java
@Controller
public class ChatController {

    @MessageMapping("/chat.send")
    @SendTo("/topic/chat")
    public ChatMessage handleMessage(ChatMessage message) {
        return message;   // broadcast to all subscribers
    }

    @MessageMapping("/chat.private")
    public void handlePrivateMessage(ChatMessage message, Principal principal) {
        // send to specific user
        messagingTemplate.convertAndSendToUser(
            message.getRecipient(),
            "/queue/private",
            message
        );
    }
}
```

### SimpMessagingTemplate — programmatic send

```java
@Service
public class NotificationService {

    private final SimpMessagingTemplate messagingTemplate;

    public NotificationService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendNotification(String userId, String message) {
        messagingTemplate.convertAndSendToUser(
            userId, "/queue/notifications", message
        );
    }

    public void broadcast(String message) {
        messagingTemplate.convertAndSend("/topic/announcements", message);
    }
}
```

### Client — JavaScript STOMP

```javascript
const socket = new SockJS('http://localhost:8080/ws');
const stompClient = Stomp.over(socket);

stompClient.connect({}, () => {
    // Subscribe to topic
    stompClient.subscribe('/topic/chat', (message) => {
        console.log('Received:', JSON.parse(message.body));
    });

    // Send message
    stompClient.send('/app/chat.send', {}, JSON.stringify({
        sender: 'User1',
        content: 'Hello!'
    }));
});
```

## کد مهم

`src/WebSocketConfig.java` — STOMP configuration.
`src/ChatController.java` — message handlers.
`src/NotificationService.java` — programmatic messaging.
`src/ChatMessage.java` — message model.

## خطاهای رایج
- `@EnableWebSocketMessageBroker` را بدون `WebSocketMessageBrokerConfigurer` استفاده کردن → no endpoints.
- `setApplicationDestinationPrefixes` را بدون `/app` استفاده کردن → messages not routed.
- `@SendTo` را بدون return value استفاده کردن → no broadcast.
- `SimpMessagingTemplate` را بدون `@Autowired` استفاده کردن → NPE.

## جمع‌بندی
- Spring WebSocket برای real-time — STOMP over WebSocket.
- `@MessageMapping` برای message handlers، `@SendTo` برای broadcast.
- `SimpMessagingTemplate` برای programmatic messaging.
- `registerStompEndpoints` برای WebSocket endpoint.

**جلسهٔ بعد:** Backend Capstone 1.
