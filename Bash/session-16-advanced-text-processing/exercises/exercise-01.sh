#!/usr/bin/env bash

# تمرین ۱: پردازش CSV
# هدف: یک اسکریپت بنویسید که:
# 1. یک فایل CSV بخواند
# 2. میانگین یک ستون عددی را محاسبه کند
# 3. نتایج را چاپ کند

# راه‌حل:
# ایجاد فایل CSV نمونه
cat > /tmp/scores.csv << EOF
نام,نمره
علی,85
رضا,92
محمد,78
حسین,88
مهدی,95
EOF

echo "محتوای CSV:"
cat /tmp/scores.csv

# محاسبه میانگین نمره‌ها
echo -e "\nمحاسبه میانگین نمره‌ها:"
awk -F',' 'NR>1 {sum += $2; count++} END {print "تعداد: " count; print "مجموع: " sum; print "میانگین: " sum/count}' /tmp/scores.csv

# پاکسازی
rm /tmp/scores.csv
