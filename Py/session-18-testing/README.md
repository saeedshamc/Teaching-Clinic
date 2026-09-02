# جلسه ۱۸: تست‌نویسی (۱.۵ ساعت)

## ۰. مرور

در جلسه ۸ exception handling را دیدید. تست‌ها همان assert را **سازمان‌یافته** و **قابل تکرار** می‌کنند — پایه pytest (جلسه ۴۱).

---

## 🎯 اهداف یادگیری

پس از این جلسه می‌توانید:
- اهمیت تست خودکار را توضیح دهید
- با `assert` تست ساده بنویسید
- با ماژول `unittest` تست ساختاریافته بنویسید
- متدهای `assertEqual`، `assertRaises`، `assertTrue` را به‌کار ببرید
- fixture با `setUp` و `tearDown` بشناسید
- تست edge case و ناموفق را طراحی کنید
- عادت تست‌نویسی را در پروژه‌های کوچک پیاده کنید

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع | فعالیت |
|------|--------|--------|
| 0–10 | مرور | چرا تست؟ — regression |
| 10–25 | assert | تست ساده inline |
| 25–45 | unittest | TestCase، assertEqual |
| 45–60 | assertRaises | تست exception |
| 60–75 | setUp/tearDown | fixture |
| 75–85 | تمرین | تست توابع ریاضی |
| 85–90 | جمع‌بندی + تکلیف |

---

## ۱. تست چیست؟

تست یعنی بررسی **خودکار** اینکه کد درست کار می‌کند. به‌جای اجرای دستی هر بار، اسکریپت تست انتظار را با نتیجه مقایسه می‌کند.

**تشبیه:** قبل از پرواز، خلبان چک‌لیست می‌زند — نه اینکه هر بار «امیدوارم موتور کار کند».

---

## ۲. assert — تست ساده

```python
def add(a, b):
    return a + b

assert add(2, 3) == 5
assert add(-1, 1) == 0
print("همه تست‌ها OK")
```

اگر `assert` False شود → `AssertionError`.

---

## ۳. unittest — ساختار استاندارد

```python
import unittest

def multiply(a, b):
    return a * b

class TestMath(unittest.TestCase):
    def test_multiply(self):
        self.assertEqual(multiply(3, 4), 12)

    def test_multiply_zero(self):
        self.assertEqual(multiply(5, 0), 0)

if __name__ == "__main__":
    unittest.main()
```

**اجرا:** `python test_file.py` یا `python -m unittest test_file.py`

---

## ۴. متدهای assert پرکاربرد

| متد | کاربرد |
|-----|--------|
| `assertEqual(a, b)` | a == b |
| `assertNotEqual(a, b)` | a != b |
| `assertTrue(x)` | x is True |
| `assertFalse(x)` | x is False |
| `assertIn(a, b)` | a in b |
| `assertRaises(Exc)` | exception رخ دهد |
| `assertAlmostEqual(a, b)` | اعداد اعشاری |

---

## ۵. تست Exception

```python
def divide(a, b):
    if b == 0:
        raise ValueError("تقسیم بر صفر")
    return a / b

class TestDivide(unittest.TestCase):
    def test_divide_by_zero(self):
        with self.assertRaises(ValueError):
            divide(10, 0)
```

---

## ۶. setUp و tearDown

```python
class TestList(unittest.TestCase):
    def setUp(self):
        self.items = [1, 2, 3]  # قبل از هر test

    def tearDown(self):
        pass  # بعد از هر test

    def test_length(self):
        self.assertEqual(len(self.items), 3)
```

---

## ۷. AAA Pattern

**Arrange — Act — Assert**

```python
def test_add():
    # Arrange
    a, b = 2, 3
    # Act
    result = add(a, b)
    # Assert
    assert result == 5
```

---

## ۸. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_unittest_demo.py](./examples/01_unittest_demo.py) | unittest پایه |
| [02_assert_raises.py](./examples/02_assert_raises.py) | تست exception |
| [03_setup_teardown.py](./examples/03_setup_teardown.py) | fixture |
| [04_test_bank_account.py](./examples/04_test_bank_account.py) | تست کلاس واقعی |

