# جلسه ۴۸: مستندسازی و Docstring

## اهداف یادگیری (Learning Objectives)

پس از این جلسه می‌توانید:
- docstring استاندارد بنویسید
- با help مستند را بخوانید
- کد را برای دیگران قابل‌فهم کنید

---

## Docstring چیست؟

Docstring توضیح داخل‌کدی برای ماژول، کلاس و تابع است که ابزارها و complementها می‌توانند آن را نمایش دهند.

```python
def add(a: int, b: int) -> int:
    """دو عدد را با هم جمع می‌کند.

    Args:
        a: عدد اول
        b: عدد دوم
    Returns:
        حاصل جمع
    """
    return a + b

print(add.__doc__)
```

### توضیح
- اولین رشته بعد از تعریف تابع = docstring
- هدف، ورودی و خروجی را بنویسید
- مستند کوتاه بهتر از مستند نیست

---

## فایل‌های این جلسه
- [examples/01_docstrings.py](./examples/01_docstrings.py)
- [exercises/exercise_01.py](./exercises/exercise_01.py)

## تمرین‌ها
- تمرین ساده: یک docstring یک‌خطی بنویسید
- تمرین متوسط: Args و Returns را کامل کنید
