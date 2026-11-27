# signals.py — گیرنده‌های سیگنال با کامنت فارسی
from django.db.models.signals import post_delete, post_save, pre_save
from django.dispatch import receiver

from .models import Book


@receiver(pre_save, sender=Book)
def book_pre_save(sender, instance, **kwargs):
    """قبل از ذخیره — می‌توانید فیلدها را تغییر دهید."""
    if not instance.title:
        instance.title = "بدون عنوان"


@receiver(post_save, sender=Book)
def book_post_save(sender, instance, created, **kwargs):
    """بعد از ذخیره — created=True یعنی INSERT، False یعنی UPDATE."""
    if created:
        print(f"[signal] کتاب جدید: {instance.title} (id={instance.pk})")
    else:
        print(f"[signal] کتاب ویرایش شد: {instance.title}")


@receiver(post_delete, sender=Book)
def book_deleted(sender, instance, **kwargs):
    """بعد از حذف — فیلدها هنوز در دسترس‌اند."""
    print(f"[signal] کتاب حذف شد: {instance.title}")


# نکات:
# ۱. sender=Book یعنی فقط برای Book — بدون sender برای همهٔ مدل‌ها
# ۲. kwargs شامل raw, using, update_fields است
# ۳. سیگنال‌ها را در apps.py.ready() لود کنید تا register شوند
