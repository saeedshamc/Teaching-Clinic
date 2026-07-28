#!/usr/bin/env bash

echo "=== NAT Configuration ==="

# تابع enable MASQUERADE
enable_masquerade() {
    local interface=$1
    
    echo "Enabling MASQUERADE on $interface"
    iptables -t nat -A POSTROUTING -o $interface -j MASQUERADE
    echo "✓ MASQUERADE enabled"
}

# تابع enable IP forwarding
enable_ip_forwarding() {
    echo "Enabling IP forwarding:"
    echo 1 > /proc/sys/net/ipv4/ip_forward
    echo "✓ IP forwarding enabled"
}

# تابع port forwarding
port_forward() {
    local external_port=$1
    local internal_ip=$2
    local internal_port=$3
    
    echo "Port forwarding: $external_port -> $internal_ip:$internal_port"
    iptables -t nat -A PREROUTING -p tcp --dport $external_port -j DNAT \
        --to-destination $internal_ip:$internal_port
    echo "✓ Port forwarding configured"
}

# تابع show NAT rules
show_nat_rules() {
    echo "NAT Rules:"
    iptables -t nat -L -n -v
}

# استفاده
enable_ip_forwarding
enable_masquerade "eth0"
# port_forward "8080" "192.168.1.100" "80"
show_nat_rules
