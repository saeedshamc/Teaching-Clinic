# پاسخ تمرین‌های جلسه ۴۴

## آسان

**۱ و ۲.**
```java
// Deadlock:
// T1: synchronized(A) { synchronized(B) {...} }
// T2: synchronized(B) { synchronized(A) {...} }

// Lock ordering — جلوگیری:
// هر دو thread اول A بعد B را بگیرند
synchronized(A) { synchronized(B) {...} }   // T1
synchronized(A) { synchronized(B) {...} }   // T2 — ترتیب یکسان
```

## متوسط

**۳.**
```java
final class Color {
    private final int r, g, b;
    Color(int r, int g, int b) { this.r = r; this.g = g; this.b = b; }
    public int getR() { return r; }
}
```

**۴.**
```java
ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
map.put("key", 42);   // thread-safe بدون synchronized
```

## سخت

**۵.**
```java
CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
list.add("item");   // برای read-heavy — iteration بدون lock
```

**۶.**
```java
if (lock.tryLock(1, TimeUnit.SECONDS)) {
    try { /* critical */ } finally { lock.unlock(); }
} else {
    // timeout — lock در دسترس نبود
}
```
