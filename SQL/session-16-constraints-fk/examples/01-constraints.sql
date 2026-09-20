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
INSERT INTO orders (user_id, total) VALUES (1, 50);

-- این دو باید خطا بدهند؛ برای دیدن خطا در CLI جدا اجرا کنید یا از اسکریپت تعاملی.
-- INSERT INTO orders (user_id, total) VALUES (99, 10);
-- INSERT INTO orders (user_id, total) VALUES (1, -5);

SELECT * FROM orders;
