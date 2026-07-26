#!/usr/bin/env bash

# تابع با تعداد آرگومان متغیر
print_all() {
    echo "آرگومان‌ها:"
    for arg in "$@"; do
        echo "  - $arg"
    done
}

print_all "علی" "رضا" "محمد" "حسین"

# تابع با مقدار پیش‌فرض
greet_with_default() {
    local name=${1:-"کاربر"}
    echo "سلام، $name!"
}

greet_with_default "علی"
greet_with_default  # از مقدار پیش‌فرض استفاده می‌کند

# تابع برای محاسبه فاکتوریل
factorial() {
    local n=$1
    local result=1
    
    for ((i=1; i<=n; i++)); do
        result=$((result * i))
    done
    
    echo $result
}

echo -e "\nفاکتوریل 5: $(factorial 5)"
echo "فاکتوریل 7: $(factorial 7)"
