#!/usr/bin/env bash

# تمرین ۲: Advanced Firewall
# هدف: یک اسکریپت بنویسید که:
# 1. rate limiting را configure کند
# 2. IP filtering را implement کند
# 3. intrusion detection را setup کند

# راه‌حل:
echo "=== Advanced Firewall Setup ==="

# تابع setup rate limiting
setup_rate_limiting() {
    echo "1. Setting up rate limiting:"
    
    # Limit new connections
    iptables -A INPUT -p tcp --syn -m limit --limit 10/minute --limit-burst 5 -j ACCEPT
    iptables -A INPUT -p tcp --syn -j DROP
    
    # Limit ping requests
    iptables -A INPUT -p icmp --icmp-type echo-request -m limit --limit 1/second -j ACCEPT
    iptables -A INPUT -p icmp --icmp-type echo-request -j DROP
    
    # Limit connections per IP
    iptables -A INPUT -p tcp --dport 80 -m connlimit --connlimit-above 10 -j REJECT
    
    echo "✓ Rate limiting configured"
}

# تابع setup IP filtering
setup_ip_filtering() {
    echo -e "\n2. Setting up IP filtering:"
    
    # Allow trusted subnet
    iptables -A INPUT -s 192.168.1.0/24 -j ACCEPT
    
    # Block specific IPs (example)
    # iptables -A INPUT -s 10.0.0.100 -j DROP
    
    # Allow specific MAC
    # iptables -A INPUT -m mac --mac-source 00:11:22:33:44:55 -j ACCEPT
    
    echo "✓ IP filtering configured"
}

# تابع setup port filtering
setup_port_filtering() {
    echo -e "\n3. Setting up port filtering:"
    
    # Allow SSH from specific subnet only
    iptables -A INPUT -p tcp -s 192.168.1.0/24 --dport 22 -j ACCEPT
    iptables -A INPUT -p tcp --dport 22 -j DROP
    
    # Allow web traffic
    iptables -A INPUT -p tcp --dport 80 -j ACCEPT
    iptables -A INPUT -p tcp --dport 443 -j ACCEPT
    
    echo "✓ Port filtering configured"
}

# تابع setup intrusion detection
setup_intrusion_detection() {
    echo -e "\n4. Setting up intrusion detection:"
    
    # Check if fail2ban is installed
    if command -v fail2ban-client &> /dev/null; then
        echo "Fail2Ban is already installed"
    else
        echo "Installing Fail2Ban:"
        apt update && apt install -y fail2ban
        systemctl enable fail2ban
        systemctl start fail2ban
        echo "✓ Fail2Ban installed"
    fi
    
    # Create jail for SSH
    cat > /etc/fail2ban/jail.local << 'EOF'
[DEFAULT]
bantime = 3600
findtime = 600
maxretry = 5

[sshd]
enabled = true
port = ssh
filter = sshd
logpath = /var/log/auth.log
maxretry = 3

[nginx-http-auth]
enabled = true
port = http,https
filter = nginx-http-auth
logpath = /var/log/nginx/error.log
maxretry = 5
EOF
    
    systemctl restart fail2ban
    echo "✓ Fail2Ban configured"
}

# تابع show status
show_status() {
    echo -e "\n5. Firewall status:"
    iptables -L -n -v --line-numbers
    
    echo -e "\nFail2Ban status:"
    fail2ban-client status
}

# تابع test firewall
test_firewall() {
    echo -e "\n6. Testing firewall:"
    echo "Testing SSH access:"
    echo "ssh -p 22 localhost"
    
    echo -e "\nTesting HTTP access:"
    echo "curl http://localhost"
    
    echo -e "\nTesting rate limiting:"
    echo "for i in {1..20}; do ping -c 1 localhost; done"
}

# تابع backup configuration
backup_config() {
    echo -e "\n7. Backing up configuration:"
    
    # Backup iptables rules
    iptables-save > /tmp/iptables-backup-$(date +%Y%m%d-%H%M%S).rules
    
    # Backup fail2ban config
    cp /etc/fail2ban/jail.local /tmp/fail2ban-backup-$(date +%Y%m%d-%H%M%S).conf
    
    echo "✓ Configuration backed up"
}

# تابع cleanup
cleanup() {
    echo -e "\n8. Cleanup:"
    iptables -F
    iptables -X
    iptables -P INPUT ACCEPT
    iptables -P FORWARD ACCEPT
    iptables -P OUTPUT ACCEPT
    echo "✓ Firewall reset"
}

# اجرا
setup_rate_limiting
setup_ip_filtering
setup_port_filtering
setup_intrusion_detection
show_status
backup_config

# Uncomment to test and cleanup
# test_firewall
# cleanup
