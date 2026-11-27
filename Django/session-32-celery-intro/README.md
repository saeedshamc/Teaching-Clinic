# جلسه ۳۲ — Celery: پردازش پس‌زمینه

## درس

Celery تسک‌های سنگین (ایمیل، پردازش فایل، API خارجی) را **غیرهمزمان** اجرا می‌کند — کاربر منتظر نمی‌ماند.

### معماری

```
Web → Task Queue (Redis/RabbitMQ) → Worker → اجرا → نتیجه
```

### نصب و راه‌اندازی

```bash
pip install celery redis
# settings.py
CELERY_BROKER_URL = "redis://localhost:6379/0"
CELERY_RESULT_BACKEND = "redis://localhost:6379/0"
CELERY_ACCEPT_CONTENT = ["json"]
CELERY_TASK_SERIALIZER = "json"

# config/__init__.py
from celery import Celery
app = Celery("config")
app.config_from_object("django.conf:settings", namespace="CELERY")
app.autodiscover_tasks()
```

### تعریف تسک

```python
# library/tasks.py
from celery import shared_task
from django.core.mail import send_mail

@shared_task(bind=True, max_retries=3)
def send_welcome_email(self, user_id):
    """bind=True → self.retry() در دسترس است."""
    try:
        user = User.objects.get(id=user_id)
        send_mail(
            "خوش آمدید",
            f"سلام {user.username}",
            "noreply@example.com",
            [user.email],
            fail_silently=False,
        )
    except Exception as exc:
        raise self.retry(exc=exc, countdown=60)   # ۶۰ ثانیه بعد دوباره
```

### فراخوانی

```python
send_welcome_email.delay(user.id)      # async — فوری برمی‌گردد
send_welcome_email.apply_async(args=[user.id], countdown=300)  # ۵ دقیقه بعد
result = send_welcome_email.delay(user.id)
result.get(timeout=10)                 # منتظر نتیجه (در صورت نیاز)
```

### اجرا

```bash
celery -A config worker -l info         # worker
celery -A config beat -l info           # scheduler (جلسه بعد)
```

## کد نمونه

بالا کامنت‌گذاری شده — در `tasks.py` پیاده کنید.

## تمرین

یک تسک `generate_report` بنویسید که تعداد کتاب‌ها را محاسبه و در cache ذخیره کند.

## جواب تمرین

```python
@shared_task
def generate_report():
    from django.core.cache import cache
    from .models import Book
    cache.set("book_count", Book.objects.count(), 3600)
    return Book.objects.count()
```
