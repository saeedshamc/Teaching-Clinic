# settings_static.py — فایل‌های Static در Production (جلسه ۴۱)
# این بخش را در settings.py اضافه/جایگزین کنید.

# ══════════════════════════════════════════════════════════════════
# settings.py — Static files
# ══════════════════════════════════════════════════════════════════

# URL عمومی برای فایل‌های static — مرورگر این مسیر را request می‌کند
STATIC_URL = "/static/"

# مسیر نهایی collectstatic — همهٔ staticها اینجا جمع می‌شوند
STATIC_ROOT = BASE_DIR / "staticfiles"

# مسیرهای منبع static قبل از collectstatic
STATICFILES_DIRS = [
    BASE_DIR / "library" / "static",   # static/library/style.css
]

# ══════════════════════════════════════════════════════════════════
# روش ۱: ManifestStaticFilesStorage — hash در نام فایل
# ══════════════════════════════════════════════════════════════════
# STORAGES = {
#     "default": {
#         "BACKEND": "django.core.files.storage.FileSystemStorage",
#     },
#     "staticfiles": {
#         "BACKEND": "django.contrib.staticfiles.storage.ManifestStaticFilesStorage",
#     },
# }
#
# # برای پروژه‌هایی که whitenoise ندارند، این را استفاده کنید.
# # hash در نام → cache-busting خودکار

# ══════════════════════════════════════════════════════════════════
# روش ۲: WhiteNoise — بدون Nginx برای static
# ══════════════════════════════════════════════════════════════════
# pip install whitenoise
#
# MIDDLEWARE = [
#     "django.middleware.security.SecurityMiddleware",
#     "whitenoise.middleware.WhiteNoiseMiddleware",   # ← اینجا
#     "django.contrib.sessions.middleware.SessionMiddleware",
#     # ...
# ]
#
# STORAGES = {
#     "default": {
#         "BACKEND": "django.core.files.storage.FileSystemStorage",
#     },
#     "staticfiles": {
#         "BACKEND": "whitenoise.storage.CompressedManifestStaticFilesStorage",
#     },
# }
#
# # whitenoise خودش gzip + hash + cache-control را مدیریت می‌کند

# ══════════════════════════════════════════════════════════════════
# collectstatic — جمع‌آوری فایل‌ها
# ══════════════════════════════════════════════════════════════════
# python manage.py collectstatic --noinput   # جمع‌آوری
# python manage.py collectstatic --clear     # پاک‌سازی اول (اختیاری)
# python manage.py findstatic style.css      # پیدا کردن مسیر
#
# در production این را در deploy script یا Dockerfile RUN کنید.

# ══════════════════════════════════════════════════════════════════
# نکات:
# ══════════════════════════════════════════════════════════════════
# - static → فایل‌های پروژه (CSS/JS/عکس‌های ثابت)
# - media → فایل‌های آپلود کاربر (ImageField, FileField)
# - STATIC_ROOT فقط در production استفاده می‌شود — در dev خالی می‌ماند
# - hash در نام فایل → وقتی فایل عوض شود URL هم عوض می‌شود → cache-busting
# - برای CDN: STATIC_URL = "https://cdn.example.com/static/"
# - whitenoise برای پروژه‌های کوچک/متوسط کافی است؛ برای scale بزرگ از Nginx/CDN استفاده کنید
