#!/usr/bin/env bash

echo "=== بهینه‌سازی Bash ==="

# ❌ کند: استفاده از external command
echo "1. روش کند:"
time for i in {1..1000}; do
    echo $i
done | wc -l

# ✅ سریع: استفاده از builtin
echo -e "\n2. روش سریع:"
time for i in {1..1000}; do
    : $((count++))
done
echo $count

# ❌ کند: subshell
echo -e "\n3. روش کند با subshell:"
time for i in {1..100}; do
    (echo $i)
done

# ✅ سریع: بدون subshell
echo -e "\n4. روش سریع بدون subshell:"
time for i in {1..100}; do
    echo $i
done
