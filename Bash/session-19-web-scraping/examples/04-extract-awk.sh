#!/usr/bin/env bash

# ایجاد فایل HTML نمونه
cat > /tmp/test.html << EOF
<div class="product">
    <span class="name">لپتاپ</span>
    <span class="price">15000</span>
</div>
<div class="product">
    <span class="name">موبایل</span>
    <span class="price">8000</span>
</div>
EOF

echo "فایل HTML:"
cat /tmp/test.html

# استخراج نام‌ها
echo -e "\nنام محصولات:"
grep -o '<span class="name">.*</span>' /tmp/test.html | sed 's/<[^>]*>//g'

# استخراج قیمت‌ها
echo -e "\nقیمت‌ها:"
grep -o '<span class="price">.*</span>' /tmp/test.html | sed 's/<[^>]*>//g'

# پاکسازی
rm /tmp/test.html
