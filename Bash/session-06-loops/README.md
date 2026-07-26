# جلسه ۶: حلقه‌ها و کنترل جریان

## اهداف یادگیری

در این جلسه یاد می‌گیرید:
- حلقه for و استفاده از آن
- حلقه while و کاربردهای آن
- حلقه until و تفاوت آن با while
- دستورات break و continue
- حلقه‌های تو در تو
- استفاده از حلقه‌ها با فایل‌ها و دستورات

## حلقه for

حلقه for برای تکرار روی یک لیست از آیتم‌ها استفاده می‌شود.

### ساختار پایه

```bash
for item in list; do
    # کد
done
```

### مثال ساده

```bash
for i in 1 2 3 4 5; do
    echo $i
done
```

## مثال ۱: حلقه for ساده

فایل [examples/01-simple-for.sh](examples/01-simple-for.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# حلقه روی اعداد
echo "شمارش تا 5:"
for i in 1 2 3 4 5; do
    echo "عدد: $i"
done

# حلقه روی رشته‌ها
echo -e "\nرنگ‌ها:"
for color in قرمز آبی سبز زرد; do
    echo "رنگ: $color"
done

# حلقه با seq
echo -e "\nشمارش تا 10 با seq:"
for i in $(seq 1 10); do
    echo -n "$i "
done
echo
```

### توضیح خط به خط:

- `for i in 1 2 3 4 5` - حلقه روی اعداد 1 تا 5
- `echo "عدد: $i"` - هر عدد را چاپ می‌کند
- `for color in قرمز آبی سبز زرد` - حلقه روی رشته‌ها
- `$(seq 1 10)` - اعداد 1 تا 10 تولید می‌کند
- `echo -n "$i "` - بدون خط جدید چاپ می‌کند

## حلقه for با محدوده (C-style)

Bash از حلقه for به سبک C هم پشتیبانی می‌کند:

```bash
for ((i=0; i<10; i++)); do
    echo $i
done
```

## مثال ۲: حلقه for به سبک C

فایل [examples/02-c-style-for.sh](examples/02-c-style-for.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# شمارش تا 10
echo "شمارش تا 10:"
for ((i=1; i<=10; i++)); do
    echo "عدد: $i"
done

# شمارش معکوس
echo -e "\nشمارش معکوس از 10 تا 1:"
for ((i=10; i>=1; i--)); do
    echo "عدد: $i"
done

# گام‌های بزرگتر
echo -e "\nاعداد زوج تا 20:"
for ((i=0; i<=20; i+=2)); do
    echo -n "$i "
done
echo
```

### توضیح خط به خط:

- `for ((i=1; i<=10; i++))` - از 1 تا 10 با گام 1
- `for ((i=10; i>=1; i--))` - از 10 تا 1 با گام -1
- `for ((i=0; i<=20; i+=2))` - از 0 تا 20 با گام 2
- `i+=2` - i را 2 واحد افزایش می‌دهد

## حلقه for روی فایل‌ها

حلقه for می‌تواند روی فایل‌ها هم تکرار شود:

```bash
for file in *.txt; do
    echo $file
done
```

## مثال ۳: حلقه روی فایل‌ها

فایل [examples/03-for-files.sh](examples/03-for-files.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# ایجاد فایل‌های نمونه
cd /tmp
mkdir -p loop_test
cd loop_test
touch file1.txt file2.txt file3.txt data.log config.conf

echo "فایل‌های txt در دایرکتوری:"
for file in *.txt; do
    echo "پیدا شد: $file"
done

echo -e "\nتمام فایل‌ها:"
for file in *; do
    echo "فایل: $file"
done

# پاکسازی
cd /tmp
rm -rf loop_test
```

### توضیح خط به خط:

- `*.txt` - تمام فایل‌های با پسوند txt
- `*` - تمام فایل‌ها
- حلقه روی هر فایل که با الگو مطابقت دارد
- در نهایت فایل‌های نمونه پاک می‌شوند

## حلقه while

حلقه while تا زمانی که شرط درست باشد، تکرار می‌شود.

### ساختار

```bash
while [ condition ]; do
    # کد
done
```

## مثال ۴: حلقه while

فایل [examples/04-while-loop.sh](examples/04-while-loop.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# شمارش با while
echo "شمارش تا 5 با while:"
COUNT=1
while [ $COUNT -le 5 ]; do
    echo "عدد: $COUNT"
    COUNT=$((COUNT + 1))
done

# خواندن ورودی تا زمانی که کاربر exit را تایپ کند
echo -e "\nنوع 'exit' برای خروج:"
while true; do
    read -p "کامند: " CMD
    if [ "$CMD" = "exit" ]; then
        echo "خروج..."
        break
    fi
    echo "شما تایپ کردید: $CMD"
done
```

### توضیح خط به خط:

- `COUNT=1` - شمارنده را مقداردهی اولیه می‌کند
- `while [ $COUNT -le 5 ]` - تا زمانی که COUNT کمتر یا مساوی 5 است
- `COUNT=$((COUNT + 1))` - شمارنده را افزایش می‌دهد
- `while true` - حلقه بی‌نهایت
- `break` - حلقه را می‌شکند

## حلقه until

حلقه until تا زمانی که شرط غلط باشد، تکرار می‌شود (برعکس while).

### ساختار

```bash
until [ condition ]; do
    # کد
done
```

## مثال ۵: حلقه until

فایل [examples/05-until-loop.sh](examples/05-until-loop.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# شمارش با until
echo "شمارش تا 5 با until:"
COUNT=1
until [ $COUNT -gt 5 ]; do
    echo "عدد: $COUNT"
    COUNT=$((COUNT + 1))
done

# منتظر ماندن برای فایل
echo -e "\nمنتظر فایل test.txt..."
until [ -f "/tmp/test.txt" ]; do
    echo "در حال انتظار..."
    sleep 1
done
echo "فایل پیدا شد!"

# ایجاد فایل برای تست
touch /tmp/test.txt
rm /tmp/test.txt
```

### توضیح خط به خط:

- `until [ $COUNT -gt 5 ]` - تا زمانی که COUNT بزرگتر از 5 نباشد
- `until [ -f "/tmp/test.txt" ]` - تا زمانی که فایل وجود نداشته باشد
- `sleep 1` - 1 ثانیه صبر می‌کند
- وقتی فایل ایجاد شود، حلقه تمام می‌شود

## break و continue

- `break` - حلقه را کاملاً می‌شکند
- `continue` - به تکرار بعدی حلقه می‌رود

## مثال ۶: break و continue

فایل [examples/06-break-continue.sh](examples/06-break-continue.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "استفاده از break:"
for i in {1..10}; do
    if [ $i -eq 5 ]; then
        echo "رسیدیم به 5، خروج از حلقه"
        break
    fi
    echo "عدد: $i"
done

echo -e "\nاستفاده از continue:"
for i in {1..10}; do
    if [ $((i % 2)) -eq 0 ]; then
        continue  # اعداد زوج را نادیده می‌گیرد
    fi
    echo "عدد فرد: $i"
done
```

### توضیح خط به خط:

- `{1..10}` - محدوده 1 تا 10
- `if [ $i -eq 5 ]` - وقتی به 5 برسیم
- `break` - حلقه را می‌شکند
- `if [ $((i % 2)) -eq 0 ]` - اگر عدد زوج باشد
- `continue` - به تکرار بعدی می‌رود (اعداد زوج چاپ نمی‌شوند)

## حلقه‌های تو در تو

می‌توانید حلقه‌ها را درون هم قرار دهید:

## مثال ۷: حلقه‌های تو در تو

فایل [examples/07-nested-loops.sh](examples/07-nested-loops.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# جدول ضرب
echo "جدول ضرب 5x5:"
for ((i=1; i<=5; i++)); do
    for ((j=1; j<=5; j++)); do
        PRODUCT=$((i * j))
        printf "%4d" $PRODUCT
    done
    echo
done

# ترکیب رنگ‌ها
echo -e "\nترکیب رنگ‌ها:"
COLORS=("قرمز" "آبی" "سبز")
SHAPES=("دایره" "مربع" "مثلث")

for color in "${COLORS[@]}"; do
    for shape in "${SHAPES[@]}"; do
        echo "$color $shape"
    done
done
```

### توضیح خط به خط:

- حلقه بیرونی i از 1 تا 5
- حلقه داخلی j از 1 تا 5
- `printf "%4d"` - عدد را با 4 کاراکتر عرض چاپ می‌کند
- `echo` - خط جدید بعد از هر ردیف
- `"${COLORS[@]}"` - تمام عناصر آرایه
- ترکیب هر رنگ با هر شکل

## حلقه روی خروجی دستور

می‌توانید روی خروجی یک دستور حلقه بزنید:

## مثال ۸: حلقه روی خروجی دستور

فایل [examples/08-command-output.sh](examples/08-command-output.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# حلقه روی خروجی ls
echo "فایل‌ها در دایرکتوری جاری:"
for file in $(ls); do
    echo "فایل: $file"
done

# حلقه روی خطوط یک فایل
echo -e "\nخواندن خطوط فایل:"
echo -e "خط 1\nخط 2\nخط 3" > /tmp/test_file.txt

while IFS= read -r line; do
    echo "خط: $line"
done < /tmp/test_file.txt

rm /tmp/test_file.txt
```

### توضیح خط به خط:

- `$(ls)` - خروجی دستور ls
- `while IFS= read -r line` - خواندن خط به خط فایل
- `IFS=` - فاصله‌ها را حفظ می‌کند
- `-r` - کاراکترهای escape را نادیده می‌گیرد
- `< /tmp/test_file.txt` - فایل را به حلقه می‌دهد

## نکات رایج اشتباهات (Common Pitfalls)

### ۱. فراموش کردن done

❌ اشتباه:
```bash
for i in 1 2 3; do
    echo $i
# فراموش کردن done
```

✅ درست:
```bash
for i in 1 2 3; do
    echo $i
done
```

### ۲. استفاده از فاصله در for C-style

❌ اشتباه:
```bash
for ((i=0;i<10;i++)); do  # فاصله ندارند
```

✅ درست:
```bash
for ((i=0; i<10; i++)); do  # فاصله دارند
```

### ۳. حلقه بی‌نهایت بدون break

❌ خطرناک:
```bash
while true; do
    echo "بی‌نهایت"
    # فراموش کردن break
done
```

✅ درست:
```bash
while true; do
    echo "بی‌نهایت"
    if [ condition ]; then
        break
    fi
done
```

### ۴. تغییر متغیر حلقه درون حلقه

❌ مشکل‌دار:
```bash
for i in {1..10}; do
    i=$((i + 2))  # تغییر i
    echo $i
done
```

✅ درست:
```bash
for i in {1..10}; do
    j=$((i + 2))  # استفاده از متغیر دیگر
    echo $j
done
```

## بهترین شیوه‌ها (Best Practices)

1. **از for C-style برای محدوده‌های عددی استفاده کنید** - خواناتر است
2. **همیشه شرط خروج را در حلقه‌های بی‌نهایت تعریف کنید** - برای جلوگیری از loop بی‌نهایت
3. **از break و continue با احتیاط استفاده کنید** - می‌تواند کد را پیچیده کند
4. **حلقه‌های تو در تو را تا حد ممکن ساده نگه دارید** - برای خوانایی
5. **از quote برای متغیرها در حلقه‌ها استفاده کنید** - برای جلوگیری از مشکلات

## خلاصه جلسه

در این جلسه یاد گرفتیم:
- حلقه for و انواع مختلف آن
- حلقه while و کاربردهای آن
- حلقه until و تفاوت آن با while
- استفاده از break و continue
- حلقه‌های تو در تو
- حلقه روی فایل‌ها و خروجی دستورات
- نکات مهم و اشتباهات رایج

## تمرین‌ها

### تمرین ۱ (ساده): شمارش معکوس

یک اسکریپت بنویسید که:
1. یک عدد از کاربر بگیرد
2. از آن عدد تا 1 شمارش معکوس کند
3. در انتها پیام "انفجار!" چاپ کند

فایل تمرین: [exercises/exercise-01.sh](exercises/exercise-01.sh)

### تمرین ۲ (متوسط): جستجوی فایل

یک اسکریپت بنویسید که:
1. یک نام فایل را از کاربر بگیرد
2. در دایرکتوری جاری و زیردایرکتوری‌ها جستجو کند
3. تمام فایل‌هایی که با آن نام مطابقت دارند را لیست کند
4. تعداد فایل‌های پیدا شده را چاپ کند

فایل تمرین: [exercises/exercise-02.sh](exercises/exercise-02.sh)

## جلسه بعد

در جلسه بعد، توابع (Functions) و scope متغیرها را یاد می‌گیریم.
