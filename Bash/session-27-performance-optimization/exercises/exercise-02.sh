#!/usr/bin/env bash

# تمرین ۲: I/O Optimization
# هدف: یک اسکریپت بنویسید که:
# 1. فایل‌های بزرگ را پردازش کند
# 2. I/O را بهینه کند
# 3. memory را مدیریت کند

# راه‌حل:
echo "=== I/O Optimization ==="

# ایجاد فایل بزرگ
echo "ایجاد فایل تست..."
for i in {1..1000}; do
    echo "Line $i: This is test data for performance optimization"
done > /tmp/large_file.txt

# نسخه کند: write در هر iteration
echo "1. نسخه کند (write در هر iteration):"
SLOW_START=$(date +%s%N)
for i in {1..100}; do
    echo "Data $i" >> /tmp/slow_output.txt
done
SLOW_END=$(date +%s%N)
SLOW_TIME=$(( (SLOW_END - SLOW_START) / 1000000 ))
echo "زمان: ${SLOW_TIME}ms"
rm /tmp/slow_output.txt

# نسخه سریع: batch write
echo -e "\n2. نسخه سریع (batch write):"
FAST_START=$(date +%s%N)
(
    for i in {1..100}; do
        echo "Data $i"
    done
) > /tmp/fast_output.txt
FAST_END=$(date +%s%N)
FAST_TIME=$(( (FAST_END - FAST_START) / 1000000 ))
echo "زمان: ${FAST_TIME}ms"
rm /tmp/fast_output.txt

# نسخه کند: خواندن کل فایل در memory
echo -e "\n3. نسخه کند (خواندن کل فایل):"
SLOW2_START=$(date +%s%N)
CONTENT=$(cat /tmp/large_file.txt)
echo "$CONTENT" | wc -l
SLOW2_END=$(date +%s%N)
SLOW2_TIME=$(( (SLOW2_END - SLOW2_START) / 1000000 ))
echo "زمان: ${SLOW2_TIME}ms"

# نسخه سریع: stream processing
echo -e "\n4. نسخه سریع (stream processing):"
FAST2_START=$(date +%s%N)
while read line; do
    : "$line"
done < /tmp/large_file.txt
FAST2_END=$(date +%s%N)
FAST2_TIME=$(( (FAST2_END - FAST2_START) / 1000000 ))
echo "زمان: ${FAST2_TIME}ms"

# مقایسه و توضیح
echo -e "\n=== مقایسه ==="
echo "Write optimization: $SLOW_TIME vs $FAST_TIME ms"
echo "Read optimization: $SLOW2_TIME vs $FAST2_TIME ms"
echo ""
echo "بهینه‌سازی‌ها:"
echo "- Batch write به جای write در هر iteration"
echo "- Stream processing به جای خواندن کل فایل"
echo "- کاهش تعداد I/O operations"
echo "- استفاده از buffer برای write"

# پاکسازی
rm /tmp/large_file.txt
