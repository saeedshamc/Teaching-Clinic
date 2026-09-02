# ============================================================
# جلسه ۳۲ — SQLite: پایه
# ============================================================

import sqlite3

conn = sqlite3.connect(":memory:")
cur = conn.cursor()

cur.execute("""
    CREATE TABLE users (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        name TEXT NOT NULL,
        email TEXT
    )
""")

cur.execute("INSERT INTO users (name, email) VALUES (?, ?)", ("سعید", "s@example.com"))
cur.execute("INSERT INTO users (name, email) VALUES (?, ?)", ("مریم", "m@example.com"))
conn.commit()

cur.execute("SELECT id, name, email FROM users")
print("همه کاربران:")
for row in cur.fetchall():
    print(" ", row)

cur.execute("SELECT name FROM users WHERE name LIKE ?", ("م%",))
print("\nنام‌های م:", cur.fetchone())

conn.close()
