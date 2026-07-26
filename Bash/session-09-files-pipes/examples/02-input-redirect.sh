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
