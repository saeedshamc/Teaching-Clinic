# tasks.py — Celery tasks (جلسه ۳۲)
from celery import shared_task
from django.core.mail import send_mail
from django.core.cache import cache
from django.contrib.auth import get_user_model
from .models import Book

User = get_user_model()


@shared_task(bind=True, max_retries=3, default_retry_delay=60)
def send_welcome_email(self, user_id):
    """
    ارسال ایمیل خوش‌آمد — با retry خودکار در صورت خطا.
    bind=True → self.retry() در دسترس است.
    """
    try:
        user = User.objects.get(id=user_id)
        send_mail(
            subject="خوش آمدید به کتابخانه",
            message=f"سلام {user.username}،\nاز ثبت‌نام شما سپاسگزاریم.",
            from_email="noreply@library.com",
            recipient_list=[user.email],
            fail_silently=False,
        )
        return f"email sent to {user.email}"
    except User.DoesNotExist:
        # کاربر پیدا نشد — retry فایده ندارد
        return "user not found"
    except Exception as exc:
        # خطای موقت (SMTP down و...) → retry
        raise self.retry(exc=exc)


@shared_task
def generate_report():
    """تسک ساده — آمار کتاب‌ها را در cache ذخیره می‌کند."""
    stats = {
        "total_books": Book.objects.count(),
        "available": Book.objects.filter(is_available=True).count(),
        "avg_pages": Book.objects.aggregate(avg=models.Avg("pages"))["avg"],
    }
    cache.set("library_report", stats, 3600)
    return stats


@shared_task
def process_book_import(file_path):
    """پردازش فایل بزرگ — مثلاً import CSV کتاب‌ها."""
    import csv
    created = 0
    with open(file_path, "r", encoding="utf-8") as f:
        reader = csv.DictReader(f)
        for row in reader:
            Book.objects.get_or_create(
                title=row["title"],
                defaults={"pages": int(row.get("pages", 0))},
            )
            created += 1
    return f"{created} books imported"


# ══════════════════════════════════════════════════════════════════
# در views.py — فراخوانی تسک
# ══════════════════════════════════════════════════════════════════
# from .tasks import send_welcome_email, generate_report
#
# def register(request):
#     user = User.objects.create_user(...)
#     send_welcome_email.delay(user.id)   # async — فوری برمی‌گردد
#     # send_welcome_email.apply_async(args=[user.id], countdown=60)  # ۶۰ ثانیه بعد
#     return redirect("home")
#
# result = generate_report.delay()
# result.get(timeout=10)   # منتظر نتیجه — فقط وقتی واقعاً لازم است

# ══════════════════════════════════════════════════════════════════
# settings.py — تنظیمات Celery
# ══════════════════════════════════════════════════════════════════
# INSTALLED_APPS += ["django_celery_results"]   # ذخیرهٔ نتیجه در DB (اختیاری)
#
# CELERY_BROKER_URL = "redis://localhost:6379/0"      # Redis به‌عنوان broker
# CELERY_RESULT_BACKEND = "django-db"                  # یا "redis://..."
# CELERY_ACCEPT_CONTENT = ["json"]
# CELERY_TASK_SERIALIZER = "json"
# CELERY_RESULT_SERIALIZER = "json"
# CELERY_TIMEZONE = "Asia/Tehran"
#
# # config/__init__.py
# # from celery import Celery
# # app = Celery("config")
# # app.config_from_object("django.conf:settings", namespace="CELERY")
# # app.autodiscover_tasks()

# ══════════════════════════════════════════════════════════════════
# اجرا:
#   celery -A config worker -l info --pool=solo   # worker (solo برای dev)
#   celery -A config beat -l info                  # scheduler (جلسه ۳۳)
