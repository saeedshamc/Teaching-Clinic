# جلسه ۴۱ — فایل‌های Static در Production

## درس

### جریان static در production

```
DEBUG=False → collectstatic → STATIC_ROOT → Nginx/CDN سرو می‌کند
```

### تنظیمات

```python
# settings.py
STATIC_URL = "/static/"
STATIC_ROOT = BASE_DIR / "staticfiles"        # collectstatic اینجا جمع می‌کند
STATICFILES_DIRS = [BASE_DIR / "library" / "static"]   # منابع قبل از collect

# فشرده‌سازی + hash برای cache-busting
STATICFILES_STORAGE = "django.contrib.staticfiles.storage.ManifestStaticFilesStorage"
# نسخه‌های جدید Django: STORAGES = {"staticfiles": {"BACKEND": "...ManifestStaticFilesStorage"}}
```

### whitenoise — بدون Nginx برای static

```bash
pip install whitenoise
# settings.py
MIDDLEWARE = ["whitenoise.middleware.WhiteNoiseMiddleware", ...]  # بعد از SecurityMiddleware
STATICFILES_STORAGE = "whitenoise.storage.CompressedManifestStaticFilesStorage"
```

### collectstatic

```bash
python manage.py collectstatic --noinput   # همهٔ staticها را در STATIC_ROOT جمع می‌کند
python manage.py collectstatic --clear     # پاک‌سازی اول (اختیاری)
```

## تمرین

یک تنظیم `STORAGES` برای `ManifestStaticFilesStorage` بنویسید و توضیح دهید چرا hash در نام فایل مفید است.

## جواب تمرین

```python
STORAGES = {
    "staticfiles": {
        "BACKEND": "django.contrib.staticfiles.storage.ManifestStaticFilesStorage",
    },
}
# hash در نام → cache-busting: وقتی فایل عوض شود URL هم عوض می‌شود و CDN/CDN مرورگر نسخهٔ قدیمی را serve نمی‌کند.
```
