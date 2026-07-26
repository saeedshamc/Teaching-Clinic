# جلسه ۱۱: ابزارهای کاربردی خط فرمان

## اهداف یادگیری

در این جلسه یاد می‌گیرید:
- استفاده از grep برای جستجو در متن
- استفاده از sed برای ویرایش متن
- استفاده از awk برای پردازش متن
- استفاده از find برای جستجوی فایل‌ها
- استفاده از xargs برای اجرای دستورات
- ترکیب این ابزارها با Bash

## grep

grep برای جستجوی الگو در فایل‌ها استفاده می‌شود.

```bash
grep "pattern" file          # جستجوی ساده
grep -i "pattern" file       # نادیده گرفتن بزرگی و کوچکی
grep -r "pattern" dir       # جستجوی بازگشتی
grep -v "pattern" file       # خطوط بدون الگو
grep -n "pattern" file       # نمایش شماره خط
```

## مثال ۱: grep

فایل [examples/01-grep.sh](examples/01-grep.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# ایجاد فایل نمونه
cat > /tmp/test.txt << EOF
علی برنامه‌نویس است
رضا طراح است
محمد مدیر است
علی عاشق Bash است
حسین مهندس است
EOF

echo "محتوای فایل:"
cat /tmp/test.txt

# جستجوی ساده
echo -e "\nجستجوی 'علی':"
grep "علی" /tmp/test.txt

# جستجوی با -i (case insensitive)
echo -e "\nجستجوی 'ali' با -i:"
grep -i "ali" /tmp/test.txt

# جستجوی معکوس (بدون الگو)
echo -e "\nخطوط بدون 'علی':"
grep -v "علی" /tmp/test.txt

# جستجوی با شماره خط
echo -e "\nجستجوی با شماره خط:"
grep -n "است" /tmp/test.txt

# جستجوی با regex
echo -e "\nجستجوی با regex (کلمات شروع شده با 'ع'):"
grep -E "^ع" /tmp/test.txt

# پاکسازی
rm /tmp/test.txt
```

### توضیح خط به خط:

- `grep "pattern" file` - خطوط حاوی الگو را پیدا می‌کند
- `grep -i "pattern"` - بزرگی و کوچکی را نادیده می‌گیرد
- `grep -v "pattern"` - خطوط بدون الگو را نمایش می‌دهد
- `grep -n "pattern"` - شماره خط را هم نمایش می‌دهد
- `grep -E "regex"` - با regex پیشرفته جستجو می‌کند
- `^ع` - کلماتی که با "ع" شروع می‌شوند

## sed

sed برای ویرایش متن استفاده می‌شود.

```bash
sed 's/old/new/' file        # جایگزینی اولین occurrence
sed 's/old/new/g' file       # جایگزینی تمام occurrenceها
sed '3d' file                # حذف خط 3
sed '/pattern/d' file        # حذف خطوط حاوی الگو
```

## مثال ۲: sed

فایل [examples/02-sed.sh](examples/02-sed.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# ایجاد فایل نمونه
cat > /tmp/test.txt << EOF
Hello World
Hello Bash
Hello Linux
World is great
EOF

echo "محتوای اصلی:"
cat /tmp/test.txt

# جایگزینی ساده
echo -e "\nجایگزینی Hello با Hi:"
sed 's/Hello/Hi/' /tmp/test.txt

# جایگزینی سراسری
echo -e "\nجایگزینی سراسری Hello با Hi:"
sed 's/Hello/Hi/g' /tmp/test.txt

# حذف خط
echo -e "\nحذف خط 2:"
sed '2d' /tmp/test.txt

# حذف خطوط حاوی الگو
echo -e "\nحذف خطوط حاوی World:"
sed '/World/d' /tmp/test.txt

# ویرایش درجا
echo -e "\nویرایش درجا:"
cp /tmp/test.txt /tmp/test_backup.txt
sed -i 's/Hello/Hi/g' /tmp/test.txt
cat /tmp/test.txt

# بازگردانی
mv /tmp/test_backup.txt /tmp/test.txt
rm /tmp/test.txt
```

### توضیح خط به خط:

- `sed 's/old/new/'` - اولین occurrence را جایگزین می‌کند
- `sed 's/old/new/g'` - تمام occurrenceها را جایگزین می‌کند
- `sed '2d'` - خط 2 را حذف می‌کند
- `sed '/pattern/d'` - خطوط حاوی الگو را حذف می‌کند
- `sed -i` - فایل را درجا ویرایش می‌کند

## awk

awk برای پردازش متن و داده‌ها استفاده می‌شود.

```bash
awk '{print $1}' file         # چاپ ستون اول
awk -F',' '{print $2}' file   # با delimiter کاما
awk '{sum += $1} END {print sum}' file  # محاسبه جمع
```

## مثال ۳: awk

فایل [examples/03-awk.sh](examples/03-awk.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# ایجاد فایل نمونه
cat > /tmp/data.txt << EOF
علی 25 تهران
رضا 30 شیراز
محمد 22 اصفهان
حسین 28 مشهد
EOF

echo "محتوای فایل:"
cat /tmp/data.txt

# چاپ ستون اول
echo -e "\nستون اول (نام‌ها):"
awk '{print $1}' /tmp/data.txt

# چاپ ستون دوم
echo -e "\nستون دوم (سن‌ها):"
awk '{print $2}' /tmp/data.txt

# محاسبه مجموع سن‌ها
echo -e "\nمجموع سن‌ها:"
awk '{sum += $2} END {print sum}' /tmp/data.txt

# محاسبه میانگین
echo -e "\nمیانگین سن‌ها:"
awk '{sum += $2; count++} END {print sum/count}' /tmp/data.txt

# فیلتر کردن
echo -e "\nافراد بالای 25 سال:"
awk '$2 > 25 {print $1, $2}' /tmp/data.txt

# پاکسازی
rm /tmp/data.txt
```

### توضیح خط به خط:

- `awk '{print $1}'` - ستون اول را چاپ می‌کند
- `$1, $2, ...` - ستون‌ها (با فاصله جدا می‌شوند)
- `sum += $2` - جمع ستون دوم
- `END {print sum}` - بعد از پایان فایل اجرا می‌شود
- `$2 > 25` - شرط برای فیلتر کردن

## find

find برای جستجوی فایل‌ها استفاده می‌شود.

```bash
find . -name "*.txt"           # جستجوی فایل‌های txt
find . -type d                # جستجوی دایرکتوری‌ها
find . -size +1M              # فایل‌های بزرگتر از 1MB
find . -mtime -7              # فایل‌های تغییر کرده در 7 روز اخیر
```

## مثال ۴: find

فایل [examples/04-find.sh](examples/04-find.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# ایجاد ساختار نمونه
mkdir -p /tmp/find_test/{dir1,dir2}
touch /tmp/find_test/file1.txt
touch /tmp/find_test/file2.log
touch /tmp/find_test/dir1/data.txt
touch /tmp/find_test/dir2/config.conf

echo "جستجوی فایل‌های txt:"
find /tmp/find_test -name "*.txt"

echo -e "\nجستجوی دایرکتوری‌ها:"
find /tmp/find_test -type d

echo -e "\nجستجوی فایل‌های log:"
find /tmp/find_test -name "*.log"

echo -e "\nجستجوی با نام دقیق:"
find /tmp/find_test -name "file1.txt"

# پاکسازی
rm -rf /tmp/find_test
```

### توضیح خط به خط:

- `find /path -name "*.txt"` - فایل‌های با پسوند txt
- `find /path -type d` - فقط دایرکتوری‌ها
- `find /path -name "file"` - جستجوی نام دقیق
- ساختار نمونه ایجاد و سپس پاک می‌شود

## xargs

xargs برای اجرای دستورات روی ورودی استفاده می‌شود.

```bash
cat files.txt | xargs rm       # حذف فایل‌ها
find . -name "*.txt" | xargs cat  # چاپ فایل‌های txt
echo "file1 file2" | xargs touch  # ایجاد فایل‌ها
```

## مثال ۵: xargs

فایل [examples/05-xargs.sh](examples/05-xargs.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# ایجاد فایل‌های نمونه
mkdir -p /tmp/xargs_test
cd /tmp/xargs_test
touch file1.txt file2.txt file3.txt

echo "فایل‌های ایجاد شده:"
ls

# استفاده از xargs برای حذف
echo -e "\nحذف با xargs:"
ls | xargs rm

echo -e "\nبعد از حذف:"
ls

# ایجاد مجدد فایل‌ها
touch file1.txt file2.txt file3.txt

# استفاده با find
echo -e "\nجستجو و چاپ با xargs:"
find . -name "*.txt" | xargs cat

# پاکسازی
cd /tmp
rm -rf /tmp/xargs_test
```

### توضیح خط به خط:

- `ls | xargs rm` - خروجی ls را به rm می‌دهد
- `find | xargs cat` - فایل‌های پیدا شده را چاپ می‌کند
- xargs ورودی را به آرگومان دستور تبدیل می‌کند

## ترکیب ابزارها

## مثال ۶: ترکیب ابزارها

فایل [examples/06-combined-tools.sh](examples/06-combined-tools.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# ایجاد فایل نمونه
cat > /tmp/users.txt << EOF
علی,25,تهران
رضا,30,شیراز
محمد,22,تهران
حسین,28,اصفهان
مهدی,35,تهران
EOF

echo "محتوای فایل:"
cat /tmp/users.txt

# ترکیب grep و awk
echo -e "\nافراد تهران با grep و awk:"
grep "تهران" /tmp/users.txt | awk -F',' '{print $1, $2}'

# ترکیب find و xargs
echo -e "\nجستجوی فایل‌های tmp:"
find /tmp -name "*.txt" -type f 2>/dev/null | head -3 | xargs ls -lh

# ترکیب sed و awk
echo -e "\nجایگزینی و استخراج:"
sed 's/,/ /g' /tmp/users.txt | awk '{print $1, $3}'

# پاکسازی
rm /tmp/users.txt
```

### توضیح خط به خط:

- `grep | awk` - خروجی grep را به awk می‌دهد
- `find | xargs` - فایل‌های پیدا شده را به ls می‌دهد
- `sed | awk` - ویرایش و سپس استخراج
- `2>/dev/null` - خطاها را مخفی می‌کند

## نکات رایج اشتباهات (Common Pitfalls)

### ۱. فراموش کردن quote در grep

❌ اشتباه:
```bash
grep hello world file.txt  # جستجو در دو فایل
```

✅ درست:
```bash
grep "hello world" file.txt  # جستجوی عبارت
```

### ۲. استفاده از sed بدون -g

❌ اشتباه:
```bash
sed 's/foo/bar/' file  # فقط اولین occurrence
```

✅ درست:
```bash
sed 's/foo/bar/g' file  # تمام occurrenceها
```

### ۳. فراموش کردن delimiter در awk

❌ اشتباه:
```bash
awk '{print $1}' file  # فاصله delimiter پیش‌فرض
```

✅ درست:
```bash
awk -F',' '{print $1}' file  # کاما delimiter
```

### ۴. استفاده از xargs بدون احتیاط

❌ خطرناک:
```bash
find . | xargs rm  # ممکن است فایل‌های مهم حذف شوند
```

✅ امن:
```bash
find . -name "*.tmp" | xargs rm  # فقط فایل‌های tmp
```

## بهترین شیوه‌ها (Best Practices)

1. **همیشه از quote برای الگوها استفاده کنید** - برای جلوگیری از مشکلات
2. **از -g در sed برای جایگزینی کامل استفاده کنید** - مگر اینکه فقط اولین را بخواهید
3. **از -F در awk برای delimiter مشخص استفاده کنید** - برای فایل‌های CSV
4. **قبل از استفاده از xargs با -n تست کنید** - برای اطمینان
5. **از 2>/dev/null برای مخفی کردن خطاها استفاده کنید** - برای خروجی تمیز

## خلاصه جلسه

در این جلسه یاد گرفتیم:
- استفاده از grep برای جستجو در متن
- استفاده از sed برای ویرایش متن
- استفاده از awk برای پردازش متن
- استفاده از find برای جستجوی فایل‌ها
- استفاده از xargs برای اجرای دستورات
- ترکیب این ابزارها با Bash
- نکات مهم و اشتباهات رایج

## تمرین‌ها

### تمرین ۱ (ساده): جستجو و شمارش

یک اسکریپت بنویسید که:
1. یک فایل متنی ایجاد کند
2. با grep کلمه خاصی را جستجو کند
3. تعداد خطوط حاوی آن کلمه را بشمارد

فایل تمرین: [exercises/exercise-01.sh](exercises/exercise-01.sh)

### تمرین ۲ (متوسط): پردازش لاگ

یک اسکریپت بنویسید که:
1. یک فایل لاگ نمونه ایجاد کند
2. با awk خطوط ERROR را استخراج کند
3. با sed زمان‌ها را فرمت کند
4. نتایج را در فایل جداگانه ذخیره کند

فایل تمرین: [exercises/exercise-02.sh](exercises/exercise-02.sh)

## جلسه بعد

در جلسه بعد، پروژه نهایی: ساخت یک اسکریپت واقعی و کاربردی را یاد می‌گیریم.
