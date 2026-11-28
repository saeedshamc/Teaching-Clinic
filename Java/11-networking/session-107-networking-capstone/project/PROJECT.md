# پروژهٔ جلسه ۱۰۷ — سیستم پیام‌رسانی کامل

یک سیستم پیام‌رسانی کامل بسازید که:
- [ ] `ChatMessage` با `Serializable`
- [ ] `MessageProtocol` برای length-prefixed protocol
- [ ] `MessagingServer` با `ServerSocket` + multi-threading
- [ ] `ClientHandler` برای هر client
- [ ] `broadcast` برای send به همه
- [ ] `username` برای identification
- [ ] `MessagingClient` برای send/receive
- [ ] `ObjectOutputStream`/`ObjectInputStream` برای serialization

نمونه:
```
[Client1] Hello!
[Server] Broadcasting to all
[Client2] Hi!
[Client1] How are you?
```

## صورت پروژه
پاسخ در `solution/` — می‌توانید `src/` را گسترش دهید.
