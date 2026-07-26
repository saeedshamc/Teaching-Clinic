# جلسه ۲۷: لاگ‌نویسی (Logging)

## اهداف یادگیری (Learning Objectives)

پس از این جلسه می‌توانید:
- تفاوت print و logging را بدانید
- سطوح مختلف لاگ را استفاده کنید
- پیام‌های ساختاریافته برای دیباگ بنویسید

---

## Logging چیست؟

به‌جای print ساده، logging به شما سطح اهمیت پیام، زمان و کنترل بهتر خروجی می‌دهد.

```python
import logging

logging.basicConfig(level=logging.INFO, format="%(levelname)s: %(message)s")
logging.info("برنامه شروع شد")
logging.warning("مقدار ورودی مشکوک است")
logging.error("خطایی رخ داد")
```

### توضیح
- `INFO` → پیام معمولی
- `WARNING` → هشدار
- `ERROR` → خطا

---

## فایل‌های این جلسه
- [examples/01_logging.py](./examples/01_logging.py)
- [exercises/exercise_01.py](./exercises/exercise_01.py)

## تمرین‌ها
- تمرین ساده: یک پیام info و یک warning بنویسید
- تمرین متوسط: برای تقسیم بر صفر لاگ خطا بنویسید
