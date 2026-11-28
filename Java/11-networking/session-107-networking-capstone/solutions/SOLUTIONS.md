# پاسخ تمرین‌های جلسه ۱۰۷

## آسان

**۱ و ۲.**
```java
public static void sendString(DataOutputStream dos, String msg) throws IOException {
    byte[] bytes = msg.getBytes(StandardCharsets.UTF_8);
    dos.writeInt(bytes.length);
    dos.write(bytes);
    dos.flush();
}

ServerSocket server = new ServerSocket(9200);
Socket client = server.accept();
new Thread(new ClientHandler(client)).start();
```

## متوسط

**۳ و ۴.**
```java
public static class ChatMessage implements Serializable {
    private String sender, content;
    private MessageType type;
    private long timestamp;
}

static void broadcast(ChatMessage msg) {
    for (ClientHandler client : clients) {
        client.send(msg);
    }
}
```

## سخت

**۵ و ۶.**
```java
// username as first message
username = (String) ois.readObject();

// length-prefixed protocol
dos.writeInt(bytes.length);
dos.write(bytes);
int len = dis.readInt();
dis.readFully(buffer);
```
