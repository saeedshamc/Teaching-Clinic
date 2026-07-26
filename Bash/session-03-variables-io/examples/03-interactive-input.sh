#!/usr/bin/env bash

# گرفتن نام با پیام
read -p "لطفاً نام خود را وارد کنید: " NAME

# گرفتن سن
read -p "لطفاً سن خود را وارد کنید: " AGE

# گرفتن شهر
read -p "لطفاً شهر خود را وارد کنید: " CITY

# چاپ خروجی
echo -e "\n--- اطلاعات شما ---"
echo "نام: $NAME"
echo "سن: $AGE"
echo "شهر: $CITY"

# محاسبه سال تولد
CURRENT_YEAR=$(date +%Y)
BIRTH_YEAR=$((CURRENT_YEAR - AGE))
echo "سال تولد تقریبی: $BIRTH_YEAR"
