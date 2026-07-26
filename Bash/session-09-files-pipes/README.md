# جلسه ۹: کار با فایل‌ها، ریدایرکت و Pipeline

## اهداف یادگیری

در این جلسه یاد می‌گیرید:
- ریدایرکت ورودی و خروجی
- استفاده از pipe (|)
- ریدایرکت استاندارد (stdin, stdout, stderr)
- Process substitution
- کار با فایل‌ها در اسکریپت‌ها
- ترکیب دستورات با pipe

## ریدایرکت خروجی

### خروجی استاندارد (stdout)

```bash
command > file      # بازنویسی فایل
command >> file     # افزودن به فایل
```

### خروجی خطا (stderr)

```bash
command 2> file     # بازنویسی فایل خطا
command 2>> file    # افزودن به فایل خطا
```

### هر دو خروجی

```bash
command > file 2>&1    # هر دو در یک فایل
command &> file        # روش کوتاه‌تر
```

## مثال ۱: ریدایرکت خروجی

فایل [examples/01-output-redirect.sh](examples/01-output-redirect.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# ریدایرکت به فایل (بازنویسی)
echo "خط اول" > /tmp/test.txt
echo "خط دوم" > /tmp/test.txt  # خط اول پاک می‌شود

echo "محتوای فایل:"
cat /tmp/test.txt

# ریدایرکت با افزودن
echo "خط سوم" >> /tmp/test.txt
echo "خط چهارم" >> /tmp/test.txt

echo -e "\nمحتوای فایل بعد از افزودن:"
cat /tmp/test.txt

# ریدایرکت stderr
echo "این یک پیام است" > /tmp/output.txt
ls /nonexistent 2> /tmp/error.txt

echo -e "\nخروجی خطا:"
cat /tmp/error.txt

# ترکیب stdout و stderr
echo "خروجی استاندارد" > /tmp/combined.txt
ls /nonexistent >> /tmp/combined.txt 2>&1

echo -e "\nخروجی ترکیبی:"
cat /tmp/combined.txt

# پاکسازی
rm /tmp/test.txt /tmp/output.txt /tmp/error.txt /tmp/combined.txt
```

### توضیح خط به خط:

- `> file` - خروجی را در فایل می‌نویسد (فایل قبلی پاک می‌شود)
- `>> file` - خروجی را به انتهای فایل اضافه می‌کند
- `2> file` - خروجی خطا را در فایل می‌نویسد
- `2>&1` - stderr را به stdout redirect می‌کند
- `&> file` - هر دو خروجی را در یک فایل می‌نویسد

## ریدایرکت ورودی

### ورودی استاندارد (stdin)

```bash
command < file      # خواندن از فایل
```

## مثال ۲: ریدایرکت ورودی

فایل [examples/02-input-redirect.sh](examples/02-input-redirect.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# ایجاد فایل ورودی
cat > /tmp/input.txt << EOF
علی
رضا
محمد
حسین
EOF

# خواندن از فایل
echo "نام‌ها از فایل:"
while read name; do
    echo "  سلام، $name!"
done < /tmp/input.txt

# استفاده با grep
echo -e "\nجستجوی 'علی' در فایل:"
grep "علی" < /tmp/input.txt

# استفاده با sort
echo -e "\nنام‌های مرتب شده:"
sort < /tmp/input.txt

# پاکسازی
rm /tmp/input.txt
```

### توضیح خط به خط:

- `cat > file << EOF` - ایجاد فایل با محتوای چند خطی
- `while read name; do` - خط به خط از فایل می‌خواند
- `done < file` - فایل را به حلقه می‌دهد
- `grep < file` - جستجو در فایل
- `sort < file` - مرتب کردن محتوای فایل

## Pipeline (|)

Pipe خروجی یک دستور را به ورودی دستور بعدی می‌فرستد.

```bash
command1 | command2
```

## مثال ۳: Pipeline

فایل [examples/03-pipeline.sh](examples/03-pipeline.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# ایجاد فایل نمونه
cat > /tmp/data.txt << EOF
علی 25
رضا 30
محمد 22
حسین 28
مهدی 35
EOF

echo "محتوای فایل:"
cat /tmp/data.txt

# فیلتر کردن با grep
echo -e "\nخطوط حاوی 'علی':"
grep "علی" /tmp/data.txt

# مرتب کردن
echo -e "\nمرتب شده:"
sort /tmp/data.txt

# شمارش خطوط
echo -e "\nتعداد خطوط:"
wc -l < /tmp/data.txt

# ترکیب pipeline
echo -e "\nخطوط حاوی عدد بالای 25:"
grep -E "[0-9]{2,}" /tmp/data.txt | grep -E "(2[5-9]|3[0-9])"

# استخراج ستون دوم
echo -e "\nسن‌ها:"
awk '{print $2}' /tmp/data.txt

# محاسبه میانگین
echo -e "\nمیانگین سن‌ها:"
awk '{sum += $2; count++} END {print sum/count}' /tmp/data.txt

# پاکسازی
rm /tmp/data.txt
```

### توضیح خط به خط:

- `grep "pattern" file` - خطوط حاوی الگو را پیدا می‌کند
- `sort file` - خطوط را مرتب می‌کند
- `wc -l < file` - تعداد خطوط را می‌شمارد
- `|` - خروجی را به دستور بعدی می‌فرستد
- `awk '{print $2}'` - ستون دوم را استخراج می‌کند
- `awk '{sum += $2}'` - جمع ستون دوم را محاسبه می‌کند

## Process Substitution

Process substitution به شما اجازه می‌دهد خروجی یک دستور را به عنوان فایل به دستور دیگر بدهید.

```bash
command < <(other_command)
command > >(other_command)
```

## مثال ۴: Process Substitution

فایل [examples/04-process-substitution.sh](examples/04-process-substitution.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# مقایسه دو دستور
echo "مقایسه خروجی دو دستور:"
diff <(echo "Hello") <(echo "Hello")
diff <(echo "Hello") <(echo "World")

# خواندن از خروجی دستور
echo -e "\nخواندن از خروجی ls:"
while read file; do
    echo "فایل: $file"
done < <(ls /tmp | head -5)

# نوشتن به ورودی دستور
echo -e "\nنوشتن به grep:"
echo -e "علی\nرضا\nمحمد" | grep "علی"

# ترکیب چند فایل
echo -e "\nترکیب خروجی:"
cat <(echo "خط 1") <(echo "خط 2") <(echo "خط 3")
```

### توضیح خط به خط:

- `<(command)` - خروجی دستور را به عنوان فایل می‌دهد
- `diff <(cmd1) <(cmd2)` - خروجی دو دستور را مقایسه می‌کند
- `while read ... < <(ls)` - از خروجی ls می‌خواند
- `echo | grep` - pipe ساده برای grep

## کار با فایل‌ها در اسکریپت

## مثال ۵: خواندن و نوشتن فایل

فایل [examples/05-file-operations.sh](examples/05-file-operations.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

FILE="/tmp/myfile.txt"

# نوشتن در فایل
echo "نوشتن در فایل..."
cat > "$FILE" << EOF
این خط اول است
این خط دوم است
این خط سوم است
EOF

echo "فایل ایجاد شد"

# خواندن خط به خط
echo -e "\nخواندن خط به خط:"
while IFS= read -r line; do
    echo "خط: $line"
done < "$FILE"

# خواندن به آرایه
echo -e "\nخواندن به آرایه:"
mapfile -t LINES < "$FILE"
for i in "${!LINES[@]}"; do
    echo "خط $((i+1)): ${LINES[$i]}"
done

# بررسی وجود فایل
if [ -f "$FILE" ]; then
    echo -e "\nفایل وجود دارد"
fi

# بررسی اندازه فایل
SIZE=$(wc -c < "$FILE")
echo "اندازه فایل: $SIZE بایت"

# پاکسازی
rm "$FILE"
echo "فایل حذف شد"
```

### توضیح خط به خط:

- `cat > file << EOF` - ایجاد فایل با محتوای چند خطی
- `while IFS= read -r line` - خط به خط می‌خواند (IFS فاصله را حفظ می‌کند)
- `mapfile -t LINES < file` - تمام خطوط را در آرایه می‌خواند
- `[ -f "$FILE" ]` - بررسی وجود فایل
- `wc -c < file` - اندازه فایل را برمی‌گرداند
- `rm file` - فایل را حذف می‌کند

## Here Documents و Here Strings

### Here Document

```bash
command << EOF
خط 1
خط 2
EOF
```

### Here String

```bash
command <<< "string"
```

## مثال ۶: Here Documents

فایل [examples/06-here-docs.sh](examples/06-here-docs.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# Here Document
echo "ایجاد فایل با Here Document:"
cat > /tmp/heredoc.txt << EOF
این یک فایل است
با چند خط
ایجاد شده با Here Document
EOF

cat /tmp/heredoc.txt

# Here String
echo -e "\nاستفاده از Here String:"
grep "فایل" <<< "این یک فایل است برای تست"

# Here Document با متغیر
NAME="علی"
cat << EOF
سلام $NAME
این یک Here Document با متغیر است
EOF

# Here Document بدون تفسیر (quoted)
cat << 'EOF'
این $NAME تفسیر نمی‌شود
EOF

# پاکسازی
rm /tmp/heredoc.txt
```

### توضیح خط به خط:

- `<< EOF` - شروع Here Document
- `EOF` - پایان Here Document
- متغیرها درون Here Document تفسیر می‌شوند
- `<< 'EOF'` - متغیرها تفسیر نمی‌شوند (literal)
- `<<< "string"` - Here String برای رشته‌های کوتاه

## نکات رایج اشتباهات (Common Pitfalls)

### ۱. فراموش کردن فاصله در ریدایرکت

❌ اشتباه:
```bash
echo "test">file  # بدون فاصله
```

✅ درست:
```bash
echo "test" > file  # با فاصله
```

### ۲. استفاده از > به جای >>

❌ اشتباه:
```bash
echo "line1" > file
echo "line2" > file  # خط اول پاک می‌شود
```

✅ درست:
```bash
echo "line1" > file
echo "line2" >> file  # خط اول حفظ می‌شود
```

### ۳. فراموش کردن quote در نام فایل‌ها

❌ اشتباه:
```bash
cat > my file.txt  # ایجاد دو فایل
```

✅ درست:
```bash
cat > "my file.txt"  # یک فایل با فاصله
```

### ۴. استفاده از pipe بدون درک

❌ مشکل‌دار:
```bash
command | command | command | command  # پیچیده و غیرقابل خواندن
```

✅ بهتر:
```bash
command > temp.txt
command2 < temp.txt > result.txt
```

## بهترین شیوه‌ها (Best Practices)

1. **همیشه از فاصله دور ریدایرکت استفاده کنید** - برای خوانایی
2. **از >> برای افزودن استفاده کنید** - برای حفظ محتوای قبلی
3. **نام فایل‌ها را quote کنید** - برای جلوگیری از مشکلات با فاصله
4. **از pipe با احتیاط استفاده کنید** - می‌تواند کد را پیچیده کند
5. **فایل‌های موقت را پاک کنید** - برای جلوگیری از پر شدن دیسک

## خلاصه جلسه

در این جلسه یاد گرفتیم:
- ریدایرکت ورودی و خروجی
- ریدایرکت stderr
- استفاده از pipe
- Process substitution
- کار با فایل‌ها در اسکریپت‌ها
- Here Documents و Here Strings
- نکات مهم و اشتباهات رایج

## تمرین‌ها

### تمرین ۱ (ساده): لاگر ساده

یک اسکریپت بنویسید که:
1. پیام‌ها را از کاربر بگیرد
2. هر پیام را با timestamp در فایل log ذخیره کند
3. محتوای فایل log را نمایش دهد

فایل تمرین: [exercises/exercise-01.sh](exercises/exercise-01.sh)

### تمرین ۲ (متوسط): پردازش فایل CSV

یک اسکریپت بنویسید که:
1. یک فایل CSV ساده ایجاد کند (نام، سن، شهر)
2. با استفاده از awk و pipe:
   - میانگین سن را محاسبه کند
   - تعداد افراد در هر شهر را بشمارد
   - نتایج را در فایل جداگانه ذخیره کند

فایل تمرین: [exercises/exercise-02.sh](exercises/exercise-02.sh)

## جلسه بعد

در جلسه بعد، مدیریت خطا (error handling)، exit codeها، trap، و دیباگ اسکریپت را یاد می‌گیریم.
