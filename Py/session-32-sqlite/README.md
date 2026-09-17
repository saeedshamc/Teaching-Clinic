# جلسه ۳۲: دیتابیس SQLite — ۱.۵ ساعت

## 🎯 اهداف یادگیری

- اتصال با `sqlite3.connect`
- DDL: CREATE TABLE
- DML: INSERT, SELECT, UPDATE, DELETE
- parameterized queries (`?`) — جلوگیری از SQL injection
- context manager و `row_factory`
- transaction و commit/rollback
- الگوهای real-world: CRUD app, migration ساده

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | SQLite چیست؟ |
| 10–25 | connect و cursor |
| 25–40 | CREATE و INSERT |
| 40–55 | SELECT و WHERE |
| 55–70 | UPDATE/DELETE و transaction |
| 70–85 | تمرین |
| 85–90 | جمع‌بندی |

---

## ۱. SQLite

- فایل‌محور — بدون سرور جدا
- built-in در Python
- مناسب: prototype، embedded، local app
- محدودیت: concurrent write سنگین

```python
import sqlite3
conn = sqlite3.connect("app.db")
```

---

## ۲. :memory: vs file

```python
conn = sqlite3.connect(":memory:")  # موقت
conn = sqlite3.connect("data.db")   # پایدار
```

---

## ۳. cursor و execute

```python
cur = conn.cursor()
cur.execute("CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY, name TEXT)")
cur.execute("INSERT INTO users (name) VALUES (?)", ("سعید",))
conn.commit()
```

**همیشه** از `?` placeholder استفاده کنید.

---

## ۴. SELECT

```python
cur.execute("SELECT id, name FROM users WHERE name LIKE ?", ("س%",))
rows = cur.fetchall()      # [(1, 'سعید'), ...]
row = cur.fetchone()       # اولین ردیف
for row in cur:
    print(row)
```

---

## ۵. row_factory

```python
conn.row_factory = sqlite3.Row
cur.execute("SELECT * FROM users")
row = cur.fetchone()
print(row["name"])
print(dict(row))
```

---

## ۶. context manager

```python
with sqlite3.connect("app.db") as conn:
    conn.execute("INSERT INTO users (name) VALUES (?)", ("مریم",))
    # commit خودکار در موفقیت
```

---

## ۷. executemany

```python
users = [("علی",), ("رضا",), ("زهرا",)]
cur.executemany("INSERT INTO users (name) VALUES (?)", users)
conn.commit()
```

---

## ۸. UPDATE و DELETE

```python
cur.execute("UPDATE users SET name = ? WHERE id = ?", ("سعید جدید", 1))
cur.execute("DELETE FROM users WHERE id = ?", (2,))
conn.commit()
print(cur.rowcount)  # تعداد ردیف‌های affected
```

---

## ۹. transaction

```python
try:
    conn.execute("BEGIN")
    conn.execute("INSERT ...")
    conn.execute("UPDATE ...")
    conn.commit()
except sqlite3.Error:
    conn.rollback()
    raise
```

---

## ۱۰. schema version

```python
def init_db(conn):
    conn.execute("""
        CREATE TABLE IF NOT EXISTS schema_version (version INTEGER)
    """)
    conn.execute("""
        CREATE TABLE IF NOT EXISTS tasks (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            title TEXT NOT NULL,
            done INTEGER DEFAULT 0
        )
    """)
```

---

## ۱۱. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_sqlite_basics.py](./examples/01_sqlite_basics.py) | connect و CRUD |
| [02_row_factory.py](./examples/02_row_factory.py) | Row access |
| [03_transactions.py](./examples/03_transactions.py) | transaction |
| [04_task_repository.py](./examples/04_task_repository.py) | repository pattern |

---

## ۱۲. SQL injection

```python
# خطرناک — هرگز!
name = user_input
cur.execute(f"SELECT * FROM users WHERE name = '{name}'")

# امن
cur.execute("SELECT * FROM users WHERE name = ?", (name,))
```

---

## ۱۳. اشتباهات رایج

| اشتباه | مشکل |
|--------|------|
| فراموش commit | داده ذخیره نمی‌شود |
| f-string در SQL | injection |
| فراموش close | lock فایل |
| INTEGER برای bool | 0/1 — OK در SQLite |

---

## ۱۴. تمرین کلاسی

1. جدول users — insert و select
2. WHERE با parameter
3. row_factory
4. جدول tasks با done flag

**تکلیف:** [question.md](./exercises/question.md)

---

## ۱۵. sqlite3 vs ORM

| | sqlite3 | SQLAlchemy |
|---|---------|------------|
| learning | SQL مستقیم | abstraction |
| prototype | عالی | overhead |
| migration | دستی | Alembic |

---

## ۱۶. sqlite3.Row as dict

```python
for row in conn.execute("SELECT * FROM users"):
    print(dict(row))
```

---

## ۱۷. AUTOINCREMENT

```python
id INTEGER PRIMARY KEY AUTOINCREMENT
# lastrowid بعد از INSERT
```

---

## ۱۸. context manager

```python
with sqlite3.connect("app.db") as conn:
    conn.execute("INSERT ...")
```

---

## ۱۹. سوالات کلاسی

- چرا `?` بهتر از f-string در SQL؟
- `:memory:` چه زمانی مفید است؟
- commit vs autocommit؟

---

## ۲۰. PRAGMA (مرجع)

```python
conn.execute("PRAGMA foreign_keys = ON")
conn.execute("PRAGMA journal_mode = WAL")
```

---

## ۲۱. backup با sqlite

```python
import shutil
shutil.copy2("app.db", "app.db.backup")
```

---

## ۲۲. جمع‌بندی

SQLite برای **prototype و embedded** عالی است — SQL واقعی یاد بگیرید قبل از ORM.

**تکلیف خانه:** [question.md](./exercises/question.md) — TaskRepository کامل.

### چک‌لیست پایان جلسه

- [ ] CREATE TABLE
- [ ] INSERT با `?`
- [ ] SELECT و row_factory
- [ ] commit/rollback

---

## 📚 مطالعه تکمیلی

- [sqlite3 — Python docs](https://docs.python.org/3/library/sqlite3.html)
- [SQLite SQL syntax](https://www.sqlite.org/lang.html)

---

**جلسه قبل:** [۳۱ — Argparse](../session-31-argparse/) | **بعد:** [۳۳ — Threading](../session-33-threading/)
