#!/usr/bin/env bash

DB_FILE="/tmp/test.db"

# ایجاد دیتابیس و جدول
sqlite3 "$DB_FILE" << EOF
CREATE TABLE IF NOT EXISTS users (
    id INTEGER PRIMARY KEY,
    name TEXT,
    age INTEGER
);
EOF

echo "دیتابیس و جدول ایجاد شد"

# درج داده
sqlite3 "$DB_FILE" "INSERT INTO users (name, age) VALUES ('علی', 25);"
sqlite3 "$DB_FILE" "INSERT INTO users (name, age) VALUES ('رضا', 30);"
sqlite3 "$DB_FILE" "INSERT INTO users (name, age) VALUES ('محمد', 22);"

echo "داده‌ها درج شد"

# خواندن داده
echo -e "\nمحتوای جدول:"
sqlite3 "$DB_FILE" "SELECT * FROM users;"

# پاکسازی
rm "$DB_FILE"
