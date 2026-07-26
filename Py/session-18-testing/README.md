# جلسه ۱۸: تست‌نویسی

## اهداف یادگیری (Learning Objectives)

پس از این جلسه می‌توانید:
- اهمیت تست نرم‌افزار را درک کنید
- با ماژول `unittest` توابع را تست کنید
- تست‌های موفق و ناموفق را تشخیص دهید
- عادت تست‌نویسی را در پروژه‌های کوچک پیاده کنید

---

## تست چیست؟

تست یعنی بررسی خودکار اینکه کد شما درست کار می‌کند یا نه. به‌جای اجرای دستی هر بار، یک اسکریپت تست می‌نویسید که انتظار شما را با نتیجه واقعی مقایسه کند.

```python
def add(a, b):
    return a + b


assert add(2, 3) == 5
print("تست موفق بود")
```

### توضیح
- `assert` → اگر شرط درست نباشد، خطا می‌دهد
- اگر شرط درست باشد، برنامه ادامه پیدا می‌کند

---

## تست با unittest

```python
import unittest

def multiply(a, b):
    return a * b


class TestMath(unittest.TestCase):
    def test_multiply(self):
        self.assertEqual(multiply(3, 4), 12)


if __name__ == "__main__":
    unittest.main()
```

### توضیح
- `unittest.TestCase` → کلاس پایه برای نوشتن تست
- `assertEqual` → دو مقدار را با هم مقایسه می‌کند
- `unittest.main()` → همه تست‌ها را اجرا می‌کند

---

## فایل‌های این جلسه
- [examples/01_unittest_demo.py](./examples/01_unittest_demo.py)
- [exercises/exercise_01.py](./exercises/exercise_01.py)

## تمرین‌ها
- تمرین ساده: برای تابع `subtract` یک تست بنویسید
- تمرین متوسط: دو تست جداگانه برای یک تابع بنویسید
