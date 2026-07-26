#!/usr/bin/env bash

# تمرین ۲: Log Monitor
# هدف: یک اسکریپت بنویسید که:
# 1. error logs را مانیتور کند
# 2. failed attempts را شناسایی کند
# 3. alert ارسال کند

# راه‌حل:
echo "=== Log Monitor ==="

# تابع مانیتورینگ error logs
monitor_error_logs() {
    echo "1. Error Logs:"
    if [ -f /var/log/syslog ]; then
        ERROR_COUNT=$(grep -i "error" /var/log/syslog | wc -l)
        echo "Error count: $ERROR_COUNT"
        
        if [ $ERROR_COUNT -gt 10 ]; then
            echo "⚠ ALERT: Too many errors ($ERROR_COUNT)"
            echo "[$(date '+%Y-%m-%d %H:%M:%S')] [ALERT] Error count: $ERROR_COUNT (threshold: 10)"
            ALERT_ERROR=1
        else
            echo "✓ Error count normal"
            ALERT_ERROR=0
        fi
    else
        echo "Log file not found, creating sample log"
        echo "Sample error message" > /tmp/sample_syslog
        ALERT_ERROR=0
    fi
}

# تابع مانیتورینگ failed attempts
monitor_failed_attempts() {
    echo -e "\n2. Failed Login Attempts:"
    if [ -f /var/log/auth.log ]; then
        FAILED_COUNT=$(grep "Failed password" /var/log/auth.log | wc -l)
        echo "Failed attempts: $FAILED_COUNT"
        
        if [ $FAILED_COUNT -gt 5 ]; then
            echo "⚠ ALERT: Too many failed attempts ($FAILED_COUNT)"
            echo "[$(date '+%Y-%m-%d %H:%M:%S')] [ALERT] Failed attempts: $FAILED_COUNT (threshold: 5)"
            ALERT_FAILED=1
        else
            echo "✓ Failed attempts normal"
            ALERT_FAILED=0
        fi
    else
        echo "Auth log not found"
        ALERT_FAILED=0
    fi
}

# تابع مانیتورینگ recent errors
monitor_recent_errors() {
    echo -e "\n3. Recent Errors (last 10 minutes):"
    if [ -f /var/log/syslog ]; then
        RECENT_ERRORS=$(grep -i "error" /var/log/syslog | tail -10)
        if [ -n "$RECENT_ERRORS" ]; then
            echo "$RECENT_ERRORS"
        else
            echo "No recent errors"
        fi
    fi
}

# تابع گزارش نهایی
generate_log_report() {
    echo -e "\n=== Log Summary ==="
    echo "Error Alert: $([ $ALERT_ERROR -eq 1 ] && echo 'YES' || echo 'NO')"
    echo "Failed Login Alert: $([ $ALERT_FAILED -eq 1 ] && echo 'YES' || echo 'NO')"
    
    if [ $ALERT_ERROR -eq 0 ] && [ $ALERT_FAILED -eq 0 ]; then
        echo "Overall Status: HEALTHY"
    else
        echo "Overall Status: ALERT"
    fi
}

# اجرا
monitor_error_logs
monitor_failed_attempts
monitor_recent_errors
generate_log_report

# پاکسازی
rm -f /tmp/sample_syslog
