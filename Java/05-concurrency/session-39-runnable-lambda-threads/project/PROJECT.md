# پروژهٔ جلسه ۳۹ — Thread Pool ساده

یک Thread Pool ساده بسازید که:
- `Runnable[] tasks` را بگیرد
- `int poolSize` thread بسازد
- هر thread از صف تسک‌ها بردارد و اجرا کند
- `CountDownLatch` یا `join` برای انتظار

نمونه:
```
Pool size: 3
Tasks: 10
Processing task 1...
Processing task 2...
...
All tasks completed
```

## صورت پروژه
پاسخ در `solution/ThreadPool.java` — از `Runnable`, `Thread`, `synchronized` (مقدماتی) استفاده کنید.
