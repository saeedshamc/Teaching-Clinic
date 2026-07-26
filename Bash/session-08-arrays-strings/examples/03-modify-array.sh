#!/usr/bin/env bash

# تعریف آرایه
NUMBERS=(1 2 3 4 5)

echo "آرایه اولیه: ${NUMBERS[@]}"

# افزودن به انتهای آرایه
NUMBERS+=(6 7 8)
echo "بعد از افزودن: ${NUMBERS[@]}"

# تغییر یک عنصر
NUMBERS[0]=10
echo "بعد از تغییر: ${NUMBERS[@]}"

# حذف یک عنصر
unset NUMBERS[2]
echo "بعد از حذف ایندکس 2: ${NUMBERS[@]}"

# ایجاد آرایه جدید بدون عنصر خالی
NEW_ARRAY=()
for num in "${NUMBERS[@]}"; do
    if [ -n "$num" ]; then
        NEW_ARRAY+=("$num")
    fi
done
echo "آرایه تمیز: ${NEW_ARRAY[@]}"
