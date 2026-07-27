#!/usr/bin/env bash

echo "=== Backup Automation ==="

# تابع backup directory
backup_dir() {
    local source=$1
    local backup_dir=$2
    local backup_name=$3
    
    mkdir -p "$backup_dir"
    
    local timestamp=$(date '+%Y%m%d_%H%M%S')
    local backup_file="$backup_dir/${backup_name}_${timestamp}.tar.gz"
    
    echo "Backup: $source -> $backup_file"
    tar -czf "$backup_file" "$source"
    
    if [ $? -eq 0 ]; then
        echo "✓ Backup موفق"
        echo "$backup_file" > /tmp/last-backup-file
        return 0
    else
        echo "✗ Backup ناموفق"
        return 1
    fi
}

# تابع scheduled backup
scheduled_backup() {
    local source=$1
    local backup_dir=$2
    
    echo "Scheduled backup:"
    echo "اضافه به crontab:"
    echo "0 2 * * * /path/to/backup-script.sh"
    
    # ایجاد cron job
    # (echo "0 2 * * * $0 $source $backup_dir" | crontab -)
}

# استفاده
mkdir -p /tmp/test_data /tmp/backups
echo "Test" > /tmp/test_data/file.txt
backup_dir "/tmp/test_data" "/tmp/backups" "data"
scheduled_backup "/tmp/test_data" "/tmp/backups"

# پاکسازی
rm -rf /tmp/test_data /tmp/backups
