# جلسه ۱۵: مبانی شبکه (Network Basics)

## اهداف یادگیری

در این جلسه یاد می‌گیرید:
- مفاهیم پایه شبکه
- بررسی اتصال شبکه با ping
- بررسی پورت‌ها با netstat
- دانلود فایل با curl و wget
- بررسی DNS با nslookup و dig
- اسکن پورت با nmap
- مثال‌های عملی

## مقدمه

شبکه‌ها برای ارتباط بین سیستم‌ها ضروری هستند. در Bash می‌توانیم از دستورات مختلف برای بررسی، تست، و مدیریت اتصالات شبکه استفاده کنیم.

## بررسی اتصال با ping

```bash
ping host          # ارسال ICMP echo request
ping -c 4 host     # ارسال 4 بسته
ping -i 2 host     # فاصله 2 ثانیه بین بسته‌ها
```

## مثال ۱: ping

فایل [examples/01-ping.sh](examples/01-ping.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# تست اتصال به گوگل
echo "تست اتصال به google.com (4 بسته):"
ping -c 4 google.com

# تست اتصال به localhost
echo -e "\nتست اتصال به localhost:"
ping -c 2 localhost

# بررسی آدرس IP
echo -e "\nآدرس IP google.com:"
nslookup google.com | grep "Address:"
```

### توضیح خط به خط:

- `ping -c 4` - ارسال 4 بسته ICMP
- `localhost` - سیستم محلی (127.0.0.1)
- `nslookup` - تبدیل نام به IP
- `grep "Address:"` - استخراج آدرس IP

## بررسی پورت‌ها با netstat

```bash
netstat -tuln      # تمام پورت‌های باز
netstat -tulpn     # با نام پروسه
netstat -an        # تمام اتصالات
ss -tuln           # جایگزین مدرن netstat
```

## مثال ۲: netstat

فایل [examples/02-netstat.sh](examples/02-netstat.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# نمایش پورت‌های باز
echo "پورت‌های باز TCP:"
netstat -tuln | grep tcp

echo -e "\nپورت‌های باز UDP:"
netstat -tuln | grep udp

# نمایش اتصالات فعال
echo -e "\nاتصالات فعال:"
netstat -an | grep ESTABLISHED | head -5

# استفاده از ss (جایگزین مدرن)
echo -e "\nاستفاده از ss:"
ss -tuln | head -10
```

### توضیح خط به خط:

- `-t` - TCP
- `-u` - UDP
- `-l` - listening
- `-n` - عددی (بدون تبدیل نام)
- `ss` - جایگزین مدرن netstat
- `ESTABLISHED` - اتصالات برقرار شده

## دانلود فایل با curl

```bash
curl URL              # دانلود و نمایش
curl -O URL           # دانلود با نام اصلی
curl -o file URL      # دانلود با نام جدید
curl -I URL           # فقط header
```

## مثال ۳: curl

فایل [examples/03-curl.sh](examples/03-curl.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# دانلود صفحه وب
echo "دانلود صفحه example.com:"
curl -s -o /tmp/example.html http://example.com

# نمایش header
echo -e "\nHeader سایت:"
curl -I http://example.com

# بررسی status code
echo -e "\nStatus code:"
curl -s -o /dev/null -w "%{http_code}" http://example.com

# پاکسازی
rm /tmp/example.html
```

### توضیح خط به خط:

- `curl -s` - silent (بدون progress bar)
- `-o file` - ذخیره در فایل
- `-I` - فقط header
- `-w "%{http_code}"` - نمایش status code
- `-o /dev/null` - مخفی کردن body

## دانلود فایل با wget

```bash
wget URL             # دانلود
wget -O file URL     # دانلود با نام جدید
wget -c URL          - ادامه دانلود ناقص
wget -r URL          - دانلود بازگشتی
```

## مثال ۴: wget

فایل [examples/04-wget.sh](examples/04-wget.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# دانلود ساده
echo "دانلود فایل از example.com:"
wget -q -O /tmp/example.html http://example.com

# نمایش اطلاعات دانلود
echo "فایل دانلود شد"
ls -lh /tmp/example.html

# پاکسازی
rm /tmp/example.html
```

### توضیح خط به خط:

- `wget -q` - quiet (بدون خروجی)
- `-O file` - ذخیره در فایل
- `-c` - ادامه دانلود ناقص
- `-r` - دانلود بازگشتی

## بررسی DNS

```bash
nslookup host       # جستجوی DNS
dig host            - جستجوی DNS پیشرفته
dig +short host     - فقط جواب
host host           - ساده‌تر
```

## مثال ۵: DNS

فایل [examples/05-dns.sh](examples/05-dns.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# nslookup ساده
echo "nslookup برای google.com:"
nslookup google.com

# dig پیشرفته
echo -e "\ndig برای google.com:"
dig google.com +short

# host ساده
echo -e "\nhost برای google.com:"
host google.com

# بررسی MX record
echo -e "\nMX record برای gmail.com:"
dig gmail.com MX +short
```

### توضیح خط به خط:

- `nslookup` - جستجوی DNS ساده
- `dig +short` - فقط جواب کوتاه
- `host` - ابزار ساده DNS
- `MX` - mail exchange record

## اسکن پورت با nmap

```bash
nmap host            - اسکن پورت‌های رایج
nmap -p 1-100 host   - اسکن پورت 1 تا 100
nmap -sV host        - با نسخه سرویس
```

## مثال ۶: nmap

فایل [examples/06-nmap.sh](examples/06-nmap.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# اسکن localhost
echo "اسکن پورت‌های رایج localhost:"
nmap localhost

# اسکن پورت‌های خاص
echo -e "\nاسکن پورت‌های 80 و 443:"
nmap -p 80,443 localhost

# اسکن با نسخه سرویس
echo -e "\nاسکن با نسخه سرویس:"
nmap -sV localhost
```

### توضیح خط به خط:

- `nmap` - اسکن پورت‌های رایج
- `-p 80,443` - پورت‌های خاص
- `-sV` - تشخیص نسخه سرویس
- localhost برای تست امن

## بررسی اتصال شبکه

## مثال ۷: بررسی شبکه

فایل [examples/07-network-check.sh](examples/07-network-check.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== بررسی وضعیت شبکه ==="

# 1. بررسی اتصال به اینترنت
echo -e "\n1. اتصال به اینترنت:"
if ping -c 1 -W 2 8.8.8.8 > /dev/null 2>&1; then
    echo "✓ اتصال به اینترنت برقرار است"
else
    echo "✗ اتصال به اینترنت برقرار نیست"
fi

# 2. بررسی DNS
echo -e "\n2. بررسی DNS:"
if nslookup google.com > /dev/null 2>&1; then
    echo "✓ DNS کار می‌کند"
else
    echo "✗ DNS مشکل دارد"
fi

# 3. نمایش IP سیستم
echo -e "\n3. IP سیستم:"
hostname -I

# 4. پورت‌های باز
echo -e "\n4. پورت‌های باز:"
netstat -tuln | grep LISTEN | head -5

# 5. gateway
echo -e "\n5. Gateway پیش‌فرض:"
ip route | grep default
```

### توضیح خط به خط:

- `ping -W 2` - timeout 2 ثانیه
- `> /dev/null 2>&1` - مخفی کردن خروجی
- `hostname -I` - نمایش IP
- `ip route` - مسیرهای شبکه
- `default` - gateway پیش‌فرض

## مثال‌های عملی

### اسکریپت مانیتورینگ سرور

```bash
#!/usr/bin/env bash
SERVER="example.com"
if ! ping -c 1 $SERVER > /dev/null; then
    echo "سرور $SERVER پاسخ نمی‌دهد"
    # ارسال alert
fi
```

## مثال ۸: مانیتورینگ سرور

فایل [examples/08-server-monitor.sh](examples/08-server-monitor.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# تابع بررسی سرور
check_server() {
    local server=$1
    local port=${2:-80}
    
    echo "بررسی $server:$port"
    
    # بررسی ping
    if ping -c 1 -W 2 $server > /dev/null 2>&1; then
        echo "  ✓ Ping موفق"
    else
        echo "  ✗ Ping ناموفق"
    fi
    
    # بررسی پورت
    if timeout 2 bash -c "cat < /dev/null > /dev/tcp/$server/$port" 2>/dev/null; then
        echo "  ✓ پورت $port باز است"
    else
        echo "  ✗ پورت $port بسته است"
    fi
}

# تست با localhost
check_server "localhost" "22"
check_server "localhost" "80"
```

### توضیح خط به خط:

- تابع با پارامترهای server و port
- `ping -W 2` - timeout کوتاه
- `timeout 2` - محدودیت زمانی برای اتصال TCP
- `/dev/tcp/$server/$port` - اتصال TCP در Bash
- بررسی ping و پورت جداگانه

## نکات رایج اشتباهات (Common Pitfalls)

### ۱. فراموش کردن timeout

❌ اشتباه:
```bash
ping server  # ممکن است بی‌نهایت صبر کند
```

✅ درست:
```bash
ping -c 4 -W 2 server  # محدودیت تعداد و زمان
```

### ۲. استفاده از wget بدون -c

❌ مشکل:
```bash
wget URL  # دانلود ناقص از اول شروع می‌شود
```

✅ درست:
```bash
wget -c URL  - ادامه دانلود ناقص
```

### ۳. نادیده گرفتن خطاها

❌ اشتباه:
```bash
curl URL  - خطاها نادیده گرفته می‌شوند
```

✅ درست:
```bash
curl -f URL  - خطا در صورت شکست
```

## بهترین شیوه‌ها (Best Practices)

1. **همیشه از timeout استفاده کنید** - برای جلوگیری از hang
2. **status code را بررسی کنید** - برای اطمینان از موفقیت
3. **از -q برای اسکریپت‌ها استفاده کنید** - خروجی تمیز
4. **لاگ بنویسید** - برای ردیابی مشکلات
5. **از ابزارهای مناسب استفاده کنید** - curl برای API، wget برای فایل

## خلاصه جلسه

در این جلسه یاد گرفتیم:
- بررسی اتصال با ping
- بررسی پورت‌ها با netstat
- دانلود فایل با curl و wget
- بررسی DNS
- اسکن پورت با nmap
- بررسی وضعیت شبکه
- مانیتورینگ سرور
- نکات مهم و اشتباهات رایج

## تمرین‌ها

### تمرین ۱ (ساده): بررسی چند سرور

یک اسکریپت بنویسید که:
1. لیستی از سرورها را بگیرد
2. به هر سرور ping بزند
3. نتایج را در فایل ذخیره کند

فایل تمرین: [exercises/exercise-01.sh](exercises/exercise-01.sh)

### تمرین ۲ (متوسط): دانلود خودکار

یک اسکریپت بنویسید که:
1. لیستی از URLها را از فایل بخواند
2. هر URL را دانلود کند
3. در صورت خطا، retry کند
4. گزارشی از موفقیت/شکست تولید کند

فایل تمرین: [exercises/exercise-02.sh](exercises/exercise-02.sh)

## جلسه بعد

در جلسه بعد، پردازش متن پیشرفته (Advanced Text Processing) را یاد می‌گیریم.
