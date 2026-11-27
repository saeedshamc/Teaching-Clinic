# celery_beat.py — تسک‌های دوره‌ای (جلسه ۳۳)
# این فایل در settings.py یا config/celery.py قرار می‌گیرد.

from celery.schedules import crontab


# ══════════════════════════════════════════════════════════════════
# روش ۱: CELERY_BEAT_SCHEDULE در settings.py
# ══════════════════════════════════════════════════════════════════
CELERY_BEAT_SCHEDULE = {
    # روزانه ساعت ۸ صبح — گزارش کتاب‌ها
    "daily-report": {
        "task": "library.tasks.daily_report",
        "schedule": crontab(hour=8, minute=0),
        "options": {"queue": "default"},
    },

    # هر ساعت — پاک‌سازی sessionهای قدیمی
    "hourly-cleanup": {
        "task": "library.tasks.cleanup_old_sessions",
        "schedule": 3600.0,    # ثانیه
    },

    # هر ۱۵ دقیقه — sync موجودی با سیستم خارجی
    "quarter-hourly-sync": {
        "task": "library.tasks.sync_inventory",
        "schedule": crontab(minute="*/15"),
    },

    # روزانه ساعت ۶ صبح — آمار کتاب‌های جدید
    "daily-new-books": {
        "task": "library.tasks.daily_new_books",
        "schedule": crontab(hour=6, minute=0),
    },

    # هفتگی — یکشنبه‌ها ساعت ۲ بامداد — backup
    "weekly-backup": {
        "task": "library.tasks.weekly_backup",
        "schedule": crontab(day_of_week="sunday", hour=2, minute=0),
    },
}

# ══════════════════════════════════════════════════════════════════
# روش ۲: django-celery-beat — زمان‌بندی در دیتابیس
# ══════════════════════════════════════════════════════════════════
# pip install django-celery-beat
# INSTALLED_APPS += ["django_celery_beat"]
# python manage.py migrate
#
# از shell:
#   from django_celery_beat.models import PeriodicTask, IntervalSchedule, CrontabSchedule
#
#   # interval — هر N ثانیه/دقیقه/ساعت/روز
#   every_hour = IntervalSchedule.objects.create(every=1, period="hours")
#   PeriodicTask.objects.create(
#       name="hourly-task",
#       task="library.tasks.hourly_task",
#       interval=every_hour,
#   )
#
#   # crontab — زمان دقیق
#   daily_8am = CrontabSchedule.objects.create(hour=8, minute=0)
#   PeriodicTask.objects.create(
#       name="daily-report",
#       task="library.tasks.daily_report",
#       crontab=daily_8am,
#   )
#
# از ادمین: /admin/django_celery_beat/periodictask/ → CRUD گرافیکی
#
# اجرا با scheduler دیتابیسی:
#   celery -A config beat -l info --scheduler django_celery_beat.schedulers:DatabaseScheduler


# ══════════════════════════════════════════════════════════════════
# تسک‌های نمونه برای beat
# ══════════════════════════════════════════════════════════════════
# # tasks.py
# @shared_task
# def daily_report():
#     stats = Book.objects.aggregate(
#         total=Count("id"),
#         available=Count("id", filter=Q(is_available=True)),
#     )
#     cache.set("daily_report", stats, 86400)
#     send_mail("Daily Report", str(stats), "admin@lib.com", ["admin@lib.com"])
#
# @shared_task
# def cleanup_old_sessions():
#     from django.contrib.sessions.models import Session
#     Session.objects.filter(expire_date__lt=timezone.now()).delete()
#
# @shared_task
# def daily_new_books():
#     today = timezone.now().date()
#     count = Book.objects.filter(created_at__date=today).count()
#     cache.set("daily_new_books", count, 86400)
