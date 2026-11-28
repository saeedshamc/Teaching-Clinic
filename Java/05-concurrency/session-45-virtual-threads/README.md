# جلسه ۴۵ — Virtual Threads (جاوا ۲۱+)

## اهداف یادگیری
- Virtual Thread چیست و چه مشکلی حل می‌کند را بفهمید
- `Thread.ofVirtual()` و `Executors.newVirtualThreadPerTaskExecutor()` را بشناسید
- Platform Thread در برابر Virtual Thread را تشخیص دهید
- محدودیت‌ها و best practiceها را بفهمید

## پیش‌نیازها
- همهٔ جلسات فاز ۳ (۳۱–۴۴)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

> ⚠️ این جلسه نیازمند **JDK 21 یا جدیدتر** است. روی JDK 17 کامپایل نمی‌شود.

### مشکل Platform Thread

هر `Thread` یک OS thread است — گران‌قیمت:
- هر thread ~1MB stack memory
- ساخت/تخریب گران‌قیمت
- برای I/O-bound workloadها inefficient

### Virtual Thread — thread سبک JVM

```java
// ساخت virtual thread (Java 21+)
Thread vt = Thread.ofVirtual().start(() -> {
    System.out.println("Running in virtual thread");
});

// یا با runnable
Thread vt2 = Thread.ofVirtual().name("my-vt").start(task);

// یا از executor — recommended برای production
ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
executor.submit(() -> { /* task */ });
executor.shutdown();
```

### Platform در برابر Virtual

| Platform Thread | Virtual Thread |
|---|---|
| OS thread واقعی | thread سبک JVM |
| ~1MB stack | چند KB stack |
| محدود به چند هزار | میلیون‌ها ممکن |
| `Thread.ofPlatform()` | `Thread.ofVirtual()` |
| CPU-bound خوب | I/O-bound عالی |

### چه زمانی استفاده کنیم؟

- **I/O-bound** (database, HTTP, file) → virtual threads عالی.
- **CPU-bound** (محاسبات سنگین) → platform threads/executor بهتر.
- **هماهنگی/lock** → synchronized روی virtual thread مشکلی ندارد (برخلاف thread-per-request قدیمی).

### Structured Concurrency (Java 21+)

```java
try (var scope = StructuredTaskScope.open()) {
    Subtask<String> user = scope.fork(() -> fetchUser(id));
    Subtask<String> orders = scope.fork(() -> fetchOrders(id));
    scope.join();
    return user.get() + orders.get();
}   // همهٔ subtaskها قبل از خروج تمام می‌شوند
```

## کد مهم

`src/VirtualThreadsDemo.java` — دموی Java 21+ با `Thread.ofVirtual()` و `newVirtualThreadPerTaskExecutor()`.
`src/PlatformThreadsDemo.java` — همان workload با platform threads برای مقایسه (روی JDK 17 کامپایل می‌شود).

## خطاهای رایج
- `synchronized` روی virtual thread → pinning به carrier thread (performance issue) — از `ReentrantLock` استفاده کنید.
- thread pool با fixed size برای virtual threads → `newVirtualThreadPerTaskExecutor` بهتر است.
- virtual thread را برای CPU-bound سنگین استفاده کردن → scheduler overhead.
- `Thread.sleep` روی virtual thread → خوب است (unmount می‌شود) — بر خلاف synchronized block.

## جمع‌بندی
- Virtual threads = threadهای سبک JVM برای I/O-bound.
- Java 21+، با `Thread.ofVirtual()` یا `newVirtualThreadPerTaskExecutor`.
- برای I/O-bound عالی، برای CPU-bound معمولی.

**فاز ۳ کامل شد — فاز ۴: Modern Java و Legacy Java.**
