#!/usr/bin/env bash

# تمرین ۲: Backup Manager
# هدف: یک اسکریپت بنویسید که:
# 1. full و incremental backups انجام دهد
# 2. retention policy اعمال کند
# 3. restore test انجام دهد

# راه‌حل:
echo "=== Backup Manager ==="

SOURCE_DIR="/tmp/source"
BACKUP_DIR="/tmp/backups"
RESTORE_DIR="/tmp/restore"
RETENTION_DAYS=7

# ایجاد دایرکتوری‌ها
mkdir -p "$SOURCE_DIR" "$BACKUP_DIR" "$RESTORE_DIR"

# ایجاد فایل‌های تست
echo "Original data" > "$SOURCE_DIR/file1.txt"
echo "Original data" > "$SOURCE_DIR/file2.txt"

# تابع full backup
full_backup() {
    local source=$1
    local backup_dir=$2
    
    local timestamp=$(date '+%Y%m%d_%H%M%S')
    local backup_file="$backup_dir/full_${timestamp}.tar.gz"
    
    echo "Full backup: $backup_file"
    tar -czf "$backup_file" "$source"
    
    if [ $? -eq 0 ]; then
        echo "$backup_file" > "$backup_dir/last_full.txt"
        echo "✓ Full backup موفق"
        return 0
    else
        echo "✗ Full backup ناموفق"
        return 1
    fi
}

# تابع incremental backup
incremental_backup() {
    local source=$1
    local backup_dir=$2
    
    local last_full=$(cat "$backup_dir/last_full.txt" 2>/dev/null)
    if [ -z "$last_full" ]; then
        echo "هیچ full backup وجود ندارد"
        return 1
    fi
    
    local timestamp=$(date '+%Y%m%d_%H%M%S')
    local backup_file="$backup_dir/inc_${timestamp}.tar.gz"
    
    echo "Incremental backup: $backup_file"
    tar -czf "$backup_file" --newer="$last_full" "$source"
    
    if [ $? -eq 0 ]; then
        echo "✓ Incremental backup موفق"
        return 0
    else
        echo "✗ Incremental backup ناموفق"
        return 1
    fi
}

# تابع retention policy
apply_retention() {
    local backup_dir=$1
    local days=$2
    
    echo "Applying retention policy ($days days):"
    local count=$(find "$backup_dir" -name "*.tar.gz" -mtime +$days | wc -l)
    find "$backup_dir" -name "*.tar.gz" -mtime +$days -delete
    echo "✓ حذف $count فایل قدیمی"
}

# تابع restore test
test_restore() {
    local full_backup=$1
    local restore_dir=$2
    
    echo "Test restore: $full_backup -> $restore_dir"
    rm -rf "$restore_dir"
    mkdir -p "$restore_dir"
    
    tar -xzf "$full_backup" -C "$restore_dir"
    
    if [ $? -eq 0 ]; then
        echo "✓ Restore test موفق"
        ls -la "$restore_dir"
        return 0
    else
        echo "✗ Restore test ناموفق"
        return 1
    fi
}

# اجرا
echo "1. Full backup:"
full_backup "$SOURCE_DIR" "$BACKUP_DIR"

# تغییر داده
echo "Modified data" > "$SOURCE_DIR/file1.txt"
echo "New file" > "$SOURCE_DIR/file3.txt"

echo -e "\n2. Incremental backup:"
incremental_backup "$SOURCE_DIR" "$BACKUP_DIR"

echo -e "\n3. Apply retention policy:"
apply_retention "$BACKUP_DIR" "$RETENTION_DAYS"

echo -e "\n4. Test restore:"
LAST_FULL=$(cat "$BACKUP_DIR/last_full.txt")
test_restore "$LAST_FULL" "$RESTORE_DIR"

# پاکسازی
rm -rf "$SOURCE_DIR" "$BACKUP_DIR" "$RESTORE_DIR"
