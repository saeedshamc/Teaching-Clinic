# ============================================================
# جلسه ۳۲ — SQLite
# فایل: 01_sqlite.py
# ============================================================

import sqlite3

conn = sqlite3.connect(":memory:")
cur = conn.cursor()
cur.execute("CREATE TABLE users (id INTEGER PRIMARY KEY, name TEXT)")
cur.execute("INSERT INTO users (name) VALUES (?)", ("سعید",))
cur.execute("INSERT INTO users (name) VALUES (?)", ("مینا",))
conn.commit()

for row in cur.execute("SELECT id, name FROM users"):
    print(row)

conn.close()
