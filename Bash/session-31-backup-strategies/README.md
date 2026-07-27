# جلسه ۳۱: استراتژی‌های پشتیبان‌گیری (Backup Strategies)

## اهداف یادگیری

در این جلسه یاد می‌گیرید:
- مفاهیم پایه backup
- Full, Incremental, Differential backups
- Backup automation
- Backup verification
- Backup restoration
- Cloud backups
- مثال‌های عملی

## مفاهیم پایه Backup

Backup کپی از داده‌ها برای محافظت در برابر loss است:
- **Full Backup** - backup کامل همه داده‌ها
- **Incremental Backup** - backup فقط تغییرات از آخرین backup
- **Differential Backup** - backup تغییرات از آخرین full backup

## Backup Types

```bash
# Full backup
tar -czf backup-full.tar.gz /data

# Incremental backup
tar -czf backup-inc.tar.gz --newer=/tmp/last-backup /data
```

## مثال ۱: Backup Types

فایل [examples/01-backup-types.sh](examples/01-backup-types.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Backup Types ==="

# ایجاد دایرکتوری تست
mkdir -p /tmp/source /tmp/backups
echo "Test data" > /tmp/source/file1.txt
echo "More data" > /tmp/source/file2.txt

# Full backup
echo "1. Full Backup:"
tar -czf /tmp/backups/full-backup.tar.gz /tmp/source
echo "✓ Full backup ایجاد شد"
ls -lh /tmp/backups/full-backup.tar.gz

# تغییر داده
echo "Modified data" > /tmp/source/file1.txt
echo "New file" > /tmp/source/file3.txt

# Incremental backup
echo -e "\n2. Incremental Backup:"
touch /tmp/last-backup
tar -czf /tmp/backups/inc-backup.tar.gz --newer=/tmp/last-backup /tmp/source
echo "✓ Incremental backup ایجاد شد"
ls -lh /tmp/backups/inc-backup.tar.gz

# پاکسازی
rm -rf /tmp/source /tmp/backups /tmp/last-backup
```

### توضیح خط به خط:

- `tar -czf` - create compressed archive
- `--newer` - فایل‌های جدیدتر از timestamp
- full backup همه فایل‌ها
- incremental backup فقط تغییرات

## Backup Automation

```bash
# Scheduled backups با cron
# Automated backup scripts
# Notification در صورت خطا
```

## مثال ۲: Backup Automation

فایل [examples/02-backup-automation.sh](examples/02-backup-automation.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Backup Automation ==="

# تابع backup directory
backup_dir() {
    local source=$1
    local backup_dir=$2
    local backup_name=$3
    
    mkdir -p "$backup_dir"
    
    local timestamp=$(date '+%Y%m%d_%H%M%S')
    local backup_file="$backup_dir/${backup_name}_${timestamp}.tar.gz"
    
    echo "Backup: $source -> $backup_file"
    tar -czf "$backup_file" "$source"
    
    if [ $? -eq 0 ]; then
        echo "✓ Backup موفق"
        echo "$backup_file" > /tmp/last-backup-file
        return 0
    else
        echo "✗ Backup ناموفق"
        return 1
    fi
}

# تابع scheduled backup
scheduled_backup() {
    local source=$1
    local backup_dir=$2
    
    echo "Scheduled backup:"
    echo "اضافه به crontab:"
    echo "0 2 * * * /path/to/backup-script.sh"
    
    # ایجاد cron job
    # (echo "0 2 * * * $0 $source $backup_dir" | crontab -)
}

# استفاده
mkdir -p /tmp/test_data /tmp/backups
echo "Test" > /tmp/test_data/file.txt
backup_dir "/tmp/test_data" "/tmp/backups" "data"
scheduled_backup "/tmp/test_data" "/tmp/backups"

# پاکسازی
rm -rf /tmp/test_data /tmp/backups
```

### توضیح خط به خط:

- `backup_dir` - backup یک directory
- timestamp برای نام فایل
- `scheduled_backup` - تنظیم cron
- error handling برای backup

## Backup Verification

```bash
# Verify backup integrity
# Check backup size
- Test restore
```

## مثال ۳: Backup Verification

فایل [examples/03-backup-verification.sh](examples/03-backup-verification.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Backup Verification ==="

# تابع verify backup
verify_backup() {
    local backup_file=$1
    
    echo "Verifying: $backup_file"
    
    # Check file exists
    if [ ! -f "$backup_file" ]; then
        echo "✗ فایل backup وجود ندارد"
        return 1
    fi
    
    # Check file size
    local size=$(du -h "$backup_file" | cut -f1)
    echo "Size: $size"
    
    # Check archive integrity
    if tar -tzf "$backup_file" > /dev/null 2>&1; then
        echo "✓ Archive integrity OK"
    else
        echo "✗ Archive corrupted"
        return 1
    fi
    
    # List contents
    echo "Contents:"
    tar -tzf "$backup_file" | head -5
    
    return 0
}

# تابع test restore
test_restore() {
    local backup_file=$1
    local restore_dir=$2
    
    echo "Test restore to: $restore_dir"
    mkdir -p "$restore_dir"
    
    tar -xzf "$backup_file" -C "$restore_dir"
    
    if [ $? -eq 0 ]; then
        echo "✓ Restore موفق"
        ls -la "$restore_dir"
        return 0
    else
        echo "✗ Restore ناموفق"
        return 1
    fi
}

# استفاده
mkdir -p /tmp/source /tmp/backups /tmp/restore
echo "Test" > /tmp/source/file.txt
tar -czf /tmp/backups/backup.tar.gz /tmp/source

verify_backup "/tmp/backups/backup.tar.gz"
test_restore "/tmp/backups/backup.tar.gz" "/tmp/restore"

# پاکسازی
rm -rf /tmp/source /tmp/backups /tmp/restore
```

### توضیح خط به خط:

- `verify_backup` - بررسی integrity
- `tar -tzf` - test archive
- `test_restore` - restore test
- بررسی success/failure

## Backup Restoration

```bash
# Restore از full backup
- Restore از incremental backups
- Point-in-time recovery
```

## مثال ۴: Backup Restoration

فایل [examples/04-backup-restoration.sh](examples/04-backup-restoration.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Backup Restoration ==="

# تابع restore full backup
restore_full() {
    local backup_file=$1
    local restore_dir=$2
    
    echo "Restore full backup: $backup_file"
    mkdir -p "$restore_dir"
    
    tar -xzf "$backup_file" -C "$restore_dir"
    
    if [ $? -eq 0 ]; then
        echo "✓ Full restore موفق"
        return 0
    else
        echo "✗ Full restore ناموفق"
        return 1
    fi
}

# تابع restore incremental
restore_incremental() {
    local full_backup=$1
    local inc_backup=$2
    local restore_dir=$3
    
    echo "Restore incremental:"
    echo "1. Restore full backup"
    restore_full "$full_backup" "$restore_dir"
    
    echo "2. Apply incremental backup"
    tar -xzf "$inc_backup" -C "$restore_dir"
    
    if [ $? -eq 0 ]; then
        echo "✓ Incremental restore موفق"
        return 0
    else
        echo "✗ Incremental restore ناموفق"
        return 1
    fi
}

# استفاده
mkdir -p /tmp/source /tmp/backups /tmp/restore
echo "Original" > /tmp/source/file.txt
tar -czf /tmp/backups/full.tar.gz /tmp/source

echo "Modified" > /tmp/source/file.txt
tar -czf /tmp/backups/inc.tar.gz /tmp/source

restore_full "/tmp/backups/full.tar.gz" "/tmp/restore/full"
restore_incremental "/tmp/backups/full.tar.gz" "/tmp/backups/inc.tar.gz" "/tmp/restore/inc"

# پاکسازی
rm -rf /tmp/source /tmp/backups /tmp/restore
```

### توضیح خط به خط:

- `restore_full` - restore کامل
- `restore_incremental` - restore با incremental
- apply incremental روی full
- verification بعد از restore

## Cloud Backups

```bash
# Backup به cloud storage
# AWS S3, Google Cloud Storage
- Azure Blob Storage
```

## مثال ۵: Cloud Backups

فایل [examples/05-cloud-backups.sh](examples/05-cloud-backups.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Cloud Backups ==="

# تابع backup به S3
backup_s3() {
    local source=$1
    local bucket=$2
    local key=$3
    
    echo "Backup به S3: $bucket/$key"
    
    # aws s3 cp "$source" "s3://$bucket/$key"
    echo "✓ Backup به S3"
}

# تابع backup به GCS
backup_gcs() {
    local source=$1
    local bucket=$2
    local key=$3
    
    echo "Backup به GCS: $bucket/$key"
    
    # gsutil cp "$source" "gs://$bucket/$key"
    echo "✓ Backup به GCS"
}

# تابع backup به Azure
backup_azure() {
    local source=$1
    local container=$2
    local blob=$3
    
    echo "Backup به Azure: $container/$blob"
    
    # az storage blob upload -f "$source" -c "$container" -n "$blob"
    echo "✓ Backup به Azure"
}

# استفاده
backup_s3 "/tmp/data" "my-bucket" "backup.tar.gz"
backup_gcs "/tmp/data" "my-bucket" "backup.tar.gz"
backup_azure "/tmp/data" "my-container" "backup.tar.gz"
```

### توضیح خط به خط:

- `backup_s3` - AWS S3 backup
- `backup_gcs` - Google Cloud Storage
- `backup_azure` - Azure Blob Storage
- CLI commands برای هر سرویس

## نکات مهم

### ۱. 3-2-1 Rule

```bash
# 3 نسخه backup
# 2 نوع media مختلف
# 1 نسخه offsite
```

### ۲. Encryption

```bash
# Encrypt backups
# از GPG یا OpenSSL استفاده کنید
```

### ۳. Testing

```bash
# همیشه restore را تست کنید
# backup بدون تست = backup ندارد
```

## مثال ۶: Backup Best Practices

فایل [examples/06-backup-best-practices.sh](examples/06-backup-best-practices.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Backup Best Practices ==="

# 1. 3-2-1 Rule
echo -e "\n1. 3-2-1 Rule:"
echo "✓ 3 نسخه از داده‌ها"
echo "✓ 2 نوع media مختلف"
echo "✓ 1 نسخه offsite"

# 2. Encryption
echo -e "\n2. Encryption:"
echo "✓ Encrypt sensitive backups"
echo "✓ از GPG یا OpenSSL استفاده کنید"
echo "✓ Secure key management"

# 3. Testing
echo -e "\n3. Testing:"
echo "✓ Regular restore tests"
echo "✓ Verify backup integrity"
echo "✓ Document restore procedures"

# 4. Retention
echo -e "\n4. Retention:"
echo "✓ Define retention policy"
echo "✓ Automated cleanup"
echo "✓ Archive critical backups"

# 5. Monitoring
echo -e "\n5. Monitoring:"
echo "✓ Monitor backup jobs"
echo "✓ Alert on failures"
echo "✓ Track backup size"
```

### توضیح خط به خط:

- 3-2-1 rule برای redundancy
- Encryption برای security
- Testing برای reliability
- Retention policy برای management
- Monitoring برای awareness

## نکات رایج اشتباهات (Common Pitfalls)

### ۱: بدون Testing

❌ اشتباه:
```bash
# backup بدون تست restore
```

✅ درست:
```bash
# regular restore tests
```

### ۲: بدون Encryption

❌ خطرناک:
```bash
# unencrypted backups
```

✅ درست:
```bash
# encrypted backups
```

### ۳: Single Location

❌ اشتباه:
```bash
# backup در یک محل
```

✅ درست:
```bash
# backup در multiple locations
```

## بهترین شیوه‌ها (Best Practices)

1. **3-2-1 Rule** - redundancy مناسب
2. **Encryption** - محافظت از داده‌ها
3. **Testing** - regular restore tests
4. **Retention** - policy مناسب
5. **Monitoring** - آگاهی از وضعیت

## خلاصه جلسه

در این جلسه یاد گرفتیم:
- مفاهیم پایه backup
- Full, Incremental, Differential backups
- Backup automation
- Backup verification
- Backup restoration
- Cloud backups
- Best Practices
- نکات مهم و اشتباهات رایج

## تمرین‌ها

### تمرین ۱ (ساده): Backup Script

یک اسکریپت بنویسید که:
1. directory را backup کند
2. backup را verify کند
3. report تولید کند

فایل تمرین: [exercises/exercise-01.sh](exercises/exercise-01.sh)

### تمرین ۲ (متوسط): Backup Manager

یک اسکریپت بنویسید که:
1. full و incremental backups انجام دهد
2. retention policy اعمال کند
3. restore test انجام دهد

فایل تمرین: [exercises/exercise-02.sh](exercises/exercise-02.sh)

## جلسه بعد

در جلسه بعد، Container Orchestration را یاد می‌گیریم.
