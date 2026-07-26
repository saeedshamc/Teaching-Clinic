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
