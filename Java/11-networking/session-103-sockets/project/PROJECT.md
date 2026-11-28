# پروژهٔ جلسه ۱۰۳ — سیستم چت TCP

یک سیستم چت TCP بسازید که:
- `ChatServer` با `ServerSocket` و multi-threading
- `ChatClient` با `Socket`
- `InputStream`/`OutputStream` برای messaging
- `Broadcast` برای send به همهٔ clients
- `User` tracking برای client names

نمونه:
```
[Client 1] Hello!
[Server] Broadcasting to all clients
[Client 2] Hi there!
```

## صورت پروژه
پاسخ در `solution/` — ChatServer.java + ChatClient.java.
