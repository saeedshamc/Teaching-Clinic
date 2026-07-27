# جلسه ۳۰: سیستم‌های لاگ (Logging Systems)

## اهداف یادگیری

در این جلسه یاد می‌گیرید:
- مفاهیم پایه logging
- Log levels و formats
- Centralized logging
- Log rotation
- Log analysis
- Structured logging
- مثال‌های عملی

## مفاهیم پایه Logging

Logging ثبت رویدادهای سیستم است:
- **DEBUG** - اطلاعات debug
- **INFO** - اطلاعات عمومی
- **WARNING** - هشدارها
- **ERROR** - خطاها
- **CRITICAL** - خطاهای بحرانی

## Log Levels

```bash
# استفاده از log levels مناسب
echo "[INFO] Application started"
echo "[ERROR] Database connection failed"
```

## مثال ۱: Log Levels

فایل [examples/01-log-levels.sh](examples/01-log-levels.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Log Levels ==="

# تابع log با level
log() {
    local level=$1
    shift
    local message="$@"
    local timestamp=$(date '+%Y-%m-%d %H:%M:%S')
    
    echo "[$timestamp] [$level] $message"
}

# توابع log با levels مختلف
log_debug() {
    log "DEBUG" "$@"
}

log_info() {
    log "INFO" "$@"
}

log_warning() {
    log "WARNING" "$@"
}

log_error() {
    log "ERROR" "$@"
}

log_critical() {
    log "CRITICAL" "$@"
}

# استفاده
log_debug "Debug information"
log_info "Application started"
log_warning "Memory usage high"
log_error "Database connection failed"
log_critical "System crash imminent"
```

### توضیح خط به خط:

- `log` - تابع پایه logging
- timestamp برای هر log
- log levels مختلف
- توابع اختصاصی برای هر level

## Log Formats

```bash
# Structured log format
[timestamp] [level] [component] message

# JSON format
{"timestamp":"...","level":"INFO","message":"..."}
```

## مثال ۲: Log Formats

فایل [examples/02-log-formats.sh](examples/02-log-formats.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Log Formats ==="

# تابع log با structured format
log_structured() {
    local level=$1
    local component=$2
    local message=$3
    local timestamp=$(date '+%Y-%m-%d %H:%M:%S')
    
    echo "[$timestamp] [$level] [$component] $message"
}

# تابع log با JSON format
log_json() {
    local level=$1
    local message=$2
    local timestamp=$(date '+%Y-%m-%dT%H:%M:%S%z')
    
    echo "{\"timestamp\":\"$timestamp\",\"level\":\"$level\",\"message\":\"$message\"}"
}

# تابع log با key-value format
log_kv() {
    local level=$1
    shift
    local timestamp=$(date '+%Y-%m-%d %H:%M:%S')
    
    echo "timestamp=$timestamp level=$level $@"
}

# استفاده
log_structured "INFO" "auth" "User logged in"
log_json "INFO" "User logged in"
log_kv "INFO" "component=auth action=login user=admin"
```

### توضیح خط به خط:

- structured format با component
- JSON format برای parsing
- key-value format برای filtering
- timestamp در فرمت‌های مختلف

## Centralized Logging

```bash
# ارسال logs به سرور مرکزی
# استفاده از syslog
# log aggregation tools
```

## مثال ۳: Centralized Logging

فایل [examples/03-centralized-logging.sh](examples/03-centralized-logging.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Centralized Logging ==="

# تابع ارسال به syslog
log_syslog() {
    local level=$1
    local message=$2
    
    logger -p "user.$level" "$message"
}

# تابع ارسال به remote log server
log_remote() {
    local level=$1
    local message=$2
    local log_server="log.example.com"
    local log_port=514
    
    echo "[$level] $message" | nc -u $log_server $log_port
}

# تابع ارسال به file
log_file() {
    local level=$1
    local message=$2
    local log_file="/tmp/app.log"
    
    local timestamp=$(date '+%Y-%m-%d %H:%M:%S')
    echo "[$timestamp] [$level] $message" >> "$log_file"
}

# استفاده
log_syslog "info" "Application started"
log_file "INFO" "Application started"
# log_remote "INFO" "Application started"
```

### توضیح خط به خط:

- `logger` - ارسال به syslog
- `nc` - ارسال به remote server
- file logging برای local storage
- multiple destinations

## Log Rotation

```bash
# مدیریت size فایل‌های log
# rotation بر اساس time یا size
- compression و retention
```

## مثال ۴: Log Rotation

فایل [examples/04-log-rotation.sh](examples/04-log-rotation.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Log Rotation ==="

# تابع check و rotate log
rotate_log() {
    local log_file=$1
    local max_size_mb=10
    
    if [ ! -f "$log_file" ]; then
        return 0
    fi
    
    local size_mb=$(du -m "$log_file" | cut -f1)
    
    if [ $size_mb -gt $max_size_mb ]; then
        echo "Rotating log file: $log_file"
        
        local timestamp=$(date '+%Y%m%d_%H%M%S')
        mv "$log_file" "${log_file}.$timestamp"
        
        # Compress old log
        gzip "${log_file}.$timestamp"
        
        # Create new log file
        touch "$log_file"
        
        echo "✓ Log rotated"
    fi
}

# تابع cleanup old logs
cleanup_old_logs() {
    local log_dir=$1
    local retention_days=30
    
    echo "Cleaning up logs older than $retention_days days"
    find "$log_dir" -name "*.log.*" -mtime +$retention_days -delete
    echo "✓ Old logs cleaned up"
}

# استفاده
LOG_FILE="/tmp/app.log"
echo "Test log entry" >> "$LOG_FILE"
rotate_log "$LOG_FILE"
cleanup_old_logs "/tmp"
```

### توضیح خط به خط:

- check size قبل از rotation
- timestamp برای old logs
- gzip compression
- cleanup بر اساس retention

## Log Analysis

```bash
# تحلیل logs با grep و awk
- پیدا کردن error patterns
# statistics از logs
```

## مثال ۵: Log Analysis

فایل [examples/05-log-analysis.sh](examples/05-log-analysis.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Log Analysis ==="

# ایجاد sample log
cat > /tmp/sample.log << EOF
[2024-01-01 10:00:00] [INFO] Application started
[2024-01-01 10:00:01] [INFO] User logged in
[2024-01-01 10:00:02] [ERROR] Database connection failed
[2024-01-01 10:00:03] [INFO] Retrying connection
[2024-01-01 10:00:04] [ERROR] Database connection failed
[2024-01-01 10:00:05] [WARNING] Memory usage high
[2024-01-01 10:00:06] [INFO] Connection established
[2024-01-01 10:00:07] [ERROR] Query failed
EOF

# شمارش log levels
echo "1. شمارش log levels:"
echo "INFO: $(grep -c '\[INFO\]' /tmp/sample.log)"
echo "ERROR: $(grep -c '\[ERROR\]' /tmp/sample.log)"
echo "WARNING: $(grep -c '\[WARNING\]' /tmp/sample.log)"

# پیدا کردن errors
echo -e "\n2. Error logs:"
grep '\[ERROR\]' /tmp/sample.log

# پیدا کردن patterns
echo -e "\n3. Database errors:"
grep -i "database" /tmp/sample.log

# statistics
echo -e "\n4. Statistics:"
echo "Total logs: $(wc -l < /tmp/sample.log)"
echo "Error rate: $(grep -c '\[ERROR\]' /tmp/sample.log) / $(wc -l < /tmp/sample.log)"

# پاکسازی
rm /tmp/sample.log
```

### توضیح خط به خط:

- `grep -c` - شمارش pattern
- `grep -i` - case-insensitive
- statistics از logs
- error rate calculation

## نکات مهم

### ۱. Log Level مناسب

```bash
# از log level مناسب استفاده کنید
# بیش از حد logging = performance impact
```

### ۲. Sensitive Data

```bash
# هرگز sensitive data را log نکنید
# passwords, tokens, PII
```

### ۳. Performance

```bash
# asynchronous logging برای performance
# buffer برای کاهش I/O
```

## مثال ۶: Logging Best Practices

فایل [examples/06-logging-best-practices.sh](examples/06-logging-best-practices.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Logging Best Practices ==="

# 1. Log Levels
echo -e "\n1. Log Levels:"
echo "✓ از log level مناسب استفاده کنید"
echo "✓ DEBUG فقط در development"
echo "✓ INFO برای normal operations"
echo "✓ ERROR برای failures"

# 2. Sensitive Data
echo -e "\n2. Sensitive Data:"
echo "✓ هرگز passwords را log نکنید"
echo "✓ tokens را mask کنید"
echo "✓ PII را log نکنید"

# 3. Performance
echo -e "\n3. Performance:"
echo "✓ Asynchronous logging"
echo "✓ Buffer برای کاهش I/O"
echo "✓ Log rotation برای size management"

# 4. Structure
echo -e "\n4. Structure:"
echo "✓ Structured log format"
echo "✓ Consistent format"
echo "✓ Include context"

# 5. Retention
echo -e "\n5. Retention:"
echo "✓ Define retention policy"
echo "✓ Compress old logs"
echo "✓ Archive important logs"
```

### توضیح خط به خط:

- Log level مناسب
- محافظت از sensitive data
- Performance considerations
- Structured logging
- Retention policy

## نکات رایج اشتباهات (Common Pitfalls)

### ۱. بیش از حد Logging

❌ اشتباه:
```bash
# logging بیش از حد
```

✅ درست:
```bash
# logging با level مناسب
```

### ۲. Sensitive Data در Logs

❌ خطرناک:
```bash
echo "Password: $password"  - خطرناک
```

✅ درست:
```bash
echo "Password: ********"  - امن
```

### ۳. بدون Rotation

❌ اشتباه:
```bash
# بدون log rotation
```

✅ درست:
```bash
# log rotation و retention
```

## بهترین شیوه‌ها (Best Practices)

1. **Log level مناسب** - نه زیاد نه کم
2. **محافظت از sensitive data** - mask کردن
3. **Performance** - asynchronous logging
4. **Structured format** - برای parsing
5. **Retention policy** - cleanup منظم

## خلاصه جلسه

در این جلسه یاد گرفتیم:
- مفاهیم پایه logging
- Log levels و formats
- Centralized logging
- Log rotation
- Log analysis
- Best Practices
- نکات مهم و اشتباهات رایج

## تمرین‌ها

### تمرین ۱ (ساده): Logger Function

یک اسکریپت بنویسید که:
1. logger function با levels ایجاد کند
2. log به file و stdout بنویسد
3. timestamp و level داشته باشد

فایل تمرین: [exercises/exercise-01.sh](exercises/exercise-01.sh)

### تمرین ۲ (متوسط): Log Manager

یک اسکریپت بنویسید که:
1. log rotation انجام دهد
2. old logs را cleanup کند
3. log analysis انجام دهد

فایل تمرین: [exercises/exercise-02.sh](exercises/exercise-02.sh)

## جلسه بعد

در جلسه بعد، Backup Strategies را یاد می‌گیریم.
