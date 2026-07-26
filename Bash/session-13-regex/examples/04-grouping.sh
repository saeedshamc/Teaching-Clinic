#!/usr/bin/env bash

# ایجاد فایل نمونه
cat > /tmp/test.txt << EOF
Hello World
Hello Bash
Good Morning
Good Evening
EOF

echo "محتوای فایل:"
cat /tmp/test.txt

# OR (|)
echo -e "\nHello یا Good:"
grep -E "Hello|Good" /tmp/test.txt

# گروه‌بندی
echo -e "\nHello World یا Good Morning:"
grep -E "(Hello World|Good Morning)" /tmp/test.txt

# پاکسازی
rm /tmp/test.txt
