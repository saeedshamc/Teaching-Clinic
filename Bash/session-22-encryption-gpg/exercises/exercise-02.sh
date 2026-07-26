#!/usr/bin/env bash

# تمرین ۲: مدیریت GPG
# هدف: یک اسکریپت بنویسید که:
# 1. لیست keyهای GPG را نمایش دهد
# 2. یک فایل را رمزنگاری کند
# 3. امضای دیجیتال ایجاد کند
# 4. تأیید امضا کند

# راه‌حل:
echo "=== مدیریت GPG ==="

# 1. لیست keyهای GPG
echo -e "\n1. لیست Keyهای Public:"
gpg --list-keys 2>/dev/null || echo "هیچ keyی وجود ندارد"

echo -e "\n2. لیست Keyهای Private:"
gpg --list-secret-keys 2>/dev/null || echo "هیچ key private وجود ندارد"

# ایجاد فایل نمونه
echo "این فایل برای تست GPG است" > /tmp/gpg_test.txt

# 2. رمزنگاری symmetric با GPG
echo -e "\n3. رمزنگاری symmetric با GPG:"
gpg --symmetric --cipher-algo AES256 /tmp/gpg_test.txt 2>/dev/null
echo "فایل رمزنگاری شده: /tmp/gpg_test.txt.gpg"

# 3. رمزگشایی
echo -e "\n4. رمزگشایی:"
gpg --decrypt /tmp/gpg_test.txt.gpg > /tmp/gpg_decrypted.txt 2>/dev/null
echo "فایل رمزگشایی شده: /tmp/gpg_decrypted.txt"

# تأیید محتوا
echo -e "\n5. تأیید محتوا:"
ORIGINAL=$(cat /tmp/gpg_test.txt)
DECRYPTED=$(cat /tmp/gpg_decrypted.txt)
if [ "$ORIGINAL" = "$DECRYPTED" ]; then
    echo "✓ محتوا مطابقت دارد"
else
    echo "✗ محتوا مطابقت ندارد"
fi

# 4. امضای دیجیتال (اگر key وجود داشته باشد)
echo -e "\n6. امضای دیجیتال:"
if gpg --list-keys &>/dev/null; then
    gpg --default-key $(gpg --list-keys | head -1 | awk '{print $NF}') --detach-sign /tmp/gpg_test.txt 2>/dev/null
    echo "فایل امضا ایجاد شد: /tmp/gpg_test.txt.sig"
    
    # تأیید امضا
    echo -e "\n7. تأیید امضا:"
    gpg --verify /tmp/gpg_test.txt.sig /tmp/gpg_test.txt 2>&1 || echo "تأیید امضا ناموفق"
else
    echo "هیچ keyی برای امضا وجود ندارد"
fi

# پاکسازی
rm /tmp/gpg_test.txt /tmp/gpg_test.txt.gpg /tmp/gpg_decrypted.txt /tmp/gpg_test.txt.sig 2>/dev/null
echo -e "\nپاکسازی کامل شد"
