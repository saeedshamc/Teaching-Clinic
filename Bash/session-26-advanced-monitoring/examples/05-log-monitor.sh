#!/usr/bin/env bash

echo "=== Log Monitoring ==="

# تابع مانیتورینگ error logs
monitor_error_logs() {
    echo "1. Error Logs:"
    if [ -f /var/log/syslog ]; then
        ERROR_COUNT=$(grep -i "error" /var/log/syslog | wc -l)
        echo "Error count: $ERROR_COUNT"
        
        if [ $ERROR_COUNT -gt 10 ]; then
            echo "⚠ WARNING: Too many errors"
        fi
    else
        echo "Log file not found"
    fi
}

# تابع مانیتورینگ failed attempts
monitor_failed_attempts() {
    echo -e "\n2. Failed Login Attempts:"
    if [ -f /var/log/auth.log ]; then
        FAILED_COUNT=$(grep "Failed password" /var/log/auth.log | wc -l)
        echo "Failed attempts: $FAILED_COUNT"
        
        if [ $FAILED_COUNT -gt 5 ]; then
            echo "⚠ WARNING: Too many failed attempts"
        fi
    else
        echo "Auth log not found"
    fi
}

# تابع مانیتورینگ real-time
monitor_realtime() {
    echo -e "\n3. Real-time Monitoring:"
    echo "Monitoring /var/log/syslog (Ctrl+C to stop)"
    tail -f /var/log/syslog 2>/dev/null || echo "Cannot monitor"
}

# اجرا
monitor_error_logs
monitor_failed_attempts
# monitor_realtime  - برای مانیتورینگ real-time
