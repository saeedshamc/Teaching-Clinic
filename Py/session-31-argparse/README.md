# جلسه ۳۱: آرگومان‌های خط فرمان (argparse)

## اهداف یادگیری (Learning Objectives)

پس از این جلسه می‌توانید:
- با argparse آرگومان بسازید
- برنامه CLI قابل‌استفاده بنویسید
- کمک‌متن (--help) خودکار داشته باشید

---

## argparse چیست؟

argparse برای خواندن آرگومان‌های خط فرمان استفاده می‌شود تا برنامه بدون تغییر کد، ورودی مختلف بگیرد.

```python
import argparse

parser = argparse.ArgumentParser(description="ماشین‌حساب ساده")
parser.add_argument("x", type=int, help="عدد اول")
parser.add_argument("y", type=int, help="عدد دوم")
args = parser.parse_args(["3", "4"])
print(args.x + args.y)
```

### توضیح
- `ArgumentParser` → پارسر خط فرمان می‌سازد
- `add_argument` → آرگومان جدید تعریف می‌کند
- `parse_args` → مقادیر را می‌خواند

---

## فایل‌های این جلسه
- [examples/01_argparse.py](./examples/01_argparse.py)
- [exercises/exercise_01.py](./exercises/exercise_01.py)

## تمرین‌ها
- تمرین ساده: یک آرگومان عددی تعریف کنید
- تمرین متوسط: با --name خوش‌آمد بگویید
