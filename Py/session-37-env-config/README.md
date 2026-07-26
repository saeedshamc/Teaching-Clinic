# جلسه ۳۷: متغیر محیطی و پیکربندی

## اهداف یادگیری (Learning Objectives)

پس از این جلسه می‌توانید:
- با os.environ تنظیمات را بخوانید
- پیکربندی را از کد جدا کنید
- مقدار پیش‌فرض برای تنظیمات بگذارید

---

## چرا متغیر محیطی؟

رمزها، آدرس API و تنظیمات محیط بهتر است داخل کد hard-code نشوند. متغیر محیطی این کار را امن‌تر می‌کند.

```python
import os

host = os.environ.get("APP_HOST", "localhost")
port = int(os.environ.get("APP_PORT", "8000"))
print(f"{host}:{port}")
```

### توضیح
- `os.environ.get` → مقدار متغیر را می‌خواند
- آرگومان دوم → مقدار پیش‌فرض
- تنظیمات را از منطق برنامه جدا نگه دارید

---

## فایل‌های این جلسه
- [examples/01_env_config.py](./examples/01_env_config.py)
- [exercises/exercise_01.py](./exercises/exercise_01.py)

## تمرین‌ها
- تمرین ساده: یک متغیر محیطی بخوانید
- تمرین متوسط: host و port را با پیش‌فرض تنظیم کنید
