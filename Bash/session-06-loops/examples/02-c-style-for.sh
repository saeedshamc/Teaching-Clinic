#!/usr/bin/env bash

# شمارش تا 10
echo "شمارش تا 10:"
for ((i=1; i<=10; i++)); do
    echo "عدد: $i"
done

# شمارش معکوس
echo -e "\nشمارش معکوس از 10 تا 1:"
for ((i=10; i>=1; i--)); do
    echo "عدد: $i"
done

# گام‌های بزرگتر
echo -e "\nاعداد زوج تا 20:"
for ((i=0; i<=20; i+=2)); do
    echo -n "$i "
done
echo
