#!/usr/bin/env bash

echo "=== JSON Processing با jq ==="

# بررسی نصب jq
if ! command -v jq &>/dev/null; then
    echo "jq نصب نیست"
    echo "نصب با: sudo apt install jq"
    exit 1
fi

# دریافت و پردازش JSON
echo "1. دریافت و پردازش JSON:"
curl -s https://jsonplaceholder.typicode.com/posts/1 | jq '.'

# استخراج فیلد خاص
echo -e "\n2. استخراج فیلد خاص:"
curl -s https://jsonplaceholder.typicode.com/posts/1 | jq '.title'

# استخراج چند فیلد
echo -e "\n3. استخراج چند فیلد:"
curl -s https://jsonplaceholder.typicode.com/posts/1 | jq '{title, userId}'

# فیلتر کردن
echo -e "\n4. فیلتر کردن:"
curl -s https://jsonplaceholder.typicode.com/posts | jq '.[] | select(.userId == 1)'

# تبدیل به CSV
echo -e "\n5. تبدیل به CSV:"
curl -s https://jsonplaceholder.typicode.com/posts | jq -r '.[] | [.id, .title] | @csv'
