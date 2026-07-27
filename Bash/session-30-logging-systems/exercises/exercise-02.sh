#!/usr/bin/env bash

# تمرین ۲: Log Manager
# هدف: یک اسکریپت بنویسید که:
# 1. log rotation انجام دهد
# 2. old logs را cleanup کند
# 3. log analysis انجام دهد

# راه‌حل:
echo "=== Log Manager ==="

LOG_DIR="/tmp/logs"
LOG_FILE="$LOG_DIR/app.log"
MAX_SIZE_MB=1
RETENTION_DAYS=7

# ایجاد دایرکتوری
mkdir -p "$LOG_DIR"

# تابع log
log() {
    local level=$1
    shift
    local message="$@"
    local timestamp=$(date '+%Y-%m-%d %H:%M:%S')
    
    echo "[$timestamp] [$level] $message" >> "$LOG_FILE"
}

# تابع log rotation
rotate_log() {
    if [ ! -f "$LOG_FILE" ]; then
        return 0
    fi
    
    local size_kb=$(du -k "$LOG_FILE" | cut -f1)
    local size_mb=$(( size_kb / 1024 ))
    
    if [ $size_mb -ge $MAX_SIZE_MB ]; then
        echo "Rotating log file (size: ${size_mb}MB)"
        
        local timestamp=$(date '+%Y%m%d_%H%M%S')
        mv "$LOG_FILE" "${LOG_FILE}.${timestamp}"
        
        # Compress
        gzip "${LOG_FILE}.${timestamp}"
        
        # Create new log file
        touch "$LOG_FILE"
        
        echo "✓ Log rotated"
    fi
}

# تابع cleanup old logs
cleanup_logs() {
    echo "Cleaning up logs older than $RETENTION_DAYS days"
    local count=$(find "$LOG_DIR" -name "*.log.*.gz" -mtime +$RETENTION_DAYS | wc -l)
    find "$LOG_DIR" -name "*.log.*.gz" -mtime +$RETENTION_DAYS -delete
    echo "✓ Cleaned up $count old log files"
}

# تابع log analysis
analyze_logs() {
    echo "=== Log Analysis ==="
    
    # Find all log files
    local log_files=$(find "$LOG_DIR" -name "*.log*" -type f)
    
    if [ -z "$log_files" ]; then
        echo "No log files found"
        return 0
    fi
    
    # Count total lines
    local total_lines=0
    for file in $log_files; do
        if [ "${file: -3}" = ".gz" ]; then
            lines=$(zcat "$file" | wc -l)
        else
            lines=$(wc -l < "$file")
        fi
        total_lines=$(( total_lines + lines ))
    done
    echo "Total log entries: $total_lines"
    
    # Count by level (current log only)
    if [ -f "$LOG_FILE" ]; then
        echo -e "\nCurrent log levels:"
        echo "INFO: $(grep -c '\[INFO\]' "$LOG_FILE" 2>/dev/null || echo 0)"
        echo "WARNING: $(grep -c '\[WARNING\]' "$LOG_FILE" 2>/dev/null || echo 0)"
        echo "ERROR: $(grep -c '\[ERROR\]' "$LOG_FILE" 2>/dev/null || echo 0)"
        echo "CRITICAL: $(grep -c '\[CRITICAL\]' "$LOG_FILE" 2>/dev/null || echo 0)"
    fi
}

# ایجاد sample logs
echo "Creating sample logs..."
for i in {1..100}; do
    if [ $(( i % 10 )) -eq 0 ]; then
        log "ERROR" "Error occurred at iteration $i"
    elif [ $(( i % 5 )) -eq 0 ]; then
        log "WARNING" "Warning at iteration $i"
    else
        log "INFO" "Processing iteration $i"
    fi
done

# اجرا operations
rotate_log
cleanup_logs
analyze_logs

# پاکسازی
rm -rf "$LOG_DIR"
