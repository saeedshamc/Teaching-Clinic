# جلسه ۱۶: پردازش متن پیشرفته (Advanced Text Processing)

## اهداف یادگیری

در این جلسه یاد می‌گیرید:
- استفاده پیشرفته از awk
- پردازش CSV و JSON
- تبدیل فرمت‌های متن
- پردازش لاگ‌ها
- استخراج داده‌های پیچیده
- ترکیب چند ابزار
- مثال‌های عملی

## awk پیشرفته

awk یک زبان برنامه‌نویسی قدرتمند برای پردازش متن است.

```bash
awk 'BEGIN { ... } { ... } END { ... }'
```

## مثال ۱: awk پیشرفته

فایل [examples/01-advanced-awk.sh](examples/01-advanced-awk.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# ایجاد فایل نمونه
cat > /tmp/data.txt << EOF
علی,25,تهران
رضا,30,شیراز
محمد,22,اصفهان
حسین,28,مشهد
مهدی,35,تهران
EOF

echo "محتوای فایل:"
cat /tmp/data.txt

# محاسبه آمار
echo -e "\nآمار سن‌ها:"
awk -F',' '
BEGIN {
    sum = 0
    count = 0
    min = 999
    max = 0
}
{
    age = $2
    sum += age
    count++
    if (age < min) min = age
    if (age > max) max = age
}
END {
    avg = sum / count
    print "تعداد: " count
    print "مجموع: " sum
    print "میانگین: " avg
    print "کمترین: " min
    print "بیشترین: " max
}
' /tmp/data.txt

# پاکسازی
rm /tmp/data.txt
```

### توضیح خط به خط:

- `BEGIN` - قبل از پردازش فایل
- `-F','` - delimiter کاما
- `sum += age` - جمع سن‌ها
- `min` و `max` - کمترین و بیشترین
- `END` - بعد از پایان فایل
- `avg = sum / count` - میانگین

## پردازش CSV

## مثال ۲: پردازش CSV

فایل [examples/02-csv-processing.sh](examples/02-csv-processing.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# ایجاد فایل CSV
cat > /tmp/users.csv << EOF
نام,سن,شهر,شغل
علی,25,تهران,برنامه‌نویس
رضا,30,شیراز,طراح
محمد,22,اصفهان,مدیر
حسین,28,مشهد,مهندس
مهدی,35,تهران,معلم
EOF

echo "محتوای CSV:"
cat /tmp/users.csv

# فیلتر بر اساس شهر
echo -e "\nافراد تهران:"
awk -F',' 'NR>1 && $3=="تهران" {print $1, $2, $4}' /tmp/users.csv

# شمارش بر اساس شهر
echo -e "\nتعداد افراد در هر شهر:"
awk -F',' 'NR>1 {count[$3]++} END {for (city in count) print city, count[city]}' /tmp/users.csv

# پاکسازی
rm /tmp/users.csv
```

### توضیح خط به خط:

- `NR>1` - نادیده گرفتن header
- `$3=="تهران"` - فیلتر بر اساس شهر
- `count[$3]++` - شمارش هر شهر
- `for (city in count)` - حلقه روی آرایه

## پردازش JSON با jq

jq یک ابزار قدرتمند برای پردازش JSON است.

```bash
jq '.' file.json          # نمایش زیبا
jq '.key' file.json       - استخراج مقدار
jq '.[]' file.json       - آرایه
```

## مثال ۳: JSON با jq

فایل [examples/03-json-processing.sh](examples/03-json-processing.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# ایجاد فایل JSON
cat > /tmp/data.json << EOF
{
    "users": [
        {"name": "علی", "age": 25, "city": "تهران"},
        {"name": "رضا", "age": 30, "city": "شیراز"},
        {"name": "محمد", "age": 22, "city": "اصفهان"}
    ],
    "count": 3
}
EOF

echo "محتوای JSON:"
cat /tmp/data.json

# نمایش زیبا
echo -e "\nنمایش زیبا:"
jq '.' /tmp/data.json

# استخراج آرایه users
echo -e "\nآرایه users:"
jq '.users' /tmp/data.json

# استخراج نام‌ها
echo -e "\nنام‌ها:"
jq '.users[].name' /tmp/data.json

# فیلتر بر اساس سن
echo -e "\nافراد بالای 25 سال:"
jq '.users[] | select(.age > 25)' /tmp/data.json

# پاکسازی
rm /tmp/data.json
```

### توضیح خط به خط:

- `jq '.'` - نمایش کامل
- `.users` - استخراج فیلد users
- `.users[].name` - تمام نام‌ها
- `select(.age > 25)` - فیلتر

## پردازش لاگ

## مثال ۴: پردازش لاگ

فایل [examples/04-log-processing.sh](examples/04-log-processing.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# ایجاد فایل لاگ نمونه
cat > /tmp/app.log << EOF
2024-01-15 10:30:00 INFO Application started
2024-01-15 10:30:05 ERROR Database connection failed
2024-01-15 10:30:10 INFO Retrying connection
2024-01-15 10:30:15 ERROR Connection timeout
2024-01-15 10:30:20 INFO Connection established
2024-01-15 10:30:25 ERROR Query failed
2024-01-15 10:30:30 INFO Query retried successfully
EOF

echo "محتوای لاگ:"
cat /tmp/app.log

# شمارش خطوط ERROR
echo -e "\nتعداد خطوط ERROR:"
grep -c "ERROR" /tmp/app.log

# استخراج خطوط ERROR با timestamp
echo -e "\nخطوط ERROR:"
grep "ERROR" /tmp/app.log

# شمارش بر اساس سطح
echo -e "\nشمارش بر اساس سطح:"
awk '{count[$3]++} END {for (level in count) print level, count[level]}' /tmp/app.log

# پاکسازی
rm /tmp/app.log
```

### توضیح خط به خط:

- `grep -c` - شمارش تطابق‌ها
- `grep "ERROR"` - خطوط حاوی ERROR
- `count[$3]++` - شمارش بر اساس سطح (INFO/ERROR)
- `for (level in count)` - نمایش نتایج

## تبدیل فرمت

## مثال ۵: تبدیل فرمت

فایل [examples/05-format-conversion.sh](examples/05-format-conversion.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# ایجاد فایل CSV
cat > /tmp/input.csv << EOF
نام,سن,شهر
علی,25,تهران
رضا,30,شیراز
محمد,22,اصفهان
EOF

echo "فایل CSV:"
cat /tmp/input.csv

# تبدیل به JSON
echo -e "\nتبدیل به JSON:"
echo "[" > /tmp/output.json
awk -F',' 'NR>1 {
    printf "  {\"name\": \"%s\", \"age\": %s, \"city\": \"%s\"}%s\n", $1, $2, $3, (NR==2?"":",")
}' /tmp/input.csv >> /tmp/output.json
echo "]" >> /tmp/output.json

cat /tmp/output.json

# پاکسازی
rm /tmp/input.csv /tmp/output.json
```

### توضیح خط به خط:

- تبدیل CSV به JSON
- `NR>1` - نادیده گرفتن header
- `printf` - فرمت خروجی
- `(NR==2?"":",")` - کاما بین آیتم‌ها

## استخراج داده‌های پیچیده

## مثال ۶: استخراج پیچیده

فایل [examples/06-complex-extraction.sh](examples/06-complex-extraction.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# ایجاد فایل پیچیده
cat > /tmp/complex.txt << EOF
[2024-01-15] User:ali Action:login IP:192.168.1.1 Status:success
[2024-01-15] User:reza Action:logout IP:192.168.1.2 Status:success
[2024-01-15] User:mohammad Action:login IP:192.168.1.3 Status:failed
[2024-01-15] User:hosein Action:login IP:192.168.1.4 Status:success
EOF

echo "محتوای فایل:"
cat /tmp/complex.txt

# استخراج نام کاربران
echo -e "\nنام کاربران:"
grep -oP 'User:\K\w+' /tmp/complex.txt

# استخراج IPها
echo -e "\nIPها:"
grep -oP 'IP:\K[\d.]+' /tmp/complex.txt

# شمارش بر اساس Action
echo -e "\nشمارش بر اساس Action:"
grep -oP 'Action:\K\w+' /tmp/complex.txt | sort | uniq -c

# پاکسازی
rm /tmp/complex.txt
```

### توضیح خط به خط:

- `grep -oP` - فقط تطابق با Perl regex
- `User:\K\w+` - بعد از User، کلمات
- `IP:\K[\d.]+` - بعد از IP، اعداد و نقطه
- `sort | uniq -c` - شمارش منحصر به فرد

## ترکیب ابزارها

## مثال ۷: ترکیب ابزارها

فایل [examples/07-combined-tools.sh](examples/07-combined-tools.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# ایجاد فایل نمونه
cat > /tmp/mixed.txt << EOF
علی 25 تهران برنامه‌نویس
رضا 30 شیراز طراح
محمد 22 اصفهان مدیر
حسین 28 مشهد مهندس
مهدی 35 تهران معلم
EOF

echo "محتوای فایل:"
cat /tmp/mixed.txt

# ترکیب awk و sort
echo -e "\nمرتب شده بر اساس سن:"
awk '{print $2, $0}' /tmp/mixed.txt | sort -n | cut -d' ' -f2-

# ترکیب grep و awk
echo -e "\nافراد تهران:"
grep "تهران" /tmp/mixed.txt | awk '{print $1, $2}'

# ترکیب sed و awk
echo -e "\nجایگزینی و استخراج:"
sed 's/تهران/TEHRAN/g' /tmp/mixed.txt | awk '{print $1, $3}'

# پاکسازی
rm /tmp/mixed.txt
```

### توضیح خط به خط:

- `awk | sort | cut` - pipeline چند ابزار
- `sort -n` - مرتب عددی
- `cut -d' ' -f2-` - از ستون 2 تا آخر
- `sed | awk` - ویرایش و استخراج

## نکات رایج اشتباهات (Common Pitfalls)

### ۱. فراموش کردن delimiter در awk

❌ اشتباه:
```bash
awk '{print $2}' file  - فاصله delimiter پیش‌فرض
```

✅ درست:
```bash
awk -F',' '{print $2}' file  - کاما delimiter
```

### ۲. نادیده گرفتن header در CSV

❌ اشتباه:
```bash
awk '{sum += $2}' file  - header هم جمع می‌شود
```

✅ درست:
```bash
awk 'NR>1 {sum += $2}' file  - header نادیده گرفته می‌شود
```

### ۳. استفاده از grep به جای jq برای JSON

❌ اشتباه:
```bash
grep "key" file.json  - نادرست
```

✅ درست:
```bash
jq '.key' file.json  - صحیح
```

## بهترین شیوه‌ها (Best Practices)

1. **از jq برای JSON استفاده کنید** - ابزار مناسب برای کار مناسب
2. **همیشه delimiter را مشخص کنید** - برای فایل‌های CSV
3. **از BEGIN و END در awk استفاده کنید** - برای آمار کلی
4. **pipeline را ساده نگه دارید** - برای خوانایی
5. **لاگ بنویسید** - برای ردیابی مشکلات

## خلاصه جلسه

در این جلسه یاد گرفتیم:
- awk پیشرفته
- پردازش CSV
- پردازش JSON با jq
- پردازش لاگ
- تبدیل فرمت
- استخراج داده‌های پیچیده
- ترکیب ابزارها
- نکات مهم و اشتباهات رایج

## تمرین‌ها

### تمرین ۱ (ساده): پردازش CSV

یک اسکریپت بنویسید که:
1. یک فایل CSV بخواند
2. میانگین یک ستون عددی را محاسبه کند
3. نتایج را چاپ کند

فایل تمرین: [exercises/exercise-01.sh](exercises/exercise-01.sh)

### تمرین ۲ (متوسط): تحلیل لاگ

یک اسکریپت بنویسید که:
1. یک فایل لاگ بخواند
2. خطوط ERROR را استخراج کند
3. تعداد ERROR در هر ساعت را بشمارد
4. گزارش تولید کند

فایل تمرین: [exercises/exercise-02.sh](exercises/exercise-02.sh)

## جلسه بعد

در جلسه بعد، مدیریت سیستم (System Administration) را یاد می‌گیریم.
