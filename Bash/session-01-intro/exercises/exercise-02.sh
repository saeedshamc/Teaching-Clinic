#!/usr/bin/env bash

# تمرین ۲: ماشین حساب ساده
# هدف: یک اسکریپت بنویسید که:
# 1. دو عدد از کاربر بگیرد
# 2. جمع، تفریق، ضرب و تقسیم آن‌ها را چاپ کند

# راه‌حل:
echo "لطفاً اولین عدد را وارد کنید:"
read NUM1

echo "لطفاً دومین عدد را وارد کنید:"
read NUM2

# محاسبات
SUM=$((NUM1 + NUM2))
DIFF=$((NUM1 - NUM2))
PRODUCT=$((NUM1 * NUM2))
DIV=$((NUM1 / NUM2))

# چاپ نتایج
echo "جمع: $SUM"
echo "تفریق: $DIFF"
echo "ضرب: $PRODUCT"
echo "تقسیم: $DIV"
