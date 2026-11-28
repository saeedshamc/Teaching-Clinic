# پاسخ تمرین‌های جلسه ۱۰۳

## آسان

**۱ و ۲.**
```java
ServerSocket server = new ServerSocket(8080);
Socket client = new Socket("localhost", 8080);
```

## متوسط

**۳ و ۴.**
```java
OutputStream out = socket.getOutputStream();
InputStream in = socket.getInputStream();
out.write(message.getBytes());

new Thread(() -> handleClient(clientSocket)).start();
```

## سخت

**۵ و ۶.**
```java
while ((bytesRead = in.read(buffer)) != -1) {
    out.write(buffer, 0, bytesRead);
    out.flush();
}

System.out.println("Client: " + clientSocket.getInetAddress());
```
