#!/usr/bin/env bash

# ایجاد فایل CSV
cat > /tmp/users.csv << EOF
نام,سن,شهر,شغل
علی,25,تهران,برنامه‌نویس
رضا,30,شیراز,طراح
محمد,22,اصفهان,مدیر
حسین,28,مشهد,مهندس
مهدی,35,تهران,معلم
EOF

echo "محتوای CSV:"
cat /tmp/users.csv

# فیلتر بر اساس شهر
echo -e "\nافراد تهران:"
awk -F',' 'NR>1 && $3=="تهران" {print $1, $2, $4}' /tmp/users.csv

# شمارش بر اساس شهر
echo -e "\nتعداد افراد در هر شهر:"
awk -F',' 'NR>1 {count[$3]++} END {for (city in count) print city, count[city]}' /tmp/users.csv

# پاکسازی
rm /tmp/users.csv
