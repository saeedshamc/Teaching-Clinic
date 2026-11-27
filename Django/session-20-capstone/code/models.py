# models.py — مدل‌های نهایی پروژهٔ کتابخانه
from django.contrib.auth.models import User
from django.db import models


class Author(models.Model):
    """نویسنده — هر نویسنده می‌تواند چند کتاب داشته باشد."""
    name = models.CharField(max_length=100, unique=True)
    birth_year = models.IntegerField(null=True, blank=True)
    bio = models.TextField(blank=True)

    class Meta:
        ordering = ["name"]
        verbose_name = "نویسنده"
        verbose_name_plural = "نویسندگان"

    def __str__(self):
        return self.name

    def book_count(self):
        """متد کمکی — در template قابل استفاده است."""
        return self.books.count()
    book_count.short_description = "تعداد کتاب"


class Tag(models.Model):
    name = models.CharField(max_length=50, unique=True)

    def __str__(self):
        return self.name


class Book(models.Model):
    """کتاب — قلب پروژه."""
    title = models.CharField(max_length=200, db_index=True)
    author = models.ForeignKey(
        Author, on_delete=models.CASCADE,
        related_name="books", null=True, blank=True,
    )
    tags = models.ManyToManyField(Tag, blank=True, related_name="books")
    pages = models.IntegerField(default=0)
    price = models.DecimalField(max_digits=10, decimal_places=2, default=0)
    is_available = models.BooleanField(default=True)
    cover = models.ImageField(upload_to="covers/", null=True, blank=True)
    created_by = models.ForeignKey(
        User, on_delete=models.SET_NULL,
        null=True, blank=True, related_name="books",
    )
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)

    class Meta:
        ordering = ["-created_at"]
        verbose_name = "کتاب"
        verbose_name_plural = "کتاب‌ها"
        indexes = [models.Index(fields=["author", "-created_at"])]

    def __str__(self):
        return self.title


class Profile(models.Model):
    """پروفایل یک‌به‌یک کاربر — توسط سیگنال post_save ساخته می‌شود."""
    user = models.OneToOneField(
        User, on_delete=models.CASCADE, related_name="profile"
    )
    phone = models.CharField(max_length=15, blank=True)
    bio = models.TextField(blank=True)

    def __str__(self):
        return f"پروفایل {self.user.username}"
