# models.py — روابط ForeignKey / OneToOne / ManyToMany
from django.contrib.auth.models import User
from django.db import models


class Author(models.Model):
    name = models.CharField(max_length=100)

    def __str__(self):
        return self.name


class Book(models.Model):
    """هر کتاب یک نویسنده دارد → ForeignKey."""
    title = models.CharField(max_length=200)
    # on_delete=CASCADE: حذف نویسنده → حذف همهٔ کتاب‌هایش
    # related_name='books' → author.books.all() به‌جای author.book_set
    author = models.ForeignKey(
        Author,
        on_delete=models.CASCADE,
        related_name="books",
        verbose_name="نویسنده",
    )
    # ManyToMany: هر کتاب چند برچسب و هر برچسب چند کتاب
    tags = models.ManyToManyField("Tag", blank=True, related_name="books")

    def __str__(self):
        return self.title


class Tag(models.Model):
    name = models.CharField(max_length=50, unique=True)

    def __str__(self):
        return self.name


class Profile(models.Model):
    """پروفایل یک‌به‌یک با کاربر — OneToOneField."""
    user = models.OneToOneField(
        User,
        on_delete=models.CASCADE,      # حذف کاربر → حذف پروفایل
        related_name="profile",
    )
    phone = models.CharField(max_length=15, blank=True)
    avatar = models.ImageField(upload_to="avatars/", null=True, blank=True)

    def __str__(self):
        return f"پروفایل {self.user.username}"
