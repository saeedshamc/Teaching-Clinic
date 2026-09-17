# ============================================================
# تمرین ۱ جلسه ۴۷ — validation + safe SQL
# ============================================================

import re
import sqlite3


def validate_username(name: str) -> bool:
    return bool(re.fullmatch(r"[A-Za-z0-9_]{3,20}", name))


def add_user(conn: sqlite3.Connection, name: str) -> None:
    if not validate_username(name):
        raise ValueError("username نامعتبر")
    conn.execute("INSERT INTO users (name) VALUES (?)", (name,))


conn = sqlite3.connect(":memory:")
conn.execute("CREATE TABLE users (id INTEGER PRIMARY KEY, name TEXT)")
add_user(conn, "saeed_user")
print(conn.execute("SELECT name FROM users").fetchall())
