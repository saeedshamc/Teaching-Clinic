PRAGMA foreign_keys = ON;

DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id INTEGER PRIMARY KEY,
  email TEXT NOT NULL UNIQUE
);

CREATE TABLE orders (
  id INTEGER PRIMARY KEY,
  user_id INTEGER NOT NULL,
  total REAL NOT NULL CHECK (total >= 0),
  FOREIGN KEY (user_id) REFERENCES users(id)
);

INSERT INTO users (id, email) VALUES (1, 'ali@ex.com');

-- نمایش شکست FK (اسکریپت با OR IGNORE تمام نمی‌شود؛ از حالت تعاملی استفاده کنید)
-- sqlite3: این فایل را خط‌به‌خط ببینید

SELECT 'ok seed' AS status;
