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
