#!/usr/bin/env bash

echo "=== Backup Strategies ==="

# تابع full backup
full_backup() {
    local source=$1
    local destination=$2
    
    echo "Creating full backup of $source:"
    tar -czf $destination/full-$(date +%Y%m%d).tar.gz $source
    echo "✓ Full backup created"
}

# تابع incremental backup
incremental_backup() {
    local source=$1
    local destination=$2
    local last_backup=$3
    
    echo "Creating incremental backup:"
    find $source -newer $last_backup -print0 | \
        tar -czf $destination/inc-$(date +%Y%m%d).tar.gz --null -T -
    echo "✓ Incremental backup created"
}

# تابع differential backup
differential_backup() {
    local source=$1
    local destination=$2
    local full_backup=$3
    
    echo "Creating differential backup:"
    find $source -newer $full_backup -print0 | \
        tar -czf $destination/diff-$(date +%Y%m%d).tar.gz --null -T -
    echo "✓ Differential backup created"
}

# تابع backup rotation
backup_rotation() {
    local backup_dir=$1
    local keep_days=$2
    
    echo "Rotating backups (keep $keep_days days):"
    find $backup_dir -name "*.tar.gz" -mtime +$keep_days -delete
    echo "✓ Old backups removed"
}

# تابع verify backup
verify_backup() {
    local backup_file=$1
    
    echo "Verifying backup $backup_file:"
    tar -tzf $backup_file > /dev/null
    if [ $? -eq 0 ]; then
        echo "✓ Backup is valid"
    else
        echo "✗ Backup is corrupted"
    fi
}

# استفاده
# full_backup "/var/www" "/backup"
# backup_rotation "/backup" 7
# verify_backup "/backup/full-20240128.tar.gz"
