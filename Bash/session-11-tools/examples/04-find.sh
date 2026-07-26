#!/usr/bin/env bash

# ایجاد ساختار نمونه
mkdir -p /tmp/find_test/{dir1,dir2}
touch /tmp/find_test/file1.txt
touch /tmp/find_test/file2.log
touch /tmp/find_test/dir1/data.txt
touch /tmp/find_test/dir2/config.conf

echo "جستجوی فایل‌های txt:"
find /tmp/find_test -name "*.txt"

echo -e "\nجستجوی دایرکتوری‌ها:"
find /tmp/find_test -type d

echo -e "\nجستجوی فایل‌های log:"
find /tmp/find_test -name "*.log"

echo -e "\nجستجوی با نام دقیق:"
find /tmp/find_test -name "file1.txt"

# پاکسازی
rm -rf /tmp/find_test
