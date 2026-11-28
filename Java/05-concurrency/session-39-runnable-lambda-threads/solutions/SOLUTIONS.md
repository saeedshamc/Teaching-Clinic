# پاسخ تمرین‌های جلسه ۳۹

## آسان

**۱ و ۲.**
```java
Runnable r = () -> System.out.println("Hello from lambda");
new Thread(r).start();

class Task implements Runnable {
    @Override public void run() { System.out.println("Task running"); }
}
```

## متوسط

**۳.**
```java
class Utils {
    static void staticMethod() { System.out.println("Static"); }
    void instanceMethod() { System.out.println("Instance"); }
}
Runnable r1 = Utils::staticMethod;
Utils u = new Utils();
Runnable r2 = u::instanceMethod;
```

**۴.**
```java
Thread t = new Thread(() -> System.out.println("Working"), "MyWorker");
t.start();
```

## سخت

**۵.**
```java
for (int i = 0; i < 3; i++) {
    final int id = i;
    new Thread(() -> {
        for (int j = 0; j < 3; j++) {
            System.out.println("Worker " + id + ": " + j);
            try { Thread.sleep(300); } catch (InterruptedException e) {}
        }
    }).start();
}
```

**۶.**
```java
static void execute(Runnable task) {
    new Thread(task).start();
}
// استفاده:
execute(() -> System.out.println("Callback!"));
```
