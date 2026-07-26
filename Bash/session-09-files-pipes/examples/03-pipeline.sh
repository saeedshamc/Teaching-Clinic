#!/usr/bin/env bash

# ایجاد فایل نمونه
cat > /tmp/data.txt << EOF
علی 25
رضا 30
محمد 22
حسین 28
مهدی 35
EOF

echo "محتوای فایل:"
cat /tmp/data.txt

# فیلتر کردن با grep
echo -e "\nخطوط حاوی 'علی':"
grep "علی" /tmp/data.txt

# مرتب کردن
echo -e "\nمرتب شده:"
sort /tmp/data.txt

# شمارش خطوط
echo -e "\nتعداد خطوط:"
wc -l < /tmp/data.txt

# ترکیب pipeline
echo -e "\nخطوط حاوی عدد بالای 25:"
grep -E "[0-9]{2,}" /tmp/data.txt | grep -E "(2[5-9]|3[0-9])"

# استخراج ستون دوم
echo -e "\nسن‌ها:"
awk '{print $2}' /tmp/data.txt

# محاسبه میانگین
echo -e "\nمیانگین سن‌ها:"
awk '{sum += $2; count++} END {print sum/count}' /tmp/data.txt

# پاکسازی
rm /tmp/data.txt
