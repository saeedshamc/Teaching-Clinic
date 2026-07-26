#!/usr/bin/env bash

# تمرین ۱: استخراج قیمت
# هدف: یک اسکریپت بنویسید که:
# 1. یک فایل HTML با قیمت‌ها ایجاد کند
# 2. قیمت‌ها را استخراج کند
# 3. مجموع را محاسبه کند

# راه‌حل:
# ایجاد فایل HTML نمونه
cat > /tmp/products.html << EOF
<html>
<body>
    <div class="product">
        <span class="price">15000</span>
    </div>
    <div class="product">
        <span class="price">8000</span>
    </div>
    <div class="product">
        <span class="price">5000</span>
    </div>
</body>
</html>
EOF

echo "فایل HTML:"
cat /tmp/products.html

# استخراج قیمت‌ها
echo -e "\nقیمت‌ها استخراج شده:"
PRICES=$(grep -o '<span class="price">.*</span>' /tmp/products.html | sed 's/<[^>]*>//g')

echo "$PRICES"

# محاسبه مجموع
SUM=0
for price in $PRICES; do
    SUM=$((SUM + price))
done

echo -e "\nمجموع قیمت‌ها: $SUM"

# پاکسازی
rm /tmp/products.html
