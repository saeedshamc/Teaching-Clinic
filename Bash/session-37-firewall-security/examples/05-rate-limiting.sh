#!/usr/bin/env bash

echo "=== Rate Limiting ==="

# تابع limit connection rate
limit_connections() {
    local rate=$1
    local burst=$2
    
    echo "Limiting connections to $rate/sec burst $burst:"
    iptables -A INPUT -m limit --limit $rate --limit-burst $burst -j ACCEPT
    iptables -A INPUT -j DROP
    echo "✓ Rate limiting configured"
}

# تابع limit new connections
limit_new_connections() {
    local rate=$1
    
    echo "Limiting new connections to $rate/minute:"
    iptables -A INPUT -p tcp --syn -m limit --limit $rate/minute --limit-burst 5 -j ACCEPT
    iptables -A INPUT -p tcp --syn -j DROP
    echo "✓ New connections limited"
}

# تابع limit ping requests
limit_ping() {
    local rate=$1
    
    echo "Limiting ping to $rate/second:"
    iptables -A INPUT -p icmp --icmp-type echo-request -m limit --limit $rate/second -j ACCEPT
    iptables -A INPUT -p icmp --icmp-type echo-request -j DROP
    echo "✓ Ping limited"
}

# تابع limit per IP
limit_per_ip() {
    local connections=$1
    
    echo "Limiting to $connections connections per IP:"
    iptables -A INPUT -p tcp --dport 80 -m connlimit --connlimit-above $connections -j REJECT
    echo "✓ Per-IP limit configured"
}

# استفاده
limit_connections "25/minute" "100"
limit_new_connections "10"
limit_ping "1"
limit_per_ip "10"
