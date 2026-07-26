#!/usr/bin/env bash

# تمرین نهایی: سیستم مدیریت فایل‌ها
# هدف: یک اسکریپت کامل بسازید که:
# 1. فایل‌های قدیمی را در دایرکتوری مشخص پیدا کند
# 2. آن‌ها را بر اساس نوع (تصویر، مستند، ویدیو) دسته‌بندی کند
# 3. هر دسته را در پوشه جداگانه منتقل کند
# 4. گزارشی از عملیات تولید کند
# 5. از فایل کانفیگ برای تنظیمات استفاده کند

# راه‌حل:
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
CONFIG_FILE="$SCRIPT_DIR/file_manager.conf"
LOG_FILE="$SCRIPT_DIR/file_manager.log"
REPORT_FILE="$SCRIPT_DIR/file_manager_report.txt"

# بارگذاری کانفیگ
if [ -f "$CONFIG_FILE" ]; then
    source "$CONFIG_FILE"
else
    # کانفیگ پیش‌فرض
    SOURCE_DIR="/tmp/file_manager_test"
    DAYS_OLD=30
    ORGANIZE_BY_TYPE=true
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
    log "INFO" "پاکسازی..."
}

trap cleanup EXIT INT TERM

get_file_type() {
    local file=$1
    local extension="${file##*.}"
    
    case "$extension" in
        jpg|jpeg|png|gif|bmp)
            echo "image"
            ;;
        pdf|doc|docx|txt|odt)
            echo "document"
            ;;
        mp4|avi|mkv|mov|wmv)
            echo "video"
            ;;
        *)
            echo "other"
            ;;
    esac
}

organize_files() {
    local source_dir=$1
    local days_old=$2
    
    log "INFO" "شروع سازماندهی فایل‌ها از: $source_dir"
    
    # ایجاد پوشه‌های دسته‌بندی
    mkdir -p "$source_dir/images"
    mkdir -p "$source_dir/documents"
    mkdir -p "$source_dir/videos"
    mkdir -p "$source_dir/other"
    
    # پیدا کردن فایل‌های قدیمی
    local old_files=$(find "$source_dir" -type f -mtime +$days_old -not -path "$source_dir/images/*" -not -path "$source_dir/documents/*" -not -path "$source_dir/videos/*" -not -path "$source_dir/other/*")
    
    local moved_count=0
    local skipped_count=0
    
    for file in $old_files; do
        if [ -f "$file" ]; then
            local file_type=$(get_file_type "$file")
            local target_dir="$source_dir/$file_type"
            local filename=$(basename "$file")
            
            if mv "$file" "$target_dir/$filename" 2>/dev/null; then
                log "INFO" "منتقل شد: $filename -> $file_type/"
                echo "$filename -> $file_type/" >> "$REPORT_FILE"
                moved_count=$((moved_count + 1))
            else
                log "ERROR" "خطا در انتقال: $filename"
                skipped_count=$((skipped_count + 1))
            fi
        fi
    done
    
    log "INFO" "تعداد فایل‌های منتقل شده: $moved_count"
    log "INFO" "تعداد فایل‌های نادیده گرفته شده: $skipped_count"
    
    echo "گزارش نهایی:" >> "$REPORT_FILE"
    echo "تعداد فایل‌های منتقل شده: $moved_count" >> "$REPORT_FILE"
    echo "تعداد فایل‌های نادیده گرفته شده: $skipped_count" >> "$REPORT_FILE"
}

create_test_files() {
    local test_dir="/tmp/file_manager_test"
    mkdir -p "$test_dir"
    
    # ایجاد فایل‌های نمونه
    touch -d "40 days ago" "$test_dir/old_photo.jpg"
    touch -d "40 days ago" "$test_dir/old_doc.pdf"
    touch -d "40 days ago" "$test_dir/old_video.mp4"
    touch -d "40 days ago" "$test_dir/old_file.txt"
    touch -d "10 days ago" "$test_dir/new_photo.jpg"
    touch -d "10 days ago" "$test_dir/new_doc.pdf"
    
    echo "فایل‌های تست ایجاد شد در: $test_dir"
    ls -la "$test_dir"
}

main() {
    log "INFO" "شروع سیستم مدیریت فایل‌ها"
    
    # ایجاد فایل‌های تست
    create_test_files
    
    # سازماندهی فایل‌ها
    if [ "$ORGANIZE_BY_TYPE" = true ]; then
        organize_files "$SOURCE_DIR" "$DAYS_OLD"
    fi
    
    # نمایش گزارش
    echo -e "\nگزارش عملیات:"
    cat "$REPORT_FILE"
    
    log "INFO" "پایان سیستم مدیریت فایل‌ها"
    
    echo -e "\nبرای پاکسازی فایل‌های تست: rm -rf $SOURCE_DIR"
}

# اجرای اصلی
main
