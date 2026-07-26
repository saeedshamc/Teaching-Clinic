# جلسه ۴۱: مقدمه‌ای بر pytest

## اهداف یادگیری (Learning Objectives)

پس از این جلسه می‌توانید:
- ایده pytest را درک کنید
- تست‌ها را با assert ساده بنویسید
- تفاوت سبک pytest و unittest را ببینید

---

## pytest چیست؟

pytest یک فریم‌ورک محبوب تست است که با assert ساده و کشف خودکار تست‌ها کار را راحت می‌کند. اینجا سبک آن را شبیه‌سازی می‌کنیم.

```python
def add(a, b):
    return a + b

def test_add():
    assert add(2, 3) == 5

test_add()
print("تست pytest-style موفق بود")
```

### توضیح
- نام تابع با `test_` شروع می‌شود
- `assert` قلب تست‌های pytest است
- در پروژه واقعی دستور `pytest` را اجرا می‌کنید

---

## فایل‌های این جلسه
- [examples/01_pytest_style.py](./examples/01_pytest_style.py)
- [exercises/exercise_01.py](./exercises/exercise_01.py)

## تمرین‌ها
- تمرین ساده: یک test_add بنویسید
- تمرین متوسط: برای is_even دو assert بنویسید
