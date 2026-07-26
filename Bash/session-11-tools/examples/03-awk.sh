#!/usr/bin/env bash

# ایجاد فایل نمونه
cat > /tmp/data.txt << EOF
علی 25 تهران
رضا 30 شیراز
محمد 22 اصفهان
حسین 28 مشهد
EOF

echo "محتوای فایل:"
cat /tmp/data.txt

# چاپ ستون اول
echo -e "\nستون اول (نام‌ها):"
awk '{print $1}' /tmp/data.txt

# چاپ ستون دوم
echo -e "\nستون دوم (سن‌ها):"
awk '{print $2}' /tmp/data.txt

# محاسبه مجموع سن‌ها
echo -e "\nمجموع سن‌ها:"
awk '{sum += $2} END {print sum}' /tmp/data.txt

# محاسبه میانگین
echo -e "\nمیانگین سن‌ها:"
awk '{sum += $2; count++} END {print sum/count}' /tmp/data.txt

# فیلتر کردن
echo -e "\nافراد بالای 25 سال:"
awk '$2 > 25 {print $1, $2}' /tmp/data.txt

# پاکسازی
rm /tmp/data.txt
