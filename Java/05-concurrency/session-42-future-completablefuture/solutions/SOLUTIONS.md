# پاسخ تمرین‌های جلسه ۴۲

## آسان

**۱ و ۲.**
```java
CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> "Hello")
    .thenApply(s -> s.toUpperCase());
// "HELLO"
```

## متوسط

**۳.**
```java
CompletableFuture<String> chain = CompletableFuture.supplyAsync(() -> "Ali")
    .thenCompose(name -> CompletableFuture.supplyAsync(() -> "Hello " + name));
// "Hello Ali"
```

**۴.**
```java
CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> "Hello");
CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> "World");
CompletableFuture<String> combined = f1.thenCombine(f2, (a, b) -> a + " " + b);
// "Hello World"
```

## سخت

**۵.**
```java
CompletableFuture<String> safe = CompletableFuture.supplyAsync(() -> {
    throw new RuntimeException("Error");
}).exceptionally(ex -> "Recovered");
// "Recovered"
```

**۶.**
```java
CompletableFuture<Void> all = CompletableFuture.allOf(f1, f2, f3);
all.join();   // منتظر همه

CompletableFuture<Object> any = CompletableFuture.anyOf(f1, f2, f3);
any.join();   // اولین نتیجه
```
