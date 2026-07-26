#!/usr/bin/env bash

# ایجاد دایرکتوری امن
mkdir -p /tmp/secure_dir

echo "Permissions دایرکتوری:"
ls -ld /tmp/secure_dir

# تنظیم permissions امن
chmod 700 /tmp/secure_dir
echo -e "\nبعد از chmod 700:"
ls -ld /tmp/secure_dir

# ایجاد فایل حساس
echo "secret data" > /tmp/secure_dir/secret.txt
chmod 600 /tmp/secure_dir/secret.txt

echo -e "\nPermissions فایل حساس:"
ls -l /tmp/secure_dir/secret.txt

# بررسی دسترسی
echo -e "\nبررسی دسترسی:"
if [ -r /tmp/secure_dir/secret.txt ]; then
    echo "قابل خواندن"
fi
if [ -w /tmp/secure_dir/secret.txt ]; then
    echo "قابل نوشتن"
fi

# پاکسازی
rm -rf /tmp/secure_dir
