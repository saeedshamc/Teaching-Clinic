#!/usr/bin/env bash

# تمرین ۲: پردازش فایل CSV
# هدف: یک اسکریپت بنویسید که:
# 1. یک فایل CSV ساده ایجاد کند (نام، سن، شهر)
# 2. با استفاده از awk و pipe:
#    - میانگین سن را محاسبه کند
#    - تعداد افراد در هر شهر را بشمارد
#    - نتایج را در فایل جداگانه ذخیره کند

# راه‌حل:
CSV_FILE="/tmp/data.csv"
RESULT_FILE="/tmp/results.txt"

# ایجاد فایل CSV
cat > "$CSV_FILE" << EOF
علی,25,تهران
رضا,30,شیراز
محمد,22,تهران
حسین,28,اصفهان
مهدی,35,تهران
حسین,27,شیراز
EOF

echo "فایل CSV ایجاد شد:"
cat "$CSV_FILE"

# محاسبه میانگین سن با awk
echo -e "\nمحاسبه میانگین سن..."
AVG_AGE=$(awk -F',' '{sum += $2; count++} END {print sum/count}' "$CSV_FILE")
echo "میانگین سن: $AVG_AGE" | tee -a "$RESULT_FILE"

# شمارش افراد در هر شهر
echo -e "\nتعداد افراد در هر شهر:"
awk -F',' '{count[$3]++} END {for (city in count) print city, count[city]}' "$CSV_FILE" | tee -a "$RESULT_FILE"

# نمایش نتایج
echo -e "\nنتایج نهایی:"
cat "$RESULT_FILE"

# پاکسازی
rm "$CSV_FILE" "$RESULT_FILE"
