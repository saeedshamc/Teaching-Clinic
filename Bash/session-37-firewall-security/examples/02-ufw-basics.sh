#!/usr/bin/env bash

echo "=== UFW Basics ==="

# تابع enable UFW
enable_ufw() {
    echo "Enabling UFW:"
    ufw --force enable
    echo "✓ UFW enabled"
}

# تابع allow port
allow_port() {
    local port=$1
    local proto=${2:-tcp}
    
    echo "Allowing port $port/$proto:"
    ufw allow $port/$proto
    echo "✓ Port allowed"
}

# تابع deny port
deny_port() {
    local port=$1
    
    echo "Denying port $port:"
    ufw deny $port
    echo "✓ Port denied"
}

# تابع allow from IP
allow_from_ip() {
    local ip=$1
    local port=$2
    
    echo "Allowing $ip to port $port:"
    ufw allow from $ip to any port $port
    echo "✓ Rule added"
}

# تابع show status
show_status() {
    echo "UFW Status:"
    ufw status verbose
}

# تابع reset
reset_ufw() {
    echo "Resetting UFW:"
    ufw --force reset
    echo "✓ UFW reset"
}

# استفاده
# enable_ufw
allow_port 22
allow_port 80
allow_port 443
show_status
# reset_ufw
