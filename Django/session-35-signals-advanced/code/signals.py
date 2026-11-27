# signals.py — سیگنال‌های پیشرفته و Audit Log (جلسه ۳۵)
from django.db.models.signals import post_save, post_delete, m2m_changed, pre_save
from django.dispatch import receiver
from django.utils import timezone
from .models import Book, Tag, AuditLog


# ══════════════════════════════════════════════════════════════════
# Audit Log — ثبت تاریخچهٔ تغییرات
# ══════════════════════════════════════════════════════════════════

@receiver(post_save, sender=Book)
def audit_book_save(sender, instance, created, **kwargs):
    """هر save روی Book → یک رکورد AuditLog."""
    AuditLog.objects.create(
        model="Book",
        object_id=instance.pk,
        action="create" if created else "update",
        object_repr=str(instance),
        changes={
            "title": instance.title,
            "is_available": instance.is_available,
        },
    )


@receiver(post_delete, sender=Book)
def audit_book_delete(sender, instance, **kwargs):
    """حذف Book → audit با action=delete."""
    AuditLog.objects.create(
        model="Book",
        object_id=instance.pk,
        action="delete",
        object_repr=str(instance),
    )


# ══════════════════════════════════════════════════════════════════
# m2m_changed — تغییر روابط چندبه‌چند
# ══════════════════════════════════════════════════════════════════

@receiver(m2m_changed, sender=Book.tags.through)
def book_tags_changed(sender, instance, action, pk_set, **kwargs):
    """
    action: pre_add, post_add, pre_remove, post_remove, pre_clear, post_clear
    pk_set: set شناسه‌های tagهای تغییرکرده
    """
    if action == "post_add":
        # تگ به کتاب اضافه شد
        print(f"{instance.title}: {len(pk_set)} تگ اضافه شد")
    elif action == "post_remove":
        print(f"{instance.title}: {len(pk_set)} تگ حذف شد")
    elif action == "post_clear":
        print(f"{instance.title}: همهٔ تگ‌ها پاک شد")


# ══════════════════════════════════════════════════════════════════
# pre_save — قبل از ذخیره (برای محاسبهٔ فیلد)
# ══════════════════════════════════════════════════════════════════

@receiver(pre_save, sender=Book)
def book_pre_save(sender, instance, **kwargs):
    """قبل از save — مثلاً محاسبهٔ slug یا normalize کردن title."""
    if instance.title:
        instance.title = instance.title.strip().title()


# ══════════════════════════════════════════════════════════════════
# سیگنال برای مدل‌های دیگر — تمپلیت
# ══════════════════════════════════════════════════════════════════

@receiver(post_save)
def global_audit(sender, instance, created, **kwargs):
    """
    Audit log سراسری — برای همهٔ مدل‌ها.
    sender = کلاسی که save شده.
    """
    model_name = sender.__name__
    AuditLog.objects.create(
        model=model_name,
        object_id=getattr(instance, "pk", None),
        action="create" if created else "update",
        object_repr=str(instance)[:200],
    )


# ══════════════════════════════════════════════════════════════════
# مدل AuditLog (در models.py)
# ══════════════════════════════════════════════════════════════════
# class AuditLog(models.Model):
#     user = models.ForeignKey(User, null=True, blank=True,
#                              on_delete=models.SET_NULL)
#     model = models.CharField(max_length=100)
#     object_id = models.IntegerField(null=True, blank=True)
#     action = models.CharField(max_length=10)   # create/update/delete
#     object_repr = models.CharField(max_length=200, blank=True)
#     changes = models.JSONField(default=dict, blank=True)
#     timestamp = models.DateTimeField(auto_now_add=True)
#
#     class Meta:
#         ordering = ["-timestamp"]
#         indexes = [models.Index(fields=["model", "object_id"])]


# ══════════════════════════════════════════════════════════════════
# apps.py — اتصال سیگنال‌ها
# ══════════════════════════════════════════════════════════════════
# class LibraryConfig(AppConfig):
#     default_auto_field = "django.db.models.BigAutoField"
#     name = "library"
#
#     def ready(self):
#         # سیگنال‌ها اینجا import می‌شوند تا در app registry ثبت شوند
#         import library.signals   # noqa


# ══════════════════════════════════════════════════════════════════
# نکات مهم:
# ══════════════════════════════════════════════════════════════════
# - receiver به‌صورت پیش‌فرض weak reference است؛ اگر تابع GC شود کار نمی‌کند.
# - برای توابع ماژول‌لول معمولاً مشکلی نیست چون در حافظه می‌مانند.
# - سیگنال‌ها در migration اجرا نمی‌شوند — فقط در runtime.
# - برای کارهای سنگین از transaction.on_commit استفاده کنید:
#     from django.db import transaction
#     transaction.on_commit(lambda: send_email.delay(...))
# - سیگنال برای side-effect (email/log/notify/cache invalidate) — نه برای validation.
