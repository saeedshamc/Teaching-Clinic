#!/usr/bin/env bash

echo "آرگومان‌های داده شده: $@"
echo -e "\n--- استفاده از \$@ ---"
for arg in "$@"; do
    echo "آرگومان: $arg"
done

echo -e "\n--- استفاده از \$* ---"
for arg in "$*"; do
    echo "آرگومان: $arg"
done
