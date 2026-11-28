# جلسه ۴۴ — Deadlock و Thread-Safe Design

## اهداف یادگیری
- Deadlock را بفهمید و تشخیص دهید
- راه‌های جلوگیری از deadlock را بشناسید
- Immutable object و thread-safe design را به‌کار ببرید
- `ConcurrentHashMap` و `CopyOnWriteArrayList` را بشناسید

## پیش‌نیازها
- جلسات ۳۸–۴۳

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### Deadlock — بن‌بست

وقتی دو یا چند thread هرکدام منتظر lock دیگری هستند:

```java
// Thread1: lock A → lock B
// Thread2: lock B → lock A
// هر دو منتظر هم → هیچ‌کدام پیش نمی‌رود
```

```java
class DeadlockDemo {
    static Object lockA = new Object();
    static Object lockB = new Object();

    static void thread1() {
        synchronized (lockA) {
            System.out.println("T1: got A");
            try { Thread.sleep(100); } catch (InterruptedException e) {}
            synchronized (lockB) {
                System.out.println("T1: got B");
            }
        }
    }

    static void thread2() {
        synchronized (lockB) {
            System.out.println("T2: got B");
            try { Thread.sleep(100); } catch (InterruptedException e) {}
            synchronized (lockA) {
                System.out.println("T2: got A");
            }
        }
    }
}
```

### راه‌های جلوگیری

1. **Lock ordering** — همیشه lockها را به ترتیب یکسان بگیرید.
2. **tryLock با timeout** — `lock.tryLock(1, TimeUnit.SECONDS)`.
3. **یک lock** — به‌جای چند lock از یک lock استفاده کنید.
4. **Immutable objects** — بدون state مشترک → بدون lock.

### Thread-safe design — Immutable

```java
final class ImmutablePoint {
    private final int x;
    private final int y;

    ImmutablePoint(int x, int y) {
        this.x = x;
        this.y = y;   // final — بعد از ساخت تغییر نمی‌کند
    }

    public int getX() { return x; }
    public int getY() { return y; }
    // بدون setter — immutable → thread-safe بدون lock
}
```

### Concurrent collections

```java
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();   // thread-safe
CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();     // برای read-heavy
```

## کد مهم

`src/DeadlockDemo.java` — deadlock demo، lock ordering، immutable، concurrent collections.

## خطاهای رایج
- lockها را به ترتیب متفاوت گرفتن → deadlock.
- `wait()` بدون `synchronized` → `IllegalMonitorStateException`.
- mutable shared state بدون synchronization → race condition.
- `ConcurrentHashMap` را با `synchronized` → غیرضروری.

## جمع‌بندی
- Deadlock = چند thread منتظر هم.
- Lock ordering، tryLock، immutable برای جلوگیری.
- Concurrent collections برای thread-safe collections.

**جلسهٔ بعد:** Virtual Threads (Java 21+).
