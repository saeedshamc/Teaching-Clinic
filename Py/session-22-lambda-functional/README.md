# جلسه ۲۲: Lambda و توابع تابعی

## اهداف یادگیری (Learning Objectives)

پس از این جلسه می‌توانید:
- با تابع ناشناس lambda آشنا شوید
- از map و filter استفاده کنید
- کدهای کوتاه‌تر برای تبدیل داده بنویسید

---

## lambda چیست؟

lambda یک تابع کوتاه و بدون نام است که معمولاً برای عملیات ساده یک‌خطی استفاده می‌شود.

```python
square = lambda x: x * x
print(square(5))

numbers = [1, 2, 3, 4, 5]
doubled = list(map(lambda n: n * 2, numbers))
evens = list(filter(lambda n: n % 2 == 0, numbers))
print(doubled)
print(evens)
```

### توضیح
- `lambda x: ...` → تابع یک‌خطی می‌سازد
- `map` → تابع را روی هر عنصر اعمال می‌کند
- `filter` → فقط عناصر مطابق شرط را نگه می‌دارد

---

## فایل‌های این جلسه
- [examples/01_lambda_map_filter.py](./examples/01_lambda_map_filter.py)
- [exercises/exercise_01.py](./exercises/exercise_01.py)

## تمرین‌ها
- تمرین ساده: یک lambda برای جمع دو عدد بنویسید
- تمرین متوسط: با filter نام‌های بلند را جدا کنید
