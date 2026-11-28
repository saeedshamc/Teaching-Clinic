# پاسخ تمرین‌های جلسه ۱۲۲

## آسان

**۱ و ۲.**
```java
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").withSockJS();
    }
}

@Controller
public class ChatController {
    @MessageMapping("/chat.send")
    public ChatMessage handle(ChatMessage msg) { return msg; }
}
```

## متوسط

**۳ و ۴.**
```java
@MessageMapping("/chat.send")
@SendTo("/topic/chat")
public ChatMessage handle(ChatMessage msg) { return msg; }

messagingTemplate.convertAndSend("/topic/notifications", message);
```

## سخت

**۵ و ۶.**
```java
messagingTemplate.convertAndSendToUser(
    userId, "/queue/private", message
);

@MessageMapping("/chat.private")
public void handle(ChatMessage msg, Principal principal) {
    messagingTemplate.convertAndSendToUser(
        msg.getRecipient(), "/queue/private", msg
    );
}
```
