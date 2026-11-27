# settings_snippet.py — بخش‌های مهم settings.py با توضیح
# این فایل کامل نیست؛ فقط قسمت‌های کلیدی را نشان می‌دهد.

# اپ‌های نصب‌شده — اپ خودتان را اینجا اضافه کنید
INSTALLED_APPS = [
    "django.contrib.admin",       # پنل ادمین
    "django.contrib.auth",        # سیستم کاربران و احراز هویت
    "django.contrib.contenttypes",
    "django.contrib.sessions",    # سشن کاربر
    "django.contrib.messages",    # پیام‌های یک‌بارمصرف
    "django.contrib.staticfiles", # فایل‌های استاتیک
    "library",                    # ← اپ ما
]

# دیتابیس — پیش‌فرض SQLite (فایل db.sqlite3 در ریشه پروژه)
DATABASES = {
    "default": {
        "ENGINE": "django.db.backends.sqlite3",
        "NAME": BASE_DIR / "db.sqlite3",
    }
}

# DEBUG=True یعنی صفحه‌های خطای دقیق با traceback نمایش داده می‌شود.
# هرگز در production True نگذارید!
DEBUG = True
ALLOWED_HOSTS = []   # در production دامنه را اینجا بنویسید

# فایل‌های استاتیک (CSS/JS/عکس‌های قالب)
STATIC_URL = "static/"
