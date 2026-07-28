#!/usr/bin/env bash

echo "=== Ansible Roles ==="

# Role structure
echo "1. Role Structure:"
echo "roles/"
echo "  ├── nginx/"
echo "  │   ├── tasks/"
echo "  │   │   └── main.yml"
echo "  │   ├── handlers/"
echo "  │   │   └── main.yml"
echo "  │   ├── templates/"
echo "  │   ├── files/"
echo "  │   ├── vars/"
echo "  │   ├── defaults/"
echo "  │   └── meta/"

# Create role
mkdir -p /tmp/roles/nginx/{tasks,handlers,templates,files,vars,defaults,meta}

# tasks/main.yml
cat > /tmp/roles/nginx/tasks/main.yml << 'EOF'
---
- name: Install nginx
  apt:
    name: nginx
    state: present

- name: Copy nginx config
  template:
    src: nginx.conf.j2
    dest: /etc/nginx/nginx.conf
  notify: restart nginx

- name: Start nginx
  service:
    name: nginx
    state: started
    enabled: yes
EOF

echo -e "\n2. Role Tasks:"
cat /tmp/roles/nginx/tasks/main.yml

# handlers/main.yml
cat > /tmp/roles/nginx/handlers/main.yml << 'EOF'
---
- name: restart nginx
  service:
    name: nginx
    state: restarted
EOF

echo -e "\n3. Role Handlers:"
cat /tmp/roles/nginx/handlers/main.yml

# Playbook with role
cat > /tmp/role-playbook.yml << 'EOF'
---
- name: Deploy web server
  hosts: webservers
  become: yes
  
  roles:
    - nginx
EOF

echo -e "\n4. Playbook with Role:"
cat /tmp/role-playbook.yml

# Run role
echo -e "\n5. Run Role:"
echo "ansible-playbook -i /tmp/inventory /tmp/role-playbook.yml"
echo "✓ Role executed"

# پاکسازی
rm -rf /tmp/roles /tmp/role-playbook.yml
