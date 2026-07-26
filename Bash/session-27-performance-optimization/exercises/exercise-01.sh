#!/usr/bin/env bash

# تمرین ۱: Script Optimization
# هدف: یک اسکریپت بنویسید که:
# 1. نسخه کند و سریع مقایسه کند
# 2. زمان هر نسخه را اندازه‌گیری کند
# 3. بهینه‌سازی‌ها را توضیح دهد

# راه‌حل:
echo "=== Script Optimization ==="

# ایجاد فایل تست
echo "test line" > /tmp/test_file.txt

# نسخه کند: استفاده از external command در loop
echo "1. نسخه کند (external command در loop):"
SLOW_START=$(date +%s%N)
for i in {1..100}; do
    wc -l /tmp/test_file.txt
done
SLOW_END=$(date +%s%N)
SLOW_TIME=$(( (SLOW_END - SLOW_START) / 1000000 ))
echo "زمان: ${SLOW_TIME}ms"

# نسخه سریع: استفاده از builtin
echo -e "\n2. نسخه سریع (builtin):"
FAST_START=$(date +%s%N)
COUNT=0
for i in {1..100}; do
    : $((COUNT++))
done
FAST_END=$(date +%s%N)
FAST_TIME=$(( (FAST_END - FAST_START) / 1000000 ))
echo "زمان: ${FAST_TIME}ms"

# نسخه کند: subshell
echo -e "\n3. نسخه کند (subshell):"
SLOW2_START=$(date +%s%N)
for i in {1..100}; do
    (echo $i)
done
SLOW2_END=$(date +%s%N)
SLOW2_TIME=$(( (SLOW2_END - SLOW2_START) / 1000000 ))
echo "زمان: ${SLOW2_TIME}ms"

# نسخه سریع: بدون subshell
echo -e "\n4. نسخه سریع (بدون subshell):"
FAST2_START=$(date +%s%N)
for i in {1..100}; do
    echo $i > /dev/null
done
FAST2_END=$(date +%s%N)
FAST2_TIME=$(( (FAST2_END - FAST2_START) / 1000000 ))
echo "زمان: ${FAST2_TIME}ms"

# توضیح بهینه‌سازی‌ها
echo -e "\n=== توضیح بهینه‌سازی‌ها ==="
echo "1. External command به جای builtin: $SLOW_TIME vs $FAST_TIME ms"
echo "2. Subshell به جای direct: $SLOW2_TIME vs $FAST2_TIME ms"
echo ""
echo "بهینه‌سازی‌ها:"
echo "- استفاده از builtin commands سریعتر است"
echo "- جلوگیری از subshell overhead"
echo "- کاهش I/O operations"
echo "- استفاده از arithmetic builtin به جای expr"

# پاکسازی
rm /tmp/test_file.txt
