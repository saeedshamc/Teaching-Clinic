# جلسه ۶ — کوئری با ORM

## درس

ORM درjango تمام CRUD دیتابیس را بدون SQL خام انجام می‌دهد.

### ساخت و ذخیره

```python
book = Book(title="Django for Beginners", pages=300)
book.save()                     # INSERT
Book.objects.create(title="X", pages=100)   # INSERT یک‌خطی
```

### خواندن — QuerySet

```python
Book.objects.all()                    # همه
Book.objects.get(id=1)                # دقیقاً یکی — اگر نبود DoesNotExist، اگر چندتا Multi...
Book.objects.filter(pages__gt=200)    # WHERE pages > 200
Book.objects.filter(author__name="X") # join روی رابطه
Book.objects.exclude(is_available=False)
Book.objects.order_by("-created_at")  # - یعنی نزولی
Book.objects.first() / .last()
Book.objects.count()
```

### Lookup های پرکاربرد

`exact, iexact, contains, icontains, in, gt, gte, lt, lte, startswith, endswith, range, isnull`

مثال: `Book.objects.filter(title__icontains="django")`

### Aggregation

```python
from django.db.models import Sum, Avg, Count
Book.objects.aggregate(total=Sum("pages"), avg=Avg("pages"))
Author.objects.annotate(book_count=Count("books"))
```

## کد نمونه

`code/queries.py` را ببینید.

## تمرین

با استفاده از ORM: الف) کتاب‌های موجود با بیش از ۲۰۰ صفحه را پیدا کنید. ب) میانگین صفحات همهٔ کتاب‌ها را حساب کنید.

## جواب تمرین

```python
from django.db.models import Avg
Book.objects.filter(is_available=True, pages__gt=200)
Book.objects.aggregate(avg=Avg("pages"))["avg"]
```

## 📖 شرح فایل `code/queries.py`

فایل یک تابع `examples()` است که همهٔ عملیات ORM را به ترتیب اجرا می‌کند — می‌توانید آن را در `python manage.py shell` paste کنید:

- **ساخت**: `book.save()` INSERT دستی؛ `Book.objects.create(...)` INSERT یک‌خطی.
- **خواندن**: `all()` همه؛ `get(id=1)` دقیقاً یکی (خطا: `DoesNotExist`/`MultipleObjectsReturned`)؛ `filter(pages__gt=200)` شرط؛ `exclude` نقیض؛ `order_by("-created_at")` نزولی؛ `count()`، `first()`.
- **رابطه**: `author__name` روی FK join می‌زند — بدون SQL.
- **Slicing** `[:10]` → `LIMIT 10` در SQL.
- **`aggregate`** — محاسبه روی کل مجموعه (Sum/Avg/Count) → یک دیکشنری.
- **`annotate`** — فیلد محاسباتی به هر سطر اضافه می‌کند (`num_books` برای هر Author) → قابل order_by.
- **`update`/`delete`** — عملیات گروهی بدون لود کردن instanceها.
