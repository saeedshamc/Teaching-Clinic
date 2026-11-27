# جلسه ۲۱ — Manager و QuerySet سفارشی

## درس

### QuerySet سفارشی

```python
from django.db import models

class BookQuerySet(models.QuerySet):
    """متدهای زنجیره‌ای که QuerySet برمی‌گردانند."""

    def available(self):
        return self.filter(is_available=True)

    def by_author(self, name):
        return self.filter(author__name__icontains=name)

    def expensive(self, min_price):
        return self.filter(price__gte=min_price)

    # متدهایی که مقدار برمی‌گردانند (نه QuerySet)
    def total_pages(self):
        return self.aggregate(s=models.Sum("pages"))["s"] or 0
```

### Manager سفارشی

```python
class BookManager(models.Manager):
    """دسترسی به متدهای QuerySet سفارشی از Book.objects."""

    def get_queryset(self):
        return BookQuerySet(self.model, using=self._db)

    def available(self):
        return self.get_queryset().available()

class Book(models.Model):
    objects = BookManager()          # Book.objects.available()
    all_books = models.Manager()     # مدیر پیش‌فرض هم بماند
```

### from_queryset — کپی متدها به manager

```python
class BookManager(models.Manager.from_queryset(BookQuerySet)):
    pass
# حالا Book.objects.available() و .by_author() مستقیم کار می‌کنند
```

## کد نمونه

بالا با کامنت فارسی توضیح داده شده — در `models.py` پیاده کنید.

## تمرین

برای مدل `Author` یک `AuthorQuerySet` با متدهای `with_books_count` (annotate) و `senior` (birth_year < 1950) بسازید.

## جواب تمرین

```python
class AuthorQuerySet(models.QuerySet):
    def with_books_count(self):
        return self.annotate(count=models.Count("books"))
    def senior(self):
        return self.filter(birth_year__lt=1950)

class AuthorManager(models.Manager.from_queryset(AuthorQuerySet)):
    pass

class Author(models.Model):
    objects = AuthorManager()
```

## 📖 شرح فایل `code/models.py`

- **`BookQuerySet`** — متدهای زنجیره‌ای: `available()`/`by_author()`/`expensive()`/`recent()` همگی `self.filter(...)` را برمی‌گردانند تا قابل زنجیره باشند (`Book.objects.available().expensive(1000)`). `total_pages()` یک aggregate است و مقدار برمی‌گرداند نه QuerySet.
- **`BookManager`** — `get_queryset` همیشه `BookQuerySet` برمی‌گرداند؛ `available()` روی manager هم expose شده. `all_objects = models.Manager()` مدیر پیش‌فرض بدون فیلتر می‌ماند.
- **`AuthorQuerySet`/`AuthorManager`** — `with_books_count()` با `annotate(Count)` و `senior()` با `birth_year__lt`. `Manager.from_queryset` همهٔ متدهای queryset را به manager منتقل می‌کند.
