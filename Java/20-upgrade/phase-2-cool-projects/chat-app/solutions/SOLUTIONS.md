# پاسخ تمرین‌های پروژهٔ چت

## آسان
ساختار آماده است — فقط اجرا کنید. `broadcast()` به همهٔ کلاینت‌ها
به‌جز فرستنده پیام می‌فرستد.

## متوسط
**۳.** در `finally` بلاک `ClientHandler` پیام خروج broadcast می‌شود.
**۴.** `catch (IOException)` اتصال را می‌گیرد و `finally` cleanup می‌کند —
سرور زنده می‌ماند چون هر کلاینت در thread جداست.

## سخت

**۵. timestamp:**
```java
String time = java.time.LocalTime.now().format(
    java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss"));
broadcast(name, "[" + time + "] " + line, null);
```

**۶. تاریخچه:**
```java
// در ChatServer
private static final Deque<String> history = new ConcurrentLinkedDeque<>();
private static final int HISTORY_SIZE = 100;

// بعد از broadcast:
history.addLast("[" + name + "]: " + message);
while (history.size() > HISTORY_SIZE) history.pollFirst();

// دستور /history در ClientHandler:
if (line.equalsIgnoreCase("/history")) {
    for (String h : history) send(h);
}
```

**۷. نام تکراری:**
```java
// قبل از پذیرش نام، چک کنید:
boolean taken = clients.stream().anyMatch(c -> c.getName() != null && c.getName().equals(name));
if (taken) { out.println("این نام گرفته شده — نام دیگری انتخاب کنید:"); continue; }
```
