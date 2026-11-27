# جلسه ۳۸ — امنیت در Django

## درس

### محافظت‌های پیش‌فرض جنگو

| تهدید | محافظ Django |
|---|---|
| CSRF | `CsrfViewMiddleware` + `{% csrf_token %}` |
| SQL Injection | ORM از پارامترسازی استفاده می‌کند |
| XSS | خروجی قالب auto-escape است (`{{ }}`) |
| Clickjacking | `X-Frame-Options: DENY` |

### تنظیمات امنیتی production

```python
# settings.py — DEBUG=False حتماً
SECURE_BROWSER_XSS_FILTER = True
SECURE_CONTENT_TYPE_NOSNIFF = True
X_FRAME_OPTIONS = "DENY"           # clickjacking
SECURE_SSL_REDIRECT = True        # HTTP → HTTPS
SESSION_COOKIE_SECURE = True      # cookie فقط روی HTTPS
CSRF_COOKIE_SECURE = True
SECURE_HSTS_SECONDS = 31536000     # HTTP Strict Transport Security
SECURE_HSTS_INCLUDE_SUBDOMAINS = True
SECURE_PROXY_SSL_HEADER = ("HTTP_X_FORWARDED_PROTO", "https")
```

### SECRET_KEY

هرگز در git قرار ندهید:

```python
# settings.py
import os
SECRET_KEY = os.environ.get("DJANGO_SECRET_KEY", "dev-only-key-change-me")
```

### نکات دستی

- `mark_safe`/`|safe` را فقط برای محتوای trusted استفاده کنید
- `validate_password` / `User.objects.create_user` رمز را hash می‌کند — هرگز plain ذخیره نکنید
- `ALLOWED_HOSTS` را محدود کنید
- فایل‌های آپلودی را validate کنید (نوع، حجم)

## کد نمونه

بالا کامنت‌گذاری شده — در settings پیاده کنید.

## تمرین

یک middleware بنویسید که header `X-Content-Type-Options: nosniff` و `Referrer-Policy` را به همهٔ پاسخ‌ها اضافه کند.

## جواب تمرین

```python
class SecurityHeadersMiddleware:
    def __init__(self, get_response):
        self.get_response = get_response
    def __call__(self, request):
        response = self.get_response(request)
        response["X-Content-Type-Options"] = "nosniff"
        response["Referrer-Policy"] = "strict-origin-when-cross-origin"
        return response
```

## 📖 شرح فایل `code/middleware.py`

- `SecurityHeadersMiddleware` — الگوی `__init__(get_response)`+`__call__`؛ روی response این هدرها را می‌گذارد: `X-Content-Type-Options: nosniff` (ضد MIME sniffing)، `Referrer-Policy` (چه اطلاعاتی به سایت‌های دیگر می‌رود)، `Permissions-Policy` (بستن camera/mic/geo)، `COOP`.
- کامنت پایانی: تنظیمات `SECURE_*` برای production — `SECURE_SSL_REDIRECT`، `SESSION_COOKIE_SECURE`، `CSRF_COOKIE_SECURE`، `SECURE_HSTS_SECONDS`، و `SECRET_KEY` از env. `SecurityMiddleware` جنگو خودش هم هدرهای پایه می‌گذارد.
