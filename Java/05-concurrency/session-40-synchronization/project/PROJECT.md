# پروژهٔ جلسه ۴۰ — شمارندهٔ thread-safe

یک `ThreadSafeCounter` بسازید که:
- `increment()`, `decrement()`, `getCount()` با synchronized
- `add(int)` برای افزایش دلخواه
- `reset()`
- در `main` ۵ thread بسازید که هرکدام ۱۰۰۰ بار increment کنند
- نتیجه باید دقیقاً ۵۰۰۰ باشد

نمونه:
```
Thread-1: +1000
Thread-2: +1000
...
Final count: 5000
```

## صورت پروژه
پاسخ در `solution/ThreadSafeCounter.java` — از synchronized method/block استفاده کنید.
