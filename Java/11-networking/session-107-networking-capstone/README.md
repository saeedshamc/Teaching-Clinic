# جلسه ۱۰۷ — Networking Capstone: سیستم پیام‌رسانی کامل

## اهداف یادگیری
- همهٔ مباحث networking را ترکیب کنید
- TCP Socket + HTTP Client + Serialization را با هم استفاده کنید
- Multi-threaded server با concurrent connections پیاده کنید
- Protocol design برای client-server communication بسازید

## پیش‌نیازها
- جلسات ۱۰۳–۱۰۶

## مدت زمان پیشنهادی
۹۰ دقیقه

## درس

### پروژه: Messaging System

یک سیستم پیام‌رسانی کامل بسازید که:

1. **TCP Server** — `ServerSocket` با multi-threading
2. **Message Protocol** — length-prefixed messages برای reliability
3. **Object Serialization** — `Serializable` برای message objects
4. **HTTP Client** — برای REST API calls (optional)
5. **Broadcast** — send به همهٔ connected clients
6. **User Management** — track connected users

### Protocol Design

```
[4 bytes: message length] [N bytes: serialized object]
```

```java
// Send
dataOutput.writeInt(messageBytes.length);
dataOutput.write(messageBytes);
dataOutput.flush();

// Receive
dataInput.readInt();  // length
dataInput.readFully(buffer);  // read exact bytes
```

### Architecture

```
Client1 ──┐
Client2 ──┼──> ServerSocket ──> MessageServer
Client3 ──┘         │
                   ├── UserManager (track users)
                   ├── MessageBroadcaster (send to all)
                   └── ProtocolHandler (serialize/deserialize)
```

## کد مهم

`src/MessageProtocol.java` — length-prefixed protocol.
`src/MessagingServer.java` — TCP server با broadcast.
`src/MessagingClient.java` — TCP client.

## جمع‌بندی فاز ۷ (بخش Networking)
- Sockets, HTTP Client, WebSocket, Network Serialization ✅
- **بعدی: Build Tools — Maven, Gradle**
