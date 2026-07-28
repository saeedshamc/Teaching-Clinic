#!/usr/bin/env bash

# تمرین ۱: Detection Script
# هدف: یک اسکریپت بنویسید که:
# 1. auth logs را monitor کند
# 2. failed logins را detect کند
# 3. alert بفرستد

# راه‌حل:
echo "=== Incident Detection Script ==="

# تابع monitor auth logs
monitor_auth() {
    local log_file=${1:-/var/log/auth.log}
    
    echo "1. Monitoring auth logs: $log_file"
    
    # Check if log file exists
    if [ ! -f "$log_file" ]; then
        echo "Log file not found, creating sample"
        mkdir -p /tmp/logs
        cat > /tmp/logs/auth.log << 'EOF'
Jan 28 10:00:00 server sshd[1234]: Failed password for root from 192.168.1.100 port 22 ssh2
Jan 28 10:00:05 server sshd[1235]: Failed password for admin from 192.168.1.100 port 22 ssh2
Jan 28 10:00:10 server sshd[1236]: Failed password for user from 192.168.1.101 port 22 ssh2
Jan 28 10:00:15 server sshd[1237]: Accepted password for user from 192.168.1.50 port 22 ssh2
Jan 28 10:00:20 server sshd[1238]: Failed password for root from 192.168.1.100 port 22 ssh2
EOF
        log_file="/tmp/logs/auth.log"
    fi
    
    echo "✓ Monitoring $log_file"
}

# تابع detect failed logins
detect_failed() {
    local log_file=$1
    local threshold=${2:-3}
    
    echo -e "\n2. Detecting failed logins (threshold: $threshold):"
    
    # Count failed logins per IP
    local failed_ips=$(grep "Failed password" "$log_file" 2>/dev/null | \
        awk '{print $11}' | tr -d ')' | sort | uniq -c | sort -rn)
    
    echo "$failed_ips"
    
    # Check for suspicious IPs
    while read -r count ip; do
        if [ "$count" -ge "$threshold" ]; then
            echo "ALERT: Suspicious IP $ip - $count failed attempts"
            # Block IP
            echo "Blocking IP $ip:"
            # iptables -A INPUT -s $ip -j DROP
            echo "✓ IP $ip would be blocked"
        fi
    done <<< "$failed_ips"
}

# تابع detect brute force
detect_brute_force() {
    local log_file=$1
    
    echo -e "\n3. Detecting brute force attempts:"
    
    # Check for rapid successive failures
    local brute_force=$(grep "Failed password" "$log_file" 2>/dev/null | \
        awk '{print $1, $2, $11}' | tr -d ')' | \
        awk '{print $1, $2, $3}' | sort | uniq -c | sort -rn)
    
    echo "$brute_force"
}

# تابع send alert
send_alert() {
    local message=$1
    local severity=${2:-WARNING}
    
    echo -e "\n4. Sending alert:"
    echo "[$severity] $message"
    echo "✓ Alert sent (simulated)"
    
    # Log the alert
    echo "$(date '+%Y-%m-%d %H:%M:%S') [$severity] $message" >> /tmp/incident-alerts.log
}

# تابع generate report
generate_report() {
    echo -e "\n5. Detection Report:"
    echo "=================="
    echo "Scan completed at: $(date)"
    echo "Log file: $1"
    echo "Threshold: $2"
    echo "=================="
}

# تابع continuous monitoring
continuous_monitor() {
    local log_file=$1
    local interval=${2:-60}
    
    echo -e "\n6. Continuous monitoring (every ${interval}s):"
    echo "Press Ctrl+C to stop"
    
    while true; do
        detect_failed "$log_file" 3
        sleep $interval
    done
}

# اجرا
LOG_FILE="/var/log/auth.log"
THRESHOLD=3

monitor_auth "$LOG_FILE"
detect_failed "$LOG_FILE" "$THRESHOLD"
detect_brute_force "$LOG_FILE"
send_alert "Suspicious activity detected from 192.168.1.100" "CRITICAL"
generate_report "$LOG_FILE" "$THRESHOLD"

# Uncomment for continuous monitoring
# continuous_monitor "$LOG_FILE" 60
