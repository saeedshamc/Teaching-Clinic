#!/usr/bin/env bash

# ایجاد یک پوشه موقت
echo "ایجاد پوشه موقت..."
mkdir -p /tmp/bash_course_demo

# رفتن به پوشه موقت
cd /tmp/bash_course_demo

# ایجاد چند فایل
echo "ایجاد فایل‌ها..."
touch file1.txt file2.txt file3.txt

# لیست فایل‌ها
echo -e "\nفایل‌های ایجاد شده:"
ls -l

# کپی یک فایل
echo -e "\nکپی فایل..."
cp file1.txt file1_copy.txt

# تغییر نام فایل
echo -e "\nتغییر نام فایل..."
mv file2.txt file2_renamed.txt

# لیست مجدد فایل‌ها
echo -e "\nفایل‌ها بعد از تغییرات:"
ls -l

# حذف فایل‌ها
echo -e "\nحذف فایل‌ها..."
rm file1.txt file1_copy.txt file2_renamed.txt file3.txt

# بازگشت به پوشه اصلی
cd -

# حذف پوشه موقت
echo -e "\nحذف پوشه موقت..."
rm -rf /tmp/bash_course_demo

echo "تمام شد!"
