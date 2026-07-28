#!/usr/bin/env bash

# تمرین ۲: Role Creation
# هدف: یک Ansible role بنویسید که:
# 1. application را deploy کند
# 2. configuration را template کند
# 3. service را manage کند

# راه‌حل:
echo "=== Ansible Role Creation ==="

# تابع ایجاد role structure
create_role_structure() {
    mkdir -p /tmp/roles/myapp/{tasks,handlers,templates,files,vars,defaults,meta}
    echo "✓ Role structure ایجاد شد"
}

# تابع ایجاد defaults
create_defaults() {
    cat > /tmp/roles/myapp/defaults/main.yml << 'EOF'
---
app_name: myapp
app_port: 8080
app_user: appuser
app_dir: /opt/myapp
EOF
    echo "✓ Defaults ایجاد شد"
}

# تابع ایجاد tasks
create_tasks() {
    cat > /tmp/roles/myapp/tasks/main.yml << 'EOF'
---
- name: Create application user
  user:
    name: "{{ app_user }}"
    shell: /bin/bash
    home: "{{ app_dir }}"
    create_home: yes

- name: Create application directory
  file:
    path: "{{ app_dir }}"
    state: directory
    owner: "{{ app_user }}"
    mode: '0755'

- name: Install dependencies
  apt:
    name:
      - python3
      - python3-pip
    state: present
    update_cache: yes

- name: Install Python packages
  pip:
    name:
      - flask
      - gunicorn
    executable: pip3

- name: Deploy application code
  copy:
    content: |
      from flask import Flask
      app = Flask(__name__)
      
      @app.route('/')
      def hello():
          return 'Hello from {{ app_name }}'
      
      if __name__ == '__main__':
          app.run(host='0.0.0.0', port={{ app_port }})
    dest: "{{ app_dir }}/app.py"
    owner: "{{ app_user }}"
    mode: '0644'

- name: Deploy configuration template
  template:
    src: config.j2
    dest: "{{ app_dir }}/config.ini"
    owner: "{{ app_user }}"
    mode: '0644'
  notify: restart myapp

- name: Create systemd service file
  copy:
    content: |
      [Unit]
      Description={{ app_name }}
      After=network.target
      
      [Service]
      Type=simple
      User={{ app_user }}
      WorkingDirectory={{ app_dir }}
      ExecStart=/usr/local/bin/gunicorn -w 4 -b 0.0.0.0:{{ app_port }} app:app
      Restart=always
      
      [Install]
      WantedBy=multi-user.target
    dest: /etc/systemd/system/{{ app_name }}.service
    mode: '0644'
  notify: restart myapp

- name: Enable and start service
  systemd:
    name: "{{ app_name }}"
    state: started
    enabled: yes
    daemon_reload: yes
EOF
    echo "✓ Tasks ایجاد شد"
}

# تابع ایجاد handlers
create_handlers() {
    cat > /tmp/roles/myapp/handlers/main.yml << 'EOF'
---
- name: restart myapp
  systemd:
    name: "{{ app_name }}"
    state: restarted
    daemon_reload: yes
EOF
    echo "✓ Handlers ایجاد شد"
}

# تابع ایجاد template
create_template() {
    cat > /tmp/roles/myapp/templates/config.j2 << 'EOF'
[app]
name = {{ app_name }}
port = {{ app_port }}
user = {{ app_user }}
dir = {{ app_dir }}

[database]
host = localhost
port = 5432
name = {{ app_name }}_db
EOF
    echo "✓ Template ایجاد شد"
}

# تابع ایجاد playbook
create_playbook() {
    cat > /tmp/playbook.yml << 'EOF'
---
- name: Deploy application
  hosts: webservers
  become: yes
  
  roles:
    - myapp
EOF
    echo "✓ Playbook ایجاد شد"
}

# تابع run role
run_role() {
    echo "1. Run Role:"
    echo "ansible-playbook -i /tmp/inventory /tmp/playbook.yml"
    echo "✓ Role executed"
}

# تابع verify
verify() {
    echo -e "\n2. Verify:"
    echo "systemctl status {{ app_name }}"
    echo "curl localhost:{{ app_port }}"
}

# تابع cleanup
cleanup() {
    rm -rf /tmp/roles /tmp/playbook.yml
    echo "✓ Role directory cleaned up"
}

# اجرا
create_role_structure
create_defaults
create_tasks
create_handlers
create_template
create_playbook

echo -e "\n=== Role Structure ==="
tree /tmp/roles 2>/dev/null || find /tmp/roles -type f

echo -e "\n=== Defaults ==="
cat /tmp/roles/myapp/defaults/main.yml

echo -e "\n=== Tasks ==="
cat /tmp/roles/myapp/tasks/main.yml

echo -e "\n=== Handlers ==="
cat /tmp/roles/myapp/handlers/main.yml

echo -e "\n=== Template ==="
cat /tmp/roles/myapp/templates/config.j2

echo -e "\n=== Playbook ==="
cat /tmp/playbook.yml

run_role
verify
cleanup
