# جلسه ۱۷ — سیگنال‌ها (Signals)

## درس

سیگنال‌ها به شما اجازه می‌دهند **به رویدادهای مدل** واکنش نشان دهید بدون اینکه کد مدل را تغییر دهید.

### سیگنال‌های پرکاربرد

| سیگنال | زمان |
|---|---|
| `pre_save` / `post_save` | قبل/بعد از `save()` |
| `pre_delete` / `post_delete` | قبل/بعد از `delete()` |
| `m2m_changed` | تغییر رابطهٔ ManyToMany |
| `pre_init` / `post_init` | هنگام ساخت instance از دیتابیس |

### ساخت گیرنده (receiver)

```python
# library/signals.py
from django.db.models.signals import post_save
from django.dispatch import receiver
from .models import Book

@receiver(post_save, sender=Book)
def book_saved(sender, instance, created, **kwargs):
    """هر بار که کتاب save شود این تابع اجرا می‌شود."""
    if created:
        print(f"کتاب جدید ساخته شد: {instance.title}")
    else:
        print(f"کتاب ویرایش شد: {instance.title}")
```

### اتصال سیگنال‌ها

سیگنال‌ها باید **register** شوند — بهترین جا `apps.py`:

```python
# library/apps.py
from django.apps import AppConfig

class LibraryConfig(AppConfig):
    name = "library"

    def ready(self):
        import library.signals   # noqa — لود کردن سیگنال‌ها
```

## کد نمونه

`code/signals.py` را ببینید — مثال‌های واقعی.

## تمرین

یک سیگنال `post_save` برای `Author` بنویسید که وقتی نویسنده ساخته شد، یک `Profile` خالی برایش بسازد (one-to-one).

## جواب تمرین

```python
from django.db.models.signals import post_save
from django.dispatch import receiver
from .models import Author, Profile

@receiver(post_save, sender=Author)
def create_profile(sender, instance, created, **kwargs):
    if created:
        Profile.objects.create(author=instance)  # فرض: Profile به Author لینک است
```
