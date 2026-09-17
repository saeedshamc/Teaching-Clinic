# تمرین‌های جلسه ۲۷ — Async/Await

## کلاسی (۳۰ دقیقه)

1. **Countdown:** متد `CountdownAsync(int n)` — از n تا 1 چاپ کند، بین هر عدد 300ms Delay.
2. **موازی:** سه متد `FetchDataAsync` با delayهای مختلف — با `Task.WhenAll` همزمان اجرا کنید.
3. **Exception:** متدی که 50% throw می‌کند — با try/catch مدیریت کنید.

## خانه (۴۵ دقیقه)

4. **Retry:** متد `RetryAsync(func, maxAttempts)` — تا 3 بار تلاش مجدد.
5. **Timeout:** با `CancellationTokenSource.CancelAfter` timeout 2 ثانیه‌ای.
6. **Benchmark:** زمان اجرای سریال vs موازی 5 Task را مقایسه کنید.

## نکات

- از `.Result` و `.Wait()` استفاده نکنید
- پسوند `Async` برای متدهای async
- `await` را تا بالای call stack propagate کنید

پاسخ نمونه: [exercise_01.cs](./exercise_01.cs)
