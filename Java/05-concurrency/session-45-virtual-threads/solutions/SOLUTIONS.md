# پاسخ تمرین‌های جلسه ۴۵

> نیازمند JDK 21+

## آسان

**۱ و ۲.**
```java
Thread vt = Thread.ofVirtual().start(() -> {
    System.out.println("Virtual thread running");
});
vt.join();

ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
executor.submit(() -> System.out.println("Task"));
executor.shutdown();
```

## متوسط

**۳.**
```java
ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
for (int i = 0; i < 1000; i++) {
    executor.submit(() -> {
        try { Thread.sleep(100); } catch (InterruptedException e) {}
    });
}
executor.shutdown();
// با platform threads: محدود به thread pool size
// با virtual threads: هر task thread خودش را دارد — سریع‌تر برای I/O
```

**۴.**
```java
try (var scope = StructuredTaskScope.open()) {
    Subtask<String> a = scope.fork(() -> fetchA());
    Subtask<String> b = scope.fork(() -> fetchB());
    scope.join();
    return a.get() + b.get();
}
```

## سخت

**۵.**
```java
// synchronized روی virtual thread → pinning به carrier thread
// راه‌حل: ReentrantLock استفاده کنید
ReentrantLock lock = new ReentrantLock();
lock.lock();
try { /* critical */ } finally { lock.unlock(); }
```

**۶.**
```java
Thread vt = Thread.ofVirtual()
    .name("my-worker")
    .unstarted(() -> { /* task */ });
vt.start();
```
