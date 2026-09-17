# تمرین‌های جلسه ۲۹ — Enum

## کلاسی (۳۰ دقیقه)

1. **Status:** PENDING, IN_PROGRESS, DONE — چاپ name و value.
2. **Priority IntEnum:** LOW=1, HIGH=3 — مقایسه >.
3. **Role StrEnum:** ADMIN, USER, GUEST.
4. **تبدیل:** تابع `parse_status(s: str) -> Status` با ValueError.

## خانه (۴۵ دقیقه)

5. **Permission Flag:** READ, WRITE, EXEC — ترکیب و بررسی `in`.
6. **Order workflow:** state machine ساده با Enum.
7. **@unique:** Enum بدون duplicate value.
8. **JSON:** serialize/deserialize OrderStatus با `.value`.

## نکات

- `Status.PENDING == Status.PENDING` نه `"pending"`
- `Status("pending")` برای parse
- Flag با `|` ترکیب می‌شود
- StrEnum برای API string-friendly

پاسخ نمونه: [exercise_01.py](./exercise_01.py) | [exercise_02.py](./exercise_02.py)
