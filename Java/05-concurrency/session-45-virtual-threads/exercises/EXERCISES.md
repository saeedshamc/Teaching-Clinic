# تمرین‌های جلسه ۴۵ — Virtual Threads

> نیازمند JDK 21+

## آسان
1. `Thread.ofVirtual().start()` برای یک task ساده استفاده کنید.
2. `Executors.newVirtualThreadPerTaskExecutor()` برای چند task استفاده کنید.

## متوسط
3. ۱۰۰۰ virtual thread برای I/O-bound task بسازید و زمان را با platform threads مقایسه کنید.
4. `StructuredTaskScope` برای اجرای موازی چند subtask استفاده کنید.

## سخت
5. `synchronized` روی virtual thread را تست کنید و pinning را ببینید — `ReentrantLock` را جایگزین کنید.
6. `Thread.ofVirtual().name()` و `unstarted()` را برای کنترل بیشتر استفاده کنید.
