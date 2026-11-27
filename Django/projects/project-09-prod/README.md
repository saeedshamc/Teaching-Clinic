# پروژه ۹ — Production-ready: static + logging + تست + API v2 (جلسات ۴۱–۴۵)

## هدف
آماده‌سازی اپ برای production با مانیتورینگ و تست پیشرفته.

## تسک‌ها

1. `STATICFILES_STORAGE = ManifestStaticFilesStorage` + `collectstatic`
2. `LOGGING` با `RotatingFileHandler` برای `library` logger + `logger.info` در ویوها
3. `APITestCase` برای BookAPI + `BookFactory` (factory_boy) — حداقل ۸ تست
4. API v2: `/api/v2/books/` با `BookSerializerV2` (فیلدهای بیشتر) در کنار v1
5. `throttle_scope="burst"` روی endpointهای سنگین

## معیارهای پذیرش

- [ ] `python manage.py test` سبز با ≥۸ تست API
- [ ] `logs/django.log` پر می‌شود
- [ ] `GET /api/v2/books/` فیلدهای v2 را برمی‌گرداند
- [ ] static files با hash در نام سرو می‌شوند

## فایل‌های کلیدی

`settings.py` (STORAGES, LOGGING), `tests/test_api.py`, `factories.py`, `serializers.py` (V1/V2), `views.py`
