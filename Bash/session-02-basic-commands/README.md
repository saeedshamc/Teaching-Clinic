# جلسه ۲: دستورات پایه ترمینال و ناوبری فایل‌سیستم

## اهداف یادگیری

در این جلسه یاد می‌گیرید:
- دستورات پایه ترمینال را بشناسید و استفاده کنید
- چگونه در فایل‌سیستم ناوبری کنید
- چگونه فایل‌ها و پوشه‌ها را مدیریت کنید
- چگونه اطلاعات فایل‌ها را مشاهده کنید
- دستورات مفید برای کار روزمره با فایل‌ها

## دستورات پایه ناوبری

### pwd - نمایش مسیر جاری

دستور `pwd` (Print Working Directory) مسیر کامل پوشه‌ای که در آن هستید را نمایش می‌دهد.

```bash
pwd
# خروجی: /home/user
```

### ls - لیست کردن فایل‌ها

دستور `ls` فایل‌ها و پوشه‌های موجود در مسیر جاری را نمایش می‌دهد.

```bash
ls              # لیست ساده
ls -l           # لیست با جزئیات
ls -la          # لیست با جزئیات + فایل‌های مخفی
ls -lh          # لیست با اندازه‌های قابل خواندن برای انسان
```

### cd - تغییر مسیر

دستور `cd` (Change Directory) برای رفتن به پوشه‌های مختلف استفاده می‌شود.

```bash
cd /home/user       # رفتن به مسیر مشخص
cd ..               # رفتن به پوشه والد
cd ~                # رفتن به پوشه خانگی
cd -                # رفتن به مسیر قبلی
cd                  # رفتن به پوشه خانگی
```

## مثال ۱: ناوبری پایه

