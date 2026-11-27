# جلسه ۴ — انواع فیلدها، choices و validators

## درس

### فیلدهای پرکاربرد

| Field | نوع دیتابیس | نکته |
|---|---|---|
| `CharField(max_length=n)` | VARCHAR | برای متن کوتاه — max_length اجباری |
| `TextField()` | TEXT | متن بلند، بدون max_length |
| `IntegerField()` | INT | عدد صحیح |
| `FloatField()` / `DecimalField` | REAL/DECIMAL | برای پول DecimalField دقیق‌تر است |
| `BooleanField()` | BOOL | True/False |
| `DateField()` / `DateTimeField()` | DATE/DATETIME | تاریخ و زمان |
| `EmailField()` | VARCHAR | با validation ایمیل |
| `URLField()` | VARCHAR | با validation URL |
| `FileField()` / `ImageField()` | فایل روی دیسک | برای آپلود (جلسه ۱۰) |
| `JSONField()` | JSON | دادهٔ ساخت‌یافته (Django 3.1+) |

### آرگومان‌های مشترک

- `null=True`: در دیتابیس NULL مجاز است (برای CharField معمولاً `blank=True` کافی است)
- `blank=True`: در فرم‌ها اجباری نیست
- `default=...`: مقدار پیش‌فرض
- `choices=[(value, label), ...]`: محدود کردن به مقادیر مشخص
- `validators=[...]`: اعتبارسنجی سفارشی

### مثال choices

```python
class Book(models.Model):
    GENRE_CHOICES = [
        ("fiction", "داستان"),
        ("science", "علمی"),
        ("history", "تاریخ"),
    ]
    genre = models.CharField(max_length=20, choices=GENRE_CHOICES, default="fiction")
```

## کد نمونه

`code/models.py` را ببینید.

## تمرین

به مدل `Book` جلسه قبل یک فیلد `rating` از نوع `IntegerField` با `choices` از ۱ تا ۵ و validator که مقدار بین ۱ و ۵ باشد اضافه کنید.

## جواب تمرین

```python
from django.core.validators import MinValueValidator, MaxValueValidator

RATING_CHOICES = [(i, str(i)) for i in range(1, 6)]

rating = models.IntegerField(
    choices=RATING_CHOICES,
    validators=[MinValueValidator(1), MaxValueValidator(5)],
    null=True, blank=True,
)
```

## 📖 شرح فایل `code/models.py`

**`Product`** — همهٔ فیلدهای پرکاربرد در یک مدل:
- `name` (`CharField`) — `max_length` اجباری است چون VARCHAR به طول نیاز دارد.
- `description` (`TextField`) — متن بلند بدون محدودیت طول.
- `stock` (`IntegerField`) — `validators=[MinValueValidator(0)]` مقدار منفی را در `full_clean()`/فرم رد می‌کند.
- `price` (`DecimalField`) — `max_digits`/`decimal_places` دقت اعشار را مشخص می‌کند؛ برای پول از Float استفاده نکنید.
- `category` (`CharField` + `choices`) — فقط کلید اول tuple ذخیره می‌شود؛ `get_category_display()` برچسب فارسی را برمی‌گرداند.
- `class Meta.ordering = ["-created_at"]` — پیش‌فرض نزولی بر اساس تاریخ.
- `__str__` از `get_category_display()` استفاده می‌کند تا خروجی خوانا شود.
