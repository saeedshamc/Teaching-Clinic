# تمرین‌های جلسه ۳۳ — Threading

## کلاسی (۳۰ دقیقه)

1. **دو thread:** هر کدام sleep و print name.
2. **join:** بدون join چه می‌شود؟ — امتحان کنید.
3. **Lock:** counter با 3 thread — با/بدون Lock.
4. **Event:** thread منتظر event.set().

## خانه (۴۵ دقیقه)

5. **Queue:** producer 10 item، consumer چاپ.
6. **daemon:** thread daemon=True — رفتار exit.
7. **I/O timing:** sequential vs threaded (time.perf_counter).
8. **BankAccount:** withdraw/deposit thread-safe با Lock.

## نکات

- GIL — CPU-bound → multiprocessing
- I/O-bound — threading مناسب
- `with lock:` برای acquire/release امن
- Queue thread-safe است

پاسخ نمونه: [exercise_01.py](./exercise_01.py) | [exercise_02.py](./exercise_02.py)
