#!/usr/bin/env bash

# تمرین ۲: اسکریپت با trap
# هدف: یک اسکریپت بنویسید که:
# 1. یک فایل موقت ایجاد کند
# 2. با trap مطمئن شود که فایل پاک می‌شود
# 3. به Ctrl+C پاسخ دهد
# 4. پیام مناسب در صورت خطا چاپ کند

# راه‌حل:
TEMP_FILE="/tmp/temp_script_$$.txt"

# تابع پاکسازی
cleanup() {
    echo "پاکسازی در حال انجام..."
    if [ -f "$TEMP_FILE" ]; then
        rm "$TEMP_FILE"
        echo "فایل موقت پاک شد"
    fi
    echo "اسکریپت خاتمه یافت"
}

# trap برای خروج و Ctrl+C
trap cleanup EXIT INT

# ایجاد فایل موقت
echo "ایجاد فایل موقت: $TEMP_FILE"
echo "این یک فایل موقت است" > "$TEMP_FILE"

echo "اسکریپت در حال اجراست..."
echo "برای تست Ctrl+C را فشار دهید یا صبر کنید"

# شبیه‌سازی کار طولانی
for i in {1..5}; do
    echo "کار در حال انجام: $i/5"
    sleep 1
done

echo "اسکریپت با موفقیت تمام شد"
