# ============================================================
# پاسخ نمونه — تمرین جلسه ۳۲ (ساده)
# ============================================================

import sqlite3

conn = sqlite3.connect(":memory:")
conn.row_factory = sqlite3.Row

conn.execute("CREATE TABLE users (id INTEGER PRIMARY KEY, name TEXT, email TEXT)")
conn.executemany(
    "INSERT INTO users (name, email) VALUES (?, ?)",
    [("سعید", "s@x.com"), ("مریم", "m@x.com")],
)
conn.commit()

print("همه:")
for r in conn.execute("SELECT * FROM users"):
    print(dict(r))

conn.execute("UPDATE users SET email = ? WHERE name = ?", ("new@x.com", "سعید"))
conn.commit()

print("\nبعد از UPDATE:")
print(dict(conn.execute("SELECT * FROM users WHERE name = ?", ("سعید",)).fetchone()))

conn.close()
