# جلسه ۴۴: اندازه‌گیری عملکرد با timeit (۱.۵ ساعت)

## 🎯 اهداف یادگیری

پس از این جلسه می‌توانید:
- تفاوت micro-benchmark و profiling واقعی را بدانید
- با `timeit` زمان اجرا را اندازه بگیرید
- دو پیاده‌سازی را علمی مقایسه کنید
- `number` و `repeat` را تنظیم کنید
- از `time.perf_counter` برای timing دستی استفاده کنید
- اشتباهات benchmark (warmup، GC) را بشناسید

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع | فعالیت |
|------|--------|--------|
| 0–10 | چرا performance؟ | premature optimization |
| 10–30 | timeit پایه | timeit.timeit |
| 30–45 | مقایسه | list vs generator |
| 45–60 | repeat و statistics | min، mean |
| 60–75 | perf_counter | timing دستی |
| 75–85 | profiling (مرور) | cProfile |
| 85–90 | جمع‌بندی + تکلیف | question.md |

---

## ۱. «Premature optimization is the root of all evil»

اول **درست** بنویسید، بعد **سریع** — فقط جایی که bottleneck واقعی است.

ابزارها:
- **timeit** — micro-benchmark
- **cProfile** — کجا وقت می‌رود
- **memory_profiler** — حافظه

---

## ۲. timeit.timeit

```python
import timeit

elapsed = timeit.timeit(
    "sum([i*i for i in range(1000)])",
    number=1000,  # 1000 بار تکرار
)
print(elapsed)
```

**number:** تعداد تکرار — بزرگ‌تر = دقت بیشتر، زمان بیشتر.

---

## ۳. setup جدا

```python
setup = "data = list(range(10000))"
stmt = "sum(data)"
timeit.timeit(stmt, setup=setup, number=500)
```

setup یک‌بار اجرا می‌شود — stmt بارها.

---

## ۴. مقایسه list comprehension vs generator

```python
list_stmt = "sum([i*i for i in range(10000)])"
gen_stmt = "sum(i*i for i in range(10000))"
```

Generator معمولاً حافظه کمتر — سرعت بسته به case.

---

## ۵. timeit.repeat

```python
times = timeit.repeat(stmt, number=1000, repeat=5)
print(min(times))  # بهترین run
```

`repeat` چند بار کل benchmark را اجرا می‌کند — outlier کمتر.

---

## ۶. perf_counter برای timing دستی

```python
import time

start = time.perf_counter()
result = heavy_function()
elapsed = time.perf_counter() - start
print(f"{elapsed:.4f}s")
```

`perf_counter` برای interval — بهتر از `time.time()`.

---

## ۷. Big-O مرور

| الگوریتم | پیچیدگی |
|----------|---------|
| جستجوی خطی | O(n) |
| dict lookup | O(1) avg |
| sort | O(n log n) |
| nested loop | O(n²) |

benchmark عدد واقعی می‌دهد — Big-O روند را.

---

## ۸. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_timeit_basics.py](./examples/01_timeit_basics.py) | timeit ساده |
| [02_list_vs_generator.py](./examples/02_list_vs_generator.py) | مقایسه |
| [03_repeat_stats.py](./examples/03_repeat_stats.py) | repeat و min |
| [04_perf_counter.py](./examples/04_perf_counter.py) | timing دستی |

---

## ۹. cProfile (مرور)

```python
import cProfile
cProfile.run("main()", sort="cumulative")
```

نشان می‌دهد کدام تابع بیشترین cumulative time را دارد.

---

## ۱۰. اشتباهات benchmark

| اشتباه | اثر |
|--------|-----|
| number خیلی کوچک | noise |
| I/O در loop | نتیجه بی‌معنی |
| cache effect | run اول ≠ run دوم |
| debug mode | کندتر از -O |
| مقایسه در interactive | setup متفاوت |

---

## ۱۱. when to optimize

1. Measure (profile)
2. Find bottleneck
3. Optimize that part
4. Measure again

---

## ۱۲. اجرای مثال‌ها

```bash
python session-44-performance-timeit/examples/01_timeit_basics.py
python session-44-performance-timeit/examples/02_list_vs_generator.py
python session-44-performance-timeit/examples/03_repeat_stats.py
python session-44-performance-timeit/examples/04_perf_counter.py
```

---

## ۱۳. string concatenation

```python
# کند برای n بزرگ
s = ""
for x in items:
    s += x

# سریع‌تر
"".join(items)
```

timeit تفاوت را quantified می‌کند.

---

## ۱۴. تمرین کلاسی

1. مقایسه `in list` vs `in set`
2. repeat برای دو algorithm sort
3. perf_counter برای fibonacci

---

## ۱۵. تکلیف

**تکلیف:** [question.md](./exercises/question.md)

پاسخ: [exercise_01.py](./exercises/exercise_01.py) | [exercise_02.py](./exercises/exercise_02.py)

---

## ۱۶. خلاصه

```
timeit.timeit(stmt, number=N)
timeit.repeat(..., repeat=R)
min(times)           → بهترین run
perf_counter()       → interval timing
measure first        → optimize second
cProfile             → bottleneck finder
```

---

**جلسه قبل:** [۴۳ — Debugging](../session-43-debugging-pdb/) | **بعد:** [۴۵ — Design Patterns](../session-45-design-patterns/)

---

## ۱۷. timeit.default_timer

```python
import timeit
print(timeit.default_timer)  # perf_counter on most platforms
```

---

## ۱۸. memory profiling (اشاره)

```bash
pip install memory_profiler
python -m memory_profiler script.py
```

---

## ۱۹. algorithm choice

| task | structure |
|------|-----------|
| lookup | set/dict |
| sorted iteration | sorted() once |
| frequent insert | deque |

---

## ۲۱. profiling در production

هرگز cProfile در production بدون sampling — overhead بالا.  
ابزار: py-spy (sampling profiler).

---

## ۲۲. Big-O vs constant factor

گاهی O(n²) با n کوچک از O(n) با overhead زیاد سریع‌تر است — **measure**.

---

## ۲۳. cache و memoization

```python
from functools import lru_cache

@lru_cache(maxsize=128)
def fib(n): ...
```

---

## ۲۵. practical benchmark template

```python
import timeit

def bench(label, stmt, setup="pass", number=1000, repeat=5):
    times = timeit.repeat(stmt, setup=setup, number=number, repeat=repeat)
    best = min(times)
    print(f"{label:30} {best:.6f}s (best of {repeat})")

bench("list comp", "sum([i*i for i in range(1000)])")
bench("generator", "sum(i*i for i in range(1000))")
```

این template را در تمرین‌ها و پروژه‌های خود replicate کنید.

---

## ۲۶. جمع‌بندی نهایی

benchmark بدون هدف = اتلاف وقت.  
هدف: تصمیم informed — نه micro-optimization برای vanity.

**منابع:** [timeit docs](https://docs.python.org/3/library/timeit.html) | [cProfile](https://docs.python.org/3/library/profile.html)

جلسه بعد: الگوهای طراحی — وقتی performance کافی است، architecture مهم‌تر می‌شود.
