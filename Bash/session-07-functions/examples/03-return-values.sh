#!/usr/bin/env bash

# تابع با return
check_number() {
    if [ $1 -gt 10 ]; then
        return 0  # موفق
    else
        return 1  # ناموفق
    fi
}

# استفاده از return
check_number 15
if [ $? -eq 0 ]; then
    echo "عدد بزرگتر از 10 است"
else
    echo "عدد کوچکتر یا مساوی 10 است"
fi

# تابع با echo برای برگرداندن مقدار
get_square() {
    local num=$1
    echo $((num * num))
}

# استفاده از echo
RESULT=$(get_square 5)
echo "مربع 5 برابر با $RESULT است"

RESULT=$(get_square 8)
echo "مربع 8 برابر با $RESULT است"
