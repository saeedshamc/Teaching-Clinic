#!/usr/bin/env bash

echo "=== Best Practices ==="

# 1. استفاده از builtin commands
echo -e "\n1. Builtin Commands:"
echo "✓ استفاده از [[ ]] به جای [ ]"
echo "✓ استفاده از $(( )) به جای expr"
echo "✓ استفاده از printf به جای echo"

# 2. جلوگیری از subshells
echo -e "\n2. جلوگیری از Subshells:"
echo "✓ استفاده از process substitution"
echo "✓ استفاده از here documents"
echo "✓ جلوگیری از pipe در loop"

# 3. کاهش I/O
echo -e "\n3. کاهش I/O:"
echo "✓ batch write"
echo "✓ کاهش تعداد write"
echo "✓ استفاده از buffer"

# 4. استفاده از ابزارهای بهینه
echo -e "\n4. ابزارهای بهینه:"
echo "✓ awk برای text processing"
echo "✓ sed برای replacement"
echo "✓ find برای file operations"

# 5. Parallel processing
echo -e "\n5. Parallel Processing:"
echo "✓ استفاده از xargs -P"
echo "✓ استفاده از parallel"
echo "✓ استفاده از background jobs"
