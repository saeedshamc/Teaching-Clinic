# پروژهٔ جلسه ۴۵ — سرور ساده با Virtual Threads

یک سرور ساده بسازید که:
- `Executors.newVirtualThreadPerTaskExecutor()` برای هر request
- شبیه‌سازی I/O با `Thread.sleep`
- ۱۰۰ request همزمان
- زمان پاسخ‌گویی را اندازه بگیرید
- مقایسه با platform threads (در کامنت)

نمونه:
```
Virtual threads: 100 requests in 1.2s
Platform threads (est.): 10s+ (محدود به pool size)
```

## صورت پروژه
پاسخ در `solution/VirtualServer.java` — نیازمند JDK 21+.
