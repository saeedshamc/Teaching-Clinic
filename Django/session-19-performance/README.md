# جلسه ۱۹ — بهینه‌سازی و پرفورمنس

## درس

### select_related / prefetch_related (جلوگیری از N+1)

```python
# بد — برای هر کتاب یک کوئری جدا برای author اجرا می‌شود (N+1)
books = Book.objects.all()
for b in books:
    print(b.author.name)   # ← N کوئری اضافه!

# خوب — JOIN در یک کوئری
books = Book.objects.select_related("author").all()

# برای ManyToMany از prefetch_related
books = Book.objects.prefetch_related("tags").all()
for b in books:
    for t in b.tags.all():   # بدون کوئری اضافه
        pass
```

### indexing

```python
class Book(models.Model):
    title = models.CharField(max_length=200, db_index=True)

    class Meta:
        indexes = [
            models.Index(fields=["created_at"]),
            models.Index(fields=["author", "-created_at"]),
        ]
```

### pagination

```python
# در ویو
from django.core.paginator import Paginator
paginator = Paginator(books, 12)
page = paginator.get_page(request.GET.get("page"))

# یا خودکار با ListView: paginate_by = 12
# در قالب: {% for book in page_obj %} ... {{ page_obj.number }}
```

### فقط فیلدهای لازم

```python
Book.objects.only("id", "title")           # به‌جای SELECT *
Book.objects.defer("description")         # همه به‌جز...
Book.objects.values("id", "title")        # دیکشنری به‌جای مدل
Book.objects.values_list("id", "title")   # tuple
```

### bulk operations

```python
Book.objects.bulk_create([Book(...), Book(...)])  # INSERT گروهی
Book.objects.filter(...).update(is_available=False) # UPDATE گروهی
Book.objects.in_bulk([1, 2, 3])            # دیکشنری {id: obj}
```

## کد نمونه

`code/queries.py` را ببینید.

## تمرین

با استفاده از `select_related` و `prefetch_related` کوئری لیست کتاب‌ها را طوری بهینه کنید که هم author و هم tags بدون N+1 لود شوند.

## جواب تمرین

```python
books = (Book.objects
         .select_related("author")      # FK → JOIN
         .prefetch_related("tags"))     # M2M → کوئری جدا ولی یک‌بار
```

## 📖 شرح فایل `code/queries.py`

- **`optimized_list()`** — `select_related("author")` برای FK (JOIN در همان کوئری) + `prefetch_related("tags")` برای M2M (کوئری دوم ولی batch — بدون N+1).
- **`lean_queries()`** — `only()` فقط ستون‌های لازم؛ `defer()` همه به‌جز...؛ `values()` خروجی dict؛ `values_list()` خروجی tuple — هر سه SELECT * را حذف می‌کنند.
- **`paginate(books, request)`** — `Paginator` دستی؛ `request.GET.get("page", 1)` شمارهٔ صفحه؛ `get_page` شمارهٔ نامعتبر را به صفحهٔ آخر clamp می‌کند.
- **`bulk_ops()`** — `bulk_create` یک INSERT گروهی به‌جای N تا؛ `update()` یک UPDATE گروهی بدون لود instance.
