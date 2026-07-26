# جلسه ۲۳: عبارات منظم (Regex)

## اهداف یادگیری (Learning Objectives)

پس از این جلسه می‌توانید:
- با ماژول re آشنا شوید
- الگوهای ساده را جستجو و استخراج کنید
- اعتبارسنجی اولیه متن انجام دهید

---

## Regex چیست؟

عبارت منظم (Regular Expression) الگویی برای پیدا کردن یا بررسی متن است؛ مثلاً تشخیص ایمیل یا عدد.

```python
import re

text = "ایمیل من: saeed@example.com"
match = re.search(r"[\w.-]+@[\w.-]+", text)
if match:
    print(match.group())
```

### توضیح
- `re.search` → اولین تطبیق را پیدا می‌کند
- `r"..."` → raw string برای الگوی regex
- `group()` → متن پیدا‌شده را برمی‌گرداند

---

## findall و sub

```python
import re

numbers = re.findall(r"\d+", "سال ۱۴۰۳ و نسخه ۳.۱۲")
cleaned = re.sub(r"\s+", " ", "سلام   دنیا")
print(numbers)
print(cleaned)
```

---

## فایل‌های این جلسه
- [examples/01_regex.py](./examples/01_regex.py)
- [exercises/exercise_01.py](./exercises/exercise_01.py)

## تمرین‌ها
- تمرین ساده: یک عدد را در متن پیدا کنید
- تمرین متوسط: شماره موبایل‌ها را با findall استخراج کنید
