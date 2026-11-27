# جلسه ۲۵ — جستجو و فیلتر پیشرفته

## درس

### Q objects — شرط‌های OR/AND/NOT

```python
from django.db.models import Q

# OR: کتاب‌هایی که عنوانش django دارد یا قیمتش زیر ۱۰۰هزار است
Book.objects.filter(
    Q(title__icontains="django") | Q(price__lt=100000)
)

# AND + NOT: موجود + (غیرداستانی یا قدیمی)
Book.objects.filter(
    is_available=True
).filter(
    ~Q(genere="fiction") | Q(created_at__lt="2020-01-01")
)
```

### django-filter (کتابخانه)

```bash
pip install django-filter
# settings: INSTALLED_APPS += "django_filters" و REST_FRAMEWORK["DEFAULT_FILTER_BACKENDS"]
```

```python
import django_filters

class BookFilter(django_filters.FilterSet):
    title = django_filters.CharFilter(lookup_expr="icontains")
    price_min = django_filters.NumberFilter(field_name="price", lookup_expr="gte")
    price_max = django_filters.NumberFilter(field_name="price", lookup_expr="lte")

    class Meta:
        model = Book
        fields = ["author", "is_available"]

# در ویو: BookFilter(request.GET, queryset=Book.objects.all()).qs
```

### جستجوی ساده در ویو (بدون کتابخانه)

```python
q = request.GET.get("q", "").strip()
if q:
    books = books.filter(
        Q(title__icontains=q) |
        Q(author__name__icontains=q) |
        Q(tags__name__icontains=q)
    ).distinct()   # distinct برای M2M الزامی است
```

## کد نمونه

کامنت‌گذاری داخل README — در views/filterset پیاده کنید.

## تمرین

یک `BookFilter` با فیلترهای `price` (بازه)، `author` و `is_available` بنویسید و در ویو به آن وصل کنید.

## جواب تمرین

```python
class BookFilter(django_filters.FilterSet):
    price = django_filters.RangeFilter()
    class Meta:
        model = Book
        fields = {"author": ["exact"], "is_available": ["exact"]}
# ویو: books = BookFilter(request.GET, queryset=books).qs
```

## 📖 شرح فایل `code/filters.py`

- **`BookFilter(django_filters.FilterSet)`** — `Meta.model`+`fields` فیلدهای اتوماتیک می‌سازد؛ فیلدهای declared کنترل دقیق می‌دهند:
  - `title` با `lookup_expr="icontains"` → `?title=...` جستجوی بدون حساسیت به حروف.
  - `price_min`/`price_max` با `gte`/`lte` → بازهٔ قیمت.
  - `search` با `method="filter_search"` → متد سفارشی که `Q(title)|Q(author)|Q(tags)` را با `distinct()` ترکیب می‌کند (distinct برای M2M الزامی).
- بخش پایانی کامنت: مثال‌های `Q` برای OR/AND/NOT — `|` برای OR، `&` برای AND، `~` برای NOT.
