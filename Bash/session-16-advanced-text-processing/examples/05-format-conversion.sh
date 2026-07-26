#!/usr/bin/env bash

# ایجاد فایل CSV
cat > /tmp/input.csv << EOF
نام,سن,شهر
علی,25,تهران
رضا,30,شیراز
محمد,22,اصفهان
EOF

echo "فایل CSV:"
cat /tmp/input.csv

# تبدیل به JSON
echo -e "\nتبدیل به JSON:"
echo "[" > /tmp/output.json
awk -F',' 'NR>1 {
    printf "  {\"name\": \"%s\", \"age\": %s, \"city\": \"%s\"}%s\n", $1, $2, $3, (NR==2?"":",")
}' /tmp/input.csv >> /tmp/output.json
echo "]" >> /tmp/output.json

cat /tmp/output.json

# پاکسازی
rm /tmp/input.csv /tmp/output.json
