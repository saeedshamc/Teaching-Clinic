# جلسه ۱۴: مدیریت پروسه‌ها (Process Management)

## اهداف یادگیری

در این جلسه یاد می‌گیرید:
- مفهوم پروسه (process) در لینوکس
- مشاهده پروسه‌ها با ps
- مدیریت پروسه‌ها با kill
- اجرای پروسه‌ها در پس‌زمینه
- استفاده از nohup و screen
- مانیتورینگ پروسه‌ها
- مثال‌های عملی

## مقدمه

در لینوکس، هر برنامه در حال اجرا یک پروسه (process) است. مدیریت پروسه‌ها شامل مشاهده، کنترل، و مدیریت برنامه‌های در حال اجرا است.

## مشاهده پروسه‌ها

```bash
ps              # نمایش پروسه‌های جاری
ps aux          # نمایش تمام پروسه‌ها
ps -ef          # نمایش پروسه‌ها با فرمت کامل
top             # مانیتورینگ real-time
htop            # مانیتورینگ پیشرفته
```

## مثال ۱: مشاهده پروسه‌ها

فایل [examples/01-view-processes.sh](examples/01-view-processes.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# نمایش پروسه‌های جاری کاربر
echo "پروسه‌های جاری کاربر:"
ps

echo -e "\nپروسه‌های جاری با جزئیات:"
ps aux | head -10

# جستجوی پروسه خاص
echo -e "\nجستجوی پروسه bash:"
ps aux | grep bash

# نمایش PID خود
echo -e "\nPID اسکریپت جاری: $$"
echo "PID والد: $PPID"
```

### توضیح خط به خط:

- `ps` - پروسه‌های ترمینال جاری
- `ps aux` - تمام پروسه‌ها با جزئیات
- `grep` - فیلتر کردن خروجی
- `$$` - PID اسکریپت جاری
- `$PPID` - PID والد

## مدیریت پروسه‌ها با kill

```bash
kill PID           # ارسال SIGTERM (خاتمه نرم)
kill -9 PID        # ارسال SIGKILL (خاتمه فوری)
killall name       # خاتمه تمام پروسه‌ها با نام خاص
pkill pattern      # خاتمه بر اساس الگو
```

## مثال ۲: kill پروسه‌ها

فایل [examples/02-kill-processes.sh](examples/02-kill-processes.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# ایجاد یک پروسه ساده
sleep 100 &
PID=$!

echo "پروسه sleep ایجاد شد با PID: $PID"

# بررسی وجود پروسه
if ps -p $PID > /dev/null; then
    echo "پروسه در حال اجراست"
fi

# خاتمه پروسه
echo "خاتمه پروسه..."
kill $PID

# صبر برای خاتمه
sleep 1

# بررسی مجدد
if ps -p $PID > /dev/null; then
    echo "پروسه هنوز در حال اجراست"
else
    echo "پروسه خاتمه یافت"
fi
```

### توضیح خط به خط:

- `sleep 100 &` - اجرا در پس‌زمینه
- `$!` - PID آخرین پروسه پس‌زمینه
- `ps -p $PID` - بررسی وجود پروسه
- `kill $PID` - خاتمه نرم
- `> /dev/null` - مخفی کردن خروجی

## اجرا در پس‌زمینه

```bash
command &         # اجرا در پس‌زمینه
bg                 # ادامه در پس‌زمینه
fg                 # ادامه در پیش‌زمینه
jobs               # نمایش jobها
```

## مثال ۳: پس‌زمینه و پیش‌زمینه

فایل [examples/03-background-foreground.sh](examples/03-background-foreground.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# اجرا در پس‌زمینه
echo "اجرای sleep در پس‌زمینه..."
sleep 10 &
JOB1=$!

echo "اجرای another sleep در پس‌زمینه..."
sleep 15 &
JOB2=$!

# نمایش jobها
echo -e "\njobهای فعال:"
jobs

# صبر کمی
sleep 2

# آوردن به پیش‌زمینه (در اینجا فقط نمایش)
echo -e "\nPIDهای فعال: $JOB1, $JOB2"

# خاتمه jobها
kill $JOB1 $JOB2 2>/dev/null

echo "jobها خاتمه یافتند"
```

### توضیح خط به خط:

- `&` - اجرا در پس‌زمینه
- `$!` - PID آخرین job
- `jobs` - نمایش jobهای ترمینال
- `kill` - خاتمه jobها

## nohup و screen

```bash
nohup command &    # اجرا حتی بعد از بستن ترمینال
screen              # ایجاد session مجازی
screen -r           # اتصال مجدد به session
```

## مثال ۴: nohup

فایل [examples/04-nohup.sh](examples/04-nohup.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# ایجاد اسکریپت طولانی
cat > /tmp/long_task.sh << 'EOF'
#!/usr/bin/env bash
for i in {1..10}; do
    echo "کار در حال انجام: $i"
    sleep 2
done
echo "کار تمام شد"
EOF

chmod +x /tmp/long_task.sh

# اجرا با nohup
echo "اجرای task طولانی با nohup..."
nohup /tmp/long_task.sh > /tmp/nohup.out 2>&1 &
PID=$!

echo "PID: $PID"
echo "خروجی در: /tmp/nohup.out"

# نمایش خروجی بعد از کمی صبر
sleep 5
echo -e "\nخروجی تا این لحظه:"
cat /tmp/nohup.out

# پاکسازی
kill $PID 2>/dev/null
rm /tmp/long_task.sh /tmp/nohup.out
```

### توضیح خط به خط:

- `nohup` - اجرا حتی بعد از بستن ترمینال
- `> /tmp/nohup.out 2>&1` - هدایت خروجی به فایل
- `&` - اجرا در پس‌زمینه
- خروجی در فایل ذخیره می‌شود

## مانیتورینگ پروسه‌ها

```bash
top             # مانیتورینگ real-time
htop            # مانیتورینگ پیشرفته
iostat          # آمار I/O
vmstat          # آمار memory
```

## مثال ۵: مانیتورینگ ساده

فایل [examples/05-monitor-process.sh](examples/05-monitor-process.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# ایجاد پروسه نمونه
sleep 60 &
PID=$!

echo "مانیتورینگ پروسه $PID برای 5 ثانیه..."

for i in {1..5}; do
    if ps -p $PID > /dev/null; then
        echo "ثانیه $i: پروسه در حال اجراست"
        # نمایش استفاده CPU و Memory
        ps -p $PID -o pid,pcpu,pmem,comm
    else
        echo "ثانیه $i: پروسه خاتمه یافت"
        break
    fi
    sleep 1
done

# خاتمه
kill $PID 2>/dev/null
echo "پروسه خاتمه یافت"
```

### توضیح خط به خط:

- حلقه for برای مانیتورینگ
- `ps -p $PID` - بررسی وجود
- `-o pid,pcpu,pmem,comm` - فرمت خروجی
- `pcpu` - درصد CPU
- `pmem` - درصد Memory

## مدیریت چند پروسه

## مثال ۶: مدیریت چند پروسه

فایل [examples/06-multiple-processes.sh](examples/06-multiple-processes.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# ایجاد چند پروسه
echo "ایجاد 5 پروسه sleep..."
for i in {1..5}; do
    sleep $((i * 10)) &
    PIDS[$!]=$!
done

echo "PIDهای ایجاد شده: ${PIDS[@]}"

# صبر کمی
sleep 2

# نمایش وضعیت
echo -e "\nوضعیت پروسه‌ها:"
for pid in "${PIDS[@]}"; do
    if ps -p $pid > /dev/null; then
        echo "PID $pid: در حال اجرا"
    else
        echo "PID $pid: خاتمه یافته"
    fi
done

# خاتمه همه
echo -e "\nخاتمه تمام پروسه‌ها..."
for pid in "${PIDS[@]}"; do
    kill $pid 2>/dev/null
done

echo "تمام شد"
```

### توضیح خط به خط:

- آرایه PIDS برای ذخیره PIDها
- حلقه for برای ایجاد پروسه‌ها
- بررسی وضعیت هر پروسه
- خاتمه همه پروسه‌ها

## مثال‌های عملی

### اسکریپت مانیتورینگ خودکار

```bash
#!/usr/bin/env bash
# مانیتورینگ خودکار یک پروسه

PROCESS_NAME="nginx"
while true; do
    if ! pgrep $PROCESS_NAME > /dev/null; then
        echo "$PROCESS_NAME در حال اجرا نیست، راه‌اندازی مجدد..."
        # دستور راه‌اندازی
    fi
    sleep 60
done
```

## مثال ۷: مانیتورینگ خودکار

فایل [examples/07-auto-monitor.sh](examples/07-auto-monitor.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# تابع مانیتورینگ
monitor_process() {
    local process_name=$1
    local check_interval=${2:-10}
    
    echo "مانیتورینگ $process_name هر $check_interval ثانیه"
    echo "برای توقف Ctrl+C را فشار دهید"
    
    while true; do
        if pgrep -x "$process_name" > /dev/null; then
            echo "$(date): $process_name در حال اجراست"
        else
            echo "$(date): $process_name در حال اجرا نیست!"
        fi
        sleep $check_interval
    done
}

# تست با یک پروسه ساده
sleep 100 &
MONITOR_PID=$!

# مانیتورینگ برای 3 بار
for i in {1..3}; do
    if pgrep -x "sleep" > /dev/null; then
        echo "تست $i: sleep در حال اجراست"
    else
        echo "تست $i: sleep در حال اجرا نیست"
    fi
    sleep 2
done

# خاتمه
kill $MONITOR_PID 2>/dev/null
echo "تست تمام شد"
```

### توضیح خط به خط:

- `pgrep -x` - جستجوی نام دقیق پروسه
- حلقه while برای مانیتورینگ مداوم
- `date` - timestamp برای لاگ
- `check_interval` - فاصله بررسی

## نکات رایج اشتباهات (Common Pitfalls)

### ۱. فراموش کردن پس‌زمینه

❌ اشتباه:
```bash
sleep 100  # ترمینال قفل می‌شود
```

✅ درست:
```bash
sleep 100 &  # اجرا در پس‌زمینه
```

### ۲. استفاده از kill -9 بدون نیاز

❌ خطرناک:
```bash
kill -9 PID  # خاتمه فوری، ممکن است داده خراب شود
```

✅ بهتر:
```bash
kill PID  # خاتمه نرم
```

### ۳. فراموش کردن nohup

❌ مشکل:
```bash
long_script &  # با بستن ترمینال خاتمه می‌یابد
```

✅ درست:
```bash
nohup long_script &  # ادامه می‌یابد
```

## بهترین شیوه‌ها (Best Practices)

1. **همیشه PID را ذخیره کنید** - برای مدیریت بعدی
2. **از kill قبل از kill -9 استفاده کنید** - خاتمه نرم بهتر است
3. **از nohup برای taskهای طولانی استفاده کنید** - ادامه پس از بستن ترمینال
4. **لاگ بنویسید** - برای ردیابی مشکلات
5. **از timeout استفاده کنید** - برای جلوگیری از loop بی‌نهایت

## خلاصه جلسه

در این جلسه یاد گرفتیم:
- مشاهده پروسه‌ها با ps
- مدیریت پروسه‌ها با kill
- اجرا در پس‌زمینه
- استفاده از nohup
- مانیتورینگ پروسه‌ها
- مدیریت چند پروسه
- مانیتورینگ خودکار
- نکات مهم و اشتباهات رایج

## تمرین‌ها

### تمرین ۱ (ساده): مانیتورینگ ساده

یک اسکریپت بنویسید که:
1. یک پروسه sleep ایجاد کند
2. هر 2 ثانیه وضعیت آن را چک کند
3. بعد از 10 ثانیه آن را خاتمه دهد

فایل تمرین: [exercises/exercise-01.sh](exercises/exercise-01.sh)

### تمرین ۲ (متوسط): مدیر پروسه

یک اسکریپت بنویسید که:
1. نام یک پروسه را بگیرد
2. بررسی کند آیا در حال اجراست
3. اگر نبود، آن را راه‌اندازی کند
4. اگر بود، PID آن را نمایش دهد

فایل تمرین: [exercises/exercise-02.sh](exercises/exercise-02.sh)

## جلسه بعد

در جلسه بعد، مبانی شبکه (Network Basics) را یاد می‌گیریم.
