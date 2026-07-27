#!/usr/bin/env bash

# تمرین ۱: Backup Script
# هدف: یک اسکریپت بنویسید که:
# 1. directory را backup کند
# 2. backup را verify کند
# 3. report تولید کند

# راه‌حل:
echo "=== Backup Script ==="

SOURCE_DIR="/tmp/source"
BACKUP_DIR="/tmp/backups"
REPORT_FILE="/tmp/backup_report.txt"

# ایجاد دایرکتوری‌ها
mkdir -p "$SOURCE_DIR" "$BACKUP_DIR"

# ایجاد فایل‌های تست
echo "Important data 1" > "$SOURCE_DIR/file1.txt"
echo "Important data 2" > "$SOURCE_DIR/file2.txt"
echo "Important data 3" > "$SOURCE_DIR/file3.txt"

# تابع backup
backup_directory() {
    local source=$1
    local backup_dir=$2
    
    local timestamp=$(date '+%Y%m%d_%H%M%S')
    local backup_file="$backup_dir/backup_${timestamp}.tar.gz"
    
    echo "Backup: $source -> $backup_file"
    tar -czf "$backup_file" "$source"
    
    if [ $? -eq 0 ]; then
        echo "✓ Backup موفق"
        echo "$backup_file"
        return 0
    else
        echo "✗ Backup ناموفق"
        return 1
    fi
}

# تابع verify
verify_backup() {
    local backup_file=$1
    
    echo "Verifying: $backup_file"
    
    if [ ! -f "$backup_file" ]; then
        echo "✗ فایل backup وجود ندارد"
        return 1
    fi
    
    local size=$(du -h "$backup_file" | cut -f1)
    echo "Size: $size"
    
    if tar -tzf "$backup_file" > /dev/null 2>&1; then
        echo "✓ Archive integrity OK"
        return 0
    else
        echo "✗ Archive corrupted"
        return 1
    fi
}

# تابع generate report
generate_report() {
    local backup_file=$1
    local status=$2
    
    echo "=== Backup Report ===" > "$REPORT_FILE"
    echo "Date: $(date)" >> "$REPORT_FILE"
    echo "Source: $SOURCE_DIR" >> "$REPORT_FILE"
    echo "Backup: $backup_file" >> "$REPORT_FILE"
    echo "Status: $status" >> "$REPORT_FILE"
    
    if [ "$status" = "SUCCESS" ]; then
        local size=$(du -h "$backup_file" | cut -f1)
        echo "Size: $size" >> "$REPORT_FILE"
        echo "Files: $(tar -tzf "$backup_file" | wc -l)" >> "$REPORT_FILE"
    fi
    
    echo "✓ Report ایجاد شد: $REPORT_FILE"
    cat "$REPORT_FILE"
}

# اجرا
echo "1. ایجاد backup:"
BACKUP_FILE=$(backup_directory "$SOURCE_DIR" "$BACKUP_DIR")

if [ $? -eq 0 ]; then
    echo -e "\n2. Verify backup:"
    if verify_backup "$BACKUP_FILE"; then
        echo -e "\n3. Generate report:"
        generate_report "$BACKUP_FILE" "SUCCESS"
    else
        generate_report "$BACKUP_FILE" "FAILED"
    fi
else
    generate_report "" "FAILED"
fi

# پاکسازی
rm -rf "$SOURCE_DIR" "$BACKUP_DIR" "$REPORT_FILE"
