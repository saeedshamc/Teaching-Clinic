# جلسه ۲۷: لاگ‌نویسی (Logging) — ۱.۵ ساعت

## 🎯 اهداف یادگیری

- تفاوت `print` و `logging`
- سطوح لاگ: DEBUG, INFO, WARNING, ERROR, CRITICAL
- پیکربندی با `basicConfig` و `dictConfig`
- Handlerها: Console, File, RotatingFile
- Logger سلسله‌مراتبی و `__name__`
- الگوهای real-world: اپلیکیشن، exception logging

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | چرا logging؟ |
| 10–25 | سطوح و basicConfig |
| 25–40 | format و timestamp |
| 40–55 | FileHandler و RotatingFileHandler |
| 55–70 | logger چندماژوله |
| 70–85 | تمرین |
| 85–90 | جمع‌بندی |

---

## ۱. چرا logging؟

| print | logging |
|-------|---------|
| بدون سطح اهمیت | DEBUG تا CRITICAL |
| فقط stdout | فایل، شبکه، syslog |
| غیرقابل فیلتر | فیلتر بر اساس سطح |
| بدون timestamp | زمان، ماژول، خط |

```python
import logging
logging.warning("اتصال کند است")  # فقط WARNING+ نمایش داده می‌شود اگر level=WARNING
```

---

## ۲. سطوح لاگ

| سطح | عدد | کاربرد |
|-----|-----|--------|
| DEBUG | 10 | جزئیات دیباگ |
| INFO | 20 | جریان عادی |
| WARNING | 30 | هشدار |
| ERROR | 40 | خطا |
| CRITICAL | 50 | خرابی جدی |

```python
logging.debug("مقدار x=%s", x)
logging.info("کاربر وارد شد: %s", username)
logging.warning("حافظه کم")
logging.error("پرداخت ناموفق")
logging.critical("سرویس down")
```

---

## ۳. basicConfig

```python
import logging

logging.basicConfig(
    level=logging.INFO,
    format="%(asctime)s | %(levelname)-8s | %(message)s",
    datefmt="%Y-%m-%d %H:%M:%S",
)

logging.info("برنامه شروع شد")
```

**نکته:** `basicConfig` فقط بار اول اثر دارد.

---

## ۴. لاگ در فایل

```python
logging.basicConfig(
    level=logging.DEBUG,
    format="%(levelname)s: %(message)s",
    filename="app.log",
    filemode="a",
    encoding="utf-8",
)
```

---

## ۵. Handler جداگانه

```python
logger = logging.getLogger("myapp")
logger.setLevel(logging.DEBUG)

console = logging.StreamHandler()
console.setLevel(logging.INFO)
console.setFormatter(logging.Formatter("%(levelname)s: %(message)s"))

file_handler = logging.FileHandler("app.log", encoding="utf-8")
file_handler.setLevel(logging.DEBUG)
file_handler.setFormatter(logging.Formatter("%(asctime)s %(message)s"))

logger.addHandler(console)
logger.addHandler(file_handler)
logger.info("پیام")
```

---

## ۶. RotatingFileHandler

```python
from logging.handlers import RotatingFileHandler

handler = RotatingFileHandler(
    "app.log", maxBytes=1_000_000, backupCount=5, encoding="utf-8"
)
handler.setFormatter(logging.Formatter("%(asctime)s %(message)s"))
logger.addHandler(handler)
```

---

## ۷. exception logging

```python
try:
    result = 10 / 0
except ZeroDivisionError:
    logging.exception("تقسیم بر صفر")  # traceback کامل
```

یا:

```python
logging.error("خطا: %s", exc, exc_info=True)
```

---

## ۸. Logger سلسله‌مراتبی

```python
# در main.py
logger = logging.getLogger(__name__)  # __main__

# در utils.py
logger = logging.getLogger(__name__)  # utils
```

هر ماژول logger خودش را دارد؛ سطح root تعیین می‌کند چه چیزی نمایش داده شود.

---

## ۹. lazy formatting

```python
# خوب — فقط اگر INFO فعال باشد format می‌شود
logging.info("کاربر %s با id %d وارد شد", name, user_id)

# بد — همیشه string ساخته می‌شود
logging.info(f"کاربر {name} با id {user_id} وارد شد")
```

---

## ۱۰. غیرفعال کردن logger کتابخانه

```python
logging.getLogger("urllib3").setLevel(logging.WARNING)
```

---

## ۱۱. الگوی اپلیکیشن

```python
def setup_logging(level=logging.INFO):
    logging.basicConfig(
        level=level,
        format="%(asctime)s [%(levelname)s] %(name)s: %(message)s",
        handlers=[
            logging.StreamHandler(),
            logging.FileHandler("app.log", encoding="utf-8"),
        ],
    )

def main():
    setup_logging()
    log = logging.getLogger(__name__)
    log.info("شروع")
```

---

## ۱۲. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_logging_basics.py](./examples/01_logging_basics.py) | سطوح و basicConfig |
| [02_handlers.py](./examples/02_handlers.py) | Console + File |
| [03_exceptions.py](./examples/03_exceptions.py) | exception logging |
| [04_app_pattern.py](./examples/04_app_pattern.py) | الگوی real-world |

---

## ۱۳. اشتباهات رایج

| اشتباه | مشکل |
|--------|------|
| `print` برای production | بدون سطح/فایل |
| `basicConfig` چندبار | فقط اولی اعمال |
| `getLogger()` بدون نام | root logger — سخت debug |
| f-string در INFO | هزینه حتی وقتی خاموش |
| logger propagation | duplicate log |

---

## ۱۴. logging vs print در تست

```python
# در pytest می‌توانید caplog fixture استفاده کنید
# caplog.at_level(logging.INFO):
#     func()
# assert "پیام" in caplog.text
```

---

## ۱۵. تمرین کلاسی

1. INFO و WARNING با format سفارشی
2. لاگ خطای تقسیم بر صفر با `exception`
3. FileHandler برای ذخیره در `debug.log`
4. logger با نام `calculator` بسازید

**تکلیف:** [question.md](./exercises/question.md)

---

## ۱۶. dictConfig (پیشرفته)

```python
import logging.config

LOGGING = {
    "version": 1,
    "formatters": {"simple": {"format": "%(levelname)s: %(message)s"}},
    "handlers": {
        "console": {
            "class": "logging.StreamHandler",
            "formatter": "simple",
        }
    },
    "root": {"level": "INFO", "handlers": ["console"]},
}
logging.config.dictConfig(LOGGING)
```

---

## 📚 مطالعه تکمیلی

- [logging — Python docs](https://docs.python.org/3/library/logging.html)
- HOWTO — Logging Cookbook

---

**جلسه قبل:** [۲۶ — Pathlib](../session-26-pathlib/) | **بعد:** [۲۸ — Dataclasses](../session-28-dataclasses/)
