#!/usr/bin/env bash

# تمرین ۱: Backup Script
# هدف: یک اسکریپت بنویسید که:
# 1. full backup ایجاد کند
# 2. backup را verify کند
# 3. old backups را rotate کند

# راه‌حل:
echo "=== Backup Script ==="

# تابع create backup directory
create_backup_dir() {
    local backup_dir=$1
    
    echo "1. Creating backup directory: $backup_dir"
    mkdir -p $backup_dir
    echo "✓ Backup directory created"
}

# تابع full backup
full_backup() {
    local source=$1
    local backup_dir=$2
    
    local timestamp=$(date +%Y%m%d-%H%M%S)
    local backup_file="$backup_dir/full-$timestamp.tar.gz"
    
    echo "2. Creating full backup of $source:"
    tar -czf $backup_file $source
    
    if [ $? -eq 0 ]; then
        echo "✓ Full backup created: $backup_file"
        echo $backup_file
    else
        echo "✗ Backup failed"
        return 1
    fi
}

# تابع verify backup
verify_backup() {
    local backup_file=$1
    
    echo "3. Verifying backup: $backup_file"
    
    # Check if file exists
    if [ ! -f "$backup_file" ]; then
        echo "✗ Backup file does not exist"
        return 1
    fi
    
    # Verify tar integrity
    tar -tzf $backup_file > /dev/null 2>&1
    if [ $? -eq 0 ]; then
        echo "✓ Backup is valid"
        
        # Show backup size
        local size=$(du -h $backup_file | cut -f1)
        echo "Backup size: $size"
        
        # Show file count
        local count=$(tar -tzf $backup_file | wc -l)
        echo "Files in backup: $count"
        
        return 0
    else
        echo "✗ Backup is corrupted"
        return 1
    fi
}

# تابع backup rotation
backup_rotation() {
    local backup_dir=$1
    local keep_days=${2:-7}
    
    echo "4. Rotating backups (keep $keep_days days):"
    
    local deleted=0
    while IFS= read -r file; do
        rm -f "$file"
        echo "Deleted: $(basename $file)"
        ((deleted++))
    done < <(find $backup_dir -name "*.tar.gz" -mtime +$keep_days)
    
    echo "✓ Rotation completed: $deleted old backups removed"
}

# تابع list backups
list_backups() {
    local backup_dir=$1
    
    echo -e "\n5. Current backups:"
    ls -lh $backup_dir/*.tar.gz 2>/dev/null || echo "No backups found"
}

# تابع calculate RPO
calculate_rpo() {
    local backup_dir=$1
    
    echo -e "\n6. RPO Calculation:"
    
    # Find most recent backup
    local latest=$(ls -t $backup_dir/*.tar.gz 2>/dev/null | head -1)
    
    if [ -n "$latest" ]; then
        local backup_time=$(stat -c %Y $latest)
        local current_time=$(date +%s)
        local diff=$(( (current_time - backup_time) / 3600 ))
        
        echo "Latest backup: $(basename $latest)"
        echo "Hours since last backup: $diff"
        echo "RPO: $diff hours"
    else
        echo "No backups found"
    fi
}

# تابع backup summary
backup_summary() {
    local backup_dir=$1
    
    echo -e "\n=== Backup Summary ==="
    echo "Backup directory: $backup_dir"
    
    local total_size=$(du -sh $backup_dir 2>/dev/null | cut -f1)
    echo "Total size: $total_size"
    
    local count=$(ls $backup_dir/*.tar.gz 2>/dev/null | wc -l)
    echo "Number of backups: $count"
}

# اجرا
SOURCE_DIR="/var/www"
BACKUP_DIR="/tmp/backups"
KEEP_DAYS=7

create_backup_dir "$BACKUP_DIR"
BACKUP_FILE=$(full_backup "$SOURCE_DIR" "$BACKUP_DIR")

if [ -n "$BACKUP_FILE" ]; then
    verify_backup "$BACKUP_FILE"
    backup_rotation "$BACKUP_DIR" "$KEEP_DAYS"
    list_backups "$BACKUP_DIR"
    calculate_rpo "$BACKUP_DIR"
    backup_summary "$BACKUP_DIR"
fi
