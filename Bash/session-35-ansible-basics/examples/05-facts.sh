#!/usr/bin/env bash

echo "=== Ansible Facts ==="

# Gather facts
cat > /tmp/facts-playbook.yml << 'EOF'
---
- name: Gather and display facts
  hosts: all
  gather_facts: yes
  
  tasks:
    - name: Display OS family
      debug:
        msg: "OS Family: {{ ansible_os_family }}"
    
    - name: Display IP address
      debug:
        msg: "IP: {{ ansible_default_ipv4.address }}"
    
    - name: Display memory
      debug:
        msg: "Memory: {{ ansible_memtotal_mb }} MB"
    
    - name: Display CPU cores
      debug:
        msg: "CPU Cores: {{ ansible_processor_vcpus }}"
EOF

echo "1. Facts Playbook:"
cat /tmp/facts-playbook.yml

# Run playbook
echo -e "\n2. Run Facts Playbook:"
echo "ansible-playbook -i /tmp/inventory /tmp/facts-playbook.yml"
echo "✓ Facts displayed"

# Custom facts
cat > /tmp/custom-facts.yml << 'EOF'
---
- name: Set custom facts
  hosts: all
  tasks:
    - name: Create custom fact directory
      file:
        path: /etc/ansible/facts.d
        state: directory
    
    - name: Add custom fact
      copy:
        content: "deployment_date=2024-01-01"
        dest: /etc/ansible/facts.d/custom.fact
EOF

echo -e "\n3. Custom Facts:"
cat /tmp/custom-facts.yml

# پاکسازی
rm /tmp/facts-playbook.yml /tmp/custom-facts.yml
