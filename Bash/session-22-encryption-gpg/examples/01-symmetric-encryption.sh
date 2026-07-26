#!/usr/bin/env bash

# ایجاد فایل نمونه
echo "این یک متن محرمانه است" > /tmp/secret.txt

echo "فایل اصلی:"
cat /tmp/secret.txt

# رمزنگاری با AES-256
echo -e "\nرمزنگاری با AES-256-CBC..."
openssl enc -aes-256-cbc -salt -in /tmp/secret.txt -out /tmp/secret.enc -k "mypassword"

echo "فایل رمزنگاری شده ایجاد شد"
ls -lh /tmp/secret.enc

# رمزگشایی
echo -e "\nرمزگشایی..."
openssl enc -aes-256-cbc -d -in /tmp/secret.enc -out /tmp/secret_decrypted.txt -k "mypassword"

echo "فایل رمزگشایی شده:"
cat /tmp/secret_decrypted.txt

# پاکسازی
rm /tmp/secret.txt /tmp/secret.enc /tmp/secret_decrypted.txt
