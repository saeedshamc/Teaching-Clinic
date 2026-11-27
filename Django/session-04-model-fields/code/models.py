# models.py — فیلدهای متنوع + choices + validators
from django.core.validators import MinValueValidator, MaxValueValidator
from django.db import models


class Product(models.Model):
    """مثال فیلدهای مختلف در یک مدل."""

    # متن کوتاه — max_length اجباری است
    name = models.CharField(max_length=120, verbose_name="نام محصول")

    # متن بلند بدون محدودیت طول
    description = models.TextField(blank=True, verbose_name="توضیحات")

    # عدد صحیح با بازهٔ مجاز از طریق validators
    stock = models.IntegerField(
        validators=[MinValueValidator(0)],
        default=0,
        verbose_name="موجودی",
    )

    # قیمت — DecimalField برای دقت مالی (FloatField خطای گردکردن دارد)
    price = models.DecimalField(
        max_digits=10, decimal_places=2,
        validators=[MinValueValidator(0)],
        verbose_name="قیمت",
    )

    # دسته‌بندی با choices — فقط مقادیرtuple اول مجازند
    CATEGORY_CHOICES = [
        ("book", "کتاب"),
        ("gadget", "گجت"),
        ("cloth", "پوشاک"),
    ]
    category = models.CharField(
        max_length=20, choices=CATEGORY_CHOICES, default="book",
    )

    is_active = models.BooleanField(default=True)
    release_date = models.DateField(null=True, blank=True)
    created_at = models.DateTimeField(auto_now_add=True)

    class Meta:
        ordering = ["-created_at"]   # جدیدترین‌ها اول
        verbose_name = "محصول"
        verbose_name_plural = "محصولات"

    def __str__(self):
        return f"{self.name} — {self.get_category_display()}"
        # get_category_display() برچسب فارسی choice را برمی‌گرداند
