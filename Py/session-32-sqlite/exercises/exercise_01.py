# ============================================================
# تمرین جلسه ۳۲
# هدف: یک جدول tasks بسازید و یک کار درج کنید.
# ============================================================

import sqlite3

conn = sqlite3.connect(":memory:")
cur = conn.cursor()
cur.execute("CREATE TABLE tasks (title TEXT, done INTEGER)")
cur.execute("INSERT INTO tasks VALUES (?, ?)", ("یادگیری SQL", 0))
print(cur.execute("SELECT * FROM tasks").fetchall())
conn.close()
