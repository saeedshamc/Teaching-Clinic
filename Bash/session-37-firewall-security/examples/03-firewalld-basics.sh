#!/usr/bin/env bash

echo "=== firewalld Basics ==="

# تابع start firewalld
start_firewalld() {
    echo "Starting firewalld:"
    systemctl start firewalld
    systemctl enable firewalld
    echo "✓ firewalld started"
}

# تابع add service
add_service() {
    local service=$1
    
    echo "Adding service $service:"
    firewall-cmd --permanent --add-service=$service
    firewall-cmd --reload
    echo "✓ Service added"
}

# تابع add port
add_port() {
    local port=$1
    local proto=${2:-tcp}
    
    echo "Adding port $port/$proto:"
    firewall-cmd --permanent --add-port=${port}/${proto}
    firewall-cmd --reload
    echo "✓ Port added"
}

# تابع show zones
show_zones() {
    echo "Firewall zones:"
    firewall-cmd --list-all-zones
}

# تابع show active zone
show_active_zone() {
    echo "Active zone:"
    firewall-cmd --get-active-zones
}

# تابع show rules
show_rules() {
    echo "Current rules:"
    firewall-cmd --list-all
}

# استفاده
# start_firewalld
add_service ssh
add_service http
add_port 8080 tcp
show_rules
