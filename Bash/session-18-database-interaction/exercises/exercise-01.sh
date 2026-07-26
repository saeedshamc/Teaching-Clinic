#!/usr/bin/env bash

# تمرین ۱: مدیریت مخاطبین
# هدف: یک اسکریپت بنویسید که:
# 1. یک دیتابیس SQLite ایجاد کند
# 2. جدول مخاطبین با نام، ایمیل، تلفن بسازد
# 3. امکان افزودن، لیست، و حذف مخاطب داشته باشد

# راه‌حل:
DB_FILE="/tmp/contacts.db"

# ایجاد دیتابیس و جدول
sqlite3 "$DB_FILE" << EOF
CREATE TABLE IF NOT EXISTS contacts (
    id INTEGER PRIMARY KEY,
    name TEXT,
    email TEXT,
    phone TEXT
);
EOF

echo "دیتابیس مخاطبین ایجاد شد"

# افزودن مخاطب
add_contact() {
    local name=$1
    local email=$2
    local phone=$3
    
    sqlite3 "$DB_FILE" "INSERT INTO contacts (name, email, phone) VALUES ('$name', '$email', '$phone');"
    echo "مخاطب اضافه شد: $name"
}

# لیست مخاطبین
list_contacts() {
    echo "لیست مخاطبین:"
    sqlite3 "$DB_FILE" "SELECT * FROM contacts;"
}

# حذف مخاطب
delete_contact() {
    local id=$1
    
    sqlite3 "$DB_FILE" "DELETE FROM contacts WHERE id=$id;"
    echo "مخاطب $id حذف شد"
}

# استفاده
add_contact "علی" "ali@example.com" "0912345678"
add_contact "رضا" "reza@example.com" "0912876543"
list_contacts
delete_contact 1
list_contacts

# پاکسازی
rm "$DB_FILE"
