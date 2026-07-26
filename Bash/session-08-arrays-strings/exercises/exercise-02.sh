#!/usr/bin/env bash

# تمرین ۲: پردازش متن
# هدف: یک اسکریپت بنویسید که:
# 1. یک متن طولانی از کاربر بگیرد
# 2. آن را به کلمات تبدیل کند (آرایه)
# 3. تعداد کلمات را چاپ کند
# 4. طولانی‌ترین کلمه را پیدا کند
# 5. کلمات تکراری را حذف کند

# راه‌حل:
read -p "لطفاً یک متن وارد کنید: " TEXT

# تبدیل متن به آرایه کلمات
IFS=' ' read -ra WORDS <<< "$TEXT"

# تعداد کلمات
WORD_COUNT=${#WORDS[@]}
echo "تعداد کلمات: $WORD_COUNT"

# طولانی‌ترین کلمه
LONGEST_WORD=""
LONGEST_LENGTH=0
for word in "${WORDS[@]}"; do
    WORD_LENGTH=${#word}
    if [ $WORD_LENGTH -gt $LONGEST_LENGTH ]; then
        LONGEST_LENGTH=$WORD_LENGTH
        LONGEST_WORD=$word
    fi
done
echo "طولانی‌ترین کلمه: $LONGEST_WORD ($LONGEST_LENGTH کاراکتر)"

# حذف کلمات تکراری
declare -A UNIQUE_WORDS
for word in "${WORDS[@]}"; do
    UNIQUE_WORDS["$word"]=1
done

echo -e "\nکلمات منحصر به فرد:"
for word in "${!UNIQUE_WORDS[@]}"; do
    echo "  - $word"
done

echo "تعداد کلمات منحصر به فرد: ${#UNIQUE_WORDS[@]}"
