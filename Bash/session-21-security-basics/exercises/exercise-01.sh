#!/usr/bin/env bash

# تمرین ۱: بررسی permissions
# هدف: یک اسکریپت بنویسید که:
# 1. یک دایرکتوری ایجاد کند
# 2. فایل‌های حساس در آن بسازد
# 3. permissions امن تنظیم کند
# 4. دسترسی‌ها را بررسی کند

# راه‌حل:
# ایجاد دایرکتوری امن
SECURE_DIR="/tmp/secure_test"
mkdir -p "$SECURE_DIR"

echo "ایجاد دایرکتوری امن: $SECURE_DIR"

# تنظیم permissions دایرکتوری
chmod 700 "$SECURE_DIR"
echo "Permissions دایرکتوری: 700"

# ایجاد فایل حساس
echo "secret data" > "$SECURE_DIR/secret.txt"
chmod 600 "$SECURE_DIR/secret.txt"
echo "Permissions فایل حساس: 600"

# بررسی دسترسی‌ها
echo -e "\nبررسی دسترسی‌ها:"
echo "خواندن دایرکتوری:"
if [ -r "$SECURE_DIR" ]; then
    echo "✓ قابل خواندن"
else
    echo "✗ قابل خواندن نیست"
fi

echo "نوشتن در دایرکتوری:"
if [ -w "$SECURE_DIR" ]; then
    echo "✓ قابل نوشتن"
else
    echo "✗ قابل نوشتن نیست"
fi

echo "اجرای دایرکتوری:"
if [ -x "$SECURE_DIR" ]; then
    echo "✓ قابل اجرا"
else
    echo "✗ قابل اجرا نیست"
fi

echo -e "\nخواندن فایل:"
if [ -r "$SECURE_DIR/secret.txt" ]; then
    echo "✓ قابل خواندن"
else
    echo "✗ قابل خواندن نیست"
fi

echo "نوشتن در فایل:"
if [ -w "$SECURE_DIR/secret.txt" ]; then
    echo "✓ قابل نوشتن"
else
    echo "✗ قابل نوشتن نیست"
fi

# پاکسازی
rm -rf "$SECURE_DIR"
echo -e "\nپاکسازی کامل شد"
