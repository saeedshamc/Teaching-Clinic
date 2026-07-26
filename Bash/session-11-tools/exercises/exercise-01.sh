#!/usr/bin/env bash

# تمرین ۱: جستجو و شمارش
# هدف: یک اسکریپت بنویسید که:
# 1. یک فایل متنی ایجاد کند
# 2. با grep کلمه خاصی را جستجو کند
# 3. تعداد خطوط حاوی آن کلمه را بشمارد

# راه‌حل:
# ایجاد فایل نمونه
cat > /tmp/search_test.txt << EOF
این یک تست است
تست برای grep
تست برای awk
این یک تست دیگر است
EOF

echo "محتوای فایل:"
cat /tmp/search_test.txt

# جستجوی کلمه "تست"
echo -e "\nجستجوی کلمه 'تست':"
grep "تست" /tmp/search_test.txt

# شمارش خطوط
COUNT=$(grep -c "تست" /tmp/search_test.txt)
echo -e "\nتعداد خطوط حاوی 'تست': $COUNT"

# پاکسازی
rm /tmp/search_test.txt
