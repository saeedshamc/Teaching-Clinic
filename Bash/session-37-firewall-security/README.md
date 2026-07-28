# جلسه ۳۷: امنیت فایروال (Firewall Security)

## اهداف یادگیری

در این جلسه یاد می‌گیرید:
- مفاهیم پایه firewall
- iptables basics
- UFW و firewalld
- Network filtering
- Rate limiting
- Intrusion Detection
- مثال‌های عملی

## مفاهیم پایه Firewall

Firewall برای filtering و controlling network traffic استفاده می‌شود:
- **Packet Filtering** - filtering packets
- **Stateful Inspection** - tracking connections
- **NAT** - Network Address Translation
- **Port Forwarding** - redirecting traffic
- **Rate Limiting** - limiting traffic rate

## iptables Basics

iptables یک tool برای configuring Linux kernel firewall است.

```bash
# List rules
iptables -L -n -v

# Add rule
iptables -A INPUT -p tcp --dport 22 -j ACCEPT
```

## مثال ۱: iptables Basics

فایل [examples/01-iptables-basics.sh](examples/01-iptables-basics.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== iptables Basics ==="

# تابع نمایش rules
show_rules() {
    echo "Current iptables rules:"
    iptables -L -n -v
}

# تابع flush rules
flush_rules() {
    echo "Flushing all rules:"
    iptables -F
    iptables -X
    iptables -t nat -F
    iptables -t mangle -F
    echo "✓ All rules flushed"
}

# تابع set default policy
set_default_policy() {
    local policy=$1
    
    echo "Setting default policy to $policy:"
    iptables -P INPUT $policy
    iptables -P FORWARD $policy
    iptables -P OUTPUT ACCEPT
    echo "✓ Default policy set"
}

# تابع allow SSH
allow_ssh() {
    local port=${1:-22}
    
    echo "Allowing SSH on port $port:"
    iptables -A INPUT -p tcp --dport $port -j ACCEPT
    echo "✓ SSH allowed"
}

# تابع allow HTTP/HTTPS
allow_web() {
    echo "Allowing HTTP and HTTPS:"
    iptables -A INPUT -p tcp --dport 80 -j ACCEPT
    iptables -A INPUT -p tcp --dport 443 -j ACCEPT
    echo "✓ Web traffic allowed"
}

# استفاده
show_rules
# flush_rules
# set_default_policy DROP
# allow_ssh 22
# allow_web
```

### توضیح خط به خط:

- `iptables -L` - لیست rules
- `iptables -F` - flush rules
- `iptables -P` - set default policy
- `iptables -A` - append rule
- chain INPUT, FORWARD, OUTPUT

## UFW

UFW (Uncomplicated Firewall) یک simple firewall interface است.

```bash
# Enable UFW
ufw enable

# Allow port
ufw allow 22/tcp
```

## مثال ۲: UFW Basics

فایل [examples/02-ufw-basics.sh](examples/02-ufw-basics.sh) را بررسی کنید:

```bash
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
```

### توضیح خط به خط:

- `ufw enable` - enable firewall
- `ufw allow` - allow traffic
- `ufw deny` - deny traffic
- `ufw status` - show status
- `ufw reset` - reset to defaults

## firewalld

firewalld یک dynamic firewall manager است.

```bash
# Start firewalld
systemctl start firewalld

# Add service
firewall-cmd --add-service=http
```

## مثال ۳: firewalld Basics

فایل [examples/03-firewalld-basics.sh](examples/03-firewalld-basics.sh) را بررسی کنید:

```bash
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
```

### توضیح خط به خط:

- `firewall-cmd` - firewalld CLI
- `--permanent` - permanent rule
- `--reload` - reload configuration
- zones برای different trust levels
- services و ports

## Network Filtering

Network filtering برای controlling access است.

```bash
# Filter by IP
iptables -A INPUT -s 192.168.1.0/24 -j ACCEPT

# Filter by MAC
iptables -A INPUT -m mac --mac-source 00:11:22:33:44:55 -j ACCEPT
```

## مثال ۴: Network Filtering

فایل [examples/04-network-filtering.sh](examples/04-network-filtering.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Network Filtering ==="

# تابع allow from subnet
allow_subnet() {
    local subnet=$1
    
    echo "Allowing from subnet $subnet:"
    iptables -A INPUT -s $subnet -j ACCEPT
    echo "✓ Subnet allowed"
}

# تابع block IP
block_ip() {
    local ip=$1
    
    echo "Blocking IP $ip:"
    iptables -A INPUT -s $ip -j DROP
    echo "✓ IP blocked"
}

# تابع allow specific MAC
allow_mac() {
    local mac=$1
    
    echo "Allowing MAC $mac:"
    iptables -A INPUT -m mac --mac-source $mac -j ACCEPT
    echo "✓ MAC allowed"
}

# تابع block MAC
block_mac() {
    local mac=$1
    
    echo "Blocking MAC $mac:"
    iptables -A INPUT -m mac --mac-source $mac -j DROP
    echo "✓ MAC blocked"
}

# تابع allow from interface
allow_interface() {
    local interface=$1
    
    echo "Allowing from interface $interface:"
    iptables -A INPUT -i $interface -j ACCEPT
    echo "✓ Interface allowed"
}

# استفاده
allow_subnet "192.168.1.0/24"
# block_ip "10.0.0.100"
# allow_mac "00:11:22:33:44:55"
```

### توضیح خط به خط:

- `-s` - source address
- `-m mac` - MAC address matching
- `-i` - input interface
- `ACCEPT` و `DROP` targets
- filtering بر اساس various criteria

## Rate Limiting

Rate limiting برای DDoS protection است.

```bash
# Limit connection rate
iptables -A INPUT -m limit --limit 25/minute --limit-burst 100 -j ACCEPT
```

## مثال ۵: Rate Limiting

فایل [examples/05-rate-limiting.sh](examples/05-rate-limiting.sh) را بررسی کنید:

```bash
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
```

### توضیح خط به خط:

- `--limit` - rate limit
- `--limit-burst` - burst allowance
- `--syn` - new connections
- `connlimit` - connection limit per IP
- DDoS protection

## Intrusion Detection

Intrusion Detection برای detecting suspicious activity است.

```bash
# Fail2Ban setup
# Log monitoring
# Alerting
```

## مثال ۶: Intrusion Detection

فایل [examples/06-intrusion-detection.sh](examples/06-intrusion-detection.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Intrusion Detection ==="

# تابع install fail2ban
install_fail2ban() {
    echo "Installing Fail2Ban:"
    apt update && apt install -y fail2ban
    systemctl enable fail2ban
    systemctl start fail2ban
    echo "✓ Fail2Ban installed"
}

# تابع create jail config
create_jail() {
    local service=$1
    local maxretry=$2
    local bantime=$3
    
    cat > /etc/fail2ban/jail.local << EOF
[$service]
enabled = true
port = http,https
filter = $service
logpath = /var/log/$service/access.log
maxretry = $maxretry
bantime = $bantime
EOF
    
    echo "✓ Jail created for $service"
}

# تابع check banned IPs
check_banned() {
    echo "Banned IPs:"
    fail2ban-client status
}

# تابع unban IP
unban_ip() {
    local ip=$1
    local jail=${2:-sshd}
    
    echo "Unbanning $ip from $jail:"
    fail2ban-client set $jail unbanip $ip
    echo "✓ IP unbanned"
}

# تابع monitor logs
monitor_logs() {
    echo "Monitoring auth logs:"
    tail -f /var/log/auth.log
}

# استفاده
# install_fail2ban
# create_jail "nginx" "5" "3600"
# check_banned
# unban_ip "192.168.1.100"
```

### توضیح خط به خط:

- Fail2Ban برای intrusion prevention
- jail configuration
- `maxretry` و `bantime`
- automatic IP banning
- log monitoring

## نکات مهم

### ۱. Default Deny

```bash
# Default policy DROP
iptables -P INPUT DROP
```

### ۲. Logging

```bash
# Log dropped packets
iptables -A INPUT -j LOG --log-prefix "Dropped: "
```

### ۳. Regular Updates

```bash
# Keep firewall updated
# Review rules regularly
```

## مثال ۷: Firewall Best Practices

فایل [examples/07-firewall-best-practices.sh](examples/07-firewall-best-practices.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Firewall Best Practices ==="

# 1. Default Deny
echo -e "\n1. Default Deny:"
echo "✓ Default policy: DROP"
echo "✓ Explicitly allow needed traffic"
echo "✓ Least privilege principle"

# 2. Logging
echo -e "\n2. Logging:"
echo "✓ Log dropped packets"
echo "✓ Monitor firewall logs"
echo "✓ Alert on suspicious activity"

# 3. Regular Updates
echo -e "\n3. Regular Updates:"
echo "✓ Review rules regularly"
echo "✓ Remove unused rules"
echo "✓ Update firewall software"

# 4. Testing
echo -e "\n4. Testing:"
echo "✓ Test rules before applying"
echo "✓ Verify connectivity"
echo "✓ Document changes"

# 5. Backup
echo -e "\n5. Backup:"
echo "✓ Backup firewall rules"
echo "✓ Version control configs"
echo "✓ Document restore procedure"
```

### توضیح خط به خط:

- Default deny policy
- Logging برای monitoring
- Regular updates برای security
- Testing برای validation
- Backup برای recovery

## نکات رایج اشتباهات (Common Pitfalls)

### ۱: Default Allow

❌ اشتباه:
```bash
# default policy ACCEPT
```

✅ درست:
```bash
# default policy DROP
```

### ۲: بدون Logging

❌ اشتباه:
```bash
# بدون logging
```

✅ درست:
```bash
# log dropped packets
```

### ۳: Lockout

❌ اشتباه:
```bash
# lockout خودتان
```

✅ درست:
```bash
# از safe rules استفاده کنید
```

## بهترین شیوه‌ها (Best Practices)

1. **Default Deny** - DROP default policy
2. **Logging** - log dropped packets
3. **Regular Updates** - review و update
4. **Testing** - test قبل از apply
5. **Backup** - backup configs

## خلاصه جلسه

در این جلسه یاد گرفتیم:
- مفاهیم پایه firewall
- iptables basics
- UFW و firewalld
- Network filtering
- Rate limiting
- Intrusion Detection
- Best Practices
- نکات مهم و اشتباهات رایج

## تمرین‌ها

### تمرین ۱ (ساده): Basic Firewall

یک اسکریپت بنویسید که:
1. default policy را DROP کند
2. SSH و HTTP را allow کند
3. logging را enable کند

فایل تمرین: [exercises/exercise-01.sh](exercises/exercise-01.sh)

### تمرین ۲ (متوسط): Advanced Firewall

یک اسکریپت بنویسید که:
1. rate limiting را configure کند
2. IP filtering را implement کند
3. intrusion detection را setup کند

فایل تمرین: [exercises/exercise-02.sh](exercises/exercise-02.sh)

## جلسه بعد

در جلسه بعد، Incident Response را یاد می‌گیریم.
