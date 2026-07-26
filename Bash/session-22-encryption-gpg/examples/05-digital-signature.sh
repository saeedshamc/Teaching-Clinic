#!/usr/bin/env bash

# ایجاد فایل نمونه
echo "این فایل امضای دیجیتال دارد" > /tmp/document.txt

echo "فایل اصلی:"
cat /tmp/document.txt

# امضای فایل
echo -e "\nامضای فایل..."
gpg --default-key your@email.com --detach-sign /tmp/document.txt 2>/dev/null || echo "برای امضا، key نیاز است"

echo "امضای دیجیتال ایجاد شد"
ls -lh /tmp/document.txt.sig 2>/dev/null || echo "فایل امضا ایجاد نشد"

# تأیید امضا
echo -e "\nتأیید امضا:"
gpg --verify /tmp/document.txt.sig /tmp/document.txt 2>&1 || echo "Key برای تأیید وجود ندارد"

# پاکسازی
rm /tmp/document.txt /tmp/document.txt.sig 2>/dev/null
