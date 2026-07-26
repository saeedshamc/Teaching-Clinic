# جلسه ۲۰: پروژه پیشرفته نهایی (Advanced Final Project)

## اهداف یادگیری

در این جلسه یاد می‌گیرید:
- ترکیب تمام مفاهیم یادگرفته شده
- ساخت یک پروژه کامل و عملی
- مدیریت خطاها و لاگ‌برداری
- استفاده از دیتابیس
- مانیتورینگ و گزارش‌دهی
- بهترین شیوه‌ها

## پروژه: سیستم مدیریت لاگ و مانیتورینگ

این پروژه یک سیستم کامل برای مدیریت لاگ‌ها و مانیتورینگ سیستم است که شامل موارد زیر می‌شود:

1. جمع‌آوری لاگ‌ها از منابع مختلف
2. ذخیره در دیتابیس SQLite
3. تحلیل و گزارش‌دهی
4. مانیتورینگ real-time
5. alerting

## ساختار پروژه

```
log-monitor/
├── config.conf           - تنظیمات
├── log-collector.sh      - جمع‌آوری لاگ
├── log-analyzer.sh       - تحلیل لاگ
├── monitor.sh            - مانیتورینگ
├── alert.sh              - ارسال alert
└── database.db           - دیتابیس
```

## مثال ۱: فایل تنظیمات

فایل [examples/config.conf](examples/config.conf) را بررسی کنید:

```conf
# تنظیمات سیستم مدیریت لاگ

# مسیرهای لاگ
LOG_PATHS=(
    "/var/log/syslog"
    "/var/log/auth.log"
    "/var/log/apache2/access.log"
)

# تنظیمات دیتابیس
DB_PATH="/tmp/log_monitor.db"

# تنظیمات alerting
ALERT_EMAIL="admin@example.com"
ALERT_THRESHOLD_ERROR=10
ALERT_THRESHOLD_WARNING=20

# تنظیمات مانیتورینگ
MONITOR_INTERVAL=60
LOG_RETENTION_DAYS=30
```

### توضیح:

- `LOG_PATHS` - مسیرهای فایل‌های لاگ
- `DB_PATH` - مسیر دیتابیس
- `ALERT_EMAIL` - ایمیل برای alert
- `ALERT_THRESHOLD` - حد آستانه برای alert
- `MONITOR_INTERVAL` - فاصله مانیتورینگ (ثانیه)

## مثال ۲: جمع‌آوری لاگ

فایل [examples/log-collector.sh](examples/log-collector.sh) را بررسی کنید:

```bash
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
```

### توضیح خط به خط:

- `source "$CONFIG_FILE"` - بارگذاری تنظیمات
- `init_db` - ایجاد جداول دیتابیس
- `log_to_db` - ذخیره لاگ در دیتابیس
- `collect_from_file` - خواندن از فایل لاگ
- `collect_all` - جمع‌آوری از تمام منابع

## مثال ۳: تحلیل لاگ

فایل [examples/log-analyzer.sh](examples/log-analyzer.sh) را بررسی کنید:

```bash
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
```

### توضیح خط به خط:

- `generate_report` - گزارش کلی
- `check_errors` - بررسی تعداد خطاها
- `find_frequent` - یافتن لاگ‌های پرتکرار
- استفاده از SQL برای تحلیل

## مثال ۴: مانیتورینگ

فایل [examples/monitor.sh](examples/monitor.sh) را بررسی کنید:

```bash
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
```

### توضیح خط به خط:

- حلقه while برای مانیتورینگ مداوم
- بررسی دیسک، حافظه، load
- بررسی خطاها در ساعت اخیر
- alert در صورت عبور از_limit

## مثال ۵: ارسال Alert

فایل [examples/alert.sh](examples/alert.sh) را بررسی کنید:

```bash
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
```

### توضیح خط به خط:

- `send_email_alert` - ارسال ایمیل
- `send_db_alert` - ذخیره alert در دیتابیس
- `check_pending_alerts` - بررسی و ارسال alertهای ارسال نشده

## مثال ۶: اسکریپت اصلی

فایل [examples/main.sh](examples/main.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
CONFIG_FILE="$SCRIPT_DIR/config.conf"

source "$CONFIG_FILE"

# تابع help
show_help() {
    echo "استفاده: $0 [گزینه]"
    echo "گزینه‌ها:"
    echo "  collect   - جمع‌آوری لاگ‌ها"
    echo "  analyze   - تحلیل لاگ‌ها"
    echo "  monitor   - مانیتورینگ سیستم"
    echo "  alert     - بررسی alertها"
    echo "  all       - اجرای تمام عملیات"
}

# اجرای عملیات
case "$1" in
    collect)
        "$SCRIPT_DIR/log-collector.sh"
        ;;
    analyze)
        "$SCRIPT_DIR/log-analyzer.sh"
        ;;
    monitor)
        "$SCRIPT_DIR/monitor.sh"
        ;;
    alert)
        "$SCRIPT_DIR/alert.sh"
        ;;
    all)
        echo "اجرای تمام عملیات..."
        "$SCRIPT_DIR/log-collector.sh"
        "$SCRIPT_DIR/log-analyzer.sh"
        "$SCRIPT_DIR/alert.sh"
        ;;
    *)
        show_help
        ;;
esac
```

### توضیح خط به خط:

- اسکریپت اصلی برای مدیریت تمام عملیات
- case statement برای انتخاب عملیات
- فراخوانی اسکریپت‌های دیگر

## نکات مهم

### ۱. مدیریت خطاها

```bash
set -e  - خروج در صورت خطا
set -u  - خطا در صورت متغیر تعریف نشده
set -o pipefail  - خطا در pipeline
```

### ۲. لاگ‌برداری

```bash
log() {
    echo "[$(date '+%Y-%m-%d %H:%M:%S')] $1"
}
```

### ۳. تنظیمات

```bash
# استفاده از فایل تنظیمات
source config.conf
```

### ۴. دیتابیس

```bash
# استفاده از SQLite برای ذخیره‌سازی
sqlite3 db.db "QUERY"
```

## خلاصه پروژه

این پروژه شامل:
- جمع‌آوری لاگ از منابع مختلف
- ذخیره در دیتابیس
- تحلیل و گزارش‌دهی
- مانیتورینگ real-time
- سیستم alerting
- مدیریت خطاها
- تنظیمات قابل تنظیم

## تمرین نهایی

فایل تمرین: [exercises/final-project.sh](exercises/final-project.sh)

یک سیستم کامل مدیریت فایل بسازید که:
1. فایل‌های قدیمی را شناسایی کند
2. بر اساس نوع فایل دسته‌بندی کند
3. فایل‌ها را به دایرکتوری‌های مناسب منتقل کند
4. گزارش تولید کند
5. از دیتابیس برای ذخیره اطلاعات استفاده کند

## خلاصه دوره

در این ۲۰ جلسه یاد گرفتیم:
- مقدمه و دستورات پایه
- متغیرها و I/O
- آرگومان‌ها و environment
- عملگرها و شرط‌ها
- حلقه‌ها
- توابع
- آرایه‌ها و رشته‌ها
- فایل‌ها و pipe
- مدیریت خطا
- ابزارهای خط فرمان
- پروژه نهایی اول
- عبارات منظم
- مدیریت پروسه‌ها
- مبانی شبکه
- پردازش متن پیشرفته
- مدیریت سیستم
- تعامل با دیتابیس
- Web scraping
- پروژه پیشرفته نهایی

تبریک! شما دوره کامل Bash Scripting را به پایان رساندید! 🎉
