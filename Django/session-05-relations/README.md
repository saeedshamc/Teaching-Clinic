# جلسه ۵ — روابط بین مدل‌ها

## درس

سه نوع رابطه در Django وجود دارد:

### ۱. ForeignKey (بسیاربه‌یک)

هر کتاب یک نویسنده دارد؛ هر نویسنده چند کتاب می‌تواند داشته باشد.

```python
class Book(models.Model):
    author = models.ForeignKey(Author, on_delete=models.CASCADE, related_name="books")
```

- `on_delete=models.CASCADE`: با حذف نویسنده، کتاب‌هایش هم حذف می‌شوند
- گزینه‌های دیگر: `PROTECT` (جلوگیری از حذف)، `SET_NULL`، `SET_DEFAULT`
- `related_name="books"` → `author.books.all()` به‌جای `author.book_set.all()`

### ۲. OneToOneField (یک‌به‌یک)

مثلاً پروفایل کاربر:

```python
class Profile(models.Model):
    user = models.OneToOneField(User, on_delete=models.CASCADE)
```

### ۳. ManyToManyField (چندبه‌چند)

کتاب می‌تواند چند برچسب داشته باشد و هر برچسب چند کتاب:

```python
class Tag(models.Model):
    name = models.CharField(max_length=50)

class Book(models.Model):
    tags = models.ManyToManyField(Tag, blank=True)
```

### دسترسی معکوس

- FK: `book.author` و `author.books.all()` (با related_name)
- M2M: `book.tags.all()` و `tag.book_set.all()`

## کد نمونه

`code/models.py` را ببینید.

## تمرین

رابطهٔ ManyToMany بین `Book` و `Category` بسازید به‌طوری که هر کتاب حداکثر ۳ دسته داشته باشد. (راهنما: از `limit_choices_to` یا اعتبارسنجی در `clean()` استفاده کنید.)

## جواب تمرین

```python
class Book(models.Model):
    categories = models.ManyToManyField(Category, blank=True)

    def clean(self):
        super().clean()
        if self.pk and self.categories.count() > 3:
            from django.core.exceptions import ValidationError
            raise ValidationError("حداکثر ۳ دسته مجاز است")
```

## 📖 شرح فایل `code/models.py`

**`Author`** — مدل ساده با `__str__`؛ مقصد رابطه‌ها.

**`Book`**
- `author = ForeignKey(Author, on_delete=CASCADE, related_name="books")` — هر کتاب یک نویسنده؛ حذف نویسنده همهٔ کتاب‌هایش را حذف می‌کند. `related_name` دسترسی معکوس `author.books.all()` را می‌سازد (پیش‌فرض `book_set` بود).
- `tags = ManyToManyField("Tag", blank=True, related_name="books")` — رابطهٔ چندبه‌چند؛ Django جدول میانی را خودکار می‌سازد. `"Tag"` به‌صورت string تا ترتیب تعریف مدل مهم نباشد.

**`Tag`** — `unique=True` یعنی نام تگ تکراری نمی‌شود.

**`Profile`**
- `user = OneToOneField(User, on_delete=CASCADE, related_name="profile")` — هر کاربر دقیقاً یک پروفایل؛ `user.profile` مستقیم کار می‌کند.
- `avatar = ImageField` — آپلود تصویر (نیازمند Pillow).
