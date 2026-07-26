#!/usr/bin/env bash

FILE="/tmp/myfile.txt"

# نوشتن در فایل
echo "نوشتن در فایل..."
cat > "$FILE" << EOF
این خط اول است
این خط دوم است
این خط سوم است
EOF

echo "فایل ایجاد شد"

# خواندن خط به خط
echo -e "\nخواندن خط به خط:"
while IFS= read -r line; do
    echo "خط: $line"
done < "$FILE"

# خواندن به آرایه
echo -e "\nخواندن به آرایه:"
mapfile -t LINES < "$FILE"
for i in "${!LINES[@]}"; do
    echo "خط $((i+1)): ${LINES[$i]}"
done

# بررسی وجود فایل
if [ -f "$FILE" ]; then
    echo -e "\nفایل وجود دارد"
fi

# بررسی اندازه فایل
SIZE=$(wc -c < "$FILE")
echo "اندازه فایل: $SIZE بایت"

# پاکسازی
rm "$FILE"
echo "فایل حذف شد"
