#!/usr/bin/env bash

# تمرین ۲: تبدیل واحد
# هدف: یک اسکریپت بنویسید که:
# 1. توابعی برای تبدیل واحد داشته باشد
# 2. از کاربر عدد و نوع تبدیل بگیرد
# 3. تابع مناسب را فراخوانی کند
# 4. نتیجه تبدیل شده را چاپ کند

# راه‌حل:
# تعریف توابع تبدیل
meters_to_kilometers() {
    local meters=$1
    local km=$(echo "scale=3; $meters / 1000" | bc)
    echo "$km"
}

kilometers_to_miles() {
    local km=$1
    local miles=$(echo "scale=3; $km * 0.621371" | bc)
    echo "$miles"
}

centimeters_to_inches() {
    local cm=$1
    local inches=$(echo "scale=3; $cm / 2.54" | bc)
    echo "$inches"
}

# گرفتن ورودی از کاربر
echo "تبدیل‌های موجود:"
echo "1. متر به کیلومتر"
echo "2. کیلومتر به مایل"
echo "3. سانتی‌متر به اینچ"
read -p "انتخاب کنید (1-3): " CHOICE

read -p "مقدار را وارد کنید: " VALUE

# اعمال تبدیل مناسب
case $CHOICE in
    1)
        RESULT=$(meters_to_kilometers $VALUE)
        echo "$VALUE متر = $RESULT کیلومتر"
        ;;
    2)
        RESULT=$(kilometers_to_miles $VALUE)
        echo "$VALUE کیلومتر = $RESULT مایل"
        ;;
    3)
        RESULT=$(centimeters_to_inches $VALUE)
        echo "$VALUE سانتی‌متر = $RESULT اینچ"
        ;;
    *)
        echo "انتخاب نامعتبر است"
        ;;
esac
