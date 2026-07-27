#!/usr/bin/env bash

echo "=== Backup Restoration ==="

# تابع restore full backup
restore_full() {
    local backup_file=$1
    local restore_dir=$2
    
    echo "Restore full backup: $backup_file"
    mkdir -p "$restore_dir"
    
    tar -xzf "$backup_file" -C "$restore_dir"
    
    if [ $? -eq 0 ]; then
        echo "✓ Full restore موفق"
        return 0
    else
        echo "✗ Full restore ناموفق"
        return 1
    fi
}

# تابع restore incremental
restore_incremental() {
    local full_backup=$1
    local inc_backup=$2
    local restore_dir=$3
    
    echo "Restore incremental:"
    echo "1. Restore full backup"
    restore_full "$full_backup" "$restore_dir"
    
    echo "2. Apply incremental backup"
    tar -xzf "$inc_backup" -C "$restore_dir"
    
    if [ $? -eq 0 ]; then
        echo "✓ Incremental restore موفق"
        return 0
    else
        echo "✗ Incremental restore ناموفق"
        return 1
    fi
}

# استفاده
mkdir -p /tmp/source /tmp/backups /tmp/restore
echo "Original" > /tmp/source/file.txt
tar -czf /tmp/backups/full.tar.gz /tmp/source

echo "Modified" > /tmp/source/file.txt
tar -czf /tmp/backups/inc.tar.gz /tmp/source

restore_full "/tmp/backups/full.tar.gz" "/tmp/restore/full"
restore_incremental "/tmp/backups/full.tar.gz" "/tmp/backups/inc.tar.gz" "/tmp/restore/inc"

# پاکسازی
rm -rf /tmp/source /tmp/backups /tmp/restore
