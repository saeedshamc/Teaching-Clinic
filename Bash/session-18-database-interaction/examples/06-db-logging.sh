#!/usr/bin/env bash

DB_FILE="/tmp/logs.db"

# ایجاد جدول لاگ
sqlite3 "$DB_FILE" << EOF
CREATE TABLE IF NOT EXISTS logs (
    id INTEGER PRIMARY KEY,
    level TEXT,
    message TEXT,
    timestamp TEXT
);
EOF

# تابع لاگ
log_message() {
    local level=$1
    local message=$2
    local timestamp=$(date '+%Y-%m-%d %H:%M:%S')
    
    sqlite3 "$DB_FILE" "INSERT INTO logs (level, message, timestamp) VALUES ('$level', '$message', '$timestamp');"
    echo "[$timestamp] [$level] $message"
}

# استفاده
log_message "INFO" "اسکریپت شروع شد"
log_message "ERROR" "خطا در اتصال"
log_message "INFO" "اسکریپت تمام شد"

# نمایش لاگ‌ها
echo -e "\nلاگ‌ها:"
sqlite3 "$DB_FILE" "SELECT * FROM logs;"

# پاکسازی
rm "$DB_FILE"
