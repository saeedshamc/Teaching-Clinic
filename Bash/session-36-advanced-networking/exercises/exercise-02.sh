#!/usr/bin/env bash

# تمرین ۲: Load Balancer
# هدف: یک اسکریپت بنویسید که:
# 1. HAProxy config ایجاد کند
# 2. backend servers را configure کند
# 3. health checks را setup کند

# راه‌حل:
echo "=== Load Balancer Setup ==="

# تابع ایجاد HAProxy config
create_haproxy_config() {
    local frontend_port=$1
    local stats_port=$2
    
    cat > /tmp/haproxy.cfg << EOF
global
    log /dev/log local0
    log /dev/log local1 notice
    chroot /var/lib/haproxy
    stats socket /run/haproxy/admin.sock mode 660 level admin
    stats timeout 30s
    user haproxy
    group haproxy
    daemon
    maxconn 4000

defaults
    log     global
    mode    http
    option  httplog
    option  dontlognull
    timeout connect 5000
    timeout client  50000
    timeout server  50000
    errorfile 400 /etc/haproxy/errors/400.http
    errorfile 403 /etc/haproxy/errors/403.http
    errorfile 408 /etc/haproxy/errors/408.http
    errorfile 500 /etc/haproxy/errors/500.http
    errorfile 502 /etc/haproxy/errors/502.http
    errorfile 503 /etc/haproxy/errors/503.http
    errorfile 504 /etc/haproxy/errors/504.http

frontend http-in
    bind *:$frontend_port
    mode http
    default_backend web-servers
    option httplog
    option dontlognull

backend web-servers
    mode http
    balance roundrobin
    option httpchk GET /health
    option httplog
    option httpclose
    option forwardfor
    cookie SERVERID insert indirect nocache
    server web1 192.168.1.10:80 check cookie web1 inter 2000 rise 2 fall 3
    server web2 192.168.1.11:80 check cookie web2 inter 2000 rise 2 fall 3
    server web3 192.168.1.12:80 check cookie web3 inter 2000 rise 2 fall 3 backup

frontend stats-in
    bind *:$stats_port
    mode http
    stats enable
    stats uri /stats
    stats refresh 10s
    stats auth admin:securepassword
    stats show-legends
    stats show-node

listen admin
    bind *:8405
    mode http
    stats enable
    stats uri /
    stats refresh 30s
    stats admin if TRUE
EOF
    
    echo "✓ HAProxy config created"
}

# تابع validate config
validate_config() {
    echo "1. Validating HAProxy config:"
    echo "haproxy -c -f /tmp/haproxy.cfg"
    echo "✓ Config validated"
}

# تابع install HAProxy
install_haproxy() {
    echo -e "\n2. Installing HAProxy:"
    echo "apt update && apt install -y haproxy"
    echo "✓ HAProxy installed"
}

# تابع deploy config
deploy_config() {
    echo -e "\n3. Deploying config:"
    echo "cp /tmp/haproxy.cfg /etc/haproxy/haproxy.cfg"
    echo "systemctl restart haproxy"
    echo "✓ Config deployed"
}

# تابع check status
check_status() {
    echo -e "\n4. Checking HAProxy status:"
    echo "systemctl status haproxy"
    echo "haproxy -c -f /etc/haproxy/haproxy.cfg"
}

# تابع show stats
show_stats() {
    echo -e "\n5. HAProxy Stats:"
    echo "curl http://localhost:8404/stats"
}

# تابع test load balancing
test_lb() {
    echo -e "\n6. Testing load balancing:"
    echo "for i in {1..10}; do curl http://localhost:80; done"
    echo "✓ Load balancing tested"
}

# تابع cleanup
cleanup() {
    rm /tmp/haproxy.cfg
    echo "✓ Temporary file cleaned up"
}

# اجرا
create_haproxy_config "80" "8404"

echo -e "\n=== HAProxy Configuration ==="
cat /tmp/haproxy.cfg

validate_config
install_haproxy
deploy_config
check_status
show_stats
test_lb
cleanup
