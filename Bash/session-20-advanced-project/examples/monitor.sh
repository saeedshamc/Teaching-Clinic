#!/usr/bin/env bash

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
CONFIG_FILE="$SCRIPT_DIR/config.conf"

source "$CONFIG_FILE"

# تابع مانیتورینگ
monitor() {
    echo "مانیتورینگ سیستم (هر $MONITOR_INTERVAL ثانیه)..."
    echo "برای توقف Ctrl+C را فشار دهید"
    
    while true; do
        echo -e "\n$(date): بررسی سیستم"
        
        # 1. فضای دیسک
        DISK_USAGE=$(df / | awk 'NR==2 {sub(/%/,"",$5); print $5}')
        echo "فضای دیسک: $DISK_USAGE%"
        
        # 2. حافظه
        MEM_USAGE=$(free | awk 'NR==2 {printf "%.0f", $3/$2 * 100}')
        echo "حافظه: $MEM_USAGE%"
        
        # 3. Load
        LOAD=$(uptime | awk -F'load average:' '{print $2}' | awk '{print $1}' | sed 's/,//')
        echo "Load: $LOAD"
        
        # 4. بررسی خطاها
        ERROR_COUNT=$(sqlite3 "$DB_PATH" "SELECT COUNT(*) FROM logs WHERE level='ERROR' AND timestamp > datetime('now', '-1 hour');" 2>/dev/null || echo "0")
        echo "خطاها در ساعت اخیر: $ERROR_COUNT"
        
        # Alert در صورت نیاز
        if [ $ERROR_COUNT -gt $ALERT_THRESHOLD_ERROR ]; then
            echo "⚠ Alert: تعداد خطاها زیاد است"
        fi
        
        sleep $MONITOR_INTERVAL
    done
}

# اجرای مانیتورینگ
monitor
