# جلسه ۳۹: Property و Encapsulation (۱.۵ ساعت)

## 🎯 اهداف یادگیری

پس از این جلسه می‌توانید:
- تفاوت attribute عمومی و دسترسی کنترل‌شده را توضیح دهید
- با `@property`، getter و setter بنویسید
- اعتبارسنجی هنگام مقداردهی پیاده‌سازی کنید
- property محاسباتی (computed) بسازید
- از `@name.deleter` برای حذف کنترل‌شده استفاده کنید
- قرارداد نام‌گذاری `_private` را در encapsulation رعایت کنید

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع | فعالیت |
|------|--------|--------|
| 0–10 | مرور OOP | attribute عمومی vs خصوصی |
| 10–25 | @property پایه | getter/setter برای age |
| 25–40 | اعتبارسنجی | قیمت، ایمیل، محدوده |
| 40–55 | property محاسباتی | BMI، full_name، area |
| 55–70 | deleter و readonly | حذف امن، init-only |
| 70–85 | تمرین کلاسی | Product، BankAccount |
| 85–90 | جمع‌بندی + تکلیف | question.md |

---

## ۱. Encapsulation چیست؟

**Encapsulation (کپسوله‌سازی)** یعنی داده‌های داخلی شیء را پشت یک لایه کنترل قرار دهید تا:
- قوانین کسب‌وکار هنگام تغییر مقدار اعمال شود
- جزئیات پیاده‌سازی از مصرف‌کننده پنهان بماند
- باگ‌های ناشی از مقداردهی اشتباه کاهش یابد

در Python قرارداد `_name` (یک underscore) یعنی «داخلی — لطفاً مستقیم دستکاری نکن»؛ برخلاف Java/C#، این **قرارداد** است نه enforce سخت.

```python
class User:
    def __init__(self, name: str):
        self._name = name  # «خصوصی» از نظر قرارداد

    @property
    def name(self) -> str:
        return self._name

    @name.setter
    def name(self, value: str) -> None:
        if not value.strip():
            raise ValueError("نام نمی‌تواند خالی باشد")
        self._name = value.strip()
```

---

## ۲. چرا property به‌جای متد get/set؟

| روش | مصرف | خوانایی |
|-----|------|---------|
| `user.get_age()` / `user.set_age(30)` | verbose | ضعیف |
| `user.age = 30` / `user.age` | طبیعی | عالی |

property به شما اجازه می‌دهد **سینتکس attribute** را حفظ کنید ولی **منطق** اضافه کنید — بدون شکستن API مصرف‌کننده.

---

## ۳. @property — getter

```python
class Circle:
    def __init__(self, radius: float):
        self._radius = radius

    @property
    def radius(self) -> float:
        """شعاع دایره — فقط خواندنی از بیرون."""
        return self._radius
```

بدون setter، `circle.radius = 5` خطای `AttributeError` می‌دهد — مناسب فیلدهای فقط-خواندنی.

---

## ۴. @x.setter — نوشتن کنترل‌شده

```python
    @radius.setter
    def radius(self, value: float) -> None:
        if value <= 0:
            raise ValueError("شعاع باید مثبت باشد")
        self._radius = value
```

**ترتیب مهم است:** ابتدا `@property`، سپس `@radius.setter` روی همان نام.

---

## ۵. property محاسباتی

property لازم نیست فقط `_field` را برگرداند — می‌تواند **محاسبه** کند:

```python
class Rectangle:
    def __init__(self, width: float, height: float):
        self.width = width
        self.height = height

    @property
    def area(self) -> float:
        return self.width * self.height
```

`area` setter ندارد چون از width و height مشتق می‌شود.

---

## ۶. @name.deleter

```python
class Tag:
    def __init__(self, label: str):
        self.label = label

    @label.deleter
    def label(self) -> None:
        print("برچسب حذف شد")
        del self._label
```

در عمل کمتر از getter/setter استفاده می‌شود؛ برای resource cleanup مفید است.

---

## ۷. property vs dataclass field

| ابزار | کاربرد |
|-------|--------|
| `@dataclass` | مدل داده ساده با فیلدهای مستقیم |
| `@property` | اعتبارسنجی، محاسبه، مهاجرت از attribute عمومی |

می‌توان ترکیب کرد: dataclass برای ساختار + property برای فیلدهای حساس.

---

## ۸. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_property_basics.py](./examples/01_property_basics.py) | getter/setter سن |
| [02_validation.py](./examples/02_validation.py) | قیمت و موجودی |
| [03_computed_property.py](./examples/03_computed_property.py) | full_name، area |
| [04_readonly_deleter.py](./examples/04_readonly_deleter.py) | فقط خواندنی و deleter |

