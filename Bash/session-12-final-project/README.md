# جلسه ۱۲: پروژه نهایی - سیستم بکاپ‌گیری خودکار

## اهداف یادگیری

در این جلسه یاد می‌گیرید:
- چگونه تمام مفاهیم جلسات قبلی را ترکیب کنید
- ساخت یک اسکریپت واقعی و کاربردی
- بهترین شیوه‌های سازماندهی کد
- مستندسازی و نگهداری اسکریپت
- تست و دیباگ اسکریپت کامل

## پروژه: سیستم بکاپ‌گیری خودکار

در این پروژه، یک سیستم بکاپ‌گیری کامل می‌سازیم که:
- دایرکتوری‌های مشخص را بکاپ می‌گیرد
- فایل‌های قدیمی را پاک می‌کند
- لاگ می‌نویسد
- خطاها را مدیریت می‌کند
- قابل تنظیم با فایل کانفیگ است

## ساختار پروژه

```
backup-system/
├── backup.sh              # اسکریپت اصلی
├── config.conf            # فایل کانفیگ
├── backup.log             # فایل لاگ
└── backups/               # دایرکتوری بکاپ
    ├── daily/
    └── weekly/
```

## مثال ۱: اسکریپت کامل بکاپ

فایل [examples/backup-system.sh](examples/backup-system.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# ============================================
# سیستم بکاپ‌گیری خودکار
# نسخه: 1.0
# نویسنده: دوره آموزشی Bash
# ============================================

# تنظیمات
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
CONFIG_FILE="$SCRIPT_DIR/config.conf"
LOG_FILE="$SCRIPT_DIR/backup.log"
BACKUP_DIR="$SCRIPT_DIR/backups"

# بارگذاری کانفیگ
if [ -f "$CONFIG_FILE" ]; then
    source "$CONFIG_FILE"
else
    echo "خطا: فایل کانفیگ یافت نشد: $CONFIG_FILE"
    exit 1
fi

# توابع
log() {
    local level=$1
    shift
    local message="$@"
    local timestamp=$(date '+%Y-%m-%d %H:%M:%S')
    echo "[$timestamp] [$level] $message" >> "$LOG_FILE"
    echo "[$level] $message"
}

cleanup() {
    log "INFO" "پاکسازی فایل‌های موقت..."
    rm -f /tmp/backup_*.tmp
    log "INFO" "پاکسازی انجام شد"
}

trap cleanup EXIT INT TERM

check_backup_dir() {
    if [ ! -d "$BACKUP_DIR" ]; then
        log "INFO" "ایجاد دایرکتوری بکاپ: $BACKUP_DIR"
        mkdir -p "$BACKUP_DIR/daily"
        mkdir -p "$BACKUP_DIR/weekly"
    fi
}

backup_directory() {
    local source_dir=$1
    local backup_type=$2
    
    if [ ! -d "$source_dir" ]; then
        log "ERROR" "دایرکتوری منبع وجود ندارد: $source_dir"
        return 1
    fi
    
    local timestamp=$(date '+%Y%m%d_%H%M%S')
    local backup_name="backup_$(basename "$source_dir")_$timestamp.tar.gz"
    local backup_path="$BACKUP_DIR/$backup_type/$backup_name"
    
    log "INFO" "شروع بکاپ: $source_dir -> $backup_path"
    
    if tar -czf "$backup_path" "$source_dir" 2>/dev/null; then
        local size=$(du -h "$backup_path" | cut -f1)
        log "INFO" "بکاپ موفق: $backup_path (اندازه: $size)"
        return 0
    else
        log "ERROR" "بکاپ ناموفق: $source_dir"
        return 1
    fi
}

cleanup_old_backups() {
    local backup_type=$1
    local days=$2
    
    log "INFO" "پاکسازی بکاپ‌های قدیمی ($backup_type، قدیمی‌تر از $days روز)"
    
    find "$BACKUP_DIR/$backup_type" -name "*.tar.gz" -mtime +$days -delete
    
    local count=$(find "$BACKUP_DIR/$backup_type" -name "*.tar.gz" | wc -l)
    log "INFO" "تعداد بکاپ‌های باقی‌مانده: $count"
}

main() {
    log "INFO" "شروع سیستم بکاپ‌گیری"
    
    check_backup_dir
    
    # بکاپ روزانه
    log "INFO" "شروع بکاپ روزانه"
    for dir in "${DAILY_BACKUP_DIRS[@]}"; do
        backup_directory "$dir" "daily"
    done
    cleanup_old_backups "daily" 7
    
    # بکاپ هفتگی (فقط روز یکشنبه)
    if [ $(date +%u) -eq 7 ]; then
        log "INFO" "شروع بکاپ هفتگی"
        for dir in "${WEEKLY_BACKUP_DIRS[@]}"; do
            backup_directory "$dir" "weekly"
        done
        cleanup_old_backups "weekly" 30
    fi
    
    log "INFO" "پایان سیستم بکاپ‌گیری"
}

# اجرای اصلی
main
```

### توضیح خط به خط:

- `SCRIPT_DIR="$(cd ...)"` - مسیر اسکریپت را پیدا می‌کند
- `source "$CONFIG_FILE"` - کانفیگ را بارگذاری می‌کند
- `log()` - تابع لاگ‌نویسی با timestamp
- `cleanup()` - تابع پاکسازی
- `trap cleanup EXIT` - پاکسازی هنگام خروج
- `check_backup_dir()` - دایرکتوری بکاپ را بررسی/ایجاد می‌کند
- `backup_directory()` - بکاپ یک دایرکتوری را انجام می‌دهد
- `cleanup_old_backups()` - بکاپ‌های قدیمی را پاک می‌کند
- `main()` - تابع اصلی که همه را هماهنگ می‌کند

## فایل کانفیگ

فایل [examples/config.conf](examples/config.conf) را بررسی کنید:

```bash
# کانفیگ سیستم بکاپ

# دایرکتوری‌های بکاپ روزانه
DAILY_BACKUP_DIRS=(
    "/home/user/documents"
    "/home/user/projects"
)

# دایرکتوری‌های بکاپ هفتگی
WEEKLY_BACKUP_DIRS=(
    "/home/user/documents"
    "/home/user/photos"
)

# تنظیمات نگهداری
DAILY_RETENTION_DAYS=7
WEEKLY_RETENTION_DAYS=30
```

### توضیح:

- آرایه‌های Bash برای لیست دایرکتوری‌ها
- متغیرهای ساده برای تنظیمات نگهداری
- کامنت‌های فارسی برای مستندسازی

## نکات کلیدی پروژه

### ۱. سازماندهی کد

- توابع مجزا برای هر کار
- تابع main برای هماهنگی
- کامنت‌های واضح و فارسی

### ۲. مدیریت خطا

- بررسی وجود فایل/دایرکتوری قبل از استفاده
- لاگ‌نویسی برای ردیابی مشکلات
- trap برای پاکسازی

### ۳. قابلیت تنظیم

- فایل کانفیگ جداگانه
- آرایه‌ها برای لیست‌ها
- متغیرهای قابل تنظیم

### ۴. لاگ‌نویسی

- timestamp برای هر پیام
- سطوح لاگ (INFO, ERROR)
- ذخیره در فایل و نمایش در کنسول

## مثال ۲: سیستم مانیتورینگ ساده

فایل [examples/monitor.sh](examples/monitor.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# سیستم مانیتورینگ ساده

LOG_FILE="/tmp/monitor.log"
ALERT_THRESHOLD=90

log() {
    echo "$(date '+%Y-%m-%d %H:%M:%S') - $*" >> "$LOG_FILE"
    echo "$*"
}

check_disk() {
    local usage=$(df / | awk 'NR==2 {print $5}' | sed 's/%//')
    log "INFO" "استفاده دیسک: $usage%"
    
    if [ $usage -gt $ALERT_THRESHOLD ]; then
        log "ALERT" "هشدار: استفاده دیسک بالای $ALERT_THRESHOLD%"
        return 1
    fi
    return 0
}

check_memory() {
    local usage=$(free | awk 'NR==2 {printf "%.0f", $3/$2 * 100}')
    log "INFO" "استفاده حافظه: $usage%"
    
    if [ $usage -gt $ALERT_THRESHOLD ]; then
        log "ALERT" "هشدار: استفاده حافظه بالای $ALERT_THRESHOLD%"
        return 1
    fi
    return 0
}

check_cpu() {
    local load=$(uptime | awk -F'load average:' '{print $2}' | awk '{print $1}' | sed 's/,//')
    log "INFO" "Load average: $load"
    
    local cores=$(nproc)
    local load_int=$(echo $load | cut -d. -f1)
    
    if [ $load_int -gt $cores ]; then
        log "ALERT" "هشدار: Load بالا ($load > $cores cores)"
        return 1
    fi
    return 0
}

main() {
    log "INFO" "شروع مانیتورینگ"
    
    check_disk
    check_memory
    check_cpu
    
    log "INFO" "پایان مانیتورینگ"
}

main
```

### توضیح:

- بررسی دیسک، حافظه و CPU
- هشدار در صورت عبور از حد
- لاگ‌نویسی برای ردیابی
- استفاده از awk برای استخراج داده‌ها

## بهترین شیوه‌ها برای پروژه‌های واقعی

### ۱. مستندسازی

```bash
# ============================================
# نام پروژه
# توضیح کوتاه
# نسخه: X.X
# تاریخ: YYYY-MM-DD
# ============================================
```

### ۲. مدیریت خطا

```bash
# همیشه exit code را بررسی کنید
if command; then
    # موفق
else
    # ناموفق
fi
```

### ۳. لاگ‌نویسی

```bash
# تابع لاگ استاندارد
log() {
    echo "$(date) - $*" >> log_file
}
```

### ۴. پاکسازی

```bash
# همیشه trap برای پاکسازی
trap cleanup EXIT
```

### ۵. تنظیمات

```bash
# فایل کانفیگ جداگانه
source config.conf
```

## تمرین نهایی

### تمرین: سیستم مدیریت فایل‌ها

یک اسکریپت کامل بسازید که:
1. فایل‌های قدیمی را در دایرکتوری مشخص پیدا کند
2. آن‌ها را بر اساس نوع (تصویر، مستند، ویدیو) دسته‌بندی کند
3. هر دسته را در پوشه جداگانه منتقل کند
4. گزارشی از عملیات تولید کند
5. از فایل کانفیگ برای تنظیمات استفاده کند

فایل تمرین: [exercises/final-project.sh](exercises/final-project.sh)

## خلاصه دوره

تبریک می‌گوییم! شما دوره کامل Bash Scripting را به پایان رساندید.

### آنچه یاد گرفتید:

1. **جلسه ۱**: مقدمه‌ای بر Shell و Bash
2. **جلسه ۲**: دستورات پایه ترمینال
3. **جلسه ۳**: متغیرها، ورودی/خروجی
4. **جلسه ۴**: آرگومان‌ها و متغیرهای محیطی
5. **جلسه ۵**: عملگرها و شرط‌ها
6. **جلسه ۶**: حلقه‌ها
7. **جلسه ۷**: توابع
8. **جلسه ۸**: آرایه‌ها و رشته‌ها
9. **جلسه ۹**: فایل‌ها، ریدایرکت و pipe
10. **جلسه ۱۰**: مدیریت خطا
11. **جلسه ۱۱**: ابزارهای خط فرمان
12. **جلسه ۱۲**: پروژه نهایی

### قدم‌های بعدی:

- تمرین بیشتر با پروژه‌های واقعی
- یادگیری Bash پیشرفته
- مطالعه مستندات رسمی Bash
- مشارکت در پروژه‌های Open Source

موفق باشید! 🎉
