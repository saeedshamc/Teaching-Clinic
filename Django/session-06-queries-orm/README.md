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
