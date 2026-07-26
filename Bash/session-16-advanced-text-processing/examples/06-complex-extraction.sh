#!/usr/bin/env bash

# ایجاد فایل پیچیده
cat > /tmp/complex.txt << EOF
[2024-01-15] User:ali Action:login IP:192.168.1.1 Status:success
[2024-01-15] User:reza Action:logout IP:192.168.1.2 Status:success
[2024-01-15] User:mohammad Action:login IP:192.168.1.3 Status:failed
[2024-01-15] User:hosein Action:login IP:192.168.1.4 Status:success
EOF

echo "محتوای فایل:"
cat /tmp/complex.txt

# استخراج نام کاربران
echo -e "\nنام کاربران:"
grep -oP 'User:\K\w+' /tmp/complex.txt

# استخراج IPها
echo -e "\nIPها:"
grep -oP 'IP:\K[\d.]+' /tmp/complex.txt

# شمارش بر اساس Action
echo -e "\nشمارش بر اساس Action:"
grep -oP 'Action:\K\w+' /tmp/complex.txt | sort | uniq -c

# پاکسازی
rm /tmp/complex.txt
