#!/usr/bin/env bash

echo "=== بهینه‌سازی پردازش ==="

# ❌ کند: loop با grep
echo "1. روش کند:"
time for file in /tmp/*; do
    grep "pattern" "$file"
done

# ✅ سریع: find با exec
echo -e "\n2. روش سریع:"
time find /tmp -type f -exec grep "pattern" {} +

# ❌ کند: sequential processing
echo -e "\n3. روش کند sequential:"
time for i in {1..10}; do
    sleep 1
done

# ✅ سریع: parallel
echo -e "\n4. روش سریع parallel:"
if command -v parallel &>/dev/null; then
    time seq 1 10 | parallel -j 4 sleep 1
else
    echo "parallel نصب نیست"
fi
