# تمرین‌های جلسه ۳۲ — SQLite

## کلاسی (۳۰ دقیقه)

1. **users:** CREATE + INSERT دو کاربر + SELECT همه.
2. **WHERE:** SELECT با parameter name.
3. **row_factory:** دسترسی با نام ستون.
4. **UPDATE:** تغییر email یک کاربر.

## خانه (۴۵ دقیقه)

5. **tasks table:** id, title, done — CRUD کامل.
6. **TaskRepository:** کلاس با add/list/mark_done/delete.
7. **Transaction:** transfer بین دو account (مثل مثال 03).
8. **File DB:** ذخیره در `app.db` با pathlib.

## نکات

- `?` placeholder — هرگز f-string در SQL
- `conn.commit()` بعد از تغییر
- `with sqlite3.connect(...) as conn` برای auto-commit
- bool در SQLite → 0/1

پاسخ نمونه: [exercise_01.py](./exercise_01.py) | [exercise_02.py](./exercise_02.py)
