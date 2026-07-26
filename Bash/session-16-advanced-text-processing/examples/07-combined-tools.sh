#!/usr/bin/env bash

# ایجاد فایل نمونه
cat > /tmp/mixed.txt << EOF
علی 25 تهران برنامه‌نویس
رضا 30 شیراز طراح
محمد 22 اصفهان مدیر
حسین 28 مشهد مهندس
مهدی 35 تهران معلم
EOF

echo "محتوای فایل:"
cat /tmp/mixed.txt

# ترکیب awk و sort
echo -e "\nمرتب شده بر اساس سن:"
awk '{print $2, $0}' /tmp/mixed.txt | sort -n | cut -d' ' -f2-

# ترکیب grep و awk
echo -e "\nافراد تهران:"
grep "تهران" /tmp/mixed.txt | awk '{print $1, $2}'

# ترکیب sed و awk
echo -e "\nجایگزینی و استخراج:"
sed 's/تهران/TEHRAN/g' /tmp/mixed.txt | awk '{print $1, $3}'

# پاکسازی
rm /tmp/mixed.txt
