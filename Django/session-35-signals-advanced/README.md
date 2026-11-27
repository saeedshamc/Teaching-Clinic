# جلسه ۳۵ — سیگنال‌های پیشرفته و Audit Log

## درس

### m2m_changed — تغییر روابط چندبه‌چند

```python
from django.db.models.signals import m2m_changed
from django.dispatch import receiver

@receiver(m2m_changed, sender=Book.tags.through)
def tags_changed(sender, instance, action, **kwargs):
    """action: pre_add, post_add, pre_remove, post_remove, pre_clear, post_clear."""
    if action == "post_add":
        print(f"تگ به {instance.title} اضافه شد")
```

### Audit Log — ثبت تاریخچهٔ تغییرات

```python
class AuditLog(models.Model):
    user = models.ForeignKey(User, null=True, on_delete=models.SET_NULL)
    model = models.CharField(max_length=100)
    object_id = models.IntegerField()
    action = models.CharField(max_length=10)   # create/update/delete
    changes = models.JSONField(default=dict)
    timestamp = models.DateTimeField(auto_now_add=True)

@receiver(post_save, sender=Book)
def audit_book(sender, instance, created, **kwargs):
    AuditLog.objects.create(
        user=getattr(instance, "_request_user", None),
        model="Book", object_id=instance.pk,
        action="create" if created else "update",
        changes={"title": instance.title},
    )
```

### signal قوی (strong ref) در برابر weak

`@receiver` به‌صورت پیش‌فرض weak reference است — اگر تابع garbage collection شود، سیگنال کار نمی‌کند. برای توابع ماژول‌لول معمولاً مشکلی نیست چون در ماژول زنده‌اند.

## کد نمونه

بالا کامنت‌گذاری شده — در `signals.py` پیاده کنید.

## تمرین

یک audit log ساده بسازید که هر `post_save` روی `Book` یک رکورد با فیلدهای `action` و `object_repr` ثبت کند.

## جواب تمرین

```python
@receiver(post_save, sender=Book)
def log_book(sender, instance, created, **kwargs):
    AuditLog.objects.create(
        model="Book", object_id=instance.pk,
        action="create" if created else "update",
        object_repr=str(instance),
    )
```