فایل [examples/01-navigation.sh](examples/01-navigation.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# نمایش مسیر جاری
echo "مسیر جاری:"
pwd

# لیست فایل‌های مسیر جاری
echo -e "\nفایل‌های مسیر جاری:"
ls

# رفتن به پوشه والد
echo -e "\nرفتن به پوشه والد:"
cd ..
pwd

# بازگشت به پوشه قبلی
echo -e "\nبازگشت به پوشه قبلی:"
cd -
pwd
```

### توضیح خط به خط:

- `pwd` - مسیر جاری را چاپ می‌کند
- `ls` - فایل‌های مسیر جاری را لیست می‌کند
- `cd ..` - به پوشه والد می‌رود
- `cd -` - به پوشه قبلی برمی‌گردد
- `echo -e "\n"` - یک خط خالی چاپ می‌کند (\n = newline)

## مدیریت فایل‌ها و پوشه‌ها

### mkdir - ایجاد پوشه

```bash
mkdir folder_name           # ایجاد یک پوشه
mkdir -p path/to/folder     # ایجاد پوشه‌های تو در تو
```

### touch - ایجاد فایل خالی

```bash
touch file.txt              # ایجاد یک فایل خالی
touch file1.txt file2.txt   # ایجاد چند فایل
```

### cp - کپی فایل

```bash
cp source.txt destination.txt    # کپی فایل
cp -r source_dir dest_dir        # کپی پوشه به صورت بازگشتی
```

### mv - جابجایی/تغییر نام

```bash
mv old_name.txt new_name.txt     # تغییر نام فایل
mv file.txt /path/to/dest/      # جابجایی فایل
```

### rm - حذف فایل

```bash
rm file.txt              # حذف فایل
rm -r folder_name        # حذف پوشه به صورت بازگشتی
rm -rf folder_name       # حذف اجباری بدون سوال
```

⚠️ **هشدار**: دستور `rm -rf` بسیار خطرناک است و حذف شده‌ها قابل بازیابی نیستند!

## مثال ۲: مدیریت فایل‌ها

فایل [examples/02-file-management.sh](examples/02-file-management.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# ایجاد یک پوشه موقت
echo "ایجاد پوشه موقت..."
mkdir -p /tmp/bash_course_demo

# رفتن به پوشه موقت
cd /tmp/bash_course_demo

# ایجاد چند فایل
echo "ایجاد فایل‌ها..."
touch file1.txt file2.txt file3.txt

# لیست فایل‌ها
echo -e "\nفایل‌های ایجاد شده:"
ls -l

# کپی یک فایل
echo -e "\nکپی فایل..."
cp file1.txt file1_copy.txt

# تغییر نام فایل
echo -e "\nتغییر نام فایل..."
mv file2.txt file2_renamed.txt

# لیست مجدد فایل‌ها
echo -e "\nفایل‌ها بعد از تغییرات:"
ls -l

# حذف فایل‌ها
echo -e "\nحذف فایل‌ها..."
rm file1.txt file1_copy.txt file2_renamed.txt file3.txt

# بازگشت به پوشه اصلی
cd -

# حذف پوشه موقت
echo -e "\nحذف پوشه موقت..."
rm -rf /tmp/bash_course_demo

echo "تمام شد!"
```

### توضیح خط به خط:

- `mkdir -p /tmp/bash_course_demo` - پوشه موقت ایجاد می‌کند (-p برای ایجاد پوشه‌های والد)
- `cd /tmp/bash_course_demo` - به پوشه موقت می‌رود
- `touch file1.txt file2.txt file3.txt` - سه فایل خالی ایجاد می‌کند
- `ls -l` - فایل‌ها را با جزئیات لیست می‌کند
- `cp file1.txt file1_copy.txt` - فایل را کپی می‌کند
- `mv file2.txt file2_renamed.txt` - نام فایل را تغییر می‌دهد
- `rm file1.txt ...` - فایل‌ها را حذف می‌کند
- `cd -` - به پوشه قبلی برمی‌گردد
- `rm -rf /tmp/bash_course_demo` - پوشه موقت را با تمام محتویاتش حذف می‌کند

## مشاهده اطلاعات فایل‌ها

### cat - نمایش محتوای فایل

```bash
cat file.txt              # نمایش کل محتوا
cat -n file.txt           # نمایش با شماره خط
cat file1.txt file2.txt   # نمایش چند فایل پشت سر هم
```

### less - مشاهده فایل‌های بزرگ

```bash
less file.txt             # مشاهده فایل با قابلیت اسکرول
# کلیدهای مفید: q برای خروج، / برای جستجو
```

### head و tail - نمایش ابتدا و انتهای فایل

```bash
head file.txt             # نمایش ۱۰ خط اول
head -n 20 file.txt       # نمایش ۲۰ خط اول
tail file.txt             # نمایش ۱۰ خط آخر
tail -n 15 file.txt       # نمایش ۱۵ خط آخر
tail -f file.txt          # نمایش زنده فایل (برای log files)
```

### file - تشخیص نوع فایل

```bash
file document.pdf         # تشخیص نوع فایل
```

### stat - اطلاعات دقیق فایل

```bash
stat file.txt             # اطلاعات کامل فایل
```

## مثال ۳: مشاهده محتوای فایل

فایل [examples/03-view-files.sh](examples/03-view-files.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# ایجاد یک فایل نمونه
echo "ایجاد فایل نمونه..."
cat > /tmp/demo_file.txt << EOF
این خط اول است
این خط دوم است
این خط سوم است
این خط چهارم است
این خط پنجم است
این خط ششم است
این خط هفتم است
این خط هشتم است
این خط نهم است
این خط دهم است
EOF

# نمایش کل فایل
echo "محتوای کامل فایل:"
cat /tmp/demo_file.txt

# نمایش ۳ خط اول
echo -e "\n۳ خط اول:"
head -n 3 /tmp/demo_file.txt

# نمایش ۳ خط آخر
echo -e "\n۳ خط آخر:"
tail -n 3 /tmp/demo_file.txt

# نمایش با شماره خط
echo -e "\nبا شماره خط:"
cat -n /tmp/demo_file.txt

# اطلاعات فایل
echo -e "\nاطلاعات فایل:"
file /tmp/demo_file.txt

# حذف فایل نمونه
rm /tmp/demo_file.txt
```

### توضیح خط به خط:

- `cat > /tmp/demo_file.txt << EOF` - ایجاد فایل با محتوای چند خطی (heredoc)
- `EOF` - نشان‌دهنده پایان محتوای چند خطی
- `cat /tmp/demo_file.txt` - کل محتوای فایل را نمایش می‌دهد
- `head -n 3` - ۳ خط اول را نمایش می‌دهد
- `tail -n 3` - ۳ خط آخر را نمایش می‌دهد
- `cat -n` - با شماره خط نمایش می‌دهد
- `file` - نوع فایل را تشخیص می‌دهد

## جستجو در فایل‌سیستم

### find - جستجوی فایل‌ها

```bash
find . -name "*.txt"           # جستجوی فایل‌های txt در مسیر جاری
find /home -name "config"      # جستجوی فایل/پوشه با نام مشخص
find . -type f -name "*.sh"    # جستجوی فایل‌های sh
find . -type d -name "test"    # جستجوی پوشه‌ها
```

### locate - جستجوی سریع (نیاز به updatedb)

```bash
locate file.txt                # جستجوی سریع فایل
```

## مثال ۴: جستجوی فایل‌ها

فایل [examples/04-search-files.sh](examples/04-search-files.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# ایجاد ساختار نمونه برای تست
echo "ایجاد ساختار نمونه..."
mkdir -p /tmp/search_test/{dir1,dir2,dir3}
touch /tmp/search_test/file1.txt
touch /tmp/search_test/file2.txt
touch /tmp/search_test/dir1/config.sh
touch /tmp/search_test/dir2/data.txt
touch /tmp/search_test/dir3/readme.md

# جستجوی تمام فایل‌های txt
echo "جستجوی فایل‌های txt:"
find /tmp/search_test -name "*.txt"

# جستجوی تمام فایل‌های sh
echo -e "\nجستجوی فایل‌های sh:"
find /tmp/search_test -name "*.sh"

# جستجوی پوشه‌ها
echo -e "\nجستجوی پوشه‌ها:"
find /tmp/search_test -type d

# جستجوی فایل‌ها با نام مشخص
echo -e "\nجستجوی فایل با نام file1:"
find /tmp/search_test -name "file1*"

# پاکسازی
rm -rf /tmp/search_test
echo -e "\nپاکسازی انجام شد."
```

## نکات رایج اشتباهات (Common Pitfalls)

### ۱. فراموش کردن -r در حذف پوشه

❌ اشتباه:
```bash
rm folder_name  # خطا: پوشه را نمی‌توان بدون -r حذف کرد
```

✅ درست:
```bash
rm -r folder_name
```

### ۲. استفاده از rm -rf بدون احتیاط

❌ خطرناک:
```bash
rm -rf /home/user/important/*  # ممکن است فایل‌های مهم حذف شوند
```

✅ امن:
```bash
rm -ri /home/user/important/*  # -i برای تایید قبل از هر حذف
```

### ۳. فراموش کردن مسیر مطلق در اسکریپت‌ها

❌ مشکل‌دار:
```bash
cd my_folder  # اگر اسکریپت از مسیر دیگری اجرا شود، کار نمی‌کند
```

✅ بهتر:
```bash
cd "$(dirname "$0")/my_folder"  # مسیر نسبی به محل اسکریپت
```

### ۴. استفاده از فاصله در نام فایل‌ها

❌ مشکل‌دار:
```bash
touch my file.txt  # ایجاد دو فایل جداگانه
```

✅ درست:
```bash
touch "my file.txt"  # یا استفاده از زیرخط: my_file.txt
```

## بهترین شیوه‌ها (Best Practices)

1. **قبل از حذف، لیست کنید** - با `ls` مطمئن شوید فایل‌های درست را حذف می‌کنید
2. **از مسیرهای مطلق استفاده کنید** - در اسکریپت‌ها از مسیر کامل استفاده کنید
3. **نام فایل‌ها را ساده انتخاب کنید** - از فاصله و کاراکترهای خاص پرهیز کنید
4. **از -i استفاده کنید** - برای حذف با تایید
5. **قبل از اجرای دستورات خطرناک، echo کنید** - برای تست

## خلاصه جلسه

در این جلسه یاد گرفتیم:
- دستورات پایه ناوبری: pwd, ls, cd
- مدیریت فایل‌ها و پوشه‌ها: mkdir, touch, cp, mv, rm
- مشاهده محتوای فایل‌ها: cat, less, head, tail
- جستجو در فایل‌سیستم: find
- نکات مهم و اشتباهات رایج

## تمرین‌ها

### تمرین ۱ (ساده): مدیریت فایل‌ها

یک اسکریپت بنویسید که:
1. یک پوشه به نام "my_project" ایجاد کند
2. در آن سه فایل ایجاد کند: main.txt, data.txt, config.txt
3. فایل main.txt را به backup.txt کپی کند
4. فایل data.txt را به info.txt تغییر نام دهد
5. لیست تمام فایل‌ها را چاپ کند
6. در نهایت پوشه را حذف کند

فایل تمرین: [exercises/exercise-01.sh](exercises/exercise-01.sh)

### تمرین ۲ (متوسط): جستجوی فایل‌ها

یک اسکریپت بنویسید که:
1. یک ساختار پوشه‌ای پیچیده ایجاد کند
2. فایل‌های مختلف با پسوندهای مختلف در آن ایجاد کند
3. با استفاده از find، تمام فایل‌های با پسوند مشخص را پیدا کند
4. تعداد فایل‌های پیدا شده را چاپ کند
5. در نهایت همه را پاک کند

فایل تمرین: [exercises/exercise-02.sh](exercises/exercise-02.sh)

## جلسه بعد

در جلسه بعد، متغیرها، ورودی/خروجی و quoting را یاد می‌گیریم.
