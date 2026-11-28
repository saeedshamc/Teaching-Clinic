# جلسه ۳۸ — Thread Basics

## اهداف یادگیری
- Thread چیست و چرا استفاده می‌شود را بفهمید
- `Thread` class را extend و `run()` را override کنید
- `start()` در برابر `run()` را تشخیص دهید
- `sleep()` و `join()` را به‌کار ببرید

## پیش‌نیازها
- فازهای ۱ و ۲

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### Thread — اجرای همزمان

```java
class MyThread extends Thread {
    @Override
    public void run() {
        // کدی که در thread جدا اجرا می‌شود
        for (int i = 0; i < 5; i++) {
            System.out.println("Thread-" + getName() + ": " + i);
            try { Thread.sleep(1000); } catch (InterruptedException e) {}
        }
    }
}

public static void main(String[] args) {
    MyThread t1 = new MyThread();
    MyThread t2 = new MyThread();
    t1.start();   // thread جدید شروع می‌شود
    t2.start();
    // main thread ادامه می‌دهد
}
```

### start() در برابر run()

```java
// run() — در همان thread فعلی اجرا می‌شود (همگام)
t1.run();   // main thread منتظر می‌ماند

// start() — thread جدید می‌سازد و run() را در آن اجرا می‌کند (async)
t1.start(); // thread جدا — main ادامه می‌دهد
```

### sleep و join

```java
// sleep — مکث thread فعلی
try {
    Thread.sleep(1000);   // 1 ثانیه
} catch (InterruptedException e) {
    Thread.currentThread().interrupt();   // flag را دوباره ست کنید
}

// join — منتظر تمام شدن thread دیگر
Thread t = new MyThread();
t.start();
t.join();   // main تا تمام شدن t صبر می‌کند
System.out.println("Thread finished");
```

### Thread.currentThread()

```java
System.out.println(Thread.currentThread().getName());   // main یا نام thread
System.out.println(Thread.currentThread().isAlive());   // آیا در حال اجراست
```

## کد مهم

`src/ThreadDemo.java` — Thread class، start/run، sleep، join، currentThread.

## خطاهای رایج
- `run()` را به‌جای `start()` صدا زدن → همگام اجرا می‌شود نه async.
- `start()` را دوبار صدا زدن → `IllegalThreadStateException`.
- `InterruptedException` را نادیده گرفتن → `Thread.currentThread().interrupt()` را فراموش نکنید.
- `join()` روی thread که start نشده → بلافاصله برمی‌گردد.

## جمع‌بندی
- Thread برای اجرای همزمان کد.
- `start()` برای async، `run()` برای sync.
- `sleep` برای مکث، `join` برای انتظار.

**جلسهٔ بعد:** Runnable و lambda threads.
