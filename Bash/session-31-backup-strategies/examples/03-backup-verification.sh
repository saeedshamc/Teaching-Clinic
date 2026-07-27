#!/usr/bin/env bash

echo "=== Backup Verification ==="

# تابع verify backup
verify_backup() {
    local backup_file=$1
    
    echo "Verifying: $backup_file"
    
    # Check file exists
    if [ ! -f "$backup_file" ]; then
        echo "✗ فایل backup وجود ندارد"
        return 1
    fi
    
    # Check file size
    local size=$(du -h "$backup_file" | cut -f1)
    echo "Size: $size"
    
    # Check archive integrity
    if tar -tzf "$backup_file" > /dev/null 2>&1; then
        echo "✓ Archive integrity OK"
    else
        echo "✗ Archive corrupted"
        return 1
    fi
    
    # List contents
    echo "Contents:"
    tar -tzf "$backup_file" | head -5
    
    return 0
}

# تابع test restore
test_restore() {
    local backup_file=$1
    local restore_dir=$2
    
    echo "Test restore to: $restore_dir"
    mkdir -p "$restore_dir"
    
    tar -xzf "$backup_file" -C "$restore_dir"
    
    if [ $? -eq 0 ]; then
        echo "✓ Restore موفق"
        ls -la "$restore_dir"
        return 0
    else
        echo "✗ Restore ناموفق"
        return 1
    fi
}

# استفاده
mkdir -p /tmp/source /tmp/backups /tmp/restore
echo "Test" > /tmp/source/file.txt
tar -czf /tmp/backups/backup.tar.gz /tmp/source

verify_backup "/tmp/backups/backup.tar.gz"
test_restore "/tmp/backups/backup.tar.gz" "/tmp/restore"

# پاکسازی
rm -rf /tmp/source /tmp/backups /tmp/restore
