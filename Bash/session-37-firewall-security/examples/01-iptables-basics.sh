#!/usr/bin/env bash

echo "=== iptables Basics ==="

# تابع نمایش rules
show_rules() {
    echo "Current iptables rules:"
    iptables -L -n -v
}

# تابع flush rules
flush_rules() {
    echo "Flushing all rules:"
    iptables -F
    iptables -X
    iptables -t nat -F
    iptables -t mangle -F
    echo "✓ All rules flushed"
}

# تابع set default policy
set_default_policy() {
    local policy=$1
    
    echo "Setting default policy to $policy:"
    iptables -P INPUT $policy
    iptables -P FORWARD $policy
    iptables -P OUTPUT ACCEPT
    echo "✓ Default policy set"
}

# تابع allow SSH
allow_ssh() {
    local port=${1:-22}
    
    echo "Allowing SSH on port $port:"
    iptables -A INPUT -p tcp --dport $port -j ACCEPT
    echo "✓ SSH allowed"
}

# تابع allow HTTP/HTTPS
allow_web() {
    echo "Allowing HTTP and HTTPS:"
    iptables -A INPUT -p tcp --dport 80 -j ACCEPT
    iptables -A INPUT -p tcp --dport 443 -j ACCEPT
    echo "✓ Web traffic allowed"
}

# استفاده
show_rules
# flush_rules
# set_default_policy DROP
# allow_ssh 22
# allow_web
