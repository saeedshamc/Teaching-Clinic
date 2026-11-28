# جلسه ۴۲ — Future و CompletableFuture

## اهداف یادگیری
- `Future` را برای نتیجهٔ async بفهمید
- `CompletableFuture` را برای chain کردن تسک‌ها به‌کار ببرید
- `thenApply`, `thenCompose`, `thenCombine` را بشناسید
- `exceptionally` و `handle` را برای خطا بفهمید

## پیش‌نیازها
- جلسهٔ ۴۱

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### Future — نتیجهٔ آینده

```java
Future<String> future = executor.submit(() -> {
    Thread.sleep(1000);
    return "Done";
});

// منتظر و گرفتن نتیجه
String result = future.get();   // blocking — تا تمام شود صبر می‌کند
```

### CompletableFuture — chain کردن

```java
import java.util.concurrent.CompletableFuture;

CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
    return "Hello";
}).thenApply(result -> {
    return result + " World";   // روی نتیجهٔ قبلی کار می‌کند
});

String final = future.join();   // منتظر و گرفتن
```

### thenApply در برابر thenCompose

```java
// thenApply — تابع sync، نتیجه را transform می‌کند
CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> "Ali")
    .thenApply(name -> name.toUpperCase());

// thenCompose — تابع async، نتیجهٔ جدید CompletableFuture می‌دهد
CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> "Ali")
    .thenCompose(name -> CompletableFuture.supplyAsync(() -> "Hello " + name));
```

### thenCombine — ترکیب دو future

```java
CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> "Hello");
CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> "World");

CompletableFuture<String> combined = f1.thenCombine(f2, (a, b) -> a + " " + b);
// "Hello World"
```

### exceptionally و handle

```java
CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
    if (true) throw new RuntimeException("Error!");
    return "OK";
}).exceptionally(ex -> {
    return "Recovered: " + ex.getMessage();
});

// یا handle — هم نتیجه هم خطا را می‌گیرد
CompletableFuture<String> handled = CompletableFuture.supplyAsync(() -> "OK")
    .handle((result, ex) -> {
        if (ex != null) return "Error";
        return result;
    });
```

## کد مهم

`src/CompletableFutureDemo.java` — Future، supplyAsync، thenApply/Compose/Combine، exceptionally.

## خطاهای رایج
- `get()` روی future که exception داده → `ExecutionException`.
- `join()` روی future که exception داده → `CompletionException`.
- chain را فراموش کردن → نتیجهٔ میانی استفاده می‌شود.
- `thenApply` با side effect → بهتر است `thenAccept`.

## جمع‌بندی
- Future برای نتیجهٔ async، CompletableFuture برای chain.
- `thenApply`/`thenCompose`/`thenCombine` برای ترکیب.
- `exceptionally`/`handle` برای خطا.

**جلسهٔ بعد:** Locks و Atomic.
