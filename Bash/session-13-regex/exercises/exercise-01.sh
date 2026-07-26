#!/usr/bin/env bash

# تمرین ۱: استخراج اعداد
# هدف: یک اسکریپت بنویسید که:
# 1. یک متن از کاربر بگیرد
# 2. تمام اعداد را با regex استخراج کند
# 3. مجموع اعداد را محاسبه کند

# راه‌حل:
read -p "لطفاً یک متن وارد کنید: " TEXT

# استخراج اعداد با grep و regex
NUMBERS=$(echo "$TEXT" | grep -o -E '[0-9]+')

echo "اعداد پیدا شده:"
echo "$NUMBERS"

# محاسبه مجموع
SUM=0
for num in $NUMBERS; do
    SUM=$((SUM + num))
done

echo -e "\nمجموع اعداد: $SUM"
