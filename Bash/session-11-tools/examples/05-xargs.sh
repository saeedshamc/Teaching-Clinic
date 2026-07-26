#!/usr/bin/env bash

# ایجاد فایل‌های نمونه
mkdir -p /tmp/xargs_test
cd /tmp/xargs_test
touch file1.txt file2.txt file3.txt

echo "فایل‌های ایجاد شده:"
ls

# استفاده از xargs برای حذف
echo -e "\nحذف با xargs:"
ls | xargs rm

echo -e "\nبعد از حذف:"
ls

# ایجاد مجدد فایل‌ها
touch file1.txt file2.txt file3.txt

# استفاده با find
echo -e "\nجستجو و چاپ با xargs:"
find . -name "*.txt" | xargs cat

# پاکسازی
cd /tmp
rm -rf /tmp/xargs_test
