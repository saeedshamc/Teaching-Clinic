#!/usr/bin/env bash

echo "=== Network Filtering ==="

# تابع allow from subnet
allow_subnet() {
    local subnet=$1
    
    echo "Allowing from subnet $subnet:"
    iptables -A INPUT -s $subnet -j ACCEPT
    echo "✓ Subnet allowed"
}

# تابع block IP
block_ip() {
    local ip=$1
    
    echo "Blocking IP $ip:"
    iptables -A INPUT -s $ip -j DROP
    echo "✓ IP blocked"
}

# تابع allow specific MAC
allow_mac() {
    local mac=$1
    
    echo "Allowing MAC $mac:"
    iptables -A INPUT -m mac --mac-source $mac -j ACCEPT
    echo "✓ MAC allowed"
}

# تابع block MAC
block_mac() {
    local mac=$1
    
    echo "Blocking MAC $mac:"
    iptables -A INPUT -m mac --mac-source $mac -j DROP
    echo "✓ MAC blocked"
}

# تابع allow from interface
allow_interface() {
    local interface=$1
    
    echo "Allowing from interface $interface:"
    iptables -A INPUT -i $interface -j ACCEPT
    echo "✓ Interface allowed"
}

# استفاده
allow_subnet "192.168.1.0/24"
# block_ip "10.0.0.100"
# allow_mac "00:11:22:33:44:55"
