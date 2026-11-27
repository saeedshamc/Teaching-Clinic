# settings_logging.py — Logging در Django (جلسه ۴۲)
# این بخش را در settings.py اضافه کنید.

import os
from pathlib import Path

BASE_DIR = Path(__file__).resolve().parent.parent
LOG_DIR = BASE_DIR / "logs"
LOG_DIR.mkdir(exist_ok=True)

# ══════════════════════════════════════════════════════════════════
# settings.py — LOGGING
# ══════════════════════════════════════════════════════════════════

LOGGING = {
    "version": 1,
    "disable_existing_loggers": False,

    "formatters": {
        "verbose": {
            "format": "[{levelname}] {asctime} {name}:{lineno} — {message}",
            "style": "{",
            "datefmt": "%Y-%m-%d %H:%M:%S",
        },
        "simple": {
            "format": "{levelname} {message}",
            "style": "{",
        },
    },

    "handlers": {
        "console": {
            "class": "logging.StreamHandler",
            "formatter": "verbose",
        },
        "file": {
            "class": "logging.handlers.RotatingFileHandler",
            "filename": LOG_DIR / "django.log",
            "maxBytes": 5_000_000,      # ۵ مگابایت
            "backupCount": 3,            # ۳ فایل backup
            "formatter": "verbose",
        },
        "error_file": {
            "class": "logging.handlers.RotatingFileHandler",
            "filename": LOG_DIR / "errors.log",
            "maxBytes": 5_000_000,
            "backupCount": 5,
            "formatter": "verbose",
            "level": "ERROR",
        },
        # "mail_admins": {
        #     "class": "django.utils.log.AdminEmailHandler",
        #     "level": "ERROR",
        # },
    },

    "loggers": {
        # لاگ‌های خود Django
        "django": {
            "handlers": ["console", "file"],
            "level": "INFO",
            "propagate": False,
        },
        # لاگ‌های اپ library — سطح DEBUG برای جزئیات بیشتر
        "library": {
            "handlers": ["file", "error_file"],
            "level": "DEBUG",
            "propagate": True,
        },
        # لاگ‌های Celery
        "celery": {
            "handlers": ["console"],
            "level": "INFO",
            "propagate": False,
        },
    },

    "root": {
        "handlers": ["console"],
        "level": "WARNING",
    },
}

# ══════════════════════════════════════════════════════════════════
# استفاده در کد
# ══════════════════════════════════════════════════════════════════
# import logging
# logger = logging.getLogger(__name__)       # یا "library" برای اختصاصی
#
# logger.debug("جزئیات توسعه")
# logger.info("کاربر %s لاگین کرد", request.user.username)
# logger.warning("دادهٔ مشکوک: %s", data)
# logger.error("خطا در پردازش", exc_info=True)   # exc_info → traceback کامل
#
# # در ویو:
# try:
#     book = Book.objects.get(pk=pk)
# except Book.DoesNotExist:
#     logger.warning("Book %s یافت نشد", pk)
#     return Http404()
#
# # برای exception:
# try:
#     process_payment()
# except Exception:
#     logger.exception("پرداخت ناموفق")   # exception → error + traceback

# ══════════════════════════════════════════════════════════════════
# Log levels:
# ══════════════════════════════════════════════════════════════════
# DEBUG    → جزئیات توسعه (متغیرها، flow)
# INFO     → رویدادهای عادی (user login, task done)
# WARNING  → مشکل بالقوه (deprecated, retry)
# ERROR    → خطا که بازیابی شده (exception گرفته شده)
# CRITICAL → خطای بحرانی (system down)
#
# نکات:
# - هرگز password/token/PII را log نکنید
# - از logger.exception() برای traceback کامل استفاده کنید
# - structured logging (JSON) برای production بزرگ بهتر است
# - RotatingFileHandler برای جلوگیری از پر شدن دیسک
