# پروژه ۸ — i18n + امنیت + Docker (جلسات ۳۶–۴۰)

## هدف
چندزبانه + امنیت production + کانتینری برای اپ کتابخانه.

## تسک‌ها

1. `templatetags/library_tags.py`: `total_books` (simple_tag) + `fa_number` (filter)
2. `USE_I18N=True` + `LANGUAGE_CODE="fa"` + ترجمهٔ «لیست کتاب‌ها» به en
3. `SecurityHeadersMiddleware` + `SECURE_*` settings برای production
4. `Dockerfile` multi-stage + `docker-compose.yml` با web/db/redis
5. `.dockerignore` + `.env` برای SECRET_KEY و DATABASE_URL

## معیارهای پذیرش

- [ ] `{% load library_tags %}{% total_books %}` کار می‌کند
- [ ] `{{ 1234|fa_number }}` → «۱۲۳۴»
- [ ] `docker-compose up` پروژه را بالا می‌آورد
- [ ] `DEBUG=False` + `ALLOWED_HOSTS` + `SECRET_KEY` از env

## فایل‌های کلیدی

`templatetags/library_tags.py`, `locale/en/LC_MESSAGES/django.po`, `Dockerfile`, `docker-compose.yml`, `.dockerignore`, `.env.example`
