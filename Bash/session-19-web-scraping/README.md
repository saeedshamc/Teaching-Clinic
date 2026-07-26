# جلسه ۱۹: Web Scraping با Bash

## اهداف یادگیری

در این جلسه یاد می‌گیرید:
- دانلود صفحات وب با curl
- استخراج داده با grep و sed
- استخراج داده با awk
- استفاده از xpath/html-xml-utils
- مثال‌های عملی
- نکات اخلاقی و قانونی

## دانلود صفحات وب

```bash
curl URL              - دانلود صفحه
curl -s URL           - silent
curl -o file URL      - ذخیره در فایل
```

## مثال ۱: دانلود صفحه

فایل [examples/01-download-page.sh](examples/01-download-page.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# دانلود صفحه example.com
echo "دانلود صفحه example.com..."
curl -s -o /tmp/page.html http://example.com

# نمایش بخشی از صفحه
echo -e "\n50 خط اول صفحه:"
head -20 /tmp/page.html

# پاکسازی
rm /tmp/page.html
```

### توضیح خط به خط:

- `curl -s` - silent (بدون progress)
- `-o file` - ذخیره در فایل
- `head -20` - 20 خط اول

## استخراج لینک‌ها

## مثال ۲: استخراج لینک‌ها

فایل [examples/02-extract-links.sh](examples/02-extract-links.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# دانلود صفحه
curl -s -o /tmp/page.html http://example.com

# استخراج لینک‌ها با grep
echo "لینک‌های صفحه:"
grep -o 'href="[^"]*"' /tmp/page.html | sed 's/href="//' | sed 's/"$//' | head -10

# پاکسازی
rm /tmp/page.html
```

### توضیح خط به خط:

- `grep -o` - فقط تطابق
- `href="[^"]*"` - الگوی لینک
- `sed 's/href="//'` - حذف href="
- `sed 's/"$//'` - حذف " آخر

## استخراج متن

## مثال ۳: استخراج متن

فایل [examples/03-extract-text.sh](examples/03-extract-text.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# ایجاد فایل HTML نمونه
cat > /tmp/test.html << EOF
<html>
<head><title>صفحه تست</title></head>
<body>
    <h1>عنوان اصلی</h1>
    <p>پاراگراف اول</p>
    <p>پاراگراف دوم</p>
    <div class="content">محتوا</div>
</body>
</html>
EOF

echo "فایل HTML:"
cat /tmp/test.html

# استخراج عنوان
echo -e "\nعنوان صفحه:"
grep -o '<title>.*</title>' /tmp/test.html | sed 's/<title>//;s/<\/title>//'

# استخراج پاراگراف‌ها
echo -e "\nپاراگراف‌ها:"
grep -o '<p>.*</p>' /tmp/test.html | sed 's/<p>//;s/<\/p>//'

# پاکسازی
rm /tmp/test.html
```

### توضیح خط به خط:

- `grep -o '<title>.*</title>'` - استخراج title
- `sed 's/<title>//;s/<\/title>//'` - حذف تگ‌ها
- `;` - اجرای چند دستور sed

## استخراج با awk

## مثال ۴: استخراج با awk

فایل [examples/04-extract-awk.sh](examples/04-extract-awk.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# ایجاد فایل HTML نمونه
cat > /tmp/test.html << EOF
<div class="product">
    <span class="name">لپتاپ</span>
    <span class="price">15000</span>
</div>
<div class="product">
    <span class="name">موبایل</span>
    <span class="price">8000</span>
</div>
EOF

echo "فایل HTML:"
cat /tmp/test.html

# استخراج نام‌ها
echo -e "\nنام محصولات:"
grep -o '<span class="name">.*</span>' /tmp/test.html | sed 's/<[^>]*>//g'

# استخراج قیمت‌ها
echo -e "\nقیمت‌ها:"
grep -o '<span class="price">.*</span>' /tmp/test.html | sed 's/<[^>]*>//g'

# پاکسازی
rm /tmp/test.html
```

### توضیح خط به خط:

- `grep -o` - استخراج الگو
- `sed 's/<[^>]*>//g'` - حذف تمام تگ‌های HTML
- `<[^>]*>` - هر تگ HTML

## استفاده از html-xml-utils

```bash
hxselect selector file    - انتخاب با CSS selector
hxextract selector file  - استخراج با CSS selector
```

## مثال ۵: html-xml-utils

فایل [examples/05-html-xml-utils.sh](examples/05-html-xml-utils.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# ایجاد فایل HTML نمونه
cat > /tmp/test.html << EOF
<html>
<body>
    <div class="container">
        <p id="p1">پاراگراف ۱</p>
        <p id="p2">پاراگراف ۲</p>
    </div>
</body>
</html>
EOF

echo "فایل HTML:"
cat /tmp/test.html

# استخراج با CSS selector (اگر html-xml-utils نصب باشد)
if command -v hxselect &>/dev/null; then
    echo -e "\nاستخراج با hxselect:"
    hxselect 'p' /tmp/test.html
else
    echo -e "\nhxselect نصب نیست"
    echo "نصب با: sudo apt install html-xml-utils"
fi

# پاکسازی
rm /tmp/test.html
```

### توضیح خط به خط:

- `hxselect 'p'` - انتخاب تمام تگ‌های p
- `command -v` - بررسی نصب بودن
- html-xml-utils باید نصب باشد

## اسکریپت scraping

## مثال ۶: اسکریپت scraping

فایل [examples/06-scraping-script.sh](examples/06-scraping-script.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# تابع استخراج عنوان
extract_title() {
    local html=$1
    echo "$html" | grep -o '<title>.*</title>' | sed 's/<title>//;s/<\/title>//'
}

# تابع استخراج لینک‌ها
extract_links() {
    local html=$1
    echo "$html" | grep -o 'href="[^"]*"' | sed 's/href="//;s/"$//'
}

# تابع استخراج متن
extract_text() {
    local html=$1
    echo "$html" | sed 's/<[^>]*>//g' | tr -s '[:space:]' '\n' | grep -v '^$'
}

# ایجاد HTML نمونه
HTML=$(cat << EOF
<html>
<head><title>صفحه تست</title></head>
<body>
    <a href="http://example.com">لینک ۱</a>
    <a href="http://test.com">لینک ۲</a>
    <p>متن نمونه</p>
</body>
</html>
EOF
)

echo "عنوان:"
extract_title "$HTML"

echo -e "\nلینک‌ها:"
extract_links "$HTML"

echo -e "\nمتن:"
extract_text "$HTML"
```

### توضیح خط به خط:

- توابع جداگانه برای هر عملیات
- `extract_title` - استخراج عنوان
- `extract_links` - استخراج لینک‌ها
- `extract_text` - استخراج متن
- `tr -s '[:space:]' '\n'` - تبدیل فاصله به خط جدید

## نکات اخلاقی و قانونی

### قوانین مهم:

1. **robots.txt را رعایت کنید** - قوانین سایت
2. **rate limiting** - درخواست‌های زیاد ممنوع
3. **کپی‌رایت** - محتوای محافظت شده
4. **Terms of Service** - قوانین استفاده از سایت
5. **استفاده شخصی** - برای استفاده تجاری اجازه بگیرید

## مثال ۷: scraping اخلاقی

فایل [examples/07-ethical-scraping.sh](examples/07-ethical-scraping.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

# تابع بررسی robots.txt
check_robots() {
    local url=$1
    local robots_url="${url}/robots.txt"
    
    echo "بررسی robots.txt: $robots_url"
    curl -s -I "$robots_url" | head -5
}

# تابع scraping با delay
scrape_with_delay() {
    local url=$1
    local delay=${2:-2}
    
    echo "دانلود $url با delay $delay ثانیه"
    curl -s "$url" > /tmp/page.html
    sleep $delay
    echo "دانلود کامل شد"
    
    rm /tmp/page.html
}

# تابع User-Agent مناسب
scrape_with_ua() {
    local url=$1
    local ua="MyBot/1.0 (contact: email@example.com)"
    
    echo "دانلود با User-Agent مناسب"
    curl -s -A "$ua" "$url" > /tmp/page.html
    rm /tmp/page.html
}

# مثال
echo "=== Scraping اخلاقی ==="
echo "1. همیشه robots.txt را بررسی کنید"
echo "2. از delay استفاده کنید"
echo "3. User-Agent مناسب بگذارید"
echo "4. فقط داده‌های عمومی را استخراج کنید"
echo "5. Terms of Service را رعایت کنید"
```

### توضیح خط به خط:

- `check_robots` - بررسی robots.txt
- `scrape_with_delay` - scraping با delay
- `scrape_with_ua` - scraping با User-Agent
- نکات اخلاقی مهم

## نکات رایج اشتباهات (Common Pitfalls)

### ۱. نادیده گرفتن robots.txt

❌ اشتباه:
```bash
curl site.com  - بدون بررسی robots.txt
```

✅ درست:
```bash
curl site.com/robots.txt  - بررسی قوانین
```

### ۲. درخواست‌های زیاد

❌ خطرناک:
```bash
for i in {1..1000}; do curl site.com; done  - DOS attack
```

✅ درست:
```bash
for i in {1..10}; do curl site.com; sleep 2; done  - با delay
```

### ۳. عدم احترام به کپی‌رایت

❌ اشتباه:
```bash
استخراج محتوای محافظت شده
```

✅ درست:
```bash
فقط داده‌های عمومی
```

## بهترین شیوه‌ها (Best Practices)

1. **همیشه robots.txt را بررسی کنید** - رعایت قوانین
2. **از delay استفاده کنید** - جلوگیری از overload
3. **User-Agent مناسب بگذارید** - شناسایی خود
4. **فقط داده‌های عمومی** - احترام به کپی‌رایت
5. **cache کنید** - کاهش درخواست‌ها

## خلاصه جلسه

در این جلسه یاد گرفتیم:
- دانلود صفحات وب با curl
- استخراج داده با grep و sed
- استخراج داده با awk
- استفاده از html-xml-utils
- اسکریپت scraping
- نکات اخلاقی و قانونی
- نکات مهم و اشتباهات رایج

## تمرین‌ها

### تمرین ۱ (ساده): استخراج قیمت

یک اسکریپت بنویسید که:
1. یک فایل HTML با قیمت‌ها ایجاد کند
2. قیمت‌ها را استخراج کند
3. مجموع را محاسبه کند

فایل تمرین: [exercises/exercise-01.sh](exercises/exercise-01.sh)

### تمرین ۲ (متوسط): scraping اخلاقی

یک اسکریپت بنویسید که:
1. robots.txt را بررسی کند
2. با delay مناسب صفحه را دانلود کند
3. داده‌های مورد نیاز را استخراج کند

فایل تمرین: [exercises/exercise-02.sh](exercises/exercise-02.sh)

## جلسه بعد

در جلسه بعد، پروژه پیشرفته نهایی را انجام می‌دهیم.
