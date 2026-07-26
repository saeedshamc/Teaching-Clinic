# جلسه ۱۷: مدیریت سیستم (System Administration)

## اهداف یادگیری

در این جلسه یاد می‌گیرید:
- مدیریت کاربران و گروه‌ها
- مدیریت سرویس‌ها
- مدیریت پکیج‌ها
- مانیتورینگ سیستم
- تنظیمات cron
- مدیریت لاگ
- مثال‌های عملی

## مدیریت کاربران

```bash
useradd username          - ایجاد کاربر
userdel username          - حذف کاربر
usermod username          - ویرایش کاربر
passwd username           - تغییر پسورد
groups username          - گروه‌های کاربر
```

## مثال ۱: مدیریت کاربران

فایل [examples/01-user-management.sh](examples/01-user-management.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# نمایش کاربران سیستم
echo "کاربران سیستم:"
cut -d: -f1 /etc/passwd | head -10

# نمایش کاربر جاری
echo -e "\nکاربر جاری:"
whoami

# نمایش گروه‌های کاربر جاری
echo -e "\nگروه‌های کاربر جاری:"
groups

# نمایش اطلاعات کاربر
echo -e "\nاطلاعات کاربر جاری:"
id

# بررسی وجود کاربر
echo -e "\nبررسی وجود کاربر root:"
if id root &>/dev/null; then
    echo "کاربر root وجود دارد"
else
    echo "کاربر root وجود ندارد"
fi
```

### توضیح خط به خط:

- `cut -d: -f1` - استخراج نام کاربران از /etc/passwd
- `whoami` - نام کاربر جاری
- `groups` - گروه‌های کاربر جاری
- `id` - اطلاعات کامل کاربر
- `id username` - بررسی وجود کاربر

## مدیریت سرویس‌ها

```bash
systemctl start service    - شروع سرویس
systemctl stop service     - توقف سرویس
systemctl restart service  - راه‌اندازی مجدد
systemctl status service   - وضعیت سرویس
systemctl enable service   - فعال کردن در boot
```

## مثال ۲: مدیریت سرویس‌ها

فایل [examples/02-service-management.sh](examples/02-service-management.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# نمایش سرویس‌های فعال
echo "سرویس‌های فعال:"
systemctl list-units --type=service --state=running | head -10

# وضعیت سرویس خاص
echo -e "\nوضعیت ssh:"
systemctl status ssh 2>/dev/null || echo "سرویس ssh یافت نشد"

# سرویس‌های فعال شده در boot
echo -e "\nسرویس‌های فعال شده در boot:"
systemctl list-unit-files --state=enabled | head -10
```

### توضیح خط به خط:

- `systemctl list-units` - لیست سرویس‌ها
- `--state=running` - فقط در حال اجرا
- `systemctl status` - وضعیت سرویس
- `list-unit-files` - فایل‌های سرویس
- `--state=enabled` - فعال شده در boot

## مدیریت پکیج‌ها

```bash
apt update              - به‌روزرسانی لیست پکیج‌ها
apt upgrade             - به‌روزرسانی پکیج‌ها
apt install package     - نصب پکیج
apt remove package      - حذف پکیج
apt search package      - جستجوی پکیج
```

## مثال ۳: مدیریت پکیج‌ها

فایل [examples/03-package-management.sh](examples/03-package-management.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# بررسی نصب بودن پکیج
echo "بررسی نصب بودن curl:"
if command -v curl &>/dev/null; then
    echo "curl نصب است"
    curl --version | head -1
else
    echo "curl نصب نیست"
fi

# لیست پکیج‌های نصب شده
echo -e "\nتعداد پکیج‌های نصب شده:"
dpkg -l | wc -l

# جستجوی پکیج
echo -e "\nجستجوی پکیج wget:"
apt-cache policy wget 2>/dev/null | head -5
```

### توضیح خط به خط:

- `command -v` - بررسی وجود دستور
- `dpkg -l` - لیست پکیج‌های نصب شده
- `wc -l` - شمارش خطوط
- `apt-cache policy` - اطلاعات پکیج

## مانیتورینگ سیستم

```bash
df -h                  - فضای دیسک
du -sh dir             - اندازه دایرکتوری
free -h                - حافظه
uptime                 - uptime سیستم
```

## مثال ۴: مانیتورینگ سیستم

فایل [examples/04-system-monitor.sh](examples/04-system-monitor.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== مانیتورینگ سیستم ==="

# فضای دیسک
echo -e "\n1. فضای دیسک:"
df -h | head -5

# حافظه
echo -e "\n2. حافظه:"
free -h

# CPU
echo -e "\n3. Load average:"
uptime

# Uptime
echo -e "\n4. Uptime:"
uptime -p

# تعداد پروسه‌ها
echo -e "\n5. تعداد پروسه‌ها:"
ps aux | wc -l
```

### توضیح خط به خط:

- `df -h` - فضای دیسک با فرمت انسانی
- `free -h` - حافظه با فرمت انسانی
- `uptime` - load average و uptime
- `uptime -p` - uptime قابل خواندن
- `ps aux | wc -l` - تعداد پروسه‌ها

## تنظیمات Cron

Cron برای اجرای خودکار taskها در زمان‌های مشخص استفاده می‌شود.

```bash
crontab -e              - ویرایش cron
crontab -l              - نمایش cron
crontab -r              - حذف cron
```

## مثال ۵: Cron

فایل [examples/05-cron.sh](examples/05-cron.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# نمایش cron جاری
echo "Cron jobs جاری:"
crontab -l 2>/dev/null || echo "هیچ cron job وجود ندارد"

# ایجاد cron job نمونه
echo -e "\nایجاد cron job نمونه:"
# هر روز ساعت 12:00 اجرا شود
(crontab -l 2>/dev/null; echo "0 12 * * * echo 'Daily task' > /tmp/cron_test.txt") | crontab -

echo "Cron job ایجاد شد"
echo "برای مشاهده: crontab -l"
echo "برای حذف: crontab -r"
```

### توضیح خط به خط:

- `crontab -l` - نمایش cron jobs
- `0 12 * * *` - هر روز ساعت 12:00
- `(crontab -l; echo "...") | crontab` - افزودن به cron
- فرمت cron: دقیقه ساعت روز ماه روز هفته

## مدیریت لاگ

```bash
journalctl              - مشاهده journal
journalctl -u service   - لاگ سرویس خاص
tail -f /var/log/syslog - مشاهده real-time
```

## مثال ۶: مدیریت لاگ

فایل [examples/06-log-management.sh](examples/06-log-management.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# نمایش لاگ‌های اخیر
echo "لاگ‌های اخیر:"
journalctl -n 10 --no-pager

# لاگ‌های boot
echo -e "\nلاگ‌های boot:"
journalctl -b --no-pager | head -10

# لاگ‌های kernel
echo -e "\nلاگ‌های kernel:"
dmesg | tail -10
```

### توضیح خط به خط:

- `journalctl -n 10` - 10 لاگ اخیر
- `--no-pager` - بدون pager
- `-b` - لاگ‌های boot فعلی
- `dmesg` - لاگ‌های kernel
- `tail -10` - 10 خط آخر

## اسکریپت مدیریت سیستم

## مثال ۷: اسکریپت مدیریت

فایل [examples/07-admin-script.sh](examples/07-admin-script.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# تابع بررسی سلامت سیستم
check_health() {
    echo "=== بررسی سلامت سیستم ==="
    
    # 1. فضای دیسک
    echo -e "\n1. فضای دیسک:"
    DISK_USAGE=$(df / | awk 'NR==2 {print $5}' | sed 's/%//')
    if [ $DISK_USAGE -gt 80 ]; then
        echo "⚠ هشدار: استفاده دیسک $DISK_USAGE%"
    else
        echo "✓ استفاده دیسک $DISK_USAGE%"
    fi
    
    # 2. حافظه
    echo -e "\n2. حافظه:"
    MEM_USAGE=$(free | awk 'NR==2 {printf "%.0f", $3/$2 * 100}')
    if [ $MEM_USAGE -gt 80 ]; then
        echo "⚠ هشدار: استفاده حافظه $MEM_USAGE%"
    else
        echo "✓ استفاده حافظه $MEM_USAGE%"
    fi
    
    # 3. Load
    echo -e "\n3. Load average:"
    LOAD=$(uptime | awk -F'load average:' '{print $2}' | awk '{print $1}' | sed 's/,//')
    CORES=$(nproc)
    echo "Load: $LOAD (cores: $CORES)"
}

check_health
```

### توضیح خط به خط:

- تابع check_health برای بررسی سیستم
- بررسی فضای دیسک، حافظه، و load
- هشدار در صورت عبور از حد
- استفاده از awk برای استخراج داده‌ها

## نکات رایج اشتباهات (Common Pitfalls)

### ۱. اجرای دستورات admin بدون sudo

❌ اشتباه:
```bash
systemctl restart nginx  - ممکن است خطا دهد
```

✅ درست:
```bash
sudo systemctl restart nginx  - با دسترسی admin
```

### ۲. فراموش کردن بررسی قبل از حذف

❌ خطرناک:
```bash
userdel username  - بدون بررسی
```

✅ بهتر:
```bash
id username && userdel username  - بررسی قبل از حذف
```

### ۳. نادیده گرفتن لاگ‌ها

❌ اشتباه:
```bash
service restart  - بدون بررسی لاگ
```

✅ درست:
```bash
service restart && journalctl -u service  - بررسی لاگ
```

## بهترین شیوه‌ها (Best Practices)

1. **همیشه از sudo استفاده کنید** - برای دستورات admin
2. **قبل از تغییر، بررسی کنید** - برای جلوگیری از خطا
3. **لاگ‌ها را بررسی کنید** - برای ردیابی مشکلات
4. **backup بگیرید** - قبل از تغییرات مهم
5. **از cron با احتیاط استفاده کنید** - تست قبل از فعال‌سازی

## خلاصه جلسه

در این جلسه یاد گرفتیم:
- مدیریت کاربران و گروه‌ها
- مدیریت سرویس‌ها
- مدیریت پکیج‌ها
- مانیتورینگ سیستم
- تنظیمات cron
- مدیریت لاگ
- اسکریپت مدیریت سیستم
- نکات مهم و اشتباهات رایج

## تمرین‌ها

### تمرین ۱ (ساده): بررسی سلامت

یک اسکریپت بنویسید که:
1. فضای دیسک را بررسی کند
2. حافظه را بررسی کند
3. load average را بررسی کند
4. گزارش سلامت تولید دارد

فایل تمرین: [exercises/exercise-01.sh](exercises/exercise-01.sh)

### تمرین ۲ (متوسط): مدیر سرویس

یک اسکریپت بنویسید که:
1. لیستی از سرویس‌ها را بگیرد
2. وضعیت هر سرویس را بررسی کند
3. سرویس‌های متوقف را شناسایی کند
4. گزارش تولید کند

فایل تمرین: [exercises/exercise-02.sh](exercises/exercise-02.sh)

## جلسه بعد

در جلسه بعد، تعامل با دیتابیس (Database Interaction) را یاد می‌گیریم.
