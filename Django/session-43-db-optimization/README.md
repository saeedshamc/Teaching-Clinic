# جلسه ۴۳ — بهینه‌سازی دیتابیس

## درس

### Index — شاخص برای ستون‌های پرجستجو

```python
class Book(models.Model):
    title = models.CharField(max_length=200, db_index=True)   # index روی title
    created_at = models.DateTimeField(auto_now_add=True, db_index=True)

    class Meta:
        indexes = [
            models.Index(fields=["author", "-created_at"]),   # composite
        ]
```

### select_related / prefetch_related — N+1

```python
# بد — N+1:
books = Book.objects.all()
for b in books:
    print(b.author.name)   # N کوئری جدا!

# خوب — JOIN:
books = Book.objects.select_related("author").all()          # FK
books = Book.objects.prefetch_related("tags").all()          # M2M
```

### فقط فیلدهای لازم

```python
Book.objects.only("id", "title")           # SELECT id,title نه *
Book.objects.values("id", "title")         # dict به‌جای مدل
Book.objects.values_list("id", "title")    # tuple
Book.objects.defer("description")          # همه به‌جز...
```

### bulk operations

```python
Book.objects.bulk_create([Book(...) for _ in range(100)])   # یک INSERT
Book.objects.filter(is_available=False).update(stock=0)     # یک UPDATE
```

### explain() — تحلیل کوئری

```python
print(Book.objects.filter(pages__gt=100).explain())
# Seq Scan vs Index Scan را نشان می‌دهد
```

## تمرین

برای `Book.objects.filter(author__name="X", is_available=True)` یک composite index مناسب در `Meta.indexes` تعریف کنید.

## جواب تمرین

```python
class Meta:
    indexes = [
        models.Index(fields=["author", "is_available"]),
    ]
```

## 📖 شرح فایل `code/models.py`

- `db_index=True` روی title/pages/is_available/created_at — ایندکس جداگانه برای ستون‌های پرجستجو.
- `Meta.indexes` — composite index روی `(author, is_available)` و `(-created_at, price)` برای کوئری‌های ترکیبی.
- کامنت‌های پایانی: `select_related` برای FK، `prefetch_related` برای M2M، `only/defer/values/values_list` برای SELECT سبک‌تر، `bulk_create/update` برای عملیات گروهی، `save(update_fields=[...])` برای UPDATE جزئی، `explain()` برای تحلیل کوئری، و raw SQL با `connection.cursor()` + پارامتر `%s` (ضد SQL injection).
