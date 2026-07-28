#!/usr/bin/env bash

echo "=== Common Ansible Modules ==="

# apt module
cat > /tmp/apt-example.yml << 'EOF'
- name: Install packages
  hosts: all
  become: yes
  tasks:
    - name: Install nginx
      apt:
        name: nginx
        state: present
EOF

echo "1. Apt Module:"
cat /tmp/apt-example.yml

# copy module
cat > /tmp/copy-example.yml << 'EOF'
- name: Copy files
  hosts: all
  become: yes
  tasks:
    - name: Copy config file
      copy:
        src: nginx.conf
        dest: /etc/nginx/nginx.conf
        backup: yes
EOF

echo -e "\n2. Copy Module:"
cat /tmp/copy-example.yml

# template module
cat > /tmp/template-example.yml << 'EOF'
- name: Deploy templates
  hosts: all
  become: yes
  tasks:
    - name: Template config
      template:
        src: config.j2
        dest: /etc/app/config
        mode: '0644'
EOF

echo -e "\n3. Template Module:"
cat /tmp/template-example.yml

# service module
cat > /tmp/service-example.yml << 'EOF'
- name: Manage services
  hosts: all
  become: yes
  tasks:
    - name: Start nginx
      service:
        name: nginx
        state: started
        enabled: yes
EOF

echo -e "\n4. Service Module:"
cat /tmp/service-example.yml

# user module
cat > /tmp/user-example.yml << 'EOF'
- name: Manage users
  hosts: all
  become: yes
  tasks:
    - name: Create user
      user:
        name: appuser
        shell: /bin/bash
        groups: sudo
EOF

echo -e "\n5. User Module:"
cat /tmp/user-example.yml

# پاکسازی
rm /tmp/apt-example.yml /tmp/copy-example.yml /tmp/template-example.yml /tmp/service-example.yml /tmp/user-example.yml
