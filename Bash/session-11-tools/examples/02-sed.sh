#!/usr/bin/env bash

# ایجاد فایل نمونه
cat > /tmp/test.txt << EOF
Hello World
Hello Bash
Hello Linux
World is great
EOF

echo "محتوای اصلی:"
cat /tmp/test.txt

# جایگزینی ساده
echo -e "\nجایگزینی Hello با Hi:"
sed 's/Hello/Hi/' /tmp/test.txt

# جایگزینی سراسری
echo -e "\nجایگزینی سراسری Hello با Hi:"
sed 's/Hello/Hi/g' /tmp/test.txt

# حذف خط
echo -e "\nحذف خط 2:"
sed '2d' /tmp/test.txt

# حذف خطوط حاوی الگو
echo -e "\nحذف خطوط حاوی World:"
sed '/World/d' /tmp/test.txt

# ویرایش درجا
echo -e "\nویرایش درجا:"
cp /tmp/test.txt /tmp/test_backup.txt
sed -i 's/Hello/Hi/g' /tmp/test.txt
cat /tmp/test.txt

# بازگردانی
mv /tmp/test_backup.txt /tmp/test.txt
rm /tmp/test.txt
