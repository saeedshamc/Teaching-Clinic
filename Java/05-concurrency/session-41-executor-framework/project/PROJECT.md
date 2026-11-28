# پروژهٔ جلسه ۴۱ — پردازش دسته‌ای

یک سیستم پردازش دسته‌ای بسازید که:
- `ExecutorService` با `newFixedThreadPool(4)`
- ۱۰ task که هرکدام عددی را پردازش می‌کنند (مثل مربع، فاکتوریل)
- `submit` برای هر task و `Future` برای نتیجه
- `invokeAll` برای batch
- زمان کل را اندازه بگیرید

نمونه:
```
Pool size: 4
Tasks: 10
Processing 1... done in 0.5s
...
Total time: 1.5s
```

## صورت پروژه
پاسخ در `solution/BatchProcessor.java` — از ExecutorService و Future استفاده کنید.
