#!/usr/bin/env bash

# دانلود صفحه example.com
echo "دانلود صفحه example.com..."
curl -s -o /tmp/page.html http://example.com

# نمایش بخشی از صفحه
echo -e "\n50 خط اول صفحه:"
head -20 /tmp/page.html

# پاکسازی
rm /tmp/page.html
