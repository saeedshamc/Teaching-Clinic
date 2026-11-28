# پروژهٔ جلسه ۱۲۲ — سیستم چت WebSocket با Spring Boot

یک سیستم چت WebSocket با Spring Boot بسازید که:
- `WebSocketConfig` با STOMP endpoints
- `ChatController` با `@MessageMapping` برای messages
- `NotificationService` با `SimpMessagingTemplate`
- `ChatMessage` model با sender, content, recipient
- Public chat (`/topic/chat`) و private messages (`/queue/private`)
- Typing indicator (`/topic/typing`)

نمونه:
```
Client1 → /app/chat.send → broadcast to /topic/chat
Client1 → /app/chat.private → send to /queue/private
Client1 → /app/chat.typing → broadcast typing status
```

## صورت پروژه
پاسخ در `solution/` — می‌توانید `src/` را گسترش دهید.
