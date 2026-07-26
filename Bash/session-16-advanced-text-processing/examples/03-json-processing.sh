#!/usr/bin/env bash

# ایجاد فایل JSON
cat > /tmp/data.json << EOF
{
    "users": [
        {"name": "علی", "age": 25, "city": "تهران"},
        {"name": "رضا", "age": 30, "city": "شیراز"},
        {"name": "محمد", "age": 22, "city": "اصفهان"}
    ],
    "count": 3
}
EOF

echo "محتوای JSON:"
cat /tmp/data.json

# نمایش زیبا
echo -e "\nنمایش زیبا:"
jq '.' /tmp/data.json

# استخراج آرایه users
echo -e "\nآرایه users:"
jq '.users' /tmp/data.json

# استخراج نام‌ها
echo -e "\nنام‌ها:"
jq '.users[].name' /tmp/data.json

# فیلتر بر اساس سن
echo -e "\nافراد بالای 25 سال:"
jq '.users[] | select(.age > 25)' /tmp/data.json

# پاکسازی
rm /tmp/data.json
