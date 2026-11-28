# تمرین‌های جلسه ۴۰ — Synchronization

## آسان
1. `UnsafeCounter` با `count++` بدون synchronized بسازید و race condition را ببینید.
2. `SafeCounter` با `synchronized` متد بسازید و نتیجه را مقایسه کنید.

## متوسط
3. `synchronized` block روی `this` استفاده کنید.
4. `volatile` برای flag boolean تست کنید.

## سخت
5. `wait()`/`notify()` برای communication بین دو thread استفاده کنید.
6. deadlock را با دو lock تو در تو شبیه‌سازی کنید.
