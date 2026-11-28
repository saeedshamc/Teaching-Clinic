# جلسه ۴۱ — Executor Framework

## اهداف یادگیری
- `ExecutorService` را برای مدیریت thread pool به‌کار ببرید
- `Executors` factory methods را بشناسید
- `submit` و `invokeAll`/`invokeAny` را بفهمید
- `shutdown` و `awaitTermination` را یاد بگیرید

## پیش‌نیازها
- جلسات ۳۸–۴۰

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### ExecutorService — thread pool مدیریت‌شده

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

ExecutorService executor = Executors.newFixedThreadPool(3);   // ۳ thread

executor.submit(() -> {
    System.out.println("Task running");
});

executor.shutdown();   // دیگر تسک جدید نمی‌پذیرد
executor.awaitTermination(60, TimeUnit.SECONDS);   // صبر برای اتمام
```

### Executors factory methods

```java
Executors.newFixedThreadPool(4);          // thread pool با اندازهٔ ثابت
Executors.newCachedThreadPool();          // thread pool با cache — برای taskهای کوتاه
Executors.newSingleThreadExecutor();      // یک thread
Executors.newScheduledThreadPool(2);      // برای scheduled tasks
```

### submit — Callable و Future

```java
Future<String> future = executor.submit(() -> {
    return "Result";
});

String result = future.get();   // منتظر نتیجه — blocking
```

### invokeAll و invokeAny

```java
List<Callable<String>> tasks = List.of(
    () -> "Task1",
    () -> "Task2",
    () -> "Task3"
);

// همه را اجرا کن و منتظر همه بمان
List<Future<String>> results = executor.invokeAll(tasks);

// اولین نتیجه را بگیر
String first = executor.invokeAny(tasks);
```

## کد مهم

`src/ExecutorDemo.java` — ExecutorService، submit، invokeAll، shutdown/awaitTermination.

## خطاهای رایج
- `shutdown()` را فراموش کردن → threadها زنده می‌مانند.
- `awaitTermination` بدون `shutdown` → برای همیشه صبر می‌کند.
- `future.get()` روی task که exception داده → `ExecutionException`.
- thread pool بزرگ‌تر از CPU → overhead.

## جمع‌بندی
- ExecutorService برای مدیریت thread pool.
- `submit` برای async، `invokeAll`/`invokeAny` برای batch.
- `shutdown` + `awaitTermination` برای cleanup.

**جلسهٔ بعد:** Future و CompletableFuture.
