# جلسه ۴۸: مستندسازی کد (Documentation) (۱.۵ ساعت)

## 🎯 اهداف یادگیری

پس از این جلسه می‌توانید:
- docstring استاندارد Google/NumPy style بنویسید
- از `help()` و `__doc__` استفاده کنید
- type hints را در docstring تکمیل کنید
- README پروژه ساخت‌یافته بنویسید
- Sphinx/MkDocs را بشناسید (مرور)
- comment خوب vs بد را تشخیص دهید

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع | فعالیت |
|------|--------|--------|
| 0–10 | چرا مستند؟ | future you |
| 10–30 | docstring | Google style |
| 30–45 | module/class docs | سطح‌بندی |
| 45–60 | type hints + doc | تکمیل |
| 60–75 | README | install، usage |
| 75–85 | Sphinx مرور | autodoc |
| 85–90 | جمع‌بندی + تکلیف | question.md |

---

## ۱. کد خود-مستند vs docstring

**خوب:** نام‌گذاری واضح، توابع کوتاه
**docstring:** چرا، نه چه — contract، edge cases، examples

```python
def add(a: int, b: int) -> int:
    """جمع دو عدد صحیح."""
    return a + b
```

---

## ۲. Google style docstring

```python
def fetch_user(user_id: int, timeout: float = 5.0) -> dict:
    """کاربر را از API برمی‌گرداند.

    Args:
        user_id: شناسه یکتا
        timeout: حداکثر ثانیه انتظار

    Returns:
        dict با کلیدهای id و name

    Raises:
        ValueError: user_id منفی
        ConnectionError: timeout شبکه
    """
```

---

## ۳. Module docstring

```python
"""Utilities for parsing config files.

Supports JSON and YAML formats.
"""
```

اولین statement فایل — `help(module)` نمایش می‌دهد.

---

## ۴. Class docstring

```python
class BankAccount:
    """حساب بانکی با واریز و برداشت امن.

    Attributes:
        owner: نام صاحب حساب
    """
```

---

## ۵. help() و __doc__

```python
print(add.__doc__)
help(add)
```

REPL و IDE از docstring برای tooltip استفاده می‌کنند.

---

## ۶. doctest (مرور)

```python
def double(x):
    """Double the input.

    >>> double(3)
    6
    """
    return x * 2
```

`python -m doctest module.py` — docstring = test.

---

## ۷. README ساختار

```markdown
# Project Name
## نصب
## استفاده سریع
## API
## توسعه
## مجوز
```

اولین چیزی که contributor می‌بیند.

---

## ۸. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_docstrings.py](./examples/01_docstrings.py) | Google style |
| [02_module_class_docs.py](./examples/02_module_class_docs.py) | class/module |
| [03_help_and_doctest.py](./examples/03_help_and_doctest.py) | help |
| [04_readme_generator.py](./examples/04_readme_generator.py) | README template |

---

## ۹. comment خوب

```python
# ✅ چرا
# TTL cache — API rate limit 100/min

# ❌ چه (واضح از کد)
# i را یکی زیاد کن
i += 1
```

---

## ۱۰. Sphinx (مرور)

```bash
pip install sphinx
sphinx-quickstart
# autodoc from docstrings
```

MkDocs + Material — alternative مدرن.

---

## ۱۱. type hints as documentation

```python
def process(items: list[str]) -> dict[str, int]:
```

mypy + IDE — docstring برای human، hints برای tool.

---

## ۱۲. اجرای مثال‌ها

```bash
python session-48-documentation/examples/01_docstrings.py
python session-48-documentation/examples/02_module_class_docs.py
python session-48-documentation/examples/03_help_and_doctest.py
python session-48-documentation/examples/04_readme_generator.py
python -m doctest session-48-documentation/examples/03_help_and_doctest.py -v
```

---

## ۱۳. اشتباهات رایج

| اشتباه | مشکل |
|--------|------|
| doc stale | worse than none |
| every line commented | noise |
| no Raises/Args | incomplete contract |
| README بدون install | contributor lost |

---

## ۱۴. تمرین کلاسی

1. docstring کامل برای divide
2. class Product با Attributes
3. README ۱۰ خطی

---

## ۱۵. تکلیف

**تکلیف:** [question.md](./exercises/question.md)

پاسخ: [exercise_01.py](./exercises/exercise_01.py) | [exercise_02.py](./exercises/exercise_02.py)

---

## ۱۶. خلاصه

```
Google docstring  → Args, Returns, Raises
module __doc__    → top of file
help(fn)          → interactive
README            → install + usage
comment why       → not what
doctest           → doc as test
```

---

**جلسه قبل:** [۴۷ — Security](../session-47-security-basics/) | **بعد:** [۴۹ — Project Structure](../session-49-project-structure/)

---

## ۱۷. NumPy docstring style

```python
def func(x):
    """
    Parameters
    ----------
    x : int
        Input value

    Returns
    -------
    int
        Result
    """
```

---

## ۱۸. API documentation

- docstring → Sphinx autodoc → HTML
- MkDocs Material — سریع‌تر برای پروژه‌های کوچک

---

## ۱۹. CHANGELOG.md

```markdown
## [0.2.0] - 2026-01-01
### Added
- feature X
### Fixed
- bug Y
```

---

## ۲۱. type hints in docstring vs signature

Python 3.10+: signature کافی — docstring برای narrative.

---

## ۲۲. documenting exceptions

همه public exceptions در Raises — consumer آماده handle.

---

## ۲۳. version in doc

```python
"""Module — since v0.2.0"""
```

---

## ۲۴. living documentation

تست + docstring + README — هر سه sync با code.
