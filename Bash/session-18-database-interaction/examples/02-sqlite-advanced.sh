#!/usr/bin/env bash

DB_FILE="/tmp/test.db"

# ایجاد دیتابیس
sqlite3 "$DB_FILE" << EOF
CREATE TABLE IF NOT EXISTS products (
    id INTEGER PRIMARY KEY,
    name TEXT,
    price REAL,
    category TEXT
);
EOF

# درج داده‌ها
sqlite3 "$DB_FILE" << EOF
INSERT INTO products (name, price, category) VALUES ('لپتاپ', 15000, 'الکترونیک');
INSERT INTO products (name, price, category) VALUES ('موبایل', 8000, 'الکترونیک');
INSERT INTO products (name, price, category) VALUES ('کتاب', 50, 'کتاب');
EOF

# فیلتر و مرتب‌سازی
echo -e "\nمحصولات الکترونیک:"
sqlite3 "$DB_FILE" "SELECT * FROM products WHERE category='الکترونیک';"

echo -e "\nمحصولات بالای 1000:"
sqlite3 "$DB_FILE" "SELECT * FROM products WHERE price > 1000;"

echo -e "\nمرتب شده بر اساس قیمت:"
sqlite3 "$DB_FILE" "SELECT * FROM products ORDER BY price DESC;"

# پاکسازی
rm "$DB_FILE"
