# جلسه ۱۰۶ — Network Serialization: Object Serialization over Socket

## اهداف یادگیری
- `Serializable` interface را برای object serialization بفهمید
- `ObjectOutputStream` و `ObjectInputStream` را برای socket communication به‌کار ببرید
- `transient` keyword را برای sensitive data بشناسید
- `serialVersionUID` را برای version compatibility یاد بگیرید

## پیش‌نیازها
- جلسهٔ ۱۰۵ (WebSocket)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### Object Serialization — تبدیل object به bytes

```java
public class Message implements Serializable {
    private static final long serialVersionUID = 1L;
    private String sender;
    private String content;
    private transient String password;  // serialize نمی‌شود
}
```

### Send Object over Socket

```java
// Sender
ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
oos.writeObject(message);   // serialize + send
oos.flush();

// Receiver
ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
Message received = (Message) ois.readObject();   // receive + deserialize
```

### Server — Echo Objects

```java
public class ObjectServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (true) {
                Socket client = server.accept();
                new Thread(() -> handleClient(client)).start();
            }
        }
    }

    static void handleClient(Socket socket) {
        try (ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream())) {
            Message msg;
            while ((msg = (Message) ois.readObject()) != null) {
                System.out.println("Received: " + msg);
                oos.writeObject(msg);   // echo back
                oos.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

### Client — Send/Receive Objects

```java
public class ObjectClient {
    public static void main(String[] args) throws Exception {
        try (Socket socket = new Socket("localhost", 9000);
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {

            Message msg = new Message("Client", "Hello Server!");
            oos.writeObject(msg);
            oos.flush();

            Message response = (Message) ois.readObject();
            System.out.println("Echo: " + response);
        }
    }
}
```

### transient — برای sensitive data

```java
public class User implements Serializable {
    private String username;
    private transient String password;   // serialize نمی‌شود
    private transient Connection conn;   // runtime object — serialize نمی‌شود
}
```

## کد مهم

`src/Message.java` — serializable message class.
`src/ObjectServer.java` — object echo server.
`src/ObjectClient.java` — object client.

## خطاهای رایج
- `Serializable` را فراموش کردن → `NotSerializableException`.
- `serialVersionUID` را تعریف نکردن → `InvalidClassException` بعد از تغییر class.
- `transient` را برای sensitive data استفاده نکردن → password serialize می‌شود.
- `readObject` را بدون cast استفاده کردن → `ClassCastException`.

## جمع‌بندی
- `Serializable` برای object → bytes conversion.
- `ObjectOutputStream`/`ObjectInputStream` برای send/receive.
- `transient` برای exclude fields، `serialVersionUID` برای versioning.

**جلسهٔ بعد:** Networking Capstone.
