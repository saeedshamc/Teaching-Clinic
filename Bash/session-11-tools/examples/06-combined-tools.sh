#!/usr/bin/env bash

# ایجاد فایل نمونه
cat > /tmp/users.txt << EOF
علی,25,تهران
رضا,30,شیراز
محمد,22,تهران
حسین,28,اصفهان
مهدی,35,تهران
EOF

echo "محتوای فایل:"
cat /tmp/users.txt

# ترکیب grep و awk
echo -e "\nافراد تهران با grep و awk:"
grep "تهران" /tmp/users.txt | awk -F',' '{print $1, $2}'

# ترکیب find و xargs
echo -e "\nجستجوی فایل‌های tmp:"
find /tmp -name "*.txt" -type f 2>/dev/null | head -3 | xargs ls -lh

# ترکیب sed و awk
echo -e "\nجایگزینی و استخراج:"
sed 's/,/ /g' /tmp/users.txt | awk '{print $1, $3}'

# پاکسازی
rm /tmp/users.txt
