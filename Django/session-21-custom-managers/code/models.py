# models.py — QuerySet و Manager سفارشی (جلسه ۲۱)
from django.db import models


class BookQuerySet(models.QuerySet):
    """متدهای زنجیره‌ای — همه QuerySet برمی‌گردانند تا قابل زنجیره باشند."""

    def available(self):
        # فقط کتاب‌های موجود
        return self.filter(is_available=True)

    def by_author(self, name):
        # جستجوی نام نویسنده (case-insensitive)
        return self.filter(author__name__icontains=name)

    def expensive(self, min_price):
        # کتاب‌های بالای قیمت مشخص
        return self.filter(price__gte=min_price)

    def recent(self, days=30):
        # کتاب‌های N روز اخیر
        from django.utils import timezone
        from datetime import timedelta
        since = timezone.now() - timedelta(days=days)
        return self.filter(created_at__gte=since)

    # متدهای aggregate — مقدار برمی‌گردانند نه QuerySet
    def total_pages(self):
        return self.aggregate(s=models.Sum("pages"))["s"] or 0


class BookManager(models.Manager):
    """مدیر که متدهای QuerySet سفارشی را expose می‌کند."""

    def get_queryset(self):
        # همیشه BookQuerySet برگردان — نه QuerySet پیش‌فرض
        return BookQuerySet(self.model, using=self._db)

    def available(self):
        return self.get_queryset().available()


class AuthorQuerySet(models.QuerySet):
    def with_books_count(self):
        # annotate → هر author با تعداد کتاب‌هایش
        return self.annotate(count=models.Count("books"))

    def senior(self):
        # نویسندگان متولد قبل از ۱۹۵۰
        return self.filter(birth_year__lt=1950)


class AuthorManager(models.Manager.from_queryset(AuthorQuerySet)):
    """from_queryset → همهٔ متدهای AuthorQuerySet روی objects می‌آیند."""
    pass


class Author(models.Model):
    name = models.CharField(max_length=100)
    birth_year = models.IntegerField()
    objects = AuthorManager()   # Author.objects.senior() کار می‌کند

    class Meta:
        ordering = ["name"]

    def __str__(self):
        return self.name


class Book(models.Model):
    title = models.CharField(max_length=200)
    author = models.ForeignKey(Author, on_delete=models.CASCADE, related_name="books")
    pages = models.IntegerField(default=0)
    price = models.DecimalField(max_digits=10, decimal_places=0, default=0)
    is_available = models.BooleanField(default=True)
    created_at = models.DateTimeField(auto_now_add=True)

    objects = BookManager()
    all_objects = models.Manager()   # همهٔ کتاب‌ها حتی بدون فیلتر manager

    def __str__(self):
        return self.title
