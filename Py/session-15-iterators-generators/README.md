# جلسه ۱۵: Iterator و Generator

## اهداف یادگیری (Learning Objectives)

پس از این جلسه می‌توانید:
- تفاوت iterator و iterable را بدانید
- یک generator ساده بسازید
- از yield برای تولید داده تدریجی استفاده کنید

---

## iterator چیست؟

Iterator شی‌ای است که می‌تواند عناصر را یکی‌یکی بازگرداند.

```python
numbers = [1, 2, 3]
iterator = iter(numbers)
print(next(iterator))
```

## generator چیست؟

Generator تابعی است که با `yield` مقادیر را یکی‌یکی تولید می‌کند.

```python
def count_up(limit):
    current = 1
    while current <= limit:
        yield current
        current += 1

for value in count_up(3):
    print(value)
```

---

## فایل‌های این جلسه
- [examples/01_generator.py](./examples/01_generator.py)
- [exercises/exercise_01.py](./exercises/exercise_01.py)

## تمرین‌ها
- تمرین ساده: یک generator برای اعداد زوج بسازید
- تمرین متوسط: یک generator برای کاراکترهای یک رشته بسازید
