# جلسه ۳۵: مبانی Ansible (Ansible Basics)

## اهداف یادگیری

در این جلسه یاد می‌گیرید:
- مفاهیم پایه Ansible
- Inventory و Hosts
- Playbooks
- Modules
- Variables و Facts
- Roles
- مثال‌های عملی

## مفاهیم پایه Ansible

Ansible یک ابزار configuration management و automation است:
- **Inventory** - لیست hosts
- **Playbook** - فایل‌های automation
- **Module** - واحد اجرای Ansible
- **Task** - یک action
- **Role** - مجموعه playbooks

## Inventory

Inventory لیست managed hosts است.

```ini
[webservers]
web1.example.com
web2.example.com

[dbservers]
db1.example.com
```

## مثال ۱: Inventory Basics

فایل [examples/01-inventory.sh](examples/01-inventory.sh) را بررسی کنید:

```bash
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
```

### توضیح خط به خط:

- `[webservers]` - group name
- hostnames زیر group
- `[all:vars]` - variables برای همه hosts
- `ansible -i` - specify inventory
- `ping module` - test connectivity

## Playbooks

Playbook فایل YAML برای automation است.

```yaml
- name: Install and start nginx
  hosts: webservers
  tasks:
    - name: Install nginx
      apt:
        name: nginx
        state: present
```

## مثال ۲: Playbook Basics

فایل [examples/02-playbook-basics.sh](examples/02-playbook-basics.sh) را بررسی کنید:

```bash
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
```

### توضیح خط به خط:

- `---` - YAML start
- `hosts` - target hosts
- `become: yes` - sudo
- `tasks` - لیست tasks
- `apt module` - package management
- `service module` - service management

## Modules

Modules واحد اجرای Ansible هستند.

```yaml
- name: Copy file
  copy:
    src: /tmp/file
    dest: /etc/file
```

## مثال ۳: Common Modules

فایل [examples/03-common-modules.sh](examples/03-common-modules.sh) را بررسی کنید:

```bash
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
```

### توضیح خط به خط:

- `apt` - package management
- `copy` - copy files
- `template` - Jinja2 templates
- `service` - service management
- `user` - user management

## Variables

Variables برای parameterization استفاده می‌شوند.

```yaml
vars:
  app_port: 8080
  app_name: myapp
```

## مثال ۴: Variables

فایل [examples/04-variables.sh](examples/04-variables.sh) را بررسی کنید:

```bash
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
```

### توضیح خط به خط:

- `vars` block - تعریف variables
- `{{ variable }}` - استفاده variable
- `-e @file.yml` - load from file
- `-e 'var=value'` - command line override
- Jinja2 templating

## Facts

Facts اطلاعات سیستم هستند که Ansible جمع می‌کند.

```yaml
- name: Print facts
  debug:
    msg: "{{ ansible_os_family }}"
```

## مثال ۵: Facts

فایل [examples/05-facts.sh](examples/05-facts.sh) را بررسی کنید:

```bash
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
```

### توضیح خط به خط:

- `gather_facts: yes` - جمع‌آوری facts
- `ansible_*` - built-in facts
- custom facts در `/etc/ansible/facts.d`
- استفاده facts در conditionals
- dynamic configuration

## Roles

Roles برای organization و reusability استفاده می‌شوند.

```yaml
roles:
  - nginx
  - mysql
```

## مثال ۶: Roles

فایل [examples/06-roles.sh](examples/06-roles.sh) را بررسی کنید:

```bash
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
```

### توضیح خط به خط:

- role directory structure
- `tasks/main.yml` - main tasks
- `handlers/main.yml` - handlers
- `notify` - trigger handler
- reusability با roles
- organization بهتر

## نکات مهم

### ۱. Idempotency

```yaml
- name: Ensure service is running
  service:
    name: nginx
    state: started
```

### ۲. Handlers

```yaml
handlers:
  - name: restart nginx
    service:
      name: nginx
      state: restarted
```

### ۳. Conditionals

```yaml
- name: Install on Debian
  apt:
    name: nginx
  when: ansible_os_family == "Debian"
```

## مثال ۷: Ansible Best Practices

فایل [examples/07-ansible-best-practices.sh](examples/07-ansible-best-practices.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Ansible Best Practices ==="

# 1. Idempotency
echo -e "\n1. Idempotency:"
echo "✓ همیشه idempotent tasks بنویسید"
echo "✓ از state parameter استفاده کنید"
echo "✓ multiple runs بدون مشکل"

# 2. Handlers
echo -e "\n2. Handlers:"
echo "✓ handlers برای restart services"
echo "✓ فقط در صورت تغییر اجرا شوند"
echo "✓ notify برای trigger handler"

# 3. Variables
echo -e "\n3. Variables:"
echo "✓ defaults برای default values"
echo "✓ vars برای override"
echo "✓ vault برای sensitive data"

# 4. Roles
echo -e "\n4. Roles:"
echo "✓ reusable roles ایجاد کنید"
echo "✓ roles را version control کنید"
echo "✓ از ansible-galaxy استفاده کنید"

# 5. Testing
echo -e "\n5. Testing:"
echo "✓ --check برای dry run"
echo "✓ --syntax-check برای validation"
echo "✓ ansible-lint برای linting"
```

### توضیح خط به خط:

- Idempotency برای safety
- Handlers برای efficiency
- Variables برای flexibility
- Roles برای reusability
- Testing برای quality

## نکات رایج اشتباهات (Common Pitfalls)

### ۱: بدون Idempotency

❌ اشتباه:
```yaml
# بدون idempotency
```

✅ درست:
```yaml
# با state parameter
```

### ۲: Hardcoded Values

❌ اشتباه:
```yaml
# hardcoded values
```

✅ درست:
```yaml
# variables
```

### ۳: بدون Handlers

❌ اشتباه:
```yaml
# بدون handlers
```

✅ درست:
```yaml
# با handlers
```

## بهترین شیوه‌ها (Best Practices)

1. **Idempotency** - همیشه idempotent
2. **Handlers** - برای service restarts
3. **Variables** - برای parameterization
4. **Roles** - برای reusability
5. **Testing** - --check و --syntax-check

## خلاصه جلسه

در این جلسه یاد گرفتیم:
- مفاهیم پایه Ansible
- Inventory و Hosts
- Playbooks
- Modules
- Variables و Facts
- Roles
- Best Practices
- نکات مهم و اشتباهات رایج

## تمرین‌ها

### تمرین ۱ (ساده): Basic Playbook

یک Ansible playbook بنویسید که:
1. package نصب کند
2. service را start کند
3. file را copy کند

فایل تمرین: [exercises/exercise-01.sh](exercises/exercise-01.sh)

### تمرین ۲ (متوسط): Role Creation

یک Ansible role بنویسید که:
1. application را deploy کند
2. configuration را template کند
3. service را manage کند

فایل تمرین: [exercises/exercise-02.sh](exercises/exercise-02.sh)

## جلسه بعد

در جلسه بعد، Advanced Networking را یاد می‌گیریم.
