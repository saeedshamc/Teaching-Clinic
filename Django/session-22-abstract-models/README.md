# جلسه ۲۲ — مدل‌های Abstract، Proxy و ارث‌بری

## درس

### AbstractModel — فیلدهای مشترک بدون جدول

```python
class TimestampedModel(models.Model):
    """مدل انتزاعی — جدول خودش ساخته نمی‌شود."""
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)

    class Meta:
        abstract = True    # ← کلیدی: جدول نمی‌سازد

class Book(TimestampedModel):   # فیلدهای created_at/updated_at را به ارث می‌برد
    title = models.CharField(max_length=200)
```

### Proxy Model — مدل جایگزین بدون جدول جدید

```python
class AvailableBook(Book):
    """فقط کتاب‌های موجود — همان جدول book، manager متفاوت."""
    objects = models.Manager()  # manager فیلترشده
    class Meta:
        proxy = True
        verbose_name = "کتاب موجود"
```

### Multi-table Inheritance — ارث‌بری واقعی

```python
class Person(models.Model):
    name = models.CharField(max_length=100)

class Employee(Person):   # جدول employee + لینک به person (OneToOne ضمنی)
    salary = models.IntegerField()
```

## کد نمونه

کامنت‌های فارسی داخل هر بلوک — مستقیم در `models.py` استفاده کنید.

## تمرین

یک `AbstractModel` به نام `SluggedModel` بسازید که فیلد `slug` را با `unique=True` و `prepopulated` نداشته باشد — خودتان در `save()` از title بسازید.

## جواب تمرین

```python
from django.utils.text import slugify

class SluggedModel(models.Model):
    slug = models.SlugField(max_length=100, unique=True, blank=True)
    class Meta:
        abstract = True
    def save(self, *a, **kw):
        if not self.slug:
            self.slug = slugify(getattr(self, "title", self.__class__.__name__))
        super().save(*a, **kw)
```
