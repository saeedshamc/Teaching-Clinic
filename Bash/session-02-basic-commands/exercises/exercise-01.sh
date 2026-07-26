#!/usr/bin/env bash

# تمرین ۱: مدیریت فایل‌ها
# هدف: یک اسکریپت بنویسید که:
# 1. یک پوشه به نام "my_project" ایجاد کند
# 2. در آن سه فایل ایجاد کند: main.txt, data.txt, config.txt
# 3. فایل main.txt را به backup.txt کپی کند
# 4. فایل data.txt را به info.txt تغییر نام دهد
# 5. لیست تمام فایل‌ها را چاپ کند
# 6. در نهایت پوشه را حذف کند

# راه‌حل:
echo "ایجاد پوشه my_project..."
mkdir -p /tmp/my_project

echo "ایجاد فایل‌ها..."
cd /tmp/my_project
touch main.txt data.txt config.txt

echo "کپی فایل main.txt به backup.txt..."
cp main.txt backup.txt

echo "تغییر نام data.txt به info.txt..."
mv data.txt info.txt

echo "لیست فایل‌ها:"
ls -l

echo "بازگشت به پوشه اصلی..."
cd -

echo "حذف پوشه my_project..."
rm -rf /tmp/my_project

echo "تمام شد!"
