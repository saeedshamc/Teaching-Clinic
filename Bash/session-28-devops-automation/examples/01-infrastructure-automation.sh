#!/usr/bin/env bash

echo "=== Infrastructure Automation ==="

# تابع ایجاد VM
create_vm() {
    local vm_name=$1
    echo "ایجاد VM: $vm_name"
    # در عمل، از AWS CLI یا Azure CLI استفاده کنید
    # aws ec2 run-instances --image-id ami-xxx --count 1
    echo "✓ VM ایجاد شد"
}

# تابع ایجاد network
create_network() {
    local network_name=$1
    echo "ایجاد Network: $network_name"
    # aws ec2 create-vpc --cidr-block 10.0.0.0/16
    echo "✓ Network ایجاد شد"
}

# تابع ایجاد storage
create_storage() {
    local storage_name=$1
    local size=$2
    echo "ایجاد Storage: $storage_name ($size GB)"
    # aws ec2 create-volume --size $size
    echo "✓ Storage ایجاد شد"
}

# اجرا
create_vm "web-server-1"
create_network "vpc-production"
create_storage "data-disk" 100
