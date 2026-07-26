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
