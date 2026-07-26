#!/usr/bin/env bash

# ایجاد فایل نمونه
echo "این متن محرمانه با GPG رمزنگاری شده است" > /tmp/secret.txt

echo "فایل اصلی:"
cat /tmp/secret.txt

# رمزنگاری با symmetric key (بدون نیاز به key pair)
echo -e "\nرمزنگاری symmetric با GPG..."
gpg --symmetric --cipher-algo AES256 /tmp/secret.txt

echo "فایل رمزنگاری شده ایجاد شد"
ls -lh /tmp/secret.txt.gpg

# رمزگشایی
echo -e "\nرمزگشایی..."
gpg --decrypt /tmp/secret.txt.gpg > /tmp/secret_decrypted.txt

echo "فایل رمزگشایی شده:"
cat /tmp/secret_decrypted.txt

# پاکسازی
rm /tmp/secret.txt /tmp/secret.txt.gpg /tmp/secret_decrypted.txt
