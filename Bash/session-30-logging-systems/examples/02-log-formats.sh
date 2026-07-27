#!/usr/bin/env bash

echo "=== Log Formats ==="

# تابع log با structured format
log_structured() {
    local level=$1
    local component=$2
    local message=$3
    local timestamp=$(date '+%Y-%m-%d %H:%M:%S')
    
    echo "[$timestamp] [$level] [$component] $message"
}

# تابع log با JSON format
log_json() {
    local level=$1
    local message=$2
    local timestamp=$(date '+%Y-%m-%dT%H:%M:%S%z')
    
    echo "{\"timestamp\":\"$timestamp\",\"level\":\"$level\",\"message\":\"$message\"}"
}

# تابع log با key-value format
log_kv() {
    local level=$1
    shift
    local timestamp=$(date '+%Y-%m-%d %H:%M:%S')
    
    echo "timestamp=$timestamp level=$level $@"
}

# استفاده
log_structured "INFO" "auth" "User logged in"
log_json "INFO" "User logged in"
log_kv "INFO" "component=auth action=login user=admin"
