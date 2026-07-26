#!/usr/bin/env bash

# تمرین ۱: لاگر ساده
# هدف: یک اسکریپت بنویسید که:
# 1. پیام‌ها را از کاربر بگیرد
# 2. هر پیام را با timestamp در فایل log ذخیره کند
# 3. محتوای فایل log را نمایش دهد

# راه‌حل:
LOG_FILE="/tmp/mylog.txt"

# ایجاد فایل log خالی
> "$LOG_FILE"

echo "لاگر ساده - برای خروجی تایپ 'exit'"

while true; do
    read -p "پیام: " MESSAGE
    
    if [ "$MESSAGE" = "exit" ]; then
        break
    fi
    
    # افزودن پیام با timestamp
    TIMESTAMP=$(date '+%Y-%m-%d %H:%M:%S')
    echo "[$TIMESTAMP] $MESSAGE" >> "$LOG_FILE"
done

echo -e "\nمحتوای فایل log:"
cat "$LOG_FILE"

# پاکسازی
rm "$LOG_FILE"
