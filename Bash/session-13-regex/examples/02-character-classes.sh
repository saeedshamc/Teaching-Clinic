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
