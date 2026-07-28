#!/usr/bin/env bash

echo "=== Ansible Variables ==="

# Playbook with variables
cat > /tmp/vars-playbook.yml << 'EOF'
---
- name: Deploy application
  hosts: webservers
  become: yes
  
  vars:
    app_name: myapp
    app_port: 8080
    app_user: appuser
    app_dir: /opt/myapp
    
  tasks:
    - name: Create user
      user:
        name: "{{ app_user }}"
        shell: /bin/bash
    
    - name: Create directory
      file:
        path: "{{ app_dir }}"
        state: directory
        owner: "{{ app_user }}"
    
    - name: Deploy config
      template:
        src: config.j2
        dest: "{{ app_dir }}/config.ini"
EOF

echo "1. Playbook with Variables:"
cat /tmp/vars-playbook.yml

# Variable file
cat > /tmp/vars.yml << 'EOF'
---
app_name: production-app
app_port: 80
app_user: produser
app_dir: /opt/production-app
EOF

echo -e "\n2. Variable File:"
cat /tmp/vars.yml

# Run with vars file
echo -e "\n3. Run with Variables:"
echo "ansible-playbook -i /tmp/inventory /tmp/vars-playbook.yml -e @/tmp/vars.yml"
echo "✓ Playbook executed with variables"

# Command line variables
echo -e "\n4. Command Line Variables:"
echo "ansible-playbook -i /tmp/inventory /tmp/vars-playbook.yml -e 'app_port=9090'"
echo "✓ Playbook executed with override"

# پاکسازی
rm /tmp/vars-playbook.yml /tmp/vars.yml
