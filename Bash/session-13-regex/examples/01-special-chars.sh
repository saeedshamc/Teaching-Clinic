#!/usr/bin/env bash

# ایجاد فایل نمونه
cat > /tmp/test.txt << EOF
Hello World
Hello Bash
Hello Linux
World is great
HELLO
hello
EOF

echo "محتوای فایل:"
cat /tmp/test.txt

# نقطه (.) - هر کاراکتری
echo -e "\nجستجوی 'H.llo':"
grep "H.llo" /tmp/test.txt

# شروع خط (^)
echo -e "\nخطوط شروع شده با 'Hello':"
grep "^Hello" /tmp/test.txt

# پایان خط ($)
echo -e "\nخطوط تمام شده با 'World':"
grep "World$" /tmp/test.txt

# ستاره (*) - صفر یا بیشتر
echo -e "\nجستجوی 'He*lo':"
grep "He*lo" /tmp/test.txt

# پاکسازی
rm /tmp/test.txt
