#!/usr/bin/env bash

echo "=== Incident Detection ==="

# تابع monitor auth logs
monitor_auth_logs() {
    echo "Monitoring authentication logs:"
    tail -f /var/log/auth.log | grep --line-buffered -i "failed\|invalid"
}

# تابع detect failed logins
detect_failed_logins() {
    echo "Detecting failed login attempts:"
    grep "Failed password" /var/log/auth.log | awk '{print $1, $2, $3, $11, $13}' | sort | uniq -c | sort -rn
}

# تابع detect suspicious IPs
detect_suspicious_ips() {
    echo "Detecting suspicious IPs:"
    grep "Failed password" /var/log/auth.log | awk '{print $11}' | tr -d ')' | sort | uniq -c | sort -rn | head -10
}

# تابع detect port scanning
detect_port_scan() {
    echo "Detecting port scanning attempts:"
    grep "port scan" /var/log/syslog | tail -20
}

# تابع detect file changes
detect_file_changes() {
    echo "Detecting file changes:"
    find /etc -type f -mtime -1 -ls
}

# تابع alert on anomaly
alert_anomaly() {
    local threshold=$1
    
    local failed_count=$(grep "Failed password" /var/log/auth.log | wc -l)
    
    if [ $failed_count -gt $threshold ]; then
        echo "ALERT: High number of failed logins: $failed_count"
        # Send alert notification
    fi
}

# استفاده
detect_failed_logins
detect_suspicious_ips
alert_anomaly 50
