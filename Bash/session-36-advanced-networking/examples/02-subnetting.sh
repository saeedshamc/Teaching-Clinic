#!/usr/bin/env bash

echo "=== Subnetting ==="

# تابع calculate network info
calculate_network() {
    local ip=$1
    local cidr=$2
    
    echo "IP: $ip/$cidr"
    
    # Convert to binary (simplified)
    local mask=$(( 0xffffffff << (32 - cidr) ))
    
    # Calculate network address
    local network=$(ipcalc -n $ip/$cidr | cut -d= -f2)
    local broadcast=$(ipcalc -b $ip/$cidr | cut -d= -f2)
    local first_host=$(ipcalc -h $ip/$cidr | cut -d= -f2)
    local last_host=$(ipcalc -H $ip/$cidr | cut -d= -f2)
    
    echo "Network: $network"
    echo "Broadcast: $broadcast"
    echo "First Host: $first_host"
    echo "Last Host: $last_host"
    
    # Calculate hosts
    local hosts=$(( 2**(32-cidr) - 2 ))
    echo "Available Hosts: $hosts"
}

# تابع check IP in subnet
check_ip_in_subnet() {
    local ip=$1
    local subnet=$2
    local cidr=$3
    
    echo "Checking if $ip is in $subnet/$cidr"
    
    if ipcalc -c $ip/$cidr > /dev/null 2>&1; then
        echo "✓ IP is in subnet"
    else
        echo "✗ IP is not in subnet"
    fi
}

# استفاده
calculate_network "192.168.1.100" "24"
check_ip_in_subnet "192.168.1.50" "192.168.1.0" "24"
