# جلسه ۲۷: بهینه‌سازی عملکرد (Performance Optimization)

## اهداف یادگیری

در این جلسه یاد می‌گیرید:
- مفاهیم پایه بهینه‌سازی
- بهینه‌سازی اسکریپت‌های Bash
- بهینه‌سازی I/O
- بهینه‌سازی پردازش
- بهینه‌سازی memory
- Profiling و Benchmarking
- مثال‌های عملی

## مفاهیم پایه بهینه‌سازی

بهینه‌سازی شامل موارد زیر است:
- **Time Complexity** - پیچیدگی زمانی
- **Space Complexity** - پیچیدگی فضایی
- **Bottlenecks** - نقاط گلوگاه
- **Profiling** - تحلیل عملکرد
- **Benchmarking** - مقایسه عملکرد

## بهینه‌سازی اسکریپت‌های Bash

```bash
# استفاده از builtin commands
# جلوگیری از subshells
# استفاده از آرایه‌ها
```

## مثال ۱: Bash Optimization

فایل [examples/01-bash-optimization.sh](examples/01-bash-optimization.sh) را بررسی کنید:

```bash
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
```

### توضیح خط به خط:

- `time` - اندازه‌گیری زمان
- subshell کندتر است
- builtin commands سریعتر
- جلوگیری از subshell‌های غیرضروری

## بهینه‌سازی I/O

I/O یکی از مهم‌ترین نقاط گلوگاه است.

```bash
# کاهش تعداد write
# استفاده از buffer
# batch operations
```

## مثال ۲: I/O Optimization

فایل [examples/02-io-optimization.sh](examples/02-io-optimization.sh) را بررسی کنید:

```bash
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
```

### توضیح خط به خط:

- `>>` در هر iteration کند است
- batch write با `()` سریعتر
- `cat` در loop بسیار کند
- یک بار cat سریعتر

## بهینه‌سازی پردازش

```bash
# استفاده از parallel processing
# کاهش loop iterations
# استفاده از ابزارهای بهینه
```

## مثال ۳: Processing Optimization

فایل [examples/03-processing-optimization.sh](examples/03-processing-optimization.sh) را بررسی کنید:

```bash
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
```

### توضیح خط به خط:

- `find -exec` سریعتر از loop
- `parallel` برای پردازش موازی
- `-j 4` - 4 job همزمان
- کاهش زمان با parallelism

## بهینه‌سازی Memory

```bash
# کاهش استفاده از memory
# استفاده از stream processing
# cleanup منظم
```

## مثال ۴: Memory Optimization

فایل [examples/04-memory-optimization.sh](examples/04-memory-optimization.sh) را بررسی کنید:

```bash
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
```

### توضیح خط به خط:

- `cat | while` - pipe ایجاد subshell
- `while < file` - بدون pipe
- آرایه‌های بزرگ memory زیاد مصرف می‌کنند
- جلوگیری از data structures بزرگ

## Profiling

## مثال ۵: Profiling Script

