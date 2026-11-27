# جلسه ۴۲ — Logging در Django

## درس

### LOGGING dict در settings.py

```python
LOGGING = {
    "version": 1,
    "disable_existing_loggers": False,
    "formatters": {
        "verbose": {"format": "[{levelname}] {asctime} {name}: {message}", "style": "{"},
    },
    "handlers": {
        "console": {"class": "logging.StreamHandler", "formatter": "verbose"},
        "file": {
            "class": "logging.handlers.RotatingFileHandler",
            "filename": BASE_DIR / "logs" / "django.log",
            "maxBytes": 5_000_000, "backupCount": 3,
            "formatter": "verbose",
        },
    },
    "loggers": {
        "django": {"handlers": ["console", "file"], "level": "INFO"},
        "library": {"handlers": ["file"], "level": "DEBUG", "propagate": True},
    },
    "root": {"handlers": ["console"], "level": "WARNING"},
}
```

### استفاده در کد

```python
import logging
logger = logging.getLogger(__name__)

logger.debug("جزئیات")
logger.info("کاربر %s لاگین کرد", request.user.username)
logger.warning("دادهٔ مشکوک: %s", data)
logger.error("خطا در پردازش", exc_info=True)   # exc_info → traceback
```

## تمرین

برای اپ `library` یک logger اختصاصی با level `INFO` و handler فایل تنظیم کنید و در `book_save` یک `logger.info` بگذارید.

## جواب تمرین

```python
# settings.py — در LOGGING["loggers"]:
"library": {"handlers": ["file"], "level": "INFO", "propagate": True}

# views.py
import logging
logger = logging.getLogger("library")
logger.info("کتاب %s ذخیره شد", book.title)
```

## 📖 شرح فایل `code/settings_logging.py`

ساختار `LOGGING` سه لایه دارد:
- **`formatters`** — قالب رشتهٔ لاگ (`verbose` با level/time/name/line، `simple` کوتاه).
- **`handlers`** — `console` (StreamHandler)، `file` (RotatingFileHandler با maxBytes+backupCount برای جلوگیری از پر شدن دیسک)، `error_file` فقط ERROR+.
- **`loggers`** — `django` برای فریم‌ورک، `library` برای اپ شما با level=DEBUG، `celery`؛ `propagate` تعیین می‌کند لاگ به root هم برسد یا نه.

سطوح: DEBUG < INFO < WARNING < ERROR < CRITICAL؛ `logger.exception()` traceback کامل می‌دهد. هشدار: هرگز password/token/PII را log نکنید.
