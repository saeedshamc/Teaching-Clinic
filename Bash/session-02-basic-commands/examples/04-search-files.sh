#!/usr/bin/env bash

# ایجاد ساختار نمونه برای تست
echo "ایجاد ساختار نمونه..."
mkdir -p /tmp/search_test/{dir1,dir2,dir3}
touch /tmp/search_test/file1.txt
touch /tmp/search_test/file2.txt
touch /tmp/search_test/dir1/config.sh
touch /tmp/search_test/dir2/data.txt
touch /tmp/search_test/dir3/readme.md

# جستجوی تمام فایل‌های txt
echo "جستجوی فایل‌های txt:"
find /tmp/search_test -name "*.txt"

# جستجوی تمام فایل‌های sh
echo -e "\nجستجوی فایل‌های sh:"
find /tmp/search_test -name "*.sh"

# جستجوی پوشه‌ها
echo -e "\nجستجوی پوشه‌ها:"
find /tmp/search_test -type d

# جستجوی فایل‌ها با نام مشخص
echo -e "\nجستجوی فایل با نام file1:"
find /tmp/search_test -name "file1*"

# پاکسازی
rm -rf /tmp/search_test
echo -e "\nپاکسازی انجام شد."
