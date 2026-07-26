#!/usr/bin/env bash

# تعریف متغیرهای عددی
NUM1=10
NUM2=5

# عملیات ریاضی
SUM=$((NUM1 + NUM2))
DIFF=$((NUM1 - NUM2))
PRODUCT=$((NUM1 * NUM2))
DIVISION=$((NUM1 / NUM2))
MODULO=$((NUM1 % NUM2))

echo "عدد اول: $NUM1"
echo "عدد دوم: $NUM2"
echo -e "\n--- نتایج ---"
echo "جمع: $SUM"
echo "تفریق: $DIFF"
echo "ضرب: $PRODUCT"
echo "تقسیم: $DIVISION"
echo "باقی‌مانده: $MODULO"

# محاسبه مساحت مستطیل
read -p "طول مستطیل: " LENGTH
read -p "عرض مستطیل: " WIDTH
AREA=$((LENGTH * WIDTH))
echo "مساحت مستطیل: $AREA"
