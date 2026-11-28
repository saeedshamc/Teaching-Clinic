# پاسخ تمرین‌های جلسه ۳۸

## آسان

**۱ و ۲.**
```java
class MyThread extends Thread {
    @Override public void run() {
        System.out.println("Running: " + getName());
    }
}
MyThread t1 = new MyThread();
MyThread t2 = new MyThread();
t1.start(); t2.start();
```

## متوسط

**۳.**
```java
@Override public void run() {
    for (int i = 0; i < 5; i++) {
        System.out.println(i);
        try { Thread.sleep(1000); } catch (InterruptedException e) {}
    }
}
```

**۴.**
```java
t1.start();
t1.join();   // main تا تمام شدن t1 صبر می‌کند
```

## سخت

**۵.**
```java
System.out.println("Current: " + Thread.currentThread().getName());
System.out.println("Alive: " + Thread.currentThread().isAlive());
```

**۶.**
```java
t.interrupt();   // thread را interrupt کنید
// در run():
try { Thread.sleep(10000); }
catch (InterruptedException e) {
    Thread.currentThread().interrupt();   // flag را دوباره ست کنید
    System.out.println("Interrupted!");
}
```
