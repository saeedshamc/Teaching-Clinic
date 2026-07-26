#!/usr/bin/env bash

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
CONFIG_FILE="$SCRIPT_DIR/config.conf"

source "$CONFIG_FILE"

# تابع ارسال ایمیل
send_email_alert() {
    local subject=$1
    local message=$2
    
    echo "ارسال alert به $ALERT_EMAIL"
    echo "Subject: $subject"
    echo "Message: $message"
    
    # در عمل، از mail یا sendmail استفاده کنید
    # echo "$message" | mail -s "$subject" "$ALERT_EMAIL"
}

# تابع ارسال alert دیتابیس
send_db_alert() {
    local type=$1
    local message=$2
    local timestamp=$(date '+%Y-%m-%d %H:%M:%S')
    
    sqlite3 "$DB_PATH" "INSERT INTO alerts (timestamp, type, message, sent) VALUES ('$timestamp', '$type', '$message', 0);"
}

# بررسی و ارسال alertهای ارسال نشده
check_pending_alerts() {
    echo "بررسی alertهای ارسال نشده..."
    
    PENDING=$(sqlite3 "$DB_PATH" "SELECT id, type, message FROM alerts WHERE sent=0;")
    
    if [ -n "$PENDING" ]; then
        echo "$PENDING" | while read id type message; do
            send_email_alert "Alert: $type" "$message"
            sqlite3 "$DB_PATH" "UPDATE alerts SET sent=1 WHERE id=$id;"
        done
    else
        echo "هیچ alert ارسال نشده‌ای وجود ندارد"
    fi
}

# اجرای اصلی
check_pending_alerts
