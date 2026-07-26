#!/usr/bin/env bash

# ایجاد فایل‌های نمونه
cd /tmp
mkdir -p loop_test
cd loop_test
touch file1.txt file2.txt file3.txt data.log config.conf

echo "فایل‌های txt در دایرکتوری:"
for file in *.txt; do
    echo "پیدا شد: $file"
done

echo -e "\nتمام فایل‌ها:"
for file in *; do
    echo "فایل: $file"
done

# پاکسازی
cd /tmp
rm -rf loop_test
