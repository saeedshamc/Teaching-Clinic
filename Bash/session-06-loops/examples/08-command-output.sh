#!/usr/bin/env bash

# حلقه روی خروجی ls
echo "فایل‌ها در دایرکتوری جاری:"
for file in $(ls); do
    echo "فایل: $file"
done

# حلقه روی خطوط یک فایل
echo -e "\nخواندن خطوط فایل:"
echo -e "خط 1\nخط 2\nخط 3" > /tmp/test_file.txt

while IFS= read -r line; do
    echo "خط: $line"
done < /tmp/test_file.txt

rm /tmp/test_file.txt
