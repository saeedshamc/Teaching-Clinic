# جلسه ۱۳: عبارات منظم (Regular Expressions)

## اهداف یادگیری

در این جلسه یاد می‌گیرید:
- مفهوم عبارات منظم (regex)
- کاراکترهای خاص در regex
- کلاس‌های کاراکتری
- کوانتیفایرها (quantifiers)
- گروه‌بندی و capture
- استفاده از regex در grep, sed, awk
- مثال‌های عملی

## مقدمه

عبارات منظم (Regular Expressions یا regex) الگوهایی برای جستجو و تطبیق متن هستند. در Bash از regex برای جستجوی پیشرفته، ویرایش متن، و اعتبارسنجی استفاده می‌شود.

## کاراکترهای خاص

```bash
.       # هر کاراکتری (به جز خط جدید)
^       # شروع خط
$       # پایان خط
*       # صفر یا بیشتر تکرار
+       # یک یا بیشتر تکرار
?       # صفر یا یک تکرار
[]      # کلاس کاراکتری
\       # escape character
```

## مثال ۱: کاراکترهای خاص

فایل [examples/01-special-chars.sh](examples/01-special-chars.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# ایجاد فایل نمونه
cat > /tmp/test.txt << EOF
Hello World
Hello Bash
Hello Linux
World is great
HELLO
hello
EOF

echo "محتوای فایل:"
cat /tmp/test.txt

# نقطه (.) - هر کاراکتری
echo -e "\nجستجوی 'H.llo':"
grep "H.llo" /tmp/test.txt

# شروع خط (^)
echo -e "\nخطوط شروع شده با 'Hello':"
grep "^Hello" /tmp/test.txt

# پایان خط ($)
echo -e "\nخطوط تمام شده با 'World':"
grep "World$" /tmp/test.txt

# ستاره (*) - صفر یا بیشتر
echo -e "\nجستجوی 'He*lo':"
grep "He*lo" /tmp/test.txt

# پاکسازی
rm /tmp/test.txt
```

### توضیح خط به خط:

- `.` - هر کاراکتری را تطبیق می‌دهد
- `^Hello` - خطوطی که با Hello شروع می‌شوند
- `World$` - خطوطی که با World تمام می‌شوند
- `He*lo` - H، سپس صفر یا بیشتر e، سپس lo

## کلاس‌های کاراکتری

```bash
[abc]       # a یا b یا c
[^abc]      # هر کاراکتری به جز a, b, c
[a-z]       # حروف کوچک
[A-Z]       # حروف بزرگ
[0-9]       # اعداد
[a-zA-Z]    # تمام حروف
```

## مثال ۲: کلاس‌های کاراکتری

فایل [examples/02-character-classes.sh](examples/02-character-classes.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# ایجاد فایل نمونه
cat > /tmp/test.txt << EOF
apple
banana
Apple
123
abc
XYZ
EOF

echo "محتوای فایل:"
cat /tmp/test.txt

# کلاس ساده
echo -e "\nکلمات حاوی 'a' یا 'e':"
grep "[ae]" /tmp/test.txt

# محدوده حروف
echo -e "\nکلمات با حروف کوچک:"
grep "[a-z]" /tmp/test.txt

# اعداد
echo -e "\nکلمات حاوی اعداد:"
grep "[0-9]" /tmp/test.txt

# نقیض
echo -e "\nکلمات بدون حروف بزرگ:"
grep "[^A-Z]" /tmp/test.txt

# پاکسازی
rm /tmp/test.txt
```

### توضیح خط به خط:

- `[ae]` - حاوی a یا e
- `[a-z]` - حروف کوچک انگلیسی
- `[0-9]` - اعداد 0-9
- `[^A-Z]` - بدون حروف بزرگ

## کوانتیفایرها

```bash
*       # صفر یا بیشتر
+       # یک یا بیشتر
?       # صفر یا یک
{n}     # دقیقا n بار
{n,}    # n بار یا بیشتر
{n,m}   # بین n و m بار
```

## مثال ۳: کوانتیفایرها

فایل [examples/03-quantifiers.sh](examples/03-quantifiers.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# ایجاد فایل نمونه
cat > /tmp/test.txt << EOF
a
aa
aaa
aaaa
ab
aab
aaab
EOF

echo "محتوای فایل:"
cat /tmp/test.txt

# ستاره (*) - صفر یا بیشتر
echo -e "\nجستجوی 'a*':"
grep "a*" /tmp/test.txt

# پلاس (+) - یک یا بیشتر
echo -e "\nجستجوی 'a+':"
grep -E "a+" /tmp/test.txt

# علامت سوال (?) - صفر یا یک
echo -e "\nجستجوی 'a?b':"
grep -E "a?b" /tmp/test.txt

# تعداد دقیق {n}
echo -e "\nجستجوی 'a{3}':"
grep -E "a{3}" /tmp/test.txt

# محدوده {n,m}
echo -e "\nجستجوی 'a{2,3}':"
grep -E "a{2,3}" /tmp/test.txt

# پاکسازی
rm /tmp/test.txt
```

### توضیح خط به خط:

- `grep -E` - استفاده از extended regex
- `a+` - یک یا بیشتر a
- `a?b` - صفر یا یک a، سپس b
- `a{3}` - دقیقا 3 a
- `a{2,3}` - بین 2 تا 3 a

## گروه‌بندی و Capture

```bash
()      # گروه‌بندی
|       # OR
\1, \2  # reference به گروه‌ها
```

## مثال ۴: گروه‌بندی

فایل [examples/04-grouping.sh](examples/04-grouping.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# ایجاد فایل نمونه
cat > /tmp/test.txt << EOF
Hello World
Hello Bash
Good Morning
Good Evening
EOF

echo "محتوای فایل:"
cat /tmp/test.txt

# OR (|)
echo -e "\nHello یا Good:"
grep -E "Hello|Good" /tmp/test.txt

# گروه‌بندی
echo -e "\nHello World یا Good Morning:"
grep -E "(Hello World|Good Morning)" /tmp/test.txt

# پاکسازی
rm /tmp/test.txt
```

### توضیح خط به خط:

- `Hello|Good` - Hello یا Good
- `(Hello World|Good Morning)` - عبارت کامل OR

## استفاده در grep

```bash
grep -E "pattern" file    # extended regex
grep -o "pattern" file    # فقط تطابق را چاپ کند
grep -c "pattern" file    # شمارش تطابق‌ها
```

## مثال ۵: regex در grep

فایل [examples/05-regex-grep.sh](examples/05-regex-grep.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# ایجاد فایل نمونه
cat > /tmp/test.txt << EOF
user1@example.com
user2@test.com
invalid-email
admin@company.org
no-email-here
EOF

echo "محتوای فایل:"
cat /tmp/test.txt

# جستجوی ایمیل
echo -e "\nآدرس‌های ایمیل:"
grep -E "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}" /tmp/test.txt

# فقط تطابق
echo -e "\nفقط تطابق:"
grep -o -E "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}" /tmp/test.txt

# شمارش
echo -e "\nتعداد ایمیل‌ها:"
grep -c -E "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}" /tmp/test.txt

# پاکسازی
rm /tmp/test.txt
```

### توضیح خط به خط:

- `[a-zA-Z0-9._%+-]+` - کاراکترهای مجاز قبل از @
- `@[a-zA-Z0-9.-]+` - @ و دامنه
- `\.[a-zA-Z]{2,}` - نقطه و پسوند حداقل 2 کاراکتر
- `grep -o` - فقط تطابق را چاپ می‌کند
- `grep -c` - تعداد تطابق‌ها را می‌شمارد

## استفاده در sed

```bash
sed -E 's/pattern/replacement/' file
```

## مثال ۶: regex در sed

فایل [examples/06-regex-sed.sh](examples/06-regex-sed.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# ایجاد فایل نمونه
cat > /tmp/test.txt << EOF
Hello World 123
Test 456
Example 789
EOF

echo "محتوای اصلی:"
cat /tmp/test.txt

# جایگزینی اعداد
echo -e "\nحذف اعداد:"
sed -E 's/[0-9]+//' /tmp/test.txt

# جایگزینی کلمات
echo -e "\nجایگزینی Hello با Hi:"
sed -E 's/Hello/Hi/g' /tmp/test.txt

# پاکسازی
rm /tmp/test.txt
```

### توضیح خط به خط:

- `sed -E` - استفاده از extended regex
- `s/[0-9]+//` - حذف اعداد
- `s/Hello/Hi/g` - جایگزینی سراسری

## مثال‌های عملی

### اعتبارسنجی ایمیل

```bash
email_regex="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$"
```

### اعتبارسنجی شماره تلفن

```bash
phone_regex="^[0-9]{10}$"
```

### اعتبارسنجی IP

```bash
ip_regex="^[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}$"
```

## مثال ۷: اعتبارسنجی

فایل [examples/07-validation.sh](examples/07-validation.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# تابع اعتبارسنجی ایمیل
validate_email() {
    local email=$1
    local email_regex="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$"
    
    if [[ $email =~ $email_regex ]]; then
        echo "ایمیل معتبر است: $email"
        return 0
    else
        echo "ایمیل نامعتبر است: $email"
        return 1
    fi
}

# تابع اعتبارسنجی شماره تلفن
validate_phone() {
    local phone=$1
    local phone_regex="^[0-9]{10}$"
    
    if [[ $phone =~ $phone_regex ]]; then
        echo "شماره تلفن معتبر است: $phone"
        return 0
    else
        echo "شماره تلفن نامعتبر است: $phone"
        return 1
    fi
}

# تست
validate_email "user@example.com"
validate_email "invalid-email"
validate_phone "0912345678"
validate_phone "123"
```

### توضیح خط به خط:

- `=~ $regex` - تطبیق regex در Bash
- `^...$` - شروع و پایان خط
- `[a-zA-Z0-9._%+-]+` - کاراکترهای مجاز
- `[0-9]{10}` - دقیقا 10 رقم

## نکات رایج اشتباهات (Common Pitfalls)

### ۱. فراموش کردن escape

❌ اشتباه:
```bash
grep ".txt" file  # نقطه هر کاراکتری است
```

✅ درست:
```bash
grep "\.txt" file  # نقطه literal
```

### ۲. استفاده از * به جای +

❌ اشتباه:
```bash
grep "a*" file  # صفر یا بیشتر a
```

✅ درست:
```bash
grep -E "a+" file  # یک یا بیشتر a
```

### ۳. فراموش کردن quote

❌ اشتباه:
```bash
grep $pattern file  # مشکلات با فاصله
```

✅ درست:
```bash
grep "$pattern" file  # quote برای حفظ فاصله
```

## بهترین شیوه‌ها (Best Practices)

1. **همیشه از quote برای regex استفاده کنید** - برای جلوگیری از مشکلات
2. **از -E برای extended regex استفاده کنید** - قدرتمندتر است
3. **از ابزارهای آنلاین regex برای تست استفاده کنید** - regex101.com
4. **regex را ساده نگه دارید** - پیچیدگی را کم کنید
5. **کامنت بنویسید** - برای توضیح الگوهای پیچیده

## خلاصه جلسه

در این جلسه یاد گرفتیم:
- کاراکترهای خاص در regex
- کلاس‌های کاراکتری
- کوانتیفایرها
- گروه‌بندی و OR
- استفاده از regex در grep, sed
- اعتبارسنجی با regex
- نکات مهم و اشتباهات رایج

## تمرین‌ها

### تمرین ۱ (ساده): استخراج اعداد

یک اسکریپت بنویسید که:
1. یک متن از کاربر بگیرد
2. تمام اعداد را با regex استخراج کند
3. مجموع اعداد را محاسبه کند

فایل تمرین: [exercises/exercise-01.sh](exercises/exercise-01.sh)

### تمرین ۲ (متوسط): اعتبارسنجی پسورد

یک اسکریپت بنویسید که:
1. یک پسورد از کاربر بگیرد
2. با regex بررسی کند:
   - حداقل 8 کاراکتر
   - حداقل یک حرف بزرگ
   - حداقل یک حرف کوچک
   - حداقل یک عدد
3. نتیجه اعتبارسنجی را چاپ کند

فایل تمرین: [exercises/exercise-02.sh](exercises/exercise-02.sh)

## جلسه بعد

در جلسه بعد، مدیریت پروسه‌ها (Process Management) را یاد می‌گیریم.
