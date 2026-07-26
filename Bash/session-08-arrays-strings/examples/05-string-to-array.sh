#!/usr/bin/env bash

# تبدیل رشته به آرایه با delimiter
TEXT="apple,banana,orange,grape"

# تبدیل با IFS
IFS=',' read -ra FRUITS <<< "$TEXT"

echo "آرایه میوه‌ها:"
for fruit in "${FRUITS[@]}"; do
    echo "  - $fruit"
done

# تبدیل با default delimiter (space)
WORDS=("Hello World from Bash")

echo -e "\nکلمات:"
for word in $WORDS; do
    echo "  - $word"
done
