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
