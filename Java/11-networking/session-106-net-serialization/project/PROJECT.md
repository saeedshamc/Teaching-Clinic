# پروژهٔ جلسه ۱۰۶ — سیستم پیام‌رسانی Object Serialization

یک سیستم پیام‌رسانی بسازید که:
- `ChatMessage` با `Serializable` (sender, content, timestamp, type)
- `MessageServer` با `ObjectInputStream`/`ObjectOutputStream`
- `MessageClient` برای send/receive objects
- `transient` برای sensitive data
- `serialVersionUID` برای versioning
- `broadcast` برای send به همهٔ clients

نمونه:
```
[Client1] Hello!
[Server] Broadcasting to all
[Client2] Hi!
```

## صورت پروژه
پاسخ در `solution/` — ChatMessage.java + MessageServer.java + MessageClient.java.
