# جلسه ۱۰: مدیریت خطا و دیباگ اسکریپت

## اهداف یادگیری

در این جلسه یاد می‌گیرید:
- Exit codeها و معنای آن‌ها
- بررسی خطا با $?
- استفاده از set برای مدیریت خطا
- دستور trap برای مدیریت سیگنال‌ها
- دیباگ کردن اسکریپت‌ها
- بهترین شیوه‌های مدیریت خطا

## Exit Codeها

هر دستور در Bash یک exit code برمی‌گرداند:
- **0**: موفق
- **غیر 0**: ناموفق (هر عدد دیگری)

## مثال ۱: Exit Codeها

فایل [examples/01-exit-codes.sh](examples/01-exit-codes.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# دستور موفق
ls /tmp
echo "Exit code ls /tmp: $?"

# دستور ناموفق
ls /nonexistent
echo "Exit code ls /nonexistent: $?"

# دستور grep
grep "test" /etc/passwd
echo "Exit code grep: $?"

# استفاده از exit code
if ls /tmp > /dev/null 2>&1; then
    echo "دستور موفق بود"
else
    echo "دستور ناموفق بود"
fi
```

### توضیح خط به خط:

- `$?` - exit code آخرین دستور را برمی‌گرداند
- `ls /tmp` - معمولاً موفق است (exit code 0)
- `ls /nonexistent` - ناموفق است (exit code غیر 0)
- `if command; then` - بر اساس exit code تصمیم می‌گیرد
- `> /dev/null 2>&1` - خروجی را مخفی می‌کند

## set برای مدیریت خطا

```bash
set -e          # خروج از اسکریپت در صورت خطا
set -u          # خطا در صورت استفاده از متغیر تعریف نشده
set -x          # نمایش دستورات قبل از اجرا (debug mode)
set -o pipefail   - خطا در صورت خطا در هر بخش از pipe
```

## مثال ۲: استفاده از set

فایل [examples/02-set-options.sh](examples/02-set-options.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "بدون set -e:"
false  # exit code 1
echo "این چاپ می‌شود"

echo -e "\nبا set -e:"
set -e
# false  # این باعث خروج از اسکریپت می‌شود
echo "این چاپ نمی‌شود"

set +e  # غیرفعال کردن set -e

echo -e "\nبدون set -u:"
echo $UNDEFINED_VAR  # خالی چاپ می‌شود

echo -e "\nبا set -u:"
set -u
# echo $UNDEFINED_VAR  # خطا می‌دهد
set +u

echo -e "\nبا set -x (debug mode):"
set -x
echo "این دستور نمایش داده می‌شود"
set +x
```

### توضیح خط به خط:

- `set -e` - اسکریپت در صورت خطا خروج می‌کند
- `false` - دستوری که همیشه exit code 1 برمی‌گرداند
- `set +e` - set -e را غیرفعال می‌کند
- `set -u` - خطا در صورت استفاده از متغیر تعریف نشده
- `set -x` - دستورات را قبل از اجرا نمایش می‌دهد (debug)
- `set +x` - debug mode را غیرفعال می‌کند

## trap برای مدیریت سیگنال‌ها

trap به شما اجازه می‌دهد به سیگنال‌ها پاسخ دهید.

```bash
trap 'command' SIGNAL
```

سیگنال‌های رایج:
- `EXIT` - هنگام خروج از اسکریپت
- `INT` - Ctrl+C
- `TERM` - سیگنال termination

## مثال ۳: استفاده از trap

فایل [examples/03-trap.sh](examples/03-trap.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# تابع پاکسازی
cleanup() {
    echo "پاکسازی..."
    rm -f /tmp/temp_file.txt
    echo "پاکسازی انجام شد"
}

# trap برای EXIT
trap cleanup EXIT

# ایجاد فایل موقت
echo "ایجاد فایل موقت..."
echo "test data" > /tmp/temp_file.txt

# trap برای INT (Ctrl+C)
trap 'echo "Ctrl+C فشرده شد!"; exit 1' INT

echo "اسکریپت در حال اجراست..."
echo "برای تست Ctrl+C را فشار دهید یا صبر کنید تا تمام شود"

sleep 3

echo "اسکریپت با موفقیت تمام شد"
```

### توضیح خط به خط:

- `cleanup() {` - تابع پاکسازی تعریف می‌کند
- `trap cleanup EXIT` - تابع cleanup را هنگام خروج اجرا می‌کند
- فایل موقت ایجاد می‌شود
- `trap '...' INT` - هنگام فشردن Ctrl+C اجرا می‌شود
- حتی اگر اسکریپت با خطا تمام شود، cleanup اجرا می‌شود

## دیباگ کردن اسکریپت‌ها

## مثال ۴: دیباگ

فایل [examples/04-debug.sh](examples/04-debug.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# روش ۱: set -x
echo "روش ۱: set -x"
set -x
VAR1="test"
VAR2="hello"
echo "$VAR1 $VAR2"
set +x

# روش ۲: echo برای دیباگ
echo -e "\nروش ۲: echo برای دیباگ"
debug() {
    echo "[DEBUG] $*" >&2
}

NAME="علی"
debug "NAME = $NAME"
AGE=25
debug "AGE = $AGE"

# روش ۳: bash -x
echo -e "\nروش ۳: اجرا با bash -x"
echo "برای تست: bash -x script.sh"
```

### توضیح خط به خط:

- `set -x` - هر دستور را قبل از اجرا نمایش می‌دهد
- `set +x` - debug mode را غیرفعال می‌کند
- `debug() {` - تابع دیباگ تعریف می‌کند
- `>&2` - خروجی را به stderr می‌فرستد
- `bash -x script.sh` - اسکریپت را با debug mode اجرا می‌کند

## مدیریت خطا در توابع

## مثال ۵: مدیریت خطا در توابع

فایل [examples/05-function-error-handling.sh](examples/05-function-error-handling.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# تابع با مدیریت خطا
divide() {
    local num1=$1
    local num2=$2
    
    if [ $num2 -eq 0 ]; then
        echo "خطا: تقسیم بر صفر" >&2
        return 1
    fi
    
    echo $(($num1 / $num2))
    return 0
}

# استفاده از تابع
divide 10 2
if [ $? -eq 0 ]; then
    echo "تقسیم موفق بود"
else
    echo "تقسیم ناموفق بود"
fi

divide 10 0
if [ $? -eq 0 ]; then
    echo "تقسیم موفق بود"
else
    echo "تقسیم ناموفق بود"
fi
```

### توضیح خط به خط:

- `divide() {` - تابع با مدیریت خطا تعریف می‌کند
- `if [ $num2 -eq 0 ]` - بررسی تقسیم بر صفر
- `>&2` - پیام خطا به stderr
- `return 1` - exit code ناموفق
- `return 0` - exit code موفق
- `$?` - exit code تابع را بررسی می‌کند

## نکات رایج اشتباهات (Common Pitfalls)

### ۱. فراموش کردن بررسی exit code

❌ اشتباه:
```bash
rm important_file
echo "فایل حذف شد"  # حتی اگر حذف نشده باشد
```

✅ درست:
```bash
if rm important_file; then
    echo "فایل حذف شد"
else
    echo "خطا در حذف فایل"
fi
```

### ۲. استفاده از set -e بدون درک

❌ خطرناک:
```bash
set -e
command_that_might_fail  # اسکریپت خروج می‌شود
```

✅ بهتر:
```bash
command_that_might_fail || true  # ادامه می‌دهد
```

### ۳. فراموش کردن trap برای پاکسازی

❌ مشکل‌دار:
```bash
create_temp_file
# اگر خطا رخ دهد، فایل موقت باقی می‌ماند
```

✅ درست:
```bash
trap cleanup EXIT
create_temp_file
# همیشه cleanup اجرا می‌شود
```

### ۴. نادیده گرفتن stderr

❌ اشتباه:
```bash
command 2>/dev/null  # خطاها مخفی می‌شوند
```

✅ درست:
```bash
command 2>error.log  # خطاها ذخیره می‌شوند
```

## بهترین شیوه‌ها (Best Practices)

1. **همیشه exit code را بررسی کنید** - برای دستورات مهم
2. **از trap برای پاکسازی استفاده کنید** - برای فایل‌های موقت
3. **از set -e با احتیاط استفاده کنید** - ممکن است غیرمنتظره باشد
4. **پیام‌های خطا واضح بنویسید** - برای دیباگ آسان‌تر
5. **از debug mode در توسعه استفاده کنید** - برای پیدا کردن خطا

## خلاصه جلسه

در این جلسه یاد گرفتیم:
- Exit codeها و معنای آن‌ها
- بررسی خطا با $?
- استفاده از set برای مدیریت خطا
- دستور trap برای مدیریت سیگنال‌ها
- دیباگ کردن اسکریپت‌ها
- مدیریت خطا در توابع
- نکات مهم و اشتباهات رایج

## تمرین‌ها

### تمرین ۱ (ساده): مدیریت خطا در فایل

یک اسکریپت بنویسید که:
1. سعی کند یک فایل را بخواند
2. اگر فایل وجود نداشت، پیام خطا دهد و خارج شود
3. اگر فایل وجود داشت، محتوای آن را چاپ کند

فایل تمرین: [exercises/exercise-01.sh](exercises/exercise-01.sh)

### تمرین ۲ (متوسط): اسکریپت با trap

یک اسکریپت بنویسید که:
1. یک فایل موقت ایجاد کند
2. با trap مطمئن شود که فایل پاک می‌شود
3. به Ctrl+C پاسخ دهد
4. پیام مناسب در صورت خطا چاپ کند

فایل تمرین: [exercises/exercise-02.sh](exercises/exercise-02.sh)

## جلسه بعد

در جلسه بعد، ابزارهای کاربردی خط فرمان (grep, sed, awk, find, xargs) را یاد می‌گیریم.
