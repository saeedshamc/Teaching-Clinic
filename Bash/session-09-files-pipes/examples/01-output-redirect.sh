#!/usr/bin/env bash

# ریدایرکت به فایل (بازنویسی)
echo "خط اول" > /tmp/test.txt
echo "خط دوم" > /tmp/test.txt  # خط اول پاک می‌شود

echo "محتوای فایل:"
cat /tmp/test.txt

# ریدایرکت با افزودن
echo "خط سوم" >> /tmp/test.txt
echo "خط چهارم" >> /tmp/test.txt

echo -e "\nمحتوای فایل بعد از افزودن:"
cat /tmp/test.txt

# ریدایرکت stderr
echo "این یک پیام است" > /tmp/output.txt
ls /nonexistent 2> /tmp/error.txt

echo -e "\nخروجی خطا:"
cat /tmp/error.txt

# ترکیب stdout و stderr
echo "خروجی استاندارد" > /tmp/combined.txt
ls /nonexistent >> /tmp/combined.txt 2>&1

echo -e "\nخروجی ترکیبی:"
cat /tmp/combined.txt

# پاکسازی
rm /tmp/test.txt /tmp/output.txt /tmp/error.txt /tmp/combined.txt
