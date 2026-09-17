# ============================================================
# جلسه ۴۷ — SQL امن
# فایل: 02_sql_safe.py
# ============================================================

import sqlite3


def unsafe_lookup(conn: sqlite3.Connection, name: str) -> list:
    """❌ فقط برای نمایش خطر — هرگز در production"""
    query = f"SELECT id, name FROM users WHERE name='{name}'"
    return conn.execute(query).fetchall()


def safe_lookup(conn: sqlite3.Connection, name: str) -> list:
    query = "SELECT id, name FROM users WHERE name=?"
    return conn.execute(query, (name,)).fetchall()


conn = sqlite3.connect(":memory:")
conn.execute("CREATE TABLE users (id INTEGER, name TEXT)")
conn.executemany("INSERT INTO users VALUES (?, ?)", [(1, "saeed"), (2, "mina")])

malicious = "' OR '1'='1"
print("=== safe ===")
print(safe_lookup(conn, malicious))
print("=== safe normal ===")
print(safe_lookup(conn, "saeed"))
conn.close()
