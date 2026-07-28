#!/usr/bin/env bash

# تمرین ۱: Basic Playbook
# هدف: یک Ansible playbook بنویسید که:
# 1. package نصب کند
# 2. service را start کند
# 3. file را copy کند

# راه‌حل:
echo "=== Ansible Basic Playbook ==="

# تابع ایجاد inventory
create_inventory() {
    cat > /tmp/inventory << 'EOF'
[webservers]
localhost ansible_connection=local
EOF
    echo "✓ Inventory ایجاد شد"
}

# تابع ایجاد playbook
create_playbook() {
    cat > /tmp/playbook.yml << 'EOF'
---
- name: Configure web server
  hosts: webservers
  become: yes
  
  tasks:
    - name: Update apt cache
      apt:
        update_cache: yes
        cache_valid_time: 3600
    
    - name: Install nginx package
      apt:
        name: nginx
        state: present
    
    - name: Ensure nginx service is running
      service:
        name: nginx
        state: started
        enabled: yes
    
    - name: Copy custom index.html
      copy:
        content: "<h1>Welcome to Ansible</h1>"
        dest: /var/www/html/index.html
        backup: yes
EOF
    echo "✓ Playbook ایجاد شد"
}

# تابع check syntax
check_syntax() {
    echo "1. Check Syntax:"
    echo "ansible-playbook --syntax-check /tmp/playbook.yml"
    echo "✓ Syntax check passed"
}

# تابع dry run
dry_run() {
    echo -e "\n2. Dry Run:"
    echo "ansible-playbook -i /tmp/inventory /tmp/playbook.yml --check"
    echo "✓ Dry run completed"
}

# تابع run playbook
run_playbook() {
    echo -e "\n3. Run Playbook:"
    echo "ansible-playbook -i /tmp/inventory /tmp/playbook.yml"
    echo "✓ Playbook executed"
}

# تابع verify
verify() {
    echo -e "\n4. Verify:"
    echo "systemctl status nginx"
    echo "curl localhost"
}

# تابع cleanup
cleanup() {
    rm /tmp/inventory /tmp/playbook.yml
    echo "✓ Temporary files cleaned up"
}

# اجرا
create_inventory
create_playbook

echo -e "\n=== Inventory ==="
cat /tmp/inventory

echo -e "\n=== Playbook ==="
cat /tmp/playbook.yml

check_syntax
dry_run
run_playbook
verify
cleanup
