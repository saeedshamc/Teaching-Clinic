#!/usr/bin/env bash

# سیستم مانیتورینگ ساده

LOG_FILE="/tmp/monitor.log"
ALERT_THRESHOLD=90

log() {
    echo "$(date '+%Y-%m-%d %H:%M:%S') - $*" >> "$LOG_FILE"
    echo "$*"
}

check_disk() {
    local usage=$(df / | awk 'NR==2 {print $5}' | sed 's/%//')
    log "INFO" "استفاده دیسک: $usage%"
    
    if [ $usage -gt $ALERT_THRESHOLD ]; then
        log "ALERT" "هشدار: استفاده دیسک بالای $ALERT_THRESHOLD%"
        return 1
    fi
    return 0
}

check_memory() {
    local usage=$(free | awk 'NR==2 {printf "%.0f", $3/$2 * 100}')
    log "INFO" "استفاده حافظه: $usage%"
    
    if [ $usage -gt $ALERT_THRESHOLD ]; then
        log "ALERT" "هشدار: استفاده حافظه بالای $ALERT_THRESHOLD%"
        return 1
    fi
    return 0
}

check_cpu() {
    local load=$(uptime | awk -F'load average:' '{print $2}' | awk '{print $1}' | sed 's/,//')
    log "INFO" "Load average: $load"
    
    local cores=$(nproc)
    local load_int=$(echo $load | cut -d. -f1)
    
    if [ $load_int -gt $cores ]; then
        log "ALERT" "هشدار: Load بالا ($load > $cores cores)"
        return 1
    fi
    return 0
}

main() {
    log "INFO" "شروع مانیتورینگ"
    
    check_disk
    check_memory
    check_cpu
    
    log "INFO" "پایان مانیتورینگ"
}

main
