#!/usr/bin/env bash

# تمرین نهایی: سیستم مدیریت فایل
# هدف: یک سیستم کامل مدیریت فایل بسازید که:
# 1. فایل‌های قدیمی را شناسایی کند
# 2. بر اساس نوع فایل دسته‌بندی کند
# 3. فایل‌ها را به دایرکتوری‌های مناسب منتقل کند
# 4. گزارش تولید کند
# 5. از دیتابیس برای ذخیره اطلاعات استفاده کند

# راه‌حل:
DB_FILE="/tmp/file_manager.db"
SOURCE_DIR="/tmp/source_files"
TARGET_DIR="/tmp/organized_files"

# ایجاد دایرکتوری‌ها
mkdir -p "$SOURCE_DIR" "$TARGET_DIR/images" "$TARGET_DIR/documents" "$TARGET_DIR/others"

# ایجاد دیتابیس
sqlite3 "$DB_FILE" << EOF
CREATE TABLE IF NOT EXISTS files (
    id INTEGER PRIMARY KEY,
    name TEXT,
    type TEXT,
    size INTEGER,
    source_path TEXT,
    target_path TEXT,
    processed INTEGER DEFAULT 0
);
EOF

echo "دیتابیس ایجاد شد"

# ایجاد فایل‌های نمونه
echo "ایجاد فایل‌های نمونه..."
echo "sample text" > "$SOURCE_DIR/file1.txt"
echo "sample text" > "$SOURCE_DIR/file2.txt"
echo "sample text" > "$SOURCE_DIR/file3.doc"
echo "sample image data" > "$SOURCE_DIR/image1.jpg"
echo "sample image data" > "$SOURCE_DIR/image2.png"
echo "other data" > "$SOURCE_DIR/other1.dat"

# تابع تشخیص نوع فایل
detect_file_type() {
    local filename=$1
    local extension="${filename##*.}"
    
    case "$extension" in
        jpg|jpeg|png|gif)
            echo "image"
            ;;
        txt|doc|docx|pdf)
            echo "document"
            ;;
        *)
            echo "other"
            ;;
    esac
}

# تابع پردازش فایل
process_file() {
    local file=$1
    local filename=$(basename "$file")
    local size=$(stat -f%z "$file" 2>/dev/null || stat -c%s "$file" 2>/dev/null || echo "0")
    local type=$(detect_file_type "$filename")
    
    # مسیر هدف
    local target_path="$TARGET_DIR/$type/$filename"
    
    # انتقال فایل
    mv "$file" "$target_path"
    
    # ذخیره در دیتابیس
    sqlite3 "$DB_FILE" "INSERT INTO files (name, type, size, source_path, target_path, processed) VALUES ('$filename', '$type', $size, '$file', '$target_path', 1);"
    
    echo "پردازش شد: $filename ($type)"
}

# پردازش تمام فایل‌ها
echo -e "\nپردازش فایل‌ها..."
for file in "$SOURCE_DIR"/*; do
    if [ -f "$file" ]; then
        process_file "$file"
    fi
done

# گزارش
echo -e "\n=== گزارش ==="
echo "فایل‌های پردازش شده:"
sqlite3 "$DB_FILE" "SELECT * FROM files;"

echo -e "\nتعداد بر اساس نوع:"
sqlite3 "$DB_FILE" "SELECT type, COUNT(*) FROM files GROUP BY type;"

echo -e "\nساختار دایرکتوری هدف:"
find "$TARGET_DIR" -type f

# پاکسازی
rm -rf "$SOURCE_DIR" "$TARGET_DIR" "$DB_FILE"

echo -e "\nپروژه نهایی کامل شد!"
