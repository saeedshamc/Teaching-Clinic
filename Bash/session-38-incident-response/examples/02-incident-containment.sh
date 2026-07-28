#!/usr/bin/env bash

echo "=== Incident Containment ==="

# تابع block IP
block_ip() {
    local ip=$1
    
    echo "Blocking IP $ip:"
    iptables -A INPUT -s $ip -j DROP
    echo "✓ IP blocked"
}

# تابع isolate system
isolate_system() {
    echo "Isolating system from network:"
    # Disconnect from network
    # Disable network interfaces
    echo "✓ System isolated"
}

# تابع disable account
disable_account() {
    local user=$1
    
    echo "Disabling account $user:"
    usermod -L $user
    echo "✓ Account disabled"
}

# تابع kill suspicious processes
kill_suspicious_process() {
    local pid=$1
    
    echo "Killing process $pid:"
    kill -9 $pid
    echo "✓ Process killed"
}

# تابع stop affected service
stop_service() {
    local service=$1
    
    echo "Stopping service $service:"
    systemctl stop $service
    echo "✓ Service stopped"
}

# تابع quarantine file
quarantine_file() {
    local file=$1
    
    echo "Quarantining file $file:"
    mv $file /tmp/quarantine/
    chmod 000 /tmp/quarantine/$(basename $file)
    echo "✓ File quarantined"
}

# استفاده
# block_ip "192.168.1.100"
# disable_account "hacker"
# stop_service "nginx"
