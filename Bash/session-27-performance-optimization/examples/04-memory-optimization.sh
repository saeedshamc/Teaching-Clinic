#!/usr/bin/env bash

echo "=== بهینه‌سازی Memory ==="

# ❌ بد: خواندن کل فایل در memory
echo "1. روش بد:"
time cat /tmp/large.txt | while read line; do
    echo "$line"
done

# ✅ خوب: stream processing
echo -e "\n2. روش خوب:"
time while read line; do
    echo "$line"
done < /tmp/large.txt

# ❌ بد: آرایه بزرگ
echo -e "\n3. روش بد:"
time for i in {1..100000}; do
    array[$i]=$i
done

# ✅ خوب: بدون آرایه
echo -e "\n4. روش خوب:"
time for i in {1..100000}; do
    : $i
done