---

## ۹. اشتباهات رایج (Pitfalls)

### ❌ تست بدون assertion
```python
def test_bad(self):
    add(2, 3)  # ❌ هیچ بررسی‌ای نیست
```

### ❌ تست وابسته به ترتیب
هر test باید **مستقل** باشد.

### ❌ تست implementation نه behavior
```python
# ❌ بررسی متغیر داخلی
# ✅ بررسی خروجی عمومی
```

---

## ۱۰. خلاصه

- ✅ assert برای تست سریع
- ✅ unittest برای ساختار و گزارش
- ✅ assertRaises برای exception
- ✅ setUp برای fixture
- ✅ AAA: Arrange, Act, Assert

---

## ۱۱. تکلیف

راهنما: [exercises/question.md](./exercises/question.md)  
پاسخ: [exercise_01.py](./exercises/exercise_01.py) | [exercise_02.py](./exercises/exercise_02.py)

---

## ۱۲. انواع تست

| نوع | هدف |
|-----|-----|
| Unit test | یک تابع/کلاس |
| Integration | چند ماژول با هم |
| Regression | جلوگیری از برگشت باگ |

---

## ۱۳. نام‌گذاری تست

```python
def test_add_positive_numbers(self): ...
def test_add_with_zero(self): ...
def test_divide_by_zero_raises(self): ...
```

الگو: `test_<رفتار>_<شرایط>`

---

## ۱۴. اجرای unittest

```bash
python -m unittest test_file.py
python -m unittest test_file.TestMath.test_add
python -m unittest discover -s tests
```

---

## ۱۵. توضیح فایل‌های مثال

### 📄 `01_unittest_demo.py` — TestCase و assertEqual
### 📄 `02_assert_raises.py` — ValueError و assertIn
### 📄 `03_setup_teardown.py` — fixture سبد خرید
### 📄 `04_test_bank_account.py` — تست کلاس واقعی

---

## ۱۶. سوالات کلاسی

1. تفاوت `assert` و `self.assertEqual`؟
2. چرا setUp برای هر test جدا اجرا می‌شود؟
3. AAA pattern چیست؟

---

## ۱۷. piramid تست (مفهومی)

```text
        / E2E \        ← کم
       / integr \       ← متوسط
      /  unit tests \  ← زیاد
```

در این جلسه روی **unit test** تمرکز داریم.

---

## راهنمای مدرس (۱۵ دقیقه)

- قبل از شروع: مرور کوتاه جلسه قبل و اهداف روی تخته
- حین جلسه: دانشجو کد را خودش تایپ کند — copy/paste نکند
- بعد از تمرین: ۵ دقیقه Q&A و اشاره به exercises/question.md
- تکلیف خانه: exercise_02.py — deadline هفته بعد
- ارزیابی: حضور در تمرین کلاسی + تحویل exercise_02

---

## منابع تکمیلی

- [unittest — Python docs](https://docs.python.org/3/library/unittest.html)
- [Test-Driven Development — مفهومی](https://en.wikipedia.org/wiki/Test-driven_development)

### چک‌لیست پایان جلسه

- [ ] assert و TestCase
- [ ] assertRaises
- [ ] setUp/tearDown
- [ ] BankAccount tests
- [ ] AAA pattern در تمرین
- [ ] `python -m unittest` اجرا شد

---

## نکات تکمیلی برای مدرس

هنگام تدریس unittest، حتماً یک test **عمداً failing** نشان دهید تا دانشجو تفاوت OK و FAILED را ببیند. test runner را با `verbosity=2` اجرا کنید. تأکید کنید هر test مستقل است — setUp هر بار از نو.

### اجرای تمرین‌ها

```bash
python session-18-testing/exercises/exercise_01.py
python session-18-testing/exercises/exercise_02.py
python -m unittest session-18-testing/examples/04_test_bank_account.py
```

---

**جلسه بعدی:** [جلسه ۱۹ — Type Hints](../session-19-typing/)
