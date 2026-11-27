# جلسه ۳۳ — Celery Beat: تسک‌های دوره‌ای

## درس

Celery Beat تسک‌ها را در زمان‌های مشخص اجرا می‌کند — مثل cron.

### راه‌اندازی

```bash
pip install django-celery-beat   # صف‌بندی beat در دیتابیس (توصیه‌شده)
# settings: INSTALLED_APPS += "django_celery_beat"
```

### دو روش زمان‌بندی

**۱. در settings.py (ثابت):**

```python
from celery.schedules import crontab

CELERY_BEAT_SCHEDULE = {
    "daily-report": {
        "task": "library.tasks.daily_report",
        "schedule": crontab(hour=8, minute=0),   # هر روز ۸ صبح
    },
    "hourly-cleanup": {
        "task": "library.tasks.cleanup_old_sessions",
        "schedule": 3600.0,                       # هر ۳۶۰۰ ثانیه
    },
}
```

**۲. در دیتابیس با django-celery-beat (پویای، از ادمین قابل ویرایش):**

```bash
python manage.py migrate
python manage.py shell
>>> from django_celery_beat.models import PeriodicTask, IntervalSchedule
>>> schedule = IntervalSchedule.objects.create(every=1, period="hours")
>>> PeriodicTask.objects.create(
...     name="hourly-task",
...     task="library.tasks.hourly_task",
...     interval=schedule,
... )
```

### اجرای beat

```bash
celery -A config beat -l info --scheduler django_celery_beat.schedulers:DatabaseScheduler
```

## کد نمونه

بالا کامنت‌گذاری شده — در settings/tasks پیاده کنید.

## تمرین

یک تسک روزانه بنویسید که تعداد کتاب‌های جدید امروز را در cache ذخیره کند و هر روز ۶ صبح اجرا شود.

## جواب تمرین

```python
# settings.py
CELERY_BEAT_SCHEDULE = {
    "daily-new-books": {
        "task": "library.tasks.daily_new_books",
        "schedule": crontab(hour=6, minute=0),
    },
}

# tasks.py
@shared_task
def daily_new_books():
    from django.core.cache import cache
    from django.utils import timezone
    from .models import Book
    today = timezone.now().date()
    count = Book.objects.filter(created_at__date=today).count()
    cache.set("daily_new_books", count, 86400)
```
