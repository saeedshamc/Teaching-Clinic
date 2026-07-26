# جلسه ۱۳: Comprehensionها

## اهداف یادگیری (Learning Objectives)

پس از این جلسه می‌توانید:
- با list comprehension و dict comprehension آشنا شوید
- کدهای کوتاه‌تر و خواناتر بنویسید
- از ساختارهای جمعی در Python بهره ببرید

---

## مفهوم اصلی

Comprehension یک روش concise برای ساختن ساختارهای داده است.

```python
numbers = [1, 2, 3, 4]
squares = [n * n for n in numbers]
print(squares)
```

### توضیح
- `for n in numbers` → روی هر عنصر حرکت می‌کند
- `n * n` → مقدار جدید را می‌سازد

---

## فایل‌های این جلسه
- [examples/01_list_comprehension.py](./examples/01_list_comprehension.py)
- [exercises/exercise_01.py](./exercises/exercise_01.py)

## تمرین‌ها
- تمرین ساده: یک list comprehension برای اعداد زوج بسازید
- تمرین متوسط: یک dict comprehension برای تبدیل نام به طول نام بسازید
