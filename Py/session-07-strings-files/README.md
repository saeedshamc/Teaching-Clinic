# جلسه ۷: رشته‌ها و کار با فایل‌ها

## اهداف یادگیری (Learning Objectives)

پس از این جلسه می‌توانید:
- با رشته‌ها (Strings) کار کنید
- متدهای مهم رشته را بشناسید
- فایل‌ها را بخوانید و بنویسید

---

## رشته‌ها چیست؟

رشته یک متن است. در Python متن بین گیومه قرار می‌گیرد.

```python
text = "پایتون"
print(text.upper())
```

### متدهای پرکاربرد
- `upper()` → بزرگ کردن حروف
- `lower()` → کوچک کردن حروف
- `replace()` → جایگزینی متن
- `split()` → جدا کردن متن

---

## کار با فایل‌ها

```python
with open("sample.txt", "w", encoding="utf-8") as file:
    file.write("سلام از فایل")
```

برای خواندن:

```python
with open("sample.txt", "r", encoding="utf-8") as file:
    content = file.read()
    print(content)
```

---

## خلاصه جلسه

در این جلسه یاد گرفتید:
- رشته‌ها چیست
- با فایل‌ها می‌توان نوشت و خواند

---

## فایل‌های این جلسه
- [examples/01_strings.py](./examples/01_strings.py)
- [examples/02_files.py](./examples/02_files.py)
- [exercises/exercise_01.py](./exercises/exercise_01.py)

## تمرین‌ها

### تمرین ساده
یک متن را به حروف بزرگ تبدیل کنید.

### تمرین متوسط
یک فایل ایجاد کنید و متن داخل آن را بخوانید.

---

[جلسه بعدی: مدیریت خطا](../session-08-error-handling/)
