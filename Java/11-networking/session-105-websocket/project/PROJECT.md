# پروژهٔ جلسه ۱۰۵ — سیستم چت WebSocket

یک سیستم چت WebSocket بسازید که:
- `ChatEndpoint` با `@ServerEndpoint` برای server
- `ChatClient` با `@ClientEndpoint` برای client
- `sessions` set برای broadcast
- `@OnOpen`, `@OnMessage`, `@OnClose`, `@OnError` lifecycle
- `sendText` برای messaging
- `CloseReason` برای connection close

نمونه:
```
[Client 1] Hello!
[Server] Broadcasting to all
[Client 2] Hi!
```

## صورت پروژه
پاسخ در `solution/` — می‌توانید `src/` را گسترش دهید.
