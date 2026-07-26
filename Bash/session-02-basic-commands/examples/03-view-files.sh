#!/usr/bin/env bash

# ایجاد یک فایل نمونه
echo "ایجاد فایل نمونه..."
cat > /tmp/demo_file.txt << EOF
این خط اول است
این خط دوم است
این خط سوم است
این خط چهارم است
این خط پنجم است
این خط ششم است
این خط هفتم است
این خط هشتم است
این خط نهم است
این خط دهم است
EOF

# نمایش کل فایل
echo "محتوای کامل فایل:"
cat /tmp/demo_file.txt

# نمایش ۳ خط اول
echo -e "\n۳ خط اول:"
head -n 3 /tmp/demo_file.txt

# نمایش ۳ خط آخر
echo -e "\n۳ خط آخر:"
tail -n 3 /tmp/demo_file.txt

# نمایش با شماره خط
echo -e "\nبا شماره خط:"
cat -n /tmp/demo_file.txt

# اطلاعات فایل
echo -e "\nاطلاعات فایل:"
file /tmp/demo_file.txt

# حذف فایل نمونه
rm /tmp/demo_file.txt
