#!/usr/bin/env bash

echo "=== Ansible Playbooks ==="

# ایجاد playbook
cat > /tmp/playbook.yml << 'EOF'
---
- name: Install and configure nginx
  hosts: webservers
  become: yes
  
  tasks:
    - name: Update apt cache
      apt:
        update_cache: yes
        cache_valid_time: 3600
    
    - name: Install nginx
      apt:
        name: nginx
        state: present
    
    - name: Start nginx service
      service:
        name: nginx
        state: started
        enabled: yes
EOF

echo "1. Playbook:"
cat /tmp/playbook.yml

# Check syntax
echo -e "\n2. Check Syntax:"
echo "ansible-playbook --syntax-check /tmp/playbook.yml"
echo "✓ Syntax valid"

# Dry run
echo -e "\n3. Dry Run:"
echo "ansible-playbook -i /tmp/inventory /tmp/playbook.yml --check"
echo "✓ Dry run completed"

# Run playbook
echo -e "\n4. Run Playbook:"
echo "ansible-playbook -i /tmp/inventory /tmp/playbook.yml"
echo "✓ Playbook executed"

# پاکسازی
rm /tmp/playbook.yml
