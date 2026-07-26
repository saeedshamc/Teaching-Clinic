#!/usr/bin/env bash

# حلقه روی اعداد
echo "شمارش تا 5:"
for i in 1 2 3 4 5; do
    echo "عدد: $i"
done

# حلقه روی رشته‌ها
echo -e "\nرنگ‌ها:"
for color in قرمز آبی سبز زرد; do
    echo "رنگ: $color"
done

# حلقه با seq
echo -e "\nشمارش تا 10 با seq:"
for i in $(seq 1 10); do
    echo -n "$i "
done
echo
