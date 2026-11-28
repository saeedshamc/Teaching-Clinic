# جلسه ۴۰ — Synchronization

## اهداف یادگیری
- Race condition را بفهمید
- `synchronized` keyword را به‌کار ببرید (method و block)
- intrinsic lock (monitor) را درک کنید
- `volatile` keyword را بشناسید

## پیش‌نیازها
- جلسات ۳۸ و ۳۹

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### Race Condition — مشکل همزمانی

```java
class Counter {
    int count = 0;

    void increment() {
        count++;   // NOT atomic! = read + add + write
    }
}

// دو thread همزمان increment → count کمتر از انتظار می‌شود
```

### synchronized method

```java
class Counter {
    int count = 0;

    synchronized void increment() {   // فقط یک thread در هر لحظه
        count++;
    }

    synchronized int getCount() {
        return count;
    }
}
```

### synchronized block

```java
void increment() {
    synchronized (this) {   // lock روی this
        count++;
    }
}

void printState() {
    synchronized (lock) {   // lock روی object مشخص
        // critical section
    }
}
```

### intrinsic lock (monitor)

- هر object یک lock دارد.
- `synchronized` روی object → فقط یک thread می‌تواند وارد شود.
- `wait()`, `notify()`, `notifyAll()` برای communication بین threadها.

### volatile

```java
class Flag {
    volatile boolean running = true;   // تغییرات فوراً دیده می‌شود

    void stop() { running = false; }
}
```
- `volatile` برای visibility guarantee — threadها تغییرات را می‌بینند.
- برای compound actions (مثل count++) کافی نیست — `synchronized` لازم است.

## کد مهم

`src/SynchronizationDemo.java` — race condition، synchronized method/block، volatile.

## خطاهای رایج
- `count++` بدون synchronized → race condition.
- `volatile` برای compound action → کافی نیست.
- `wait()` بدون `synchronized` → `IllegalMonitorStateException`.
- deadlock با synchronized تو در تو → مراقب باشید.

## جمع‌بندی
- Race condition = چند thread روی دادهٔ مشترک.
- `synchronized` برای mutual exclusion.
- `volatile` برای visibility، نه atomicity.

**جلسهٔ بعد:** Executor Framework.
