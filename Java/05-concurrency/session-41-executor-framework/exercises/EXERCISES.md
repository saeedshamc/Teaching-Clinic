# تمرین‌های جلسه ۴۱ — Executor Framework

## آسان
1. `ExecutorService` با `newFixedThreadPool(2)` بسازید و چند task submit کنید.
2. `shutdown()` و `awaitTermination` را تست کنید.

## متوسط
3. `submit` با `Callable` و `Future.get()` برای گرفتن نتیجه استفاده کنید.
4. `invokeAll` برای اجرای چند task همزمان استفاده کنید.

## سخت
5. `invokeAny` برای گرفتن اولین نتیجه استفاده کنید.
6. `newCachedThreadPool` را برای taskهای کوتاه تست کنید.
