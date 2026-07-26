#!/usr/bin/env bash

# تمرین ۲: مدیریت موجودی
# هدف: یک اسکریپت بنویسید که:
# 1. دیتابیس موجودی کالا ایجاد کند
# 2. امکان افزودن، به‌روزرسانی، و گزارش موجودی داشته باشد

# راه‌حل:
DB_FILE="/tmp/inventory.db"

# ایجاد دیتابیس و جدول
sqlite3 "$DB_FILE" << EOF
CREATE TABLE IF NOT EXISTS inventory (
    id INTEGER PRIMARY KEY,
    product_name TEXT,
    quantity INTEGER,
    price REAL
);
EOF

echo "دیتابیس موجودی ایجاد شد"

# افزودن محصول
add_product() {
    local name=$1
    local quantity=$2
    local price=$3
    
    sqlite3 "$DB_FILE" "INSERT INTO inventory (product_name, quantity, price) VALUES ('$name', $quantity, $price);"
    echo "محصول اضافه شد: $name"
}

# به‌روزرسانی موجودی
update_quantity() {
    local id=$1
    local quantity=$2
    
    sqlite3 "$DB_FILE" "UPDATE inventory SET quantity=$quantity WHERE id=$id;"
    echo "موجودی محصول $id به‌روزرسانی شد به $quantity"
}

# گزارش موجودی
inventory_report() {
    echo "گزارش موجودی:"
    sqlite3 "$DB_FILE" "SELECT * FROM inventory;"
    
    echo -e "\nارزش کل موجودی:"
    sqlite3 "$DB_FILE" "SELECT SUM(quantity * price) as total_value FROM inventory;"
}

# استفاده
add_product "لپتاپ" 10 15000
add_product "موبایل" 20 8000
add_product "کتاب" 50 50
inventory_report
update_quantity 1 8
inventory_report

# پاکسازی
rm "$DB_FILE"
