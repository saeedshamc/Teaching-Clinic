# models.py — مدل‌های Abstract، Proxy و ارث‌بری (جلسه ۲۲)
import uuid
from django.db import models
from django.utils.text import slugify


# ─── AbstractModel ────────────────────────────────────────────────
# abstract=True → جدول جداگانه نمی‌سازد؛ فقط فیلدها را به فرزندان می‌دهد.

class TimestampedModel(models.Model):
    """هر مدل فرزند created_at و updated_at می‌گیرد."""
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)

    class Meta:
        abstract = True


class SluggedModel(models.Model):
    """slug خودکار از title در save() ساخته می‌شود."""
    slug = models.SlugField(max_length=100, unique=True, blank=True)

    class Meta:
        abstract = True

    def save(self, *args, **kwargs):
        # اگر slug خالی است از title (یا نام کلاس) بساز
        if not self.slug:
            base = slugify(getattr(self, "title", self.__class__.__name__))
            self.slug = base or str(uuid.uuid4())[:8]
        super().save(*args, **kwargs)


class Book(TimestampedModel, SluggedModel):
    """ترکیب دو abstract — بدون کد تکراری."""
    title = models.CharField(max_length=200)
    pages = models.IntegerField(default=0)

    def __str__(self):
        return self.title


# ─── Proxy Model ──────────────────────────────────────────────────
# proxy=True → همان جدول book، اما manager/رفتار متفاوت.
# جدول جدید ساخته نمی‌شود!

class AvailableBook(Book):
    """فقط کتاب‌های موجود — فیلتر در manager."""

    class AvailableManager(models.Manager):
        def get_queryset(self):
            return super().get_queryset().filter(pages__gt=0)

    objects = AvailableManager()

    class Meta:
        proxy = True
        verbose_name = "کتاب موجود"
        verbose_name_plural = "کتاب‌های موجود"

    def __str__(self):
        return f"[موجود] {self.title}"


# ─── Multi-table Inheritance ──────────────────────────────────────
# ارث‌بری واقعی → هر کلاس جدول خودش را دارد + OneToOne به والد.

class Person(models.Model):
    name = models.CharField(max_length=100)
    birth_year = models.IntegerField()

    def __str__(self):
        return self.name


class Author(Person):
    """Author هم Person است + فیلد bio اضافه."""
    bio = models.TextField(blank=True)
    # Django خودکار person_ptr OneToOneField می‌سازد

    class Meta:
        verbose_name_plural = "نویسندگان"

    def __str__(self):
        return f"نویسنده: {self.name}"
