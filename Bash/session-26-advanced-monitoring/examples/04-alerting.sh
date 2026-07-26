#!/usr/bin/env bash

echo "=== Alerting ==="

# تابع ارسال alert
send_alert() {
    local severity=$1
    local message=$2
    
    echo "[$(date '+%Y-%m-%d %H:%M:%S')] [$severity] $message"
    
    # در عمل، از email یا Slack استفاده کنید
    # echo "$message" | mail -s "Alert: $severity" admin@example.com
}

# تابع بررسی و alert
check_and_alert() {
    local metric=$1
    local value=$2
    local threshold=$3
    local severity=$4
    
    if (( $(echo "$value > $threshold" | bc -l) )); then
        send_alert "$severity" "$metric is $value (threshold: $threshold)"
    fi
}

# مانیتورینگ با alert
CPU_USAGE=$(top -bn1 | grep "Cpu(s)" | awk '{print $2}' | cut -d'%' -f1)
check_and_alert "CPU" "$CPU_USAGE" 80 "WARNING"

MEM_USAGE=$(free | awk 'NR==2 {printf "%.0f", $3/$2 * 100}')
check_and_alert "Memory" "$MEM_USAGE" 80 "WARNING"

DISK_USAGE=$(df -h | awk '$NF=="/"{print $5}' | cut -d'%' -f1)
check_and_alert "Disk" "$DISK_USAGE" 80 "WARNING"
