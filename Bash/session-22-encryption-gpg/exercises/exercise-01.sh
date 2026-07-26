#!/usr/bin/env bash

# تمرین ۱: رمزنگاری فایل
# هدف: یک اسکریپت بنویسید که:
# 1. یک فایل متنی ایجاد کند
# 2. با AES-256 رمزنگاری کند
# 3. رمزگشایی کند
# 4. محتوا را تأیید کند

# راه‌حل:
# ایجاد فایل نمونه
echo "این یک متن محرمانه برای تست است" > /tmp/secret.txt

ORIGINAL_CONTENT=$(cat /tmp/secret.txt)
echo "محتوای اصلی: $ORIGINAL_CONTENT"

# رمزنگاری با AES-256-CBC
echo -e "\nرمزنگاری با AES-256-CBC..."
openssl enc -aes-256-cbc -salt -in /tmp/secret.txt -out /tmp/secret.enc -k "testpassword123"

echo "فایل رمزنگاری شده ایجاد شد"
ls -lh /tmp/secret.enc

# نمایش محتوای رمزنگاری شده (غیرقابل خواندن)
echo -e "\nمحتوای رمزنگاری شده:"
head -c 50 /tmp/secret.enc
echo "..."

# رمزگشایی
echo -e "\nرمزگشایی..."
openssl enc -aes-256-cbc -d -in /tmp/secret.enc -out /tmp/secret_decrypted.txt -k "testpassword123"

DECRYPTED_CONTENT=$(cat /tmp/secret_decrypted.txt)
echo "محتوای رمزگشایی شده: $DECRYPTED_CONTENT"

# تأیید محتوا
echo -e "\nتأیید محتوا:"
if [ "$ORIGINAL_CONTENT" = "$DECRYPTED_CONTENT" ]; then
    echo "✓ محتوا مطابقت دارد - رمزنگاری/رمزگشایی موفق"
else
    echo "✗ محتوا مطابقت ندارد - خطا در رمزنگاری/رمزگشایی"
fi

# پاکسازی
rm /tmp/secret.txt /tmp/secret.enc /tmp/secret_decrypted.txt
echo -e "\nپاکسازی کامل شد"
