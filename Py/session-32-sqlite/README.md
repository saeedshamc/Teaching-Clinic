# جلسه ۳۲: دیتابیس SQLite

## اهداف یادگیری (Learning Objectives)

پس از این جلسه می‌توانید:
- یک دیتابیس محلی با sqlite3 بسازید
- جدول بسازید و داده درج کنید
- داده را جستجو و نمایش دهید

---

## SQLite چیست؟

SQLite یک دیتابیس فایل‌محور است که بدون سرور جداگانه کار می‌کند و برای پروژه‌های کوچک عالی است.

```python
import sqlite3

conn = sqlite3.connect(":memory:")
cur = conn.cursor()
cur.execute("CREATE TABLE users (id INTEGER PRIMARY KEY, name TEXT)")
cur.execute("INSERT INTO users (name) VALUES (?)", ("سعید",))
print(cur.execute("SELECT name FROM users").fetchall())
conn.close()
```

### توضیح
- `:memory:` → دیتابیس موقت در حافظه
- `?` → جای‌گذاری امن پارامتر
- `fetchall` → همه ردیف‌ها را می‌گیرد

---

## فایل‌های این جلسه
- [examples/01_sqlite.py](./examples/01_sqlite.py)
- [exercises/exercise_01.py](./exercises/exercise_01.py)

## تمرین‌ها
- تمرین ساده: یک ردیف درج و بخوانید
- تمرین متوسط: جدول tasks بسازید
