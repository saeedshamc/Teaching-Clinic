#!/usr/bin/env bash

# تمرین ۱: ماشین حساب ساده
# هدف: یک اسکریپت بنویسید که:
# 1. دو عدد از کاربر بگیرد
# 2. جمع، تفریق، ضرب، تقسیم و باقی‌مانده آن‌ها را چاپ کند
# 3. نتایج را با فرمت زیبا نمایش دهد

# راه‌حل:
echo "=== ماشین حساب ساده ==="
echo

read -p "عدد اول را وارد کنید: " NUM1
read -p "عدد دوم را وارد کنید: " NUM2

# عملیات ریاضی
SUM=$((NUM1 + NUM2))
DIFF=$((NUM1 - NUM2))
PRODUCT=$((NUM1 * NUM2))
DIVISION=$((NUM1 / NUM2))
MODULO=$((NUM1 % NUM2))

# نمایش نتایج
echo
echo "--- نتایج ---"
echo "جمع: $NUM1 + $NUM2 = $SUM"
echo "تفریق: $NUM1 - $NUM2 = $DIFF"
echo "ضرب: $NUM1 × $NUM2 = $PRODUCT"
echo "تقسیم: $NUM1 ÷ $NUM2 = $DIVISION"
echo "باقی‌مانده: $NUM1 % $NUM2 = $MODULO"
