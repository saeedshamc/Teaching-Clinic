# پروژه ۷ — Celery + Cache + Channels (جلسات ۳۱–۳۵)

## هدف
زیرساخت real-time و async برای اپ کتابخانه.

## تسک‌ها

1. `CACHES` با Redis + `get_or_set` برای «پرفروش‌ترین‌ها»
2. `@shared_task send_review_notification` — ایمیل هنگام review جدید
3. `CELERY_BEAT_SCHEDULE`: تسک روزانه `daily_report` (۶ صبح)
4. `ChatConsumer` برای اتاق «announcements» — announce شدن کتاب جدید
5. `AuditLog` model + `post_save` روی Book → ثبت create/update/delete

## معیارهای پذیرش

- [ ] `cache.get("bestsellers")` بدون کوئری برمی‌گردد
- [ ] `celery -A config worker` تسک را اجرا می‌کند
- [ ] `wscat ws://localhost:8000/ws/chat/announcements/` پیام می‌گیرد
- [ ] هر save روی Book یک AuditLog می‌سازد

## فایل‌های کلیدی

`tasks.py`, `consumers.py`, `routing.py`, `signals.py`, `models.py` (AuditLog), `settings.py` (CELERY_*, CHANNEL_LAYERS)
