# ============================================================
# جلسه ۳۲ — SQLite: row_factory
# ============================================================

import sqlite3


def get_connection() -> sqlite3.Connection:
    conn = sqlite3.connect(":memory:")
    conn.row_factory = sqlite3.Row
    return conn


conn = get_connection()
conn.execute("CREATE TABLE products (id INTEGER PRIMARY KEY, name TEXT, price REAL)")
conn.executemany(
    "INSERT INTO products (name, price) VALUES (?, ?)",
    [("ماوس", 350000), ("کیبورد", 1200000), ("مانیتور", 8000000)],
)
conn.commit()

cur = conn.execute("SELECT * FROM products WHERE price > ? ORDER BY price", (500000,))

print("محصولات گران‌تر از 500000:")
for row in cur:
    print(f"  {row['name']:10} — {row['price']:,.0f} تومان")
    print(f"    dict: {dict(row)}")

conn.close()