---

## ۹. توضیح کد — `01_property_basics.py`

```python
class User:
    def __init__(self, age: int):
        self.age = age  # setter فراخوانی می‌شود

    @property
    def age(self) -> int:
        return self._age

    @age.setter
    def age(self, value: int) -> None:
        if value < 0:
            raise ValueError("سن منفی مجاز نیست")
        self._age = value
```

**نکات:**
- در `__init__` از `self.age = age` استفاده می‌کنیم تا setter اعتبارسنجی اجرا شود
- اگر `self._age = age` بگذاریم، اعتبارسنجی دور زده می‌شود
- property نام attribute و نام متد یکسان است — Python decoratorها را به هم وصل می‌کند

---

## ۱۰. توضیح کد — `02_validation.py`

اعتبارسنجی چندلایه:
- نوع داده (implicit با type hint)
- محدوده (range)
- قوانین کسب‌وکار (موجودی کافی)

```python
@stock.setter
def stock(self, value: int) -> None:
    if value < 0:
        raise ValueError("موجودی منفی مجاز نیست")
    self._stock = value
```

---

## ۱۱. توضیح کد — `03_computed_property.py`

```python
@property
def full_name(self) -> str:
    return f"{self.first_name} {self.last_name}"
```

مصرف‌کننده `person.full_name` می‌خواند بدون اینکه بداند از دو فیلد ساخته شده — encapsulation موفق.

---

## ۱۲. اجرای مثال‌ها

```bash
python session-39-properties/examples/01_property_basics.py
python session-39-properties/examples/02_validation.py
python session-39-properties/examples/03_computed_property.py
python session-39-properties/examples/04_readonly_deleter.py
```

---

## ۱۳. اشتباهات رایج

| اشتباه | مشکل | راه‌حل |
|--------|------|--------|
| `self._age = x` در setter بدون بررسی | اعتبارسنجی ناقص | همه مسیرها از setter |
| فراموش کردن setter | AttributeError هنگام نوشتن | `@x.setter` اضافه کنید |
| property سنگین (I/O، DB) | کندی غیرمنتظره | متد جدا یا cache |
| `__age` double underscore | name mangling — معمولاً لازم نیست | `_age` کافی است |
| setter بدون getter | در Python 3 کار می‌کند ولی غیرمعمول | getter هم بنویسید |

---

## ۱۴. property در دنیای واقعی

- **ORMها:** فیلد DB با lazy load
- **GUI:** binding دوطرفه با validation
- **API models:** تبدیل نوع هنگام set (string → datetime)
- **Deprecated fields:** هشدار هنگام دسترسی به attribute قدیمی

---

## ۱۵. مقایسه با زبان‌های دیگر

| Python | Java/C# |
|--------|---------|
| `@property` | get/set جدا یا auto-property |
| `_private` convention | `private` keyword |
| duck typing | access modifiers سخت |

---

## ۱۶. تمرین کلاسی

1. کلاس `Product` با property `price` غیرمنفی
2. کلاس `BankAccount` — برداشت بیش از موجودی ممنوع
3. کلاس `Temperature` — setter فقط Celsius، property `fahrenheit` محاسباتی

---

## ۱۷. تکلیف

**تکلیف:** [question.md](./exercises/question.md)

پاسخ: [exercise_01.py](./exercises/exercise_01.py) | [exercise_02.py](./exercises/exercise_02.py)

---

## ۱۸. خلاصه

```
@property          → getter
@name.setter       → setter با اعتبارسنجی
@name.deleter      → حذف کنترل‌شده
_computed property → بدون ذخیره جدا
_name              → قرارداد encapsulation
```

---

**جلسه قبل:** [۳۸ — ABC و Interface](../session-38-abc-interfaces/) | **بعد:** [۴۰ — Packaging](../session-40-packaging-pyproject/)

---

## ۱۹. پیش‌نیازهای این جلسه

- جلسه ۹ (OOP) — class و attribute
- جلسه ۱۴ (decorators) — `@property` یک decorator است
- جلسه ۱۹ (typing) — type hints در property

---

## ۲۰. سوالات متداول

**آیا property کندتر از attribute مستقیم است؟**
بله، marginally — برای hot path معمولاً ناچیز؛ readability ارزشمندتر است.

**چه زمانی از `@dataclass` field(validator) استفاده کنیم؟**
Python 3.10+ — alternative مدرن؛ property هنوze for computed fields.

**آیا می‌توان property را override کرد در subclass؟**
بله — polymorphism عادی OOP.
