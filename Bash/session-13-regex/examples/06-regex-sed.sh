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
