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
