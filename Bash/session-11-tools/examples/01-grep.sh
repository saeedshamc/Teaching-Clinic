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
