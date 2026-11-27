# جلسه ۳ — مدل‌ها و اولین migration

## درس

مدل در Django کلاس Python است که جدول دیتابیس را توصیف می‌کند. هر attribute یک **field** است که به ستون دیتابیس تبدیل می‌شود.

### چرخهٔ کار با مدل

1. در `models.py` مدل را تعریف کنید
2. `python manage.py makemigrations` → فایل migration می‌سازد
3. `python manage.py migrate` → تغییرات را روی دیتابیس اعمال می‌کند

### مثال ساده

```python
from django.db import models

class Book(models.Model):
    title = models.CharField(max_length=200)   # VARCHAR
    author = models.CharField(max_length=100)
    pages = models.IntegerField(default=0)
    created = models.DateTimeField(auto_now_add=True)  # فقط هنگام ساخت

    def __str__(self):
        return self.title   # نمایش خوانا در ادمین و shell
```

### قواعد مهم

- نام مدل **مفرد** و Capitalized: `Book` نه `Books`
- `id` به‌صورت خودکار (PrimaryKey) ساخته می‌شود
- `__str__` را همیشه تعریف کنید
- migrationها را **commit** کنید — بخشی از کد پروژه‌اند

## کد نمونه

`code/models.py` را ببینید.

## تمرین

یک مدل `Author` با فیلدهای `name` و `birth_year` بسازید و migration بسازید.

## جواب تمرین

```python
class Author(models.Model):
    name = models.CharField(max_length=100)
    birth_year = models.IntegerField()

    def __str__(self):
        return self.name
```

سپس: `python manage.py makemigrations library` و `python manage.py migrate`.

## 📖 شرح فایل `code/models.py`

**`Author`**
- `name`, `birth_year`, `bio` — هر attribute یک فیلد است که به ستون دیتابیس تبدیل می‌شود. `verbose_name` برچسب فارسی در ادمین است.
- `class Meta` — تنظیمات مدل: `verbose_name` نام نمایشی، `ordering` ترتیب پیش‌فرض همهٔ کوئری‌ها.
- `__str__` — نمایش خوانا در shell/ادمین؛ بدون آن `Author object (1)` نشان داده می‌شود.

**`Book`**
- `pages`, `price` (Decimal برای دقت مالی)، `published`, `is_available`.
- `created_at = auto_now_add` — فقط یک‌بار هنگام INSERT مقدار می‌گیرد؛ `updated_at = auto_now` — در هر UPDATE تازه می‌شود.
