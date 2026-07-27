#!/usr/bin/env bash

# تمرین ۱: Logger Function
# هدف: یک اسکریپت بنویسید که:
# 1. logger function با levels ایجاد کند
# 2. log به file و stdout بنویسد
# 3. timestamp و level داشته باشد

# راه‌حل:
echo "=== Logger Function ==="

LOG_FILE="/tmp/app.log"
LOG_LEVEL="INFO"  # DEBUG, INFO, WARNING, ERROR, CRITICAL

# تابع log
log() {
    local level=$1
    shift
    local message="$@"
    local timestamp=$(date '+%Y-%m-%d %H:%M:%S')
    
    # Check if level should be logged
    case $LOG_LEVEL in
        DEBUG)
            ;;
        INFO)
            if [ "$level" = "DEBUG" ]; then
                return 0
            fi
            ;;
        WARNING)
            if [ "$level" = "DEBUG" ] || [ "$level" = "INFO" ]; then
                return 0
            fi
            ;;
        ERROR)
            if [ "$level" = "DEBUG" ] || [ "$level" = "INFO" ] || [ "$level" = "WARNING" ]; then
                return 0
            fi
            ;;
        CRITICAL)
            if [ "$level" != "CRITICAL" ]; then
                return 0
            fi
            ;;
    esac
    
    # Log to stdout
    echo "[$timestamp] [$level] $message"
    
    # Log to file
    echo "[$timestamp] [$level] $message" >> "$LOG_FILE"
}

# توابع اختصاصی
log_debug() {
    log "DEBUG" "$@"
}

log_info() {
    log "INFO" "$@"
}

log_warning() {
    log "WARNING" "$@"
}

log_error() {
    log "ERROR" "$@"
}

log_critical() {
    log "CRITICAL" "$@"
}

# استفاده
echo "Logging with level: $LOG_LEVEL"
log_debug "Debug message (should not show if LOG_LEVEL=INFO)"
log_info "Application started"
log_warning "Memory usage high"
log_error "Database connection failed"
log_critical "System crash"

echo -e "\nLog file contents:"
cat "$LOG_FILE"

# پاکسازی
rm "$LOG_FILE"
