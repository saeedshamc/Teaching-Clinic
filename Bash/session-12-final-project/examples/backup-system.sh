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
