# جلسه ۴۱: مقدمه pytest (۱.۵ ساعت)

## 🎯 اهداف یادگیری

پس از این جلسه می‌توانید:
- فلسفه تست خودکار و TDD را در سطح مقدماتی توضیح دهید
- تست pytest با `assert` ساده بنویسید
- تفاوت unittest و pytest را بدانید
- fixture ساده و parametrize را بشناسید
- تست exception با `pytest.raises` بنویسید
- ساختار پوشه `tests/` را رعایت کنید

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع | فعالیت |
|------|--------|--------|
| 0–10 | چرا تست؟ | regression، refactor امن |
| 10–30 | assert و test_ | اولین تست‌ها |
| 30–45 | pytest vs unittest | مقایسه syntax |
| 45–60 | parametrize | چند ورودی، یک تست |
| 60–75 | fixture و exception | setup/teardown |
| 75–85 | تمرین | تست توابع واقعی |
| 85–90 | جمع‌بندی + تکلیف | question.md |

---

## ۱. چرا تست خودکار؟

**Regression:** تغییر جدید چیز قدیمی را نشکند.
**Refactor امن:** ساختار را عوض کنید با اطمینان رفتار ثابت است.
**مستند زنده:** تست نشان می‌دهد API چگونه استفاده شود.

هر بار قبل از commit: `pytest` — اگر سبز بود، احتمالاً OK.

---

## ۲. pytest چیست؟

فریم‌ورک تست Python با:
- کشف خودکار فایل/تابع `test_*`
- assert ساده (بدون `self.assertEqual`)
- fixture، parametrize، plugin ecosystem

نصب:
```bash
pip install pytest
pytest -v
```

---

## ۳. اولین تست

```python
def add(a: int, b: int) -> int:
    return a + b

def test_add():
    assert add(2, 3) == 5
    assert add(-1, 1) == 0
```

pytest فایل `test_*.py` یا توابع `test_*` را اجرا می‌کند.

---

## ۴. pytest vs unittest

| unittest | pytest |
|----------|--------|
| class TestX(unittest.TestCase) | توابع ساده |
| self.assertEqual(a, b) | assert a == b |
| setUp/tearDown | fixture |
| verbose | concise |

هر دو valid — pytest برای پروژه جدید محبوب‌تر.

---

## ۵. چند assert در یک تست

```python
def test_is_even():
    assert is_even(2) is True
    assert is_even(3) is False
    assert is_even(0) is True
```

اگر اولین assert fail شود، بقیه اجرا نمی‌شود — گاهی `@pytest.mark.parametrize` بهتر است.

---

## ۶. parametrize — DRY

```python
import pytest

@pytest.mark.parametrize("n,expected", [(2, True), (3, False), (0, True)])
def test_is_even_param(n, expected):
    assert is_even(n) == expected
```

یک تابع، چند case — گزارش pytest برای هر ورودی جداگانه.

---

## ۷. تست exception

```python
import pytest

def divide(a, b):
    if b == 0:
        raise ValueError("تقسیم بر صفر")
    return a / b

def test_divide_by_zero():
    with pytest.raises(ValueError, match="تقسیم بر صفر"):
        divide(10, 0)
```

---

## ۸. fixture — setup مشترک

```python
import pytest

@pytest.fixture
def sample_user():
    return {"name": "سعید", "age": 30}

def test_user_name(sample_user):
    assert sample_user["name"] == "سعید"
```

fixture قبل از تست inject می‌شود — جایگزین setUp.

---

## ۹. ساختار tests/

```
myproject/
├── src/myapp/core.py
└── tests/
    ├── conftest.py      # fixtureهای مشترک
    ├── test_core.py
    └── test_utils.py
```

`conftest.py` fixtureها را برای کل tests/ در دسترس قرار می‌دهد.

---

## ۱۰. دستورات مفید pytest

```bash
pytest                    # همه تست‌ها
pytest tests/test_core.py # یک فایل
pytest -k "add"           # filter نام
pytest -v                 # verbose
pytest --tb=short         # traceback کوتاه
pytest -x                 # توقف در اولین fail
```

---

## ۱۱. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_basic_tests.py](./examples/01_basic_tests.py) | assert ساده |
| [02_parametrize.py](./examples/02_parametrize.py) | چند ورودی |
| [03_fixtures.py](./examples/03_fixtures.py) | fixture |
| [04_exceptions.py](./examples/04_exceptions.py) | pytest.raises |

**نکته:** مثال‌های ۲–۴ به pytest نیاز دارند. مثال ۱ بدون pytest قابل اجراست (شبیه‌سازی).

---

## ۱۲. Arrange-Act-Assert (AAA)

```python
def test_discount():
    # Arrange
    price = 1000
    rate = 0.1
    # Act
    result = apply_discount(price, rate)
    # Assert
    assert result == 900
```

الگوی خوانا برای هر تست.

---

## ۱۳. چه چیزی تست کنیم؟

| ✅ تست کنید | ❌ کمتر |
|------------|---------|
| منطق کسب‌وکار | implementation detail |
| edge cases | private method مستقیم |
| public API | framework داخلی |
| regression باگ | 100% coverage اجباری |

---

## ۱۴. اجرای مثال‌ها

```bash
pip install pytest
python session-41-pytest-intro/examples/01_basic_tests.py
pytest session-41-pytest-intro/examples/02_parametrize.py -v
pytest session-41-pytest-intro/examples/03_fixtures.py -v
pytest session-41-pytest-intro/examples/04_exceptions.py -v
```

---

## ۱۵. اشتباهات رایج

| اشتباه | مشکل | راه‌حل |
|--------|------|--------|
| نام بدون test_ | pytest پیدا نمی‌کند | test_* |
| assert بدون پیام | debug سخت | pytest diff خودکار |
| تست به ترتیب وابسته | flaky | fixture مستقل |
| mock همه چیز | تست بی‌ارزش | فقط boundary |
| print به‌جای assert | CI fail نمی‌شود | assert |

---

## ۱۶. coverage (اشاره)

```bash
pip install pytest-cov
pytest --cov=myapp --cov-report=term-missing
```

درصد پوشش راهنماست نه هدف مطلق.

---

## ۱۷. TDD خلاصه

1. Red — تست fail
2. Green — کد minimal برای pass
3. Refactor — تمیز بدون شکست تست

---

## ۱۸. تمرین کلاسی

1. `test_add` و `test_multiply`
2. parametrize برای `is_palindrome`
3. fixture لیست اعداد

---

## ۱۹. تکلیف

**تکلیف:** [question.md](./exercises/question.md)

پاسخ: [exercise_01.py](./exercises/exercise_01.py) | [exercise_02.py](./exercises/exercise_02.py)

---

## ۲۰. خلاصه

```
test_*.py / test_*()  → کشف خودکار
assert                → بدون self.assert*
@pytest.mark.parametrize → DRY
@pytest.fixture       → setup
pytest.raises         → exception
pytest -v             → verbose
```

---

**جلسه قبل:** [۴۰ — Packaging](../session-40-packaging-pyproject/) | **بعد:** [۴۲ — Mocking](../session-42-mocking/)

---

## ۲۱. fixture scope

```python
@pytest.fixture(scope="module")
def db():
    ...
```

| scope | کاربرد |
|-------|--------|
| function | default — هر تست fresh |
| module | یک‌بار per file |
| session | کل test run |

---

## ۲۲. conftest.py

fixtureهای مشترک بدون import — pytest خودکار پیدا می‌کند.

---

## ۲۳. منابع

- [pytest docs](https://docs.pytest.org/)
- جلسه ۱۸ — unittest (مقایسه)
