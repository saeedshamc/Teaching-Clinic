#!/usr/bin/env bash

echo "=== System Monitoring ==="

# تابع مانیتورینگ CPU
monitor_cpu() {
    echo "1. CPU Usage:"
    CPU_USAGE=$(top -bn1 | grep "Cpu(s)" | awk '{print $2}' | cut -d'%' -f1)
    echo "CPU: $CPU_USAGE%"
    
    if (( $(echo "$CPU_USAGE > 80" | bc -l) )); then
        echo "⚠ WARNING: CPU usage high"
    fi
}

# تابع مانیتورینگ Memory
monitor_memory() {
    echo -e "\n2. Memory Usage:"
    MEM_USAGE=$(free | awk 'NR==2 {printf "%.0f", $3/$2 * 100}')
    echo "Memory: $MEM_USAGE%"
    
    if [ $MEM_USAGE -gt 80 ]; then
        echo "⚠ WARNING: Memory usage high"
    fi
}

# تابع مانیتورینگ Disk
monitor_disk() {
    echo -e "\n3. Disk Usage:"
    df -h | awk '$NF=="/"{print $5}' | while read usage; do
        echo "Root: $usage"
        if [ ${usage%\%} -gt 80 ]; then
            echo "⚠ WARNING: Disk usage high"
        fi
    done
}

# تابع مانیتورینگ Load
monitor_load() {
    echo -e "\n4. Load Average:"
    LOAD=$(uptime | awk -F'load average:' '{print $2}')
    echo "Load: $LOAD"
}

# اجرا
monitor_cpu
monitor_memory
monitor_disk
monitor_load
