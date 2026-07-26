#!/usr/bin/env bash

# تابع بررسی سلامت سیستم
check_health() {
    echo "=== بررسی سلامت سیستم ==="
    
    # 1. فضای دیسک
    echo -e "\n1. فضای دیسک:"
    DISK_USAGE=$(df / | awk 'NR==2 {sub(/%/,"",$5); print $5}')
    if [ $DISK_USAGE -gt 80 ]; then
        echo "⚠ هشدار: استفاده دیسک $DISK_USAGE%"
    else
        echo "✓ استفاده دیسک $DISK_USAGE%"
    fi
    
    # 2. حافظه
    echo -e "\n2. حافظه:"
    MEM_USAGE=$(free | awk 'NR==2 {printf "%.0f", $3/$2 * 100}')
    if [ $MEM_USAGE -gt 80 ]; then
        echo "⚠ هشدار: استفاده حافظه $MEM_USAGE%"
    else
        echo "✓ استفاده حافظه $MEM_USAGE%"
    fi
    
    # 3. Load
    echo -e "\n3. Load average:"
    LOAD=$(uptime | awk -F'load average:' '{print $2}' | awk '{print $1}' | sed 's/,//')
    CORES=$(nproc)
    echo "Load: $LOAD (cores: $CORES)"
}

check_health
