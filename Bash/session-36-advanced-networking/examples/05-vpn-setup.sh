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
