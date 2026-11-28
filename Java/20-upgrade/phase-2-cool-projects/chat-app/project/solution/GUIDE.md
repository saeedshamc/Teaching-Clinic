# راهنمای پاسخ — چت پیشرفته

## اتاق‌ها
```java
// در ChatServer: Map<String, Set<ClientHandler>> rooms
// ClientHandler.currentRoom — join/leave روی دستور
if (line.startsWith("/join ")) {
    String room = line.substring(6);
    rooms.computeIfAbsent(room, k -> ConcurrentHashMap.newKeySet()).add(this);
}
```

## پیام خصوصی
```java
if (line.startsWith("/w ")) {
    String[] parts = line.split(" ", 3);
    String target = parts[1];
    clients.stream().filter(c -> target.equals(c.getName()))
        .findFirst().ifPresent(c -> c.send("[خصوصی از " + name + "]: " + parts[2]));
    continue;
}
```

## Swing UI — نکتهٔ مهم thread
```java
// خواندن socket در thread جدا و به‌روزرسانی UI فقط در EDT:
SwingUtilities.invokeLater(() -> textArea.append(msg + "\n"));
```
