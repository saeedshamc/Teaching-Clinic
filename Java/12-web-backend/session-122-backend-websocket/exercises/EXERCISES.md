# تمرین‌های جلسه ۱۲۲ — Backend WebSocket

## آسان
1. `WebSocketConfig` با `registerStompEndpoints` بسازید.
2. `@MessageMapping` برای `/chat.send` بسازید.

## متوسط
3. `@SendTo("/topic/chat")` برای broadcast استفاده کنید.
4. `SimpMessagingTemplate` برای programmatic send استفاده کنید.

## سخت
5. `convertAndSendToUser` برای private messages استفاده کنید.
6. `@SendTo` با `Principal` برای user-specific messages استفاده کنید.
