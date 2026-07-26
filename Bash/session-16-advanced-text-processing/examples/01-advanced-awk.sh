#!/usr/bin/env bash

# ایجاد فایل نمونه
cat > /tmp/data.txt << EOF
علی,25,تهران
رضا,30,شیراز
محمد,22,اصفهان
حسین,28,مشهد
مهدی,35,تهران
EOF

echo "محتوای فایل:"
cat /tmp/data.txt

# محاسبه آمار
echo -e "\nآمار سن‌ها:"
awk -F',' '
BEGIN {
    sum = 0
    count = 0
    min = 999
    max = 0
}
{
    age = $2
    sum += age
    count++
    if (age < min) min = age
    if (age > max) max = age
}
END {
    avg = sum / count
    print "تعداد: " count
    print "مجموع: " sum
    print "میانگین: " avg
    print "کمترین: " min
    print "بیشترین: " max
}
' /tmp/data.txt

# پاکسازی
rm /tmp/data.txt
