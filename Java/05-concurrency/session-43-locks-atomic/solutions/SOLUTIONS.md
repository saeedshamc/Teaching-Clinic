# پاسخ تمرین‌های جلسه ۴۳

## آسان

**۱ و ۲.**
```java
class Counter {
    int count = 0;
    ReentrantLock lock = new ReentrantLock();
    void increment() {
        lock.lock();
        try { count++; } finally { lock.unlock(); }
    }
}

AtomicInteger atomic = new AtomicInteger(0);
atomic.incrementAndGet();   // بدون lock — thread-safe
```

## متوسط

**۳.**
```java
if (lock.tryLock()) {
    try { count++; } finally { lock.unlock(); }
} else {
    System.out.println("Lock busy");
}
```

**۴.**
```java
AtomicBoolean flag = new AtomicBoolean(false);
flag.compareAndSet(false, true);   // فقط اگر false است → true
```

## سخت

**۵.**
```java
AtomicReference<Person> ref = new AtomicReference<>(person);
ref.compareAndSet(person, updatedPerson);   // atomic update
```

**۶.**
```java
AtomicIntegerArray arr = new AtomicIntegerArray(10);
arr.incrementAndGet(0);   // index 0
arr.addAndGet(5, 10);     // index 5 += 10
```
