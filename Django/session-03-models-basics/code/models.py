# models.py — مدل‌های پایه با کامنت فارسی
# هر کلاس مدل = یک جدول در دیتابیس
from django.db import models


class Author(models.Model):
    """مدل نویسنده —CharField برای رشته با حداکثر طول."""
    name = models.CharField(max_length=100, verbose_name="نام نویسنده")
    birth_year = models.IntegerField(null=True, blank=True, verbose_name="سال تولد")
    bio = models.TextField(blank=True, verbose_name="زندگی‌نامه")

    class Meta:
        verbose_name = "نویسنده"
        verbose_name_plural = "نویسندگان"
        ordering = ["name"]          # ترتیب پیش‌فرض کوئری‌ها

    def __str__(self):
        # بدون این متد، در ادمین/shell شیء به شکل Author object نمایش داده می‌شود
        return self.name


class Book(models.Model):
    """مدل کتاب — AutoField id خودکار ساخته می‌شود."""
    title = models.CharField(max_length=200)
    pages = models.IntegerField(default=0)
    price = models.DecimalField(max_digits=8, decimal_places=2, default=0)
    published = models.DateField(null=True, blank=True)
    is_available = models.BooleanField(default=True)
    created_at = models.DateTimeField(auto_now_add=True)  # فقط یک‌بار هنگام insert
    updated_at = models.DateTimeField(auto_now=True)      # هر بار که ذخیره شود

    def __str__(self):
        return f"{self.title} ({self.pages} صفحه)"
