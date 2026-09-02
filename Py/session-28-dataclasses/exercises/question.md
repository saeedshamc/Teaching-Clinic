# تمرین‌های جلسه ۲۸ — Dataclasses

## کلاسی (۳۰ دقیقه)

1. **Book:** dataclass با title, author, pages — `__repr__` خودکار را ببینید.
2. **Product:** price و tags (با default_factory).
3. **Point frozen:** x, y و متد `distance()`.
4. **مقایسه:** دو User یکسان — `==` True.

## خانه (۴۵ دقیقه)

5. **Order:** id, items (list), total — validation در `__post_init__`.
6. **Employee:** name, department, skills (set با factory).
7. **JSON export:** Customer + Address → `asdict` → json.dumps.
8. **order=True:** Score(value, player) — sort بر اساس value.

## نکات

- mutable default → `field(default_factory=list)`
- frozen → تغییر فیلد ممنوع
- `__post_init__` برای validation بعد از __init__
- `asdict` برای nested هم کار می‌کند

پاسخ نمونه: [exercise_01.py](./exercise_01.py) | [exercise_02.py](./exercise_02.py)
