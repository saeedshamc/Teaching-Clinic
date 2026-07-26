#!/usr/bin/env bash

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
CONFIG_FILE="$SCRIPT_DIR/config.conf"

# بارگذاری تنظیمات
source "$CONFIG_FILE"

# ایجاد دیتابیس
init_db() {
    sqlite3 "$DB_PATH" << EOF
CREATE TABLE IF NOT EXISTS logs (
    id INTEGER PRIMARY KEY,
    timestamp TEXT,
    level TEXT,
    source TEXT,
    message TEXT
);
CREATE TABLE IF NOT EXISTS alerts (
    id INTEGER PRIMARY KEY,
    timestamp TEXT,
    type TEXT,
    message TEXT,
    sent INTEGER DEFAULT 0
);
EOF
}

# تابع لاگ
log_to_db() {
    local level=$1
    local source=$2
    local message=$3
    local timestamp=$(date '+%Y-%m-%d %H:%M:%S')
    
    sqlite3 "$DB_PATH" "INSERT INTO logs (timestamp, level, source, message) VALUES ('$timestamp', '$level', '$source', '$message');"
}

# جمع‌آوری لاگ از فایل
collect_from_file() {
    local file=$1
    local source=$(basename "$file")
    
    if [ -f "$file" ]; then
        echo "جمع‌آوری از $file"
        
        while IFS= read -r line; do
            # تشخیص level
            if echo "$line" | grep -qi "error"; then
                level="ERROR"
            elif echo "$line" | grep -qi "warning"; then
                level="WARNING"
            else
                level="INFO"
            fi
            
            log_to_db "$level" "$source" "$line"
        done < "$file"
    else
        echo "فایل $file وجود ندارد"
    fi
}

# جمع‌آوری از تمام منابع
collect_all() {
    echo "جمع‌آوری لاگ‌ها..."
    
    for log_path in "${LOG_PATHS[@]}"; do
        collect_from_file "$log_path"
    done
    
    echo "جمع‌آوری کامل شد"
}

# اجرای اصلی
init_db
collect_all
