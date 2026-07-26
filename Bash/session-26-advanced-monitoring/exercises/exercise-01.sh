#!/usr/bin/env bash

# تمرین ۱: System Monitor
# هدف: یک اسکریپت بنویسید که:
# 1. CPU، Memory، Disk را مانیتور کند
# 2. alert برای usage بالا
# 3. گزارش تولید کند

# راه‌حل:
echo "=== System Monitor ==="
REPORT_FILE="/tmp/system_monitor_report.txt"

# خالی کردن فایل گزارش
> "$REPORT_FILE"

# تابع مانیتورینگ CPU
monitor_cpu() {
    echo "1. CPU Usage:" | tee -a "$REPORT_FILE"
    CPU_USAGE=$(top -bn1 | grep "Cpu(s)" | awk '{print $2}' | cut -d'%' -f1)
    echo "CPU: $CPU_USAGE%" | tee -a "$REPORT_FILE"
    
    if (( $(echo "$CPU_USAGE > 80" | bc -l) )); then
        echo "⚠ WARNING: CPU usage high" | tee -a "$REPORT_FILE"
        ALERT_CPU=1
    else
        echo "✓ CPU usage normal" | tee -a "$REPORT_FILE"
        ALERT_CPU=0
    fi
}

# تابع مانیتورینگ Memory
monitor_memory() {
    echo -e "\n2. Memory Usage:" | tee -a "$REPORT_FILE"
    MEM_USAGE=$(free | awk 'NR==2 {printf "%.0f", $3/$2 * 100}')
    echo "Memory: $MEM_USAGE%" | tee -a "$REPORT_FILE"
    
    if [ $MEM_USAGE -gt 80 ]; then
        echo "⚠ WARNING: Memory usage high" | tee -a "$REPORT_FILE"
        ALERT_MEM=1
    else
        echo "✓ Memory usage normal" | tee -a "$REPORT_FILE"
        ALERT_MEM=0
    fi
}

# تابع مانیتورینگ Disk
monitor_disk() {
    echo -e "\n3. Disk Usage:" | tee -a "$REPORT_FILE"
    DISK_USAGE=$(df -h | awk '$NF=="/"{print $5}' | cut -d'%' -f1)
    echo "Root: $DISK_USAGE%" | tee -a "$REPORT_FILE"
    
    if [ $DISK_USAGE -gt 80 ]; then
        echo "⚠ WARNING: Disk usage high" | tee -a "$REPORT_FILE"
        ALERT_DISK=1
    else
        echo "✓ Disk usage normal" | tee -a "$REPORT_FILE"
        ALERT_DISK=0
    fi
}

# تابع گزارش نهایی
generate_report() {
    echo -e "\n=== Summary ===" | tee -a "$REPORT_FILE"
    echo "CPU Alert: $([ $ALERT_CPU -eq 1 ] && echo 'YES' || echo 'NO')" | tee -a "$REPORT_FILE"
    echo "Memory Alert: $([ $ALERT_MEM -eq 1 ] && echo 'YES' || echo 'NO')" | tee -a "$REPORT_FILE"
    echo "Disk Alert: $([ $ALERT_DISK -eq 1 ] && echo 'YES' || echo 'NO')" | tee -a "$REPORT_FILE"
    
    if [ $ALERT_CPU -eq 0 ] && [ $ALERT_MEM -eq 0 ] && [ $ALERT_DISK -eq 0 ]; then
        echo "Overall Status: HEALTHY" | tee -a "$REPORT_FILE"
    else
        echo "Overall Status: WARNING" | tee -a "$REPORT_FILE"
    fi
}

# اجرا
monitor_cpu
monitor_memory
monitor_disk
generate_report

echo -e "\nگزارش در $REPORT_FILE ذخیره شد"
cat "$REPORT_FILE"

# پاکسازی
rm "$REPORT_FILE"
