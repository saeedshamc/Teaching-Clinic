# models_extra.py — مدل‌های اضافی پروژهٔ نهایی (Review, ReadingList)
from django.conf import settings
from django.core.validators import MaxValueValidator, MinValueValidator
from django.db import models


class Review(models.Model):
    """نظر کاربر برای کتاب — هر کاربر فقط یک review برای هر کتاب."""
    book = models.ForeignKey(
        "Book", on_delete=models.CASCADE, related_name="reviews"
    )
    user = models.ForeignKey(
        settings.AUTH_USER_MODEL, on_delete=models.CASCADE
    )
    rating = models.IntegerField(
        validators=[MinValueValidator(1), MaxValueValidator(5)]
    )
    comment = models.TextField(blank=True)
    created_at = models.DateTimeField(auto_now_add=True)

    class Meta:
        unique_together = ("book", "user")
        ordering = ["-created_at"]

    def __str__(self):
        return f"{self.user.username}: {self.rating}/5 برای {self.book.title}"


class ReadingList(models.Model):
    """لیست خواندن کاربر — نشان می‌کند چه کتاب‌هایی را ذخیره کرده."""
    user = models.ForeignKey(
        settings.AUTH_USER_MODEL, on_delete=models.CASCADE,
        related_name="reading_lists",
    )
    book = models.ForeignKey(
        "Book", on_delete=models.CASCADE, related_name="in_lists"
    )
    added_at = models.DateTimeField(auto_now_add=True)

    class Meta:
        unique_together = ("user", "book")
        ordering = ["-added_at"]

    def __str__(self):
        return f"{self.user.username} → {self.book.title}"
