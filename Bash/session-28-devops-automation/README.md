# جلسه ۲۸: اتوماسیون DevOps (DevOps Automation)

## اهداف یادگیری

در این جلسه یاد می‌گیرید:
- مفاهیم پایه DevOps
- Infrastructure as Code
- Configuration Management
- Automated Deployments
- Monitoring Automation
- Backup Automation
- مثال‌های عملی

## مفاهیم پایه DevOps

DevOps ترکیب Development و Operations است:
- **Infrastructure as Code** - مدیریت infrastructure با کد
- **Configuration Management** - مدیریت تنظیمات
- **Continuous Delivery** - استقرار مداوم
- **Monitoring** - مانیتورینگ اتوماتیک
- **Automation** - اتوماسیون فرآیندها

## Infrastructure as Code

```bash
# استفاده از scripts برای مدیریت infrastructure
# version control برای infrastructure
# automated provisioning
```

## مثال ۱: Infrastructure Automation

فایل [examples/01-infrastructure-automation.sh](examples/01-infrastructure-automation.sh) را بررسی کنید:

```bash
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
```

### توضیح خط به خط:

- `create_vm` - ایجاد virtual machine
- `create_network` - ایجاد network
- `create_storage` - ایجاد storage
- در عمل از cloud CLI استفاده می‌شود

## Configuration Management

```bash
# مدیریت تنظیمات با scripts
# template-based configuration
# environment-specific configs
```

## مثال ۲: Configuration Management

فایل [examples/02-config-management.sh](examples/02-config-management.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Configuration Management ==="

# تابع اعمال config
apply_config() {
    local env=$1
    local config_file="/tmp/config_${env}.conf"
    
    echo "اعمال config برای $env"
    
    # ایجاد config بر اساس environment
    case $env in
        dev)
            cat > "$config_file" << EOF
ENV=development
DB_HOST=localhost
DB_PORT=5432
DEBUG=true
EOF
            ;;
        prod)
            cat > "$config_file" << EOF
ENV=production
DB_HOST=prod-db.example.com
DB_PORT=5432
DEBUG=false
EOF
            ;;
    esac
    
    echo "✓ Config اعمال شد"
    cat "$config_file"
    
    # پاکسازی
    rm "$config_file"
}

# تابع validate config
validate_config() {
    local config_file=$1
    
    echo "اعتبارسنجی config..."
    
    if [ -f "$config_file" ]; then
        source "$config_file"
        
        if [ -z "$ENV" ] || [ -z "$DB_HOST" ]; then
            echo "✗ Config نامعتبر است"
            return 1
        else
            echo "✓ Config معتبر است"
            return 0
        fi
    else
        echo "✗ فایل config وجود ندارد"
        return 1
    fi
}

# اجرا
apply_config "dev"
apply_config "prod"
```

### توضیح خط به خط:

- `apply_config` - اعمال config بر اساس env
- `validate_config` - اعتبارسنجی config
- environment-specific configs
- source برای بارگذاری config

## Automated Deployments

```bash
# automated deployment scripts
# zero-downtime deployment
# rollback automation
```

## مثال ۳: Deployment Automation

فایل [examples/03-deployment-automation.sh](examples/03-deployment-automation.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Deployment Automation ==="

# تابع deploy
deploy() {
    local env=$1
    local version=$2
    
    echo "Deploy به $env (version: $version)"
    
    # 1. Build
    echo "1. Build:"
    # docker build -t app:$version .
    echo "✓ Build کامل شد"
    
    # 2. Push
    echo -e "\n2. Push:"
    # docker push app:$version
    echo "✓ Push کامل شد"
    
    # 3. Deploy
    echo -e "\n3. Deploy:"
    # kubectl set image deployment/app app=app:$version
    echo "✓ Deploy کامل شد"
    
    # 4. Health check
    echo -e "\n4. Health Check:"
    sleep 5
    # curl -f http://app/health || echo "Health check failed"
    echo "✓ Health check passed"
}

# تابع rollback
rollback() {
    local env=$1
    local previous_version=$2
    
    echo "Rollback به version: $previous_version"
    
    # kubectl rollout undo deployment/app
    echo "✓ Rollback کامل شد"
}

# تابع blue-green deployment
blue_green_deploy() {
    local version=$1
    
    echo "Blue-Green Deploy:"
    echo "1. Deploy به Green:"
    # deploy to green environment
    
    echo "2. Health Check Green:"
    # check green health
    
    echo "3. Switch Traffic:"
    # switch traffic to green
    
    echo "✓ Blue-Green Deploy کامل شد"
}

# اجرا
deploy "staging" "v1.0.0"
# blue_green_deploy "v1.0.0"
```

