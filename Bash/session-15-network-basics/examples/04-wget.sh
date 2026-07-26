#!/usr/bin/env bash

# دانلود ساده
echo "دانلود فایل از example.com:"
wget -q -O /tmp/example.html http://example.com

# نمایش اطلاعات دانلود
echo "فایل دانلود شد"
ls -lh /tmp/example.html

# پاکسازی
rm /tmp/example.html
