#!/usr/bin/env bash

echo "ایجاد GPG Key..."

# ایجاد key با تنظیمات پیش‌فرض
# توجه: این دستور تعاملی است
echo "برای ایجاد key، دستور زیر را اجرا کنید:"
echo "gpg --full-generate-key"
echo ""
echo "تنظیمات پیشنهادی:"
echo "- Key type: RSA and RSA"
echo "- Key size: 4096"
echo "- Validity: 1y"
echo "- Real name: Your Name"
echo "- Email: your@email.com"
echo "- Passphrase: strong password"

# لیست keyهای موجود
echo -e "\nKeyهای موجود:"
gpg --list-keys 2>/dev/null || echo "هیچ keyی وجود ندارد"
