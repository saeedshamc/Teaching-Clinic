# جلسه ۲۱: مبانی امنیت (Security Basics)

## اهداف یادگیری

در این جلسه یاد می‌گیرید:
- مفاهیم پایه امنیت در لینوکس
- مدیریت دسترسی‌ها و permissions
- کاربران و گروه‌ها
- sudo و su
- امنیت فایل‌ها
- اسکن امنیتی پایه
- مثال‌های عملی

## مفاهیم پایه امنیت

امنیت در لینوکس بر اساس چند اصل مهم است:
- **Principle of Least Privilege** - حداقل دسترسی لازم
- **Defense in Depth** - لایه‌های متعدد دفاعی
- **Need to Know** - فقط دسترسی به اطلاعات مورد نیاز

## Permissions فایل‌ها

```bash
ls -l file              - نمایش permissions
chmod 755 file          - تغییر permissions
chown user:group file   - تغییر owner
```

## مثال ۱: Permissions

فایل [examples/01-permissions.sh](examples/01-permissions.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# ایجاد فایل نمونه
touch /tmp/test_file.txt

echo "Permissions اولیه:"
ls -l /tmp/test_file.txt

# تغییر permissions
chmod 644 /tmp/test_file.txt
echo -e "\nبعد از chmod 644:"
ls -l /tmp/test_file.txt

# chmod با symbolic
chmod u+x /tmp/test_file.txt
echo -e "\nبعد از chmod u+x:"
ls -l /tmp/test_file.txt

# پاکسازی
rm /tmp/test_file.txt
```

### توضیح خط به خط:

- `ls -l` - نمایش permissions و owner
- `chmod 644` - owner: rw, group: r, others: r
- `chmod u+x` - اضافه کردن execute برای owner
- فرمت numeric: r=4, w=2, x=1

## کاربران و گروه‌ها

```bash
useradd username        - ایجاد کاربر
userdel username        - حذف کاربر
groupadd groupname       - ایجاد گروه
usermod -aG group user  - افزودن کاربر به گروه
```

## مثال ۲: کاربران و گروه‌ها

فایل [examples/02-users-groups.sh](examples/02-users-groups.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# نمایش کاربران
echo "کاربران سیستم:"
cut -d: -f1 /etc/passwd | head -10

# نمایش گروه‌ها
echo -e "\nگروه‌های سیستم:"
cut -d: -f1 /etc/group | head -10

# کاربر جاری
echo -e "\nکاربر جاری:"
whoami

# گروه‌های کاربر جاری
echo -e "\nگروه‌های کاربر جاری:"
groups

# UID و GID
echo -e "\nUID و GID:"
id
```

### توضیح خط به خط:

- `/etc/passwd` - فایل کاربران
- `/etc/group` - فایل گروه‌ها
- `whoami` - نام کاربر جاری
- `groups` - گروه‌های کاربر
- `id` - UID, GID، و گروه‌ها

## sudo و su

```bash
sudo command            - اجرا با دسترسی root
su - username           - تغییر کاربر
sudo -u user command   - اجرا با دسترسی کاربر دیگر
```

## مثال ۳: sudo و su

فایل [examples/03-sudo-su.sh](examples/03-sudo-su.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# بررسی دسترسی sudo
echo "بررسی دسترسی sudo:"
sudo -v 2>/dev/null && echo "دسترسی sudo دارید" || echo "دسترسی sudo ندارید"

# نمایش sudoers
echo -e "\nتنظیمات sudoers (نمایش):"
sudo cat /etc/sudoers 2>/dev/null | head -10 || echo "دسترسی برای مشاهده sudoers ندارید"

# لیست دستورات مجاز با sudo
echo -e "\nدستورات اخیر با sudo:"
sudo -l 2>/dev/null | head -10 || echo "دسترسی برای مشاهده ندارید"
```

### توضیح خط به خط:

- `sudo -v` - اعتبارسنجی sudo
- `/etc/sudoers` - فایل تنظیمات sudo
- `sudo -l` - لیست دستورات مجاز

## امنیت فایل‌ها

## مثال ۴: امنیت فایل‌ها

فایل [examples/04-file-security.sh](examples/04-file-security.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# ایجاد دایرکتوری امن
mkdir -p /tmp/secure_dir

echo "Permissions دایرکتوری:"
ls -ld /tmp/secure_dir

# تنظیم permissions امن
chmod 700 /tmp/secure_dir
echo -e "\nبعد از chmod 700:"
ls -ld /tmp/secure_dir

# ایجاد فایل حساس
echo "secret data" > /tmp/secure_dir/secret.txt
chmod 600 /tmp/secure_dir/secret.txt

echo -e "\nPermissions فایل حساس:"
ls -l /tmp/secure_dir/secret.txt

# بررسی دسترسی
echo -e "\nبررسی دسترسی:"
if [ -r /tmp/secure_dir/secret.txt ]; then
    echo "قابل خواندن"
fi
if [ -w /tmp/secure_dir/secret.txt ]; then
    echo "قابل نوشتن"
fi

# پاکسازی
rm -rf /tmp/secure_dir
```

### توضیح خط به خط:

- `chmod 700` - فقط owner دسترسی دارد
- `chmod 600` - فقط owner می‌تواند بخواند/بنویسد
- `-r` - بررسی قابل خواندن بودن
- `-w` - بررسی قابل نوشتن بودن

## اسکن امنیتی پایه

## مثال ۵: اسکن امنیتی

فایل [examples/05-security-scan.sh](examples/05-security-scan.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== اسکن امنیتی پایه ==="

# 1. کاربران بدون پسورد
echo -e "\n1. کاربران بدون پسورد:"
awk -F: '($2 == "" || $2 == "!") {print $1}' /etc/shadow 2>/dev/null || echo "دسترسی ندارید"

# 2. فایل‌های world-writable
echo -e "\n2. فایل‌های world-writable در /etc:"
find /etc -perm -002 -type f 2>/dev/null | head -5 || echo "هیچ فایلی یافت نشد"

# 3. پورت‌های باز
echo -e "\n3. پورت‌های باز:"
netstat -tuln | grep LISTEN | head -5

# 4. پروسه‌های root
echo -e "\n4. پروسه‌های در حال اجرا با root:"
ps aux | awk '$1 == "root"' | head -5
```

### توضیح خط به خط:

- `/etc/shadow` - فایل پسوردها
- `find -perm -002` - فایل‌های world-writable
- `netstat -tuln` - پورت‌های باز
- `ps aux | awk '$1 == "root"'` - پروسه‌های root

## نکات امنیتی مهم

### ۱. استفاده از پسوردهای قوی

```bash
# ایجاد پسورد قوی
openssl rand -base64 12
```

### ۲. محدود کردن دسترسی SSH

```bash
# در /etc/ssh/sshd_config
PermitRootLogin no
PasswordAuthentication no
```

### ۳. استفاده از key-based authentication

```bash
ssh-keygen -t rsa -b 4096
```

## مثال ۶: بررسی امنیت

فایل [examples/06-security-check.sh](examples/06-security-check.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== بررسی امنیت سیستم ==="

# 1. آپدیت سیستم
echo -e "\n1. وضعیت آپدیت:"
if command -v apt &>/dev/null; then
    apt list --upgradable 2>/dev/null | head -5
fi

# 2. فایروال
echo -e "\n2. وضعیت فایروال:"
if command -v ufw &>/dev/null; then
    ufw status
elif command -v iptables &>/dev/null; then
    iptables -L -n | head -5
else
    echo "فایروال نصب نیست"
fi

# 3. لاگ‌های امنیتی
echo -e "\n3. لاگ‌های اخیر auth:"
tail -5 /var/log/auth.log 2>/dev/null || echo "دسترسی ندارید"

# 4. failed login attempts
echo -e "\n4. تلاش‌های ناموفق login:"
grep "Failed password" /var/log/auth.log 2>/dev/null | tail -5 || echo "دسترسی ندارید"
```

### توضیح خط به خط:

- بررسی آپدیت‌ها
- بررسی فایروال (ufw/iptables)
- بررسی لاگ‌های auth
- بررسی تلاش‌های ناموفق

## نکات رایج اشتباهات (Common Pitfalls)

### ۱. استفاده از پسوردهای ضعیف

❌ اشتباه:
```bash
password="123456"
```

✅ درست:
```bash
password=$(openssl rand -base64 12)
```

### ۲. دسترسی بیش از حد

❌ اشتباه:
```bash
chmod 777 file  - همه دسترسی دارند
```

✅ درست:
```bash
chmod 644 file  - فقط owner می‌تواند بنویسد
```

### ۳. اجرا به عنوان root بدون نیاز

❌ اشتباه:
```bash
sudo command  - بدون نیاز
```

✅ درست:
```bash
command  - بدون sudo اگر لازم نیست
```

## بهترین شیوه‌ها (Best Practices)

1. **از پسوردهای قوی استفاده کنید** - حداقل 12 کاراکتر
2. **حداقل دسترسی را بدهید** - Principle of Least Privilege
3. **سیستم را آپدیت نگه دارید** - security patches
4. **لاگ‌ها را بررسی کنید** - برای تشخیص مشکلات
5. **از key-based authentication استفاده کنید** - بهتر از پسورد

## خلاصه جلسه

در این جلسه یاد گرفتیم:
- مفاهیم پایه امنیت
- Permissions فایل‌ها
- کاربران و گروه‌ها
- sudo و su
- امنیت فایل‌ها
- اسکن امنیتی پایه
- نکات مهم و اشتباهات رایج

## تمرین‌ها

### تمرین ۱ (ساده): بررسی permissions

یک اسکریپت بنویسید که:
1. یک دایرکتوری ایجاد کند
2. فایل‌های حساس در آن بسازد
3. permissions امن تنظیم کند
4. دسترسی‌ها را بررسی کند

فایل تمرین: [exercises/exercise-01.sh](exercises/exercise-01.sh)

### تمرین ۲ (متوسط): اسکن امنیتی

یک اسکریپت بنویسید که:
1. کاربران بدون پسورد را پیدا کند
2. فایل‌های world-writable را شناسایی کند
3. پورت‌های غیرمنتظره را پیدا کند
4. گزارش امنیتی تولید کند

فایل تمرین: [exercises/exercise-02.sh](exercises/exercise-02.sh)

## جلسه بعد

در جلسه بعد، رمزنگاری و GPG را یاد می‌گیریم.
