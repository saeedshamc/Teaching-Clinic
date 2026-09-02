# تمرین‌های جلسه ۳۴ — Concurrent Futures

## کلاسی (۳۰ دقیقه)

1. **map:** مربع اعداد 1-10 با ThreadPool.
2. **submit:** 3 future — result() جمع.
3. **as_completed:** چاپ به ترتیب اتمام.
4. **exception:** یک task خطا — handle در result().

## خانه (۴۵ دقیقه)

5. **timing:** sequential vs ThreadPool برای I/O شبیه‌سازی.
6. **ProcessPool:** sum_squares روی 4 input بزرگ.
7. **timeout:** task کند — TimeoutError.
8. **batch URLs:** لیست URL — parallel fetch (شبیه‌سازی).

## نکات

- `with ThreadPoolExecutor()` — shutdown خودکار
- `fut.result()` exception را re-raise می‌کند
- ProcessPool — `if __name__ == "__main__"` در Windows
- max_workers ≈ CPU count برای Process

پاسخ نمونه: [exercise_01.py](./exercise_01.py) | [exercise_02.py](./exercise_02.py)
