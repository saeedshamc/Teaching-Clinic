#!/usr/bin/env bash

# تمرین ۲: سیستم احراز هویت ساده
# هدف: یک اسکریپت بنویسید که:
# 1. نام کاربری و رمز عبور را بگیرد
# 2. با مقادیر از پیش تعیین شده مقایسه کند
# 3. اگر هر دو درست بود، پیام خوش‌آمدگویی چاپ کند
# 4. اگر یکی غلط بود، پیام خطای مناسب چاپ کند
# 5. از case برای پیام‌های مختلف استفاده کند

# راه‌حل:
CORRECT_USERNAME="admin"
CORRECT_PASSWORD="secret123"

echo "=== سیستم احراز هویت ==="
read -p "نام کاربری: " USERNAME
read -s -p "رمز عبور: " PASSWORD
echo  # خط جدید

# بررسی نام کاربری و رمز عبور
if [ "$USERNAME" = "$CORRECT_USERNAME" ] && [ "$PASSWORD" = "$CORRECT_PASSWORD" ]; then
    echo "ورود موفق!"
    case "$USERNAME" in
        "admin")
            echo "سلام مدیر! دسترسی کامل دارید."
            ;;
        "user")
            echo "سلام کاربر! دسترسی محدود دارید."
            ;;
        *)
            echo "سلام $USERNAME!"
            ;;
    esac
else
    # بررسی اینکه کدام غلط است
    if [ "$USERNAME" != "$CORRECT_USERNAME" ] && [ "$PASSWORD" != "$CORRECT_PASSWORD" ]; then
        echo "خطا: نام کاربری و رمز عبور اشتباه است"
    elif [ "$USERNAME" != "$CORRECT_USERNAME" ]; then
        echo "خطا: نام کاربری اشتباه است"
    else
        echo "خطا: رمز عبور اشتباه است"
    fi
fi
