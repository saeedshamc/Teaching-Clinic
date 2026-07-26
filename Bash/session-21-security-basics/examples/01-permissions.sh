#!/usr/bin/env bash

# ایجاد فایل نمونه
touch /tmp/test_file.txt

echo "Permissions اولیه:"
ls -l /tmp/test_file.txt

# تغییر permissions
chmod 644 /tmp/test_file.txt
echo -e "\nبعد از chmod 644:"
ls -l /tmp/test_file.txt

# chmod با symbolic
chmod u+x /tmp/test_file.txt
echo -e "\nبعد از chmod u+x:"
ls -l /tmp/test_file.txt

# پاکسازی
rm /tmp/test_file.txt
