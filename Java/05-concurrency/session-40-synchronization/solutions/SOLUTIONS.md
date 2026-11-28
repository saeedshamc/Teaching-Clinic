# پاسخ تمرین‌های جلسه ۴۰

## آسان

**۱ و ۲.**
```java
class Counter {
    int count = 0;
    void increment() { count++; }   // race condition
}
// دو thread با 10000 increment → count < 20000

class SafeCounter {
    int count = 0;
    synchronized void increment() { count++; }   // thread-safe
}
// count = 20000
```

## متوسط

**۳.**
```java
void increment() {
    synchronized (this) { count++; }
}
```

**۴.**
```java
class Flag {
    volatile boolean running = true;
    void stop() { running = false; }
}
// تغییر running فوراً توسط thread دیگر دیده می‌شود
```

## سخت

**۵.**
```java
class ProducerConsumer {
    int data = 0;
    boolean available = false;
    synchronized void produce() {
        data = 42;
        available = true;
        notify();   // consumer را بیدار کن
    }
    synchronized void consume() throws InterruptedException {
        while (!available) wait();   // منتظر producer
        System.out.println("Consumed: " + data);
        available = false;
    }
}
```

**۶.**
```java
// Deadlock: thread1 lock A→B، thread2 lock B→A
// Thread1: synchronized(a) { synchronized(b) {...} }
// Thread2: synchronized(b) { synchronized(a) {...} }
```
