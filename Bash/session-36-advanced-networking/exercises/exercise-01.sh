#!/usr/bin/env bash

# تمرین ۱: Network Setup
# هدف: یک اسکریپت بنویسید که:
# 1. VLAN ایجاد کند
# 2. route اضافه کند
# 3. NAT را enable کند

# راه‌حل:
echo "=== Network Setup ==="

# تابع ایجاد VLAN
setup_vlan() {
    local interface=$1
    local vlan_id=$2
    local ip=$3
    
    echo "1. Setting up VLAN $vlan_id on $interface"
    
    # Load 8021q module
    modprobe 8021q
    
    # Create VLAN interface
    vconfig add $interface $vlan_id
    
    # Assign IP
    ifconfig ${interface}.${vlan_id} $ip netmask 255.255.255.0 up
    
    echo "✓ VLAN $vlan_id created with IP $ip"
    
    # Show VLAN
    echo "VLAN status:"
    cat /proc/net/vlan/config
}

# تابع اضافه کردن route
setup_route() {
    local network=$1
    local gateway=$2
    
    echo -e "\n2. Adding route: $network via $gateway"
    ip route add $network via $gateway
    echo "✓ Route added"
    
    # Show routing table
    echo "Routing table:"
    ip route show
}

# تابع enable NAT
setup_nat() {
    local interface=$1
    
    echo -e "\n3. Enabling NAT on $interface"
    
    # Enable IP forwarding
    echo 1 > /proc/sys/net/ipv4/ip_forward
    echo "✓ IP forwarding enabled"
    
    # Enable MASQUERADE
    iptables -t nat -A POSTROUTING -o $interface -j MASQUERADE
    echo "✓ MASQUERADE enabled"
    
    # Show NAT rules
    echo "NAT rules:"
    iptables -t nat -L -n -v
}

# تابع cleanup
cleanup() {
    echo -e "\n4. Cleanup:"
    # Remove VLAN
    vconfig rem eth0.100 2>/dev/null
    # Remove NAT rule
    iptables -t nat -D POSTROUTING -o eth0 -j MASQUERADE 2>/dev/null
    echo "✓ Cleanup completed"
}

# اجرا
setup_vlan "eth0" "100" "192.168.100.1"
# setup_route "192.168.200.0/24" "192.168.100.254"
setup_nat "eth0"

# Uncomment to cleanup
# cleanup
