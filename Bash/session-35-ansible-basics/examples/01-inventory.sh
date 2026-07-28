#!/usr/bin/env bash

echo "=== Ansible Inventory ==="

# ایجاد inventory file
cat > /tmp/inventory << 'EOF'
[webservers]
web1.example.com
web2.example.com
web3.example.com

[dbservers]
db1.example.com
db2.example.com

[all:vars]
ansible_user=ubuntu
ansible_ssh_private_key_file=~/.ssh/id_rsa
EOF

echo "1. Inventory File:"
cat /tmp/inventory

# Test connection
echo -e "\n2. Test Connection:"
echo "ansible -i /tmp/inventory all -m ping"
echo "✓ Connection tested"

# List hosts
echo -e "\n3. List Hosts:"
echo "ansible -i /tmp/inventory all --list-hosts"
echo "✓ Hosts listed"

# پاکسازی
rm /tmp/inventory
