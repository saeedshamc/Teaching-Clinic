# پروژه ۱۰ — Capstone نهایی: «کتابخانه دیجیتال» (جلسات ۴۶–۵۰)

## هدف
پروژهٔ نهایی که همهٔ ۵۰ جلسه را ترکیب می‌کند — تحویل production-ready.

## فیچرهای الزامی

1. **مدل‌ها**: Author, Book, Tag, Review, ReadingList, Profile, AuditLog
2. **API کامل**: `/api/books/`, `/api/reviews/`, `/api/users/` با JWT + pagination + filter + throttle
3. **Realtime**: WebSocket `/ws/chat/<room>/` برای announce + typing indicator
4. **Cache**: bestsellers + invalidate با signal
5. **Celery**: daily_report + send_email + beat schedule
6. **Admin**: CSV export + stats page + inlines
7. **Tests**: ≥۱۰ تست model + API
8. **Docker**: docker-compose با web+db+redis+celery+channels
9. **i18n**: fa/en switch
10. **Security**: SECURE_* + env vars + CSRF

## ساختار

```
library/
├── models.py  serializers.py  views.py  consumers.py
├── tasks.py  signals.py  admin.py  middleware.py
├── templatetags/  tests/  factories.py
└── templates/library/
config/: settings.py urls.py asgi.py celery.py
Dockerfile docker-compose.yml .env.example
```

## معیارهای پذیرش نهایی

- [ ] `docker-compose up` همهٔ سرویس‌ها را بالا می‌آورد
- [ ] `pytest`/`manage.py test` سبز
- [ ] `wscat ws://localhost:8000/ws/chat/general/` پیام real-time می‌گیرد
- [ ] README پروژه با اسکرین‌شات/توضیح هر فیچر
- [ ] `DEPLOYMENT.md` با گام‌های production
