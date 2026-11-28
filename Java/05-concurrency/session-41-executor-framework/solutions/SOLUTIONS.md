# پاسخ تمرین‌های جلسه ۴۱

## آسان

**۱ و ۲.**
```java
ExecutorService executor = Executors.newFixedThreadPool(2);
executor.submit(() -> System.out.println("Task1"));
executor.submit(() -> System.out.println("Task2"));
executor.shutdown();
executor.awaitTermination(10, TimeUnit.SECONDS);
```

## متوسط

**۳.**
```java
Future<Integer> future = executor.submit(() -> 42);
int result = future.get();   // 42
```

**۴.**
```java
List<Callable<String>> tasks = List.of(
    () -> "A", () -> "B", () -> "C"
);
List<Future<String>> results = executor.invokeAll(tasks);
```

## سخت

**۵.**
```java
String first = executor.invokeAny(tasks);   // اولین نتیجه
```

**۶.**
```java
ExecutorService cached = Executors.newCachedThreadPool();
for (int i = 0; i < 10; i++) {
    cached.submit(() -> System.out.println("Task"));
}
cached.shutdown();
```
