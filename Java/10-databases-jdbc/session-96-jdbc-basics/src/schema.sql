-- schema.sql — اسکیمای نمونه برای تمرین JDBC
-- این فایل را در SQLite اجرا کنید: sqlite3 mydb.db < schema.sql

CREATE TABLE IF NOT EXISTS users (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    email TEXT,
    age INTEGER
);

CREATE TABLE IF NOT EXISTS products (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    price REAL,
    stock INTEGER DEFAULT 0
);

INSERT INTO users (name, email, age) VALUES
    ('Ali', 'ali@mail.com', 25),
    ('Sara', 'sara@mail.com', 30),
    ('Reza', 'reza@mail.com', 28);

INSERT INTO products (name, price, stock) VALUES
    ('Laptop', 1200.50, 5),
    ('Mouse', 25.99, 50),
    ('Keyboard', 75.00, 20);
