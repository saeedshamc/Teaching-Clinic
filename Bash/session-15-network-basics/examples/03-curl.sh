#!/usr/bin/env bash

# دانلود صفحه وب
echo "دانلود صفحه example.com:"
curl -s -o /tmp/example.html http://example.com

# نمایش header
echo -e "\nHeader سایت:"
curl -I http://example.com

# بررسی status code
echo -e "\nStatus code:"
curl -s -o /dev/null -w "%{http_code}" http://example.com

# پاکسازی
rm /tmp/example.html
