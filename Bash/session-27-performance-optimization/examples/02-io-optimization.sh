#!/usr/bin/env bash

echo "=== بهینه‌سازی I/O ==="

# ❌ کند: write در هر iteration
echo "1. روش کند:"
time for i in {1..1000}; do
    echo $i >> /tmp/slow.txt
done
rm /tmp/slow.txt

# ✅ سریع: batch write
echo -e "\n2. روش سریع:"
time (
    for i in {1..1000}; do
        echo $i
    done
) > /tmp/fast.txt
rm /tmp/fast.txt

# ❌ کند: cat در loop
echo -e "\n3. روش کند:"
time for i in {1..100}; do
    cat /tmp/test.txt
done

# ✅ سریع: یک بار cat
echo -e "\n4. روش سریع:"
time cat /tmp/test.txt > /dev/null
