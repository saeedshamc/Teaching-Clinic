#!/usr/bin/env bash

echo "=== Routing ==="

# تابع نمایش routing table
show_routes() {
    echo "Routing Table:"
    ip route show
}

# تابع اضافه کردن route
add_route() {
    local network=$1
    local gateway=$2
    
    echo "Adding route: $network via $gateway"
    ip route add $network via $gateway
    echo "✓ Route added"
}

# تابع حذف route
delete_route() {
    local network=$1
    
    echo "Deleting route: $network"
    ip route del $network
    echo "✓ Route deleted"
}

# تابع enable IP forwarding
enable_forwarding() {
    echo "Enabling IP forwarding:"
    echo 1 > /proc/sys/net/ipv4/ip_forward
    echo "✓ IP forwarding enabled"
}

# تابع set default gateway
set_default_gateway() {
    local gateway=$1
    
    echo "Setting default gateway: $gateway"
    ip route add default via $gateway
    echo "✓ Default gateway set"
}

# استفاده
show_routes
# add_route "192.168.2.0/24" "192.168.1.1"
# set_default_gateway "192.168.1.1"