فایل [examples/05-profiling.sh](examples/05-profiling.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Profiling ==="

# تابع profiling
profile_function() {
    local func_name=$1
    shift
    local start=$(date +%s%N)
    "$@"
    local end=$(date +%s%N)
    local duration=$(( (end - start) / 1000000 ))
    echo "$func_name: ${duration}ms"
}

# توابع تست
test_slow() {
    sleep 1
}

test_fast() {
    echo "fast"
}

# profiling
profile_function "test_slow" test_slow
profile_function "test_fast" test_fast

# profiling با time
echo -e "\nProfiling با time:"
time {
    for i in {1..100}; do
        echo $i
    done
}
```

### توضیح خط به خط:

- `date +%s%N` - زمان با نانوثانیه
- محاسبه duration
- `time` builtin برای profiling
- اندازه‌گیری زمان توابع

## Benchmarking

## مثال ۶: Benchmarking

فایل [examples/06-benchmarking.sh](examples/06-benchmarking.sh) را بررسی کنید:

```bash
#!/usr/bin/env bash

echo "=== Benchmarking ==="

# تابع benchmark
benchmark() {
    local name=$1
    shift
    local iterations=100
    
    echo "Benchmark: $name"
    local start=$(date +%s%N)
    
    for i in $(seq 1 $iterations); do
        "$@"
    done
    
    local end=$(date +%s%N)
    local total=$(( (end - start) / 1000000 ))
    local avg=$(( total / iterations ))
    
    echo "Total: ${total}ms"
    echo "Average: ${avg}ms"
    echo ""
}

# مقایسه روش‌ها
benchmark "echo" echo "test"
benchmark "printf" printf "test\n"
benchmark "print" print -r test
```

### توضیح خط به خط:

- اجرای multiple iterations
- محاسبه average
- مقایسه روش‌های مختلف
- انتخاب بهترین روش

## نکات مهم

### ۱. Measure Before Optimize

```bash
# همیشه قبل از بهینه‌سازی، اندازه‌گیری کنید
# بدون measurement، بهینه‌سازی بی‌فایده است
```

### ۲. Focus on Bottlenecks

```bash
# روی نقاط گلوگاه تمرکز کنید
# 80/20 rule: 20% کد 80% زمان را مصرف می‌کند
```

### ۳. Don't Prematurely Optimize

```bash
# بهینه‌سازی زودهنگام خطرناک است
# ابتدا کد درست، سپس سریع
```

## مثال ۷: Optimization Best Practices

فایل [examples/07-optimization-best-practices.sh](examples/07-optimization-best-practices.sh) را بررسی کنید:

```bash
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
```

### توضیح خط به خط:

- builtin commands سریعتر
- جلوگیری از subshells
- کاهش I/O operations
- استفاده از ابزارهای بهینه
- parallel processing برای سرعت

## نکات رایج اشتباهات (Common Pitfalls)

### ۱. بهینه‌سازی زودهنگام

❌ اشتباه:
```bash
# بهینه‌سازی قبل از measurement
```

✅ درست:
```bash
# measurement، سپس بهینه‌سازی
```

### ۲. بهینه‌سازی بخش اشتباه

❌ اشتباه:
```bash
# بهینه‌سازی بخشی که bottleneck نیست
```

✅ درست:
```bash
# بهینه‌سازی نقاط گلوگاه
```

### ۳. خوانایی فدای سرعت

❌ اشتباه:
```bash
# کد غیرقابل خواندن برای سرعت
```

✅ درست:
```bash
# تعادل بین سرعت و خوانایی
```

## بهترین شیوه‌ها (Best Practices)

1. **Measure قبل از بهینه‌سازی** - بدون measurement بی‌فایده
2. **روی bottlenecks تمرکز کنید** - 80/20 rule
3. **از builtin commands استفاده کنید** - سریعتر
4. **I/O را کاهش دهید** - batch operations
5. **parallel processing** - برای CPU-bound tasks

## خلاصه جلسه

در این جلسه یاد گرفتیم:
- مفاهیم پایه بهینه‌سازی
- بهینه‌سازی اسکریپت‌های Bash
- بهینه‌سازی I/O
- بهینه‌سازی پردازش
- بهینه‌سازی memory
- Profiling و Benchmarking
- Best Practices
- نکات مهم و اشتباهات رایج

## تمرین‌ها

### تمرین ۱ (ساده): Script Optimization

یک اسکریپت بنویسید که:
1. نسخه کند و سریع مقایسه کند
2. زمان هر نسخه را اندازه‌گیری کند
3. بهینه‌سازی‌ها را توضیح دهد

فایل تمرین: [exercises/exercise-01.sh](exercises/exercise-01.sh)

### تمرین ۲ (متوسط): I/O Optimization

یک اسکریپت بنویسید که:
1. فایل‌های بزرگ را پردازش کند
2. I/O را بهینه کند
3. memory را مدیریت کند

فایل تمرین: [exercises/exercise-02.sh](exercises/exercise-02.sh)

## جلسه بعد

در جلسه بعد، DevOps Automation را یاد می‌گیریم.
