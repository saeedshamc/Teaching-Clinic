#!/usr/bin/env bash

# جدول ضرب
echo "جدول ضرب 5x5:"
for ((i=1; i<=5; i++)); do
    for ((j=1; j<=5; j++)); do
        PRODUCT=$((i * j))
        printf "%4d" $PRODUCT
    done
    echo
done

# ترکیب رنگ‌ها
echo -e "\nترکیب رنگ‌ها:"
COLORS=("قرمز" "آبی" "سبز")
SHAPES=("دایره" "مربع" "مثلث")

for color in "${COLORS[@]}"; do
    for shape in "${SHAPES[@]}"; do
        echo "$color $shape"
    done
done
