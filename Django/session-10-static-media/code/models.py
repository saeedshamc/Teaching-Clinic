# models.py — فیلدهای فایل (static/media)
from django.db import models


class Book(models.Model):
    title = models.CharField(max_length=200)

    # FileField: هر فایلی — ImageField همان است + اعتبارسنجی تصویر
    cover = models.ImageField(
        upload_to="covers/",        # فایل‌ها در media/covers/ ذخیره می‌شوند
        null=True, blank=True,
        verbose_name="جلد کتاب",
    )
    sample_pdf = models.FileField(
        upload_to="samples/%Y/%m/", # می‌توانید از strftime در مسیر استفاده کنید
        null=True, blank=True,
    )

    def __str__(self):
        return self.title
