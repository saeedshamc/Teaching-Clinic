#!/usr/bin/env bash

echo "=== Intrusion Detection ==="

# تابع install fail2ban
install_fail2ban() {
    echo "Installing Fail2Ban:"
    apt update && apt install -y fail2ban
    systemctl enable fail2ban
    systemctl start fail2ban
    echo "✓ Fail2Ban installed"
}

# تابع create jail config
create_jail() {
    local service=$1
    local maxretry=$2
    local bantime=$3
    
    cat > /etc/fail2ban/jail.local << EOF
[$service]
enabled = true
port = http,https
filter = $service
logpath = /var/log/$service/access.log
maxretry = $maxretry
bantime = $bantime
EOF
    
    echo "✓ Jail created for $service"
}

# تابع check banned IPs
check_banned() {
    echo "Banned IPs:"
    fail2ban-client status
}

# تابع unban IP
unban_ip() {
    local ip=$1
    local jail=${2:-sshd}
    
    echo "Unbanning $ip from $jail:"
    fail2ban-client set $jail unbanip $ip
    echo "✓ IP unbanned"
}

# تابع monitor logs
monitor_logs() {
    echo "Monitoring auth logs:"
    tail -f /var/log/auth.log
}

# استفاده
# install_fail2ban
# create_jail "nginx" "5" "3600"
# check_banned
# unban_ip "192.168.1.100"