### توضیح خط به خط:

- `deploy` - فرآیند deploy کامل
- `rollback` - بازگشت به نسخه قبلی
- `blue_green_deploy` - zero-downtime deployment
- health check بعد از deploy

## Monitoring Automation

```bash
# automated monitoring setup
# alert configuration
- dashboard automation
```

## مثال ۴: Monitoring Automation

فایل [examples/04-monitoring-automation.sh](examples/04-monitoring-automation.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Monitoring Automation ==="

# تابع setup monitoring
setup_monitoring() {
    local service=$1
    
    echo "Setup Monitoring برای $service"
    
    # 1. Install agent
    echo "1. Install Monitoring Agent:"
    # apt install prometheus-node-exporter
    echo "✓ Agent نصب شد"
    
    # 2. Configure metrics
    echo -e "\n2. Configure Metrics:"
    cat > /tmp/prometheus.yml << EOF
scrape_configs:
  - job_name: '$service'
    static_configs:
      - targets: ['localhost:9090']
EOF
    echo "✓ Metrics تنظیم شد"
    
    # 3. Setup alerts
    echo -e "\n3. Setup Alerts:"
    cat > /tmp/alerts.yml << EOF
groups:
  - name: $service
    rules:
      - alert: HighErrorRate
        expr: error_rate > 0.05
EOF
    echo "✓ Alerts تنظیم شد"
    
    # پاکسازی
    rm /tmp/prometheus.yml /tmp/alerts.yml
}

# تابع create dashboard
create_dashboard() {
    local service=$1
    
    echo "ایجاد Dashboard برای $service"
    
    cat > /tmp/dashboard.json << EOF
{
  "dashboard": {
    "title": "$service Dashboard",
    "panels": [
      {
        "title": "Request Rate",
        "targets": [{"expr": "rate(requests_total[5m])"}]
      },
      {
        "title": "Error Rate",
        "targets": [{"expr": "rate(errors_total[5m])"}]
      }
    ]
  }
}
EOF
    
    echo "✓ Dashboard ایجاد شد"
    cat /tmp/dashboard.json
    
    # پاکسازی
    rm /tmp/dashboard.json
}

# اجرا
setup_monitoring "web-service"
create_dashboard "web-service"
```

### توضیح خط به خط:

- `setup_monitoring` - نصب و تنظیم monitoring
- `create_dashboard` - ایجاد dashboard
- Prometheus config
- Alert rules

## Backup Automation

```bash
# automated backup scripts
# scheduled backups
- backup retention
```

## مثال ۵: Backup Automation

فایل [examples/05-backup-automation.sh](examples/05-backup-automation.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Backup Automation ==="

# تابع backup database
backup_database() {
    local db_name=$1
    local backup_dir="/tmp/backups"
    
    echo "Backup Database: $db_name"
    
    mkdir -p "$backup_dir"
    
    local timestamp=$(date '+%Y%m%d_%H%M%S')
    local backup_file="$backup_dir/${db_name}_${timestamp}.sql"
    
    # mysqldump -u root -p "$db_name" > "$backup_file"
    echo "✓ Backup ایجاد شد: $backup_file"
    
    # Compress
    gzip "$backup_file"
    echo "✓ Backup فشرده شد"
}

# تابع backup files
backup_files() {
    local source_dir=$1
    local backup_dir="/tmp/file_backups"
    
    echo "Backup Files: $source_dir"
    
    mkdir -p "$backup_dir"
    
    local timestamp=$(date '+%Y%m%d_%H%M%S')
    local backup_file="$backup_dir/files_${timestamp}.tar.gz"
    
    tar -czf "$backup_file" "$source_dir"
    echo "✓ Backup ایجاد شد: $backup_file"
}

# تابع cleanup old backups
cleanup_backups() {
    local backup_dir=$1
    local retention_days=7
    
    echo "Cleanup Backups (retention: $retention_days days)"
    
    find "$backup_dir" -type f -mtime +$retention_days -delete
    echo "✓ Backupهای قدیمی حذف شدند"
}

# تابع scheduled backup
scheduled_backup() {
    echo "Scheduled Backup:"
    echo "اضافه به crontab:"
    echo "0 2 * * * /path/to/backup-script.sh"
}

# اجرا
backup_database "mydb"
backup_files "/tmp/data"
cleanup_backups "/tmp/backups"
```

### توضیح خط به خط:

- `backup_database` - backup database
- `backup_files` - backup فایل‌ها
- `cleanup_backups` - حذف backupهای قدیمی
- `scheduled_backup` - تنظیم cron job
- retention policy

## نکات مهم

### ۱. Idempotency

```bash
# اسکریپت‌ها باید idempotent باشند
# اجرای چند بار نباید مشکل ایجاد کند
```

### ۲. Error Handling

```bash
# همیشه خطاها را مدیریت کنید
# rollback در صورت خطا
```

### ۳. Logging

```bash
# همه عملیات را log کنید
# برای troubleshooting
```

## مثال ۶: DevOps Best Practices

فایل [examples/06-devops-best-practices.sh](examples/06-devops-best-practices.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== DevOps Best Practices ==="

# 1. Idempotency
echo -e "\n1. Idempotency:"
echo "✓ اسکریپت‌ها باید idempotent باشند"
echo "✓ بررسی وضعیت قبل از عملیات"

# 2. Error Handling
echo -e "\n2. Error Handling:"
echo "✓ set -e برای خروج در خطا"
echo "✓ trap برای cleanup"
echo "✓ rollback در صورت خطا"

# 3. Logging
echo -e "\n3. Logging:"
echo "✓ log تمام عملیات"
echo "✓ structured logging"
echo "✓ log levels (INFO, ERROR, WARNING)"

# 4. Security
echo -e "\n4. Security:"
echo "✓ secrets را در environment variables"
echo "✓ استفاده از secret managers"
echo "✓ least privilege"

# 5. Testing
echo -e "\n5. Testing:"
echo "✓ test scripts در staging"
echo "✓ automated tests"
echo "✓ validation قبل از deploy"
```

### توضیح خط به خط:

- Idempotency - اجرای امن چندباره
- Error Handling - مدیریت خطا
- Logging - ثبت عملیات
- Security - امنیت
- Testing - تست قبل از deploy

## نکات رایج اشتباهات (Common Pitfalls)

### ۱. عدم Idempotency

❌ اشتباه:
```bash
# اجرای چند بار مشکل ایجاد می‌کند
```

✅ درست:
```bash
# بررسی وضعیت قبل از عملیات
```

### ۲. بدون Error Handling

❌ اشتباه:
```bash
# بدون مدیریت خطا
```

✅ درست:
```bash
# error handling و rollback
```

### ۳. Hardcoded Values

❌ اشتباه:
```bash
# hardcoded values در اسکریپت
```

✅ درست:
```bash
# از config files و environment variables
```

## بهترین شیوه‌ها (Best Practices)

1. **Idempotent scripts** - اجرای امن چندباره
2. **Error handling** - مدیریت خطا و rollback
3. **Logging** - ثبت تمام عملیات
4. **Security** - محافظت از secrets
5. **Testing** - تست قبل از production

## خلاصه جلسه

در این جلسه یاد گرفتیم:
- مفاهیم پایه DevOps
- Infrastructure as Code
- Configuration Management
- Automated Deployments
- Monitoring Automation
- Backup Automation
- Best Practices
- نکات مهم و اشتباهات رایج

## تمرین‌ها

### تمرین ۱ (ساده): Config Management

یک اسکریپت بنویسید که:
1. config برای multiple environments ایجاد کند
2. config را validate کند
3. config را اعمال کند

فایل تمرین: [exercises/exercise-01.sh](exercises/exercise-01.sh)

### تمرین ۲ (متوسط): Deployment Script

یک اسکریپت بنویسید که:
1. application را deploy کند
2. health check انجام دهد
3. در صورت خطا rollback کند

فایل تمرین: [exercises/exercise-02.sh](exercises/exercise-02.sh)

## جلسه بعد

در جلسه بعد، API Integration را یاد می‌گیریم.
