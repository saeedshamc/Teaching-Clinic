# جلسه ۴۳ — Locks و Atomic Classes

## اهداف یادگیری
- `ReentrantLock` را برای lockهای پیشرفته بشناسید
- `tryLock` و `lockInterruptibly` را بفهمید
- `AtomicInteger`, `AtomicBoolean` و... را به‌کار ببرید
- lock-free programming را مقدمتاً بشناسید

## پیش‌نیازها
- جلسات ۳۸–۴۲

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### ReentrantLock

```java
import java.util.concurrent.locks.ReentrantLock;

class Counter {
    private int count = 0;
    private final ReentrantLock lock = new ReentrantLock();

    void increment() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();   // همیشه unlock — حتی با exception
        }
    }

    int getCount() {
        lock.lock();
        try {
            return count;
        } finally {
            lock.unlock();
        }
    }
}
```

### tryLock — بدون blocking

```java
if (lock.tryLock()) {   // سعی کن lock بگیر — اگر نشد false برمی‌گرداند
    try {
        // critical section
    } finally {
        lock.unlock();
    }
} else {
    // lock در دسترس نبود — کار دیگری کن
}
```

### Atomic Classes — lock-free

```java
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

AtomicInteger counter = new AtomicInteger(0);
counter.incrementAndGet();   // ++
counter.addAndGet(5);        // += 5
counter.compareAndSet(0, 10); // اگر 0 است → 10

AtomicBoolean flag = new AtomicBoolean(false);
flag.compareAndSet(false, true);   // CAS — Compare And Swap

AtomicReference<String> ref = new AtomicReference<>("initial");
ref.compareAndSet("initial", "updated");
```

### AtomicIntegerArray

```java
AtomicIntegerArray array = new AtomicIntegerArray(10);
array.incrementAndGet(0);   // index 0
array.addAndGet(5, 10);     // index 5 += 10
```

## کد مهم

`src/LocksAtomicDemo.java` — ReentrantLock، tryLock، AtomicInteger/Boolean/Reference.

## خطاهای رایج
- `lock.unlock()` را فراموش کردن → deadlock.
- `tryLock` بدون `finally` → اگر exception بدهد lock آزاد نمی‌شود.
- Atomic برای compound action → `incrementAndGet` کافی است ولی `get()+set()` نیست.
- `synchronized` در برابر `ReentrantLock` → ReentrantLock انعطاف‌پذیرتر (tryLock, fairness).

## جمع‌بندی
- `synchronized` برای simple cases، `ReentrantLock` برای advanced.
- Atomic classes برای lock-free، thread-safe operations.
- `tryLock` برای non-blocking، `lockInterruptibly` برای responsive.

**جلسهٔ بعد:** Deadlock و Thread-Safe Design.
