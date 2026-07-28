# جلسه ۳۶: شبکه پیشرفته (Advanced Networking)

## اهداف یادگیری

در این جلسه یاد می‌گیرید:
- مفاهیم پیشرفته networking
- VLANs و Subnetting
- Routing و NAT
- VPNs
- Load Balancing
- Network Security
- مثال‌های عملی

## مفاهیم پیشرفته Networking

Advanced networking شامل مفاهیم پیچیده‌تر شبکه است:
- **VLAN** - Virtual LANs
- **Subnetting** - تقسیم شبکه
- **Routing** - مسیریابی packets
- **NAT** - Network Address Translation
- **VPN** - Virtual Private Network
- **Load Balancing** - توزیع ترافیک

## VLANs

VLANs برای تقسیم network به segmentهای منطقی استفاده می‌شوند.

```bash
# ایجاد VLAN
vconfig add eth0 100
ifconfig eth0.100 192.168.100.1 netmask 255.255.255.0 up
```

## مثال ۱: VLAN Configuration

فایل [examples/01-vlan-config.sh](examples/01-vlan-config.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== VLAN Configuration ==="

# تابع ایجاد VLAN
create_vlan() {
    local interface=$1
    local vlan_id=$2
    local ip=$3
    
    echo "Creating VLAN $vlan_id on $interface"
    
    # Load 8021q module
    modprobe 8021q
    
    # Create VLAN interface
    vconfig add $interface $vlan_id
    
    # Assign IP
    ifconfig ${interface}.${vlan_id} $ip netmask 255.255.255.0 up
    
    echo "✓ VLAN $vlan_id created with IP $ip"
}

# تابع لیست VLANs
list_vlans() {
    echo "Listing VLANs:"
    cat /proc/net/vlan/config
}

# تابع حذف VLAN
delete_vlan() {
    local interface=$1
    local vlan_id=$2
    
    echo "Deleting VLAN $vlan_id from $interface"
    vconfig rem ${interface}.${vlan_id}
    echo "✓ VLAN deleted"
}

# استفاده
create_vlan "eth0" "100" "192.168.100.1"
list_vlans
# delete_vlan "eth0" "100"
```

### توضیح خط به خط:

- `modprobe 8021q` - load VLAN module
- `vconfig add` - ایجاد VLAN
- `ifconfig` - assign IP
- `/proc/net/vlan/config` - لیست VLANs
- `vconfig rem` - حذف VLAN

## Subnetting

Subnetting تقسیم network به subnetهای کوچکتر است.

```bash
# Calculate subnet
# CIDR notation
# Subnet masks
```

## مثال ۲: Subnetting

فایل [examples/02-subnetting.sh](examples/02-subnetting.sh) را بررسی کنید:

```bash
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
```

### توضیح خط به خط:

- `ipcalc` - calculate network info
- network و broadcast addresses
- first و last host
- available hosts count
- validation IP در subnet

## Routing

Routing مسیریابی packets بین networks است.

```bash
# Add route
ip route add 192.168.2.0/24 via 192.168.1.1

# Show routing table
ip route show
```

## مثال ۳: Routing

فایل [examples/03-routing.sh](examples/03-routing.sh) را بررسی کنید:

```bash
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
```

### توضیح خط به خط:

- `ip route show` - نمایش routes
- `ip route add` - اضافه کردن route
- `ip route del` - حذف route
- IP forwarding برای router
- default gateway configuration

## NAT

NAT ترجمه IP addresses است.

```bash
# Enable NAT
iptables -t nat -A POSTROUTING -o eth0 -j MASQUERADE
```

## مثال ۴: NAT Configuration

فایل [examples/04-nat-config.sh](examples/04-nat-config.sh) را بررسی کنید:

```bash
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
```

### توضیح خط به خط:

- `MASQUERADE` - dynamic NAT
- IP forwarding prerequisite
- `PREROUTING` - port forwarding
- `DNAT` - destination NAT
- `iptables -t nat` - NAT table

## VPNs

VPN برای secure remote access استفاده می‌شود.

```bash
# OpenVPN configuration
# IPsec setup
# WireGuard
```

## مثال ۵: VPN Setup

فایل [examples/05-vpn-setup.sh](examples/05-vpn-setup.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== VPN Setup ==="

# تابع ایجاد OpenVPN config
create_openvpn_config() {
    local server_ip=$1
    local subnet=$2
    
    cat > /tmp/server.conf << EOF
dev tun
proto udp
port 1194
ca ca.crt
cert server.crt
key server.key
dh dh2048.pem
server $subnet 255.255.255.0
push "redirect-gateway def1"
push "dhcp-option DNS 8.8.8.8"
keepalive 10 120
cipher AES-256-CBC
comp-lzo
persist-key
persist-tun
status openvpn-status.log
verb 3
EOF
    
    echo "✓ OpenVPN server config created"
    cat /tmp/server.conf
}

# تابع ایجاد client config
create_client_config() {
    local server_ip=$1
    
    cat > /tmp/client.conf << EOF
client
dev tun
proto udp
remote $server_ip 1194
resolv-retry infinite
nobind
persist-key
persist-tun
ca ca.crt
cert client.crt
key client.key
remote-cert-tls server
cipher AES-256-CBC
comp-lzo
verb 3
EOF
    
    echo "✓ OpenVPN client config created"
    cat /tmp/client.conf
}

# تابع enable IP forwarding برای VPN
enable_vpn_forwarding() {
    echo "Enabling VPN forwarding:"
    echo 1 > /proc/sys/net/ipv4/ip_forward
    echo "✓ Forwarding enabled"
}

# استفاده
create_openvpn_config "10.0.0.1" "10.8.0.0"
create_client_config "10.0.0.1"
enable_vpn_forwarding

# پاکسازی
rm /tmp/server.conf /tmp/client.conf
```

### توضیح خط به خط:

- OpenVPN server configuration
- client configuration
- `redirect-gateway` - full tunnel
- `push` - client configuration
- IP forwarding برای VPN traffic

## Load Balancing

Load Balancing توزیع ترافیک بین multiple servers است.

```bash
# HAProxy configuration
# Nginx load balancing
# IPVS
```

## مثال ۶: Load Balancing

فایل [examples/06-load-balancing.sh](examples/06-load-balancing.sh) را بررسی کنید:

```bash
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
```

### توضیح خط به خط:

- HAProxy configuration
- `roundrobin` algorithm
- Nginx upstream
- `least_conn` algorithm
- IPVS kernel-level load balancing

## نکات مهم

### ۱. Security

```bash
# Firewall rules
# iptables برای filtering
# rate limiting
```

### ۲. Monitoring

```bash
# Network monitoring
# bandwidth tracking
# connection tracking
```

### ۳. High Availability

```bash
# VRRP
# CARP
# keepalived
```

## مثال ۷: Network Best Practices

فایل [examples/07-network-best-practices.sh](examples/07-network-best-practices.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Network Best Practices ==="

# 1. Security
echo -e "\n1. Security:"
echo "✓ Firewall rules implement کنید"
echo "✓ فقط required ports باز کنید"
echo "✓ rate limiting برای DDoS protection"

# 2. Monitoring
echo -e "\n2. Monitoring:"
echo "✓ Network traffic monitor کنید"
echo "✓ bandwidth usage track کنید"
echo "✓ alerts برای anomalies"

# 3. High Availability
echo -e "\n3. High Availability:"
echo "✓ redundant links داشته باشید"
echo "✓ VRRP برای gateway failover"
echo "✓ multiple load balancers"

# 4. Documentation
echo -e "\n4. Documentation:"
echo "✓ network diagram داشته باشید"
echo "✓ IP address scheme document کنید"
echo "✓ change log نگه دارید"

# 5. Testing
echo -e "\n5. Testing:"
echo "✓ failover test کنید"
echo "✓ load testing انجام دهید"
echo "✓ security scanning"
```

### توضیح خط به خط:

- Security با firewalls
- Monitoring برای awareness
- HA برای reliability
- Documentation برای maintainability
- Testing برای validation

## نکات رایج اشتباهات (Common Pitfalls)

### ۱: بدون Firewall

❌ اشتباه:
```bash
# بدون firewall
```

✅ درست:
```bash
# firewall rules
```

### ۲: بدون Monitoring

❌ اشتباه:
```bash
# بدون monitoring
```

✅ درست:
```bash
# network monitoring
```

### ۳: Single Point of Failure

❌ اشتباه:
```bash
# single gateway
```

✅ درست:
```bash
# redundant gateways
```

## بهترین شیوه‌ها (Best Practices)

1. **Security** - firewalls و ACLs
2. **Monitoring** - traffic و bandwidth
3. **High Availability** - redundant paths
4. **Documentation** - diagrams و IPs
5. **Testing** - failover و load

## خلاصه جلسه

در این جلسه یاد گرفتیم:
- مفاهیم پیشرفته networking
- VLANs و Subnetting
- Routing و NAT
- VPNs
- Load Balancing
- Best Practices
- نکات مهم و اشتباهات رایج

## تمرین‌ها

### تمرین ۱ (ساده): Network Setup

یک اسکریپت بنویسید که:
1. VLAN ایجاد کند
2. route اضافه کند
3. NAT را enable کند

فایل تمرین: [exercises/exercise-01.sh](exercises/exercise-01.sh)

### تمرین ۲ (متوسط): Load Balancer

یک اسکریپت بنویسید که:
1. HAProxy config ایجاد کند
2. backend servers را configure کند
3. health checks را setup کند

فایل تمرین: [exercises/exercise-02.sh](exercises/exercise-02.sh)

## جلسه بعد

در جلسه بعد، Firewall Security را یاد می‌گیریم.
