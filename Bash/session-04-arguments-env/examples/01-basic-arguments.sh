#!/usr/bin/env bash

echo "نام اسکریپت: $0"
echo "تعداد آرگومان‌ها: $#"
echo -e "\nآرگومان‌ها:"

# نمایش هر آرگومان به صورت جداگانه
for arg in "$@"; do
    echo "  - $arg"
done

# نمایش آرگومان‌های خاص
if [ $# -ge 1 ]; then
    echo -e "\nاولین آرگومان: $1"
fi

if [ $# -ge 2 ]; then
    echo "دومین آرگومان: $2"
fi

if [ $# -ge 3 ]; then
    echo "سومین آرگومان: $3"
fi
