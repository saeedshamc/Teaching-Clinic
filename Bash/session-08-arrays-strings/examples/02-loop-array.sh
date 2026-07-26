#!/usr/bin/env bash

# تعریف آرایه
NAMES=("علی" "رضا" "محمد" "حسین" "مهدی")

echo "نام‌ها:"
for name in "${NAMES[@]}"; do
    echo "  - $name"
done

# شمارش معکوس
echo -e "\nشمارش معکوس:"
for ((i=${#NAMES[@]}-1; i>=0; i--)); do
    echo "${NAMES[$i]}"
done
