#!/usr/bin/env bash

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
CONFIG_FILE="$SCRIPT_DIR/config.conf"

source "$CONFIG_FILE"

# گزارش کلی
generate_report() {
    echo "=== گزارش کلی لاگ‌ها ==="
    
    # تعداد کل لاگ‌ها
    TOTAL=$(sqlite3 "$DB_PATH" "SELECT COUNT(*) FROM logs;")
    echo "تعداد کل لاگ‌ها: $TOTAL"
    
    # بر اساس level
    echo -e "\nتعداد بر اساس level:"
    sqlite3 "$DB_PATH" "SELECT level, COUNT(*) FROM logs GROUP BY level;"
    
    # بر اساس source
    echo -e "\nتعداد بر اساس source:"
    sqlite3 "$DB_PATH" "SELECT source, COUNT(*) FROM logs GROUP BY source;"
    
    # لاگ‌های اخیر
    echo -e "\n10 لاگ اخیر:"
    sqlite3 "$DB_PATH" "SELECT * FROM logs ORDER BY timestamp DESC LIMIT 10;"
}

# بررسی خطاها
check_errors() {
    echo -e "\n=== بررسی خطاها ==="
    
    ERROR_COUNT=$(sqlite3 "$DB_PATH" "SELECT COUNT(*) FROM logs WHERE level='ERROR';")
    echo "تعداد خطاها: $ERROR_COUNT"
    
    if [ $ERROR_COUNT -gt $ALERT_THRESHOLD_ERROR ]; then
        echo "⚠ هشدار: تعداد خطاها از حد آستانه بیشتر است"
        return 1
    else
        echo "✓ تعداد خطاها نرمال است"
        return 0
    fi
}

# لاگ‌های پرتکرار
find_frequent() {
    echo -e "\n=== لاگ‌های پرتکرار ==="
    
    sqlite3 "$DB_PATH" "SELECT message, COUNT(*) as count FROM logs GROUP BY message ORDER BY count DESC LIMIT 10;"
}

# اجرای اصلی
generate_report
check_errors
find_frequent
