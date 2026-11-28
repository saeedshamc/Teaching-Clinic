# جلسه ۳۹ — Runnable و Lambda Threads

## اهداف یادگیری
- `Runnable` interface را بفهمید
- lambda برای ساخت thread را به‌کار ببرید
- `Thread` constructor با `Runnable` را بشناسید
- method reference را برای thread ببینید

## پیش‌نیازها
- جلسهٔ ۳۸

## مدت زمان پیشنهادی
۶۰ دقیقه

## درس

### Runnable در برابر Thread

```java
// Runnable — interface با run()
Runnable task = new Runnable() {
    @Override public void run() {
        System.out.println("Task running");
    }
};

// lambda — تمیزتر
Runnable task2 = () -> {
    System.out.println("Lambda task");
};

// صدا زدن با Thread
Thread t = new Thread(task);
t.start();

// یا مستقیم
new Thread(() -> {
    System.out.println("Direct lambda");
}).start();
```

### Runnable بهتر از extends Thread

| extends Thread | implements Runnable |
|---|---|
| فقط یک parent | می‌تواند interfaceهای دیگر هم داشته باشد |
| inheritance را مصرف می‌کند | composition — انعطاف‌پذیرتر |
| مناسب برای taskهای ساده | مناسب برای taskهای پیچیده |
| پیش‌فرض قدیمی | روش مدرن‌تر |

### lambda و method reference

```java
// lambda
Runnable r = () -> System.out.println("Hello");

// method reference — متد موجود را reference کنید
class Utils {
    static void printMessage() { System.out.println("Message"); }
    void instanceMethod() { System.out.println("Instance"); }
}

Runnable r2 = Utils::printMessage;   // static method reference
Utils u = new Utils();
Runnable r3 = u::instanceMethod;    // instance method reference
```

### Thread با Runnable و نام

```java
Thread t = new Thread(() -> {
    System.out.println("Running: " + Thread.currentThread().getName());
}, "MyWorker");
t.start();
```

## کد مهم

`src/RunnableDemo.java` — Runnable، lambda، method reference، Thread با Runnable.

## خطاهای رایچ
- `Runnable` را `implements` نکردن در کلاس → compile error.
- lambda بدون `()` برای بدون پارامتر → `() ->` لازم است.
- method reference به متد instance بدون instance → compile error.

## جمع‌بندی
- Runnable برای taskهای قابل اجرا در thread.
- lambda برای پیاده‌سازی سریع، method reference برای متدهای موجود.
- `new Thread(runnable)` برای ساخت thread.

**جلسهٔ بعد:** Synchronization.
