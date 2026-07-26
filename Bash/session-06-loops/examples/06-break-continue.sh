#!/usr/bin/env bash

echo "استفاده از break:"
for i in {1..10}; do
    if [ $i -eq 5 ]; then
        echo "رسیدیم به 5، خروج از حلقه"
        break
    fi
    echo "عدد: $i"
done

echo -e "\nاستفاده از continue:"
for i in {1..10}; do
    if [ $((i % 2)) -eq 0 ]; then
        continue  # اعداد زوج را نادیده می‌گیرد
    fi
    echo "عدد فرد: $i"
done
