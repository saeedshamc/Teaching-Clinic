#!/usr/bin/env bash

echo "=== Centralized Logging ==="

# تابع ارسال به syslog
log_syslog() {
    local level=$1
    local message=$2
    
    logger -p "user.$level" "$message"
}

# تابع ارسال به remote log server
log_remote() {
    local level=$1
    local message=$2
    local log_server="log.example.com"
    local log_port=514
    
    echo "[$level] $message" | nc -u $log_server $log_port
}

# تابع ارسال به file
log_file() {
    local level=$1
    local message=$2
    local log_file="/tmp/app.log"
    
    local timestamp=$(date '+%Y-%m-%d %H:%M:%S')
    echo "[$timestamp] [$level] $message" >> "$log_file"
}

# استفاده
log_syslog "info" "Application started"
log_file "INFO" "Application started"
# log_remote "INFO" "Application started"
