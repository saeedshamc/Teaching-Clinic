#!/usr/bin/env bash

# تمرین ۱: Basic Firewall
# هدف: یک اسکریپت بنویسید که:
# 1. default policy را DROP کند
# 2. SSH و HTTP را allow کند
# 3. logging را enable کند

# راه‌حل:
echo "=== Basic Firewall Setup ==="

# تابع setup default policy
setup_default_policy() {
    echo "1. Setting default policy to DROP:"
    iptables -P INPUT DROP
    iptables -P FORWARD DROP
    iptables -P OUTPUT ACCEPT
    echo "✓ Default policy set to DROP"
}

# تابع allow loopback
allow_loopback() {
    echo "2. Allowing loopback traffic:"
    iptables -A INPUT -i lo -j ACCEPT
    iptables -A OUTPUT -o lo -j ACCEPT
    echo "✓ Loopback allowed"
}

# تابع allow established connections
allow_established() {
    echo "3. Allowing established connections:"
    iptables -A INPUT -m conntrack --ctstate ESTABLISHED,RELATED -j ACCEPT
    echo "✓ Established connections allowed"
}

# تابع allow SSH
allow_ssh() {
    local port=${1:-22}
    
    echo "4. Allowing SSH on port $port:"
    iptables -A INPUT -p tcp --dport $port -j ACCEPT
    echo "✓ SSH allowed"
}

# تابع allow HTTP/HTTPS
allow_web() {
    echo "5. Allowing HTTP and HTTPS:"
    iptables -A INPUT -p tcp --dport 80 -j ACCEPT
    iptables -A INPUT -p tcp --dport 443 -j ACCEPT
    echo "✓ Web traffic allowed"
}

# تابع enable logging
enable_logging() {
    echo "6. Enabling logging:"
    iptables -A INPUT -j LOG --log-prefix "FIREWALL-DROP: " --log-level 4
    echo "✓ Logging enabled"
}

# تابع show rules
show_rules() {
    echo -e "\n7. Current firewall rules:"
    iptables -L -n -v --line-numbers
}

# تابع save rules
save_rules() {
    echo -e "\n8. Saving rules:"
    iptables-save > /tmp/firewall.rules
    echo "✓ Rules saved to /tmp/firewall.rules"
}

# تابع restore rules
restore_rules() {
    echo "Restoring rules from backup:"
    iptables-restore < /tmp/firewall.rules
    echo "✓ Rules restored"
}

# تابع cleanup
cleanup() {
    echo -e "\n9. Cleanup:"
    iptables -F
    iptables -X
    iptables -P INPUT ACCEPT
    iptables -P FORWARD ACCEPT
    echo "✓ Firewall reset to defaults"
}

# اجرا
setup_default_policy
allow_loopback
allow_established
allow_ssh 22
allow_web
enable_logging
show_rules
save_rules

# Uncomment to cleanup
# cleanup
