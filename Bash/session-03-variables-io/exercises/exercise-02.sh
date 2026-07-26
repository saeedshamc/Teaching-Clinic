#!/usr/bin/env bash

# تمرین ۲: محاسبه BMI
# هدف: یک اسکریپت بنویسید که:
# 1. وزن (کیلوگرم) و قد (سانتی‌متر) را از کاربر بگیرد
# 2. BMI را محاسبه کند: BMI = وزن / (قد/100)^2
# 3. وضعیت وزن را چاپ کند

# راه‌حل:
echo "=== محاسبه BMI ==="
echo

read -p "وزن خود را وارد کنید (کیلوگرم): " WEIGHT
read -p "قد خود را وارد کنید (سانتی‌متر): " HEIGHT_CM

# تبدیل قد به متر
HEIGHT_M=$((HEIGHT_CM / 100))
HEIGHT_M_DECIMAL=$(echo "scale=2; $HEIGHT_CM / 100" | bc)

# محاسبه BMI
BMI=$(echo "scale=2; $WEIGHT / ($HEIGHT_M_DECIMAL * $HEIGHT_M_DECIMAL)" | bc)

echo
echo "BMI شما: $BMI"

# تعیین وضعیت وزن
if (( $(echo "$BMI < 18.5" | bc -l) )); then
    echo "وضعیت: کمبود وزن"
elif (( $(echo "$BMI < 25" | bc -l) )); then
    echo "وضعیت: وزن نرمال"
elif (( $(echo "$BMI < 30" | bc -l) )); then
    echo "وضعیت: اضافه وزن"
else
    echo "وضعیت: چاقی"
fi
