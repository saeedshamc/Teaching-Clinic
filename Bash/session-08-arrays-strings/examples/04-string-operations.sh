#!/usr/bin/env bash

TEXT="Hello World from Bash"

echo "متن اصلی: $TEXT"

# طول رشته
echo "طول رشته: ${#TEXT}"

# substring
echo "5 کاراکتر اول: ${TEXT:0:5}"
echo "از ایندکس 6: ${TEXT:6}"

# جایگزینی
echo "جایگزینی World با Bash: ${TEXT/World/Bash}"

# حذف زیررشته
echo "حذف Hello: ${TEXT#Hello }"
echo "حذف از انتها: ${TEXT% Bash}"

# تبدیل به حروف بزرگ
echo "حروف بزرگ: ${TEXT^^}"

# تبدیل به حروف کوچک
echo "حروف کوچک: ${TEXT,,}"
