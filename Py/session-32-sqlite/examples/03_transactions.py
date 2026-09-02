# ============================================================
# جلسه ۳۲ — SQLite: transaction
# ============================================================

import sqlite3


def transfer(conn: sqlite3.Connection, from_id: int, to_id: int, amount: float) -> None:
    cur = conn.cursor()
    try:
        cur.execute("BEGIN")
        cur.execute(
            "UPDATE accounts SET balance = balance - ? WHERE id = ? AND balance >= ?",
            (amount, from_id, amount),
        )
        if cur.rowcount != 1:
            raise ValueError("موجودی کافی نیست")
        cur.execute(
            "UPDATE accounts SET balance = balance + ? WHERE id = ?",
            (amount, to_id),
        )
        conn.commit()
    except Exception:
        conn.rollback()
        raise


conn = sqlite3.connect(":memory:")
conn.execute("CREATE TABLE accounts (id INTEGER PRIMARY KEY, name TEXT, balance REAL)")
conn.executemany(
    "INSERT INTO accounts (id, name, balance) VALUES (?, ?, ?)",
    [(1, "سعید", 1000), (2, "مریم", 500)],
)
conn.commit()

transfer(conn, 1, 2, 300)
for row in conn.execute("SELECT * FROM accounts"):
    print(row)

try:
    transfer(conn, 2, 1, 1000)
except ValueError as e:
    print("خطا:", e)

conn.close()
