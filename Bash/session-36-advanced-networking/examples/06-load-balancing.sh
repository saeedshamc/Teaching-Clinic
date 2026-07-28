#!/usr/bin/env bash

echo "=== Load Balancing ==="

# تابع ایجاد HAProxy config
create_haproxy_config() {
    cat > /tmp/haproxy.cfg << 'EOF'
global
    log /dev/log local0
    log /dev/log local1 notice
    chroot /var/lib/haproxy
    stats socket /run/haproxy/admin.sock mode 660 level admin
    stats timeout 30s
    user haproxy
    group haproxy
    daemon

defaults
    log     global
    mode    http
    option  httplog
    option  dontlognull
    timeout connect 5000
    timeout client  50000
    timeout server  50000

frontend http-in
    bind *:80
    default_backend web-servers

backend web-servers
    balance roundrobin
    server web1 192.168.1.10:80 check
    server web2 192.168.1.11:80 check
    server web3 192.168.1.12:80 check

listen stats
    bind *:8404
    stats enable
    stats uri /stats
    stats refresh 10s
EOF
    
    echo "✓ HAProxy config created"
    cat /tmp/haproxy.cfg
}

# تابع ایجاد Nginx load balancer
create_nginx_lb() {
    cat > /tmp/nginx-lb.conf << 'EOF'
upstream web_servers {
    least_conn;
    server 192.168.1.10:80 weight=3;
    server 192.168.1.11:80;
    server 192.168.1.12:80 backup;
}

server {
    listen 80;
    
    location / {
        proxy_pass http://web_servers;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
    }
}
EOF
    
    echo "✓ Nginx load balancer config created"
    cat /tmp/nginx-lb.conf
}

# تابع IPVS load balancing
setup_ipvs() {
    echo "Setting up IPVS:"
    ipvsadm -A -t 192.168.1.100:80 -s rr
    ipvsadm -a -t 192.168.1.100:80 -r 192.168.1.10:80 -g
    ipvsadm -a -t 192.168.1.100:80 -r 192.168.1.11:80 -g
    echo "✓ IPVS configured"
}

# استفاده
create_haproxy_config
create_nginx_lb
# setup_ipvs

# پاکسازی
rm /tmp/haproxy.cfg /tmp/nginx-lb.conf
