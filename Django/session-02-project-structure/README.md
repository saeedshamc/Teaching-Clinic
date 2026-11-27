# جلسه ۲ — ساختار پروژه و فایل‌های کلیدی

## درس

### ساختار یک پروژهٔ Django

```
myproject/
├── manage.py          ← نقطه ورود دستورها (runserver, migrate, ...)
├── config/            ← پکیج پروژه
│   ├── settings.py    ← مهم‌ترین فایل: تنظیمات کامل پروژه
│   ├── urls.py        ← مسیریابی اصلی (root URLconf)
│   ├── wsgi.py        ← رابط با سرور وب (production)
│   └── asgi.py        ← رابط با ASGI (async/websocket)
└── library/           ← اپ شما
    ├── models.py      ← مدل‌های دیتابیس
    ├── views.py       ← منطق صفحه‌ها
    ├── urls.py        ← مسیریابی اپ (include می‌شود)
    ├── admin.py       ← ثبت مدل‌ها در ادمین
    ├── apps.py        ← تنظیمات اپ
    ├── migrations/    ← فایل‌های migration
    └── templates/     ← قالب‌های HTML اپ
```

### جریان یک درخواست

1. مرورگر → `config/urls.py` (روت URL)
2. اگر مسیر با `include("library.urls")` شروع شود → `library/urls.py`
3. ویوی متناظر اجرا می‌شود → پاسخ HTML/JSON برمی‌گردد

### نکات settings.py

- `INSTALLED_APPS`: اپ‌های فعال — اپ خودتان را اینجا اضافه کنید
- `DATABASES`: پیش‌فرض SQLite؛ برای production می‌توانید PostgreSQL بگذارید
- `DEBUG=True` فقط در توسعه — در production حتماً `False`
- `ALLOWED_HOSTS`: هاست‌های مجاز

## کد نمونه

`code/settings_snippet.py` و `code/project_urls.py` را ببینید.

## تمرین

چرا اپ `library` باید در `INSTALLED_APPS` باشد؟ اگر حذفش کنید چه اتفاقی می‌افتد؟

## جواب تمرین

بدون ثبت در `INSTALLED_APPS` جنگو اپ را نمی‌شناسد: مدل‌هایش migrate نمی‌شوند، قالب‌هایش لود نمی‌شوند و ادمینش کار نمی‌کند. خطا معمولاً هنگام `migrate` یا لود صفحه دیده می‌شود.

## 📖 شرح فایل‌های `code/`

**`settings_snippet.py`** — فقط بخش‌های کلیدی `settings.py` است، نه فایل کامل:
- `INSTALLED_APPS` — اپ‌های فعال؛ `"library"` اپ ماست. بدون آن مدل‌ها/قالب‌ها/ادمین کار نمی‌کنند.
- `DATABASES` — `ENGINE` نوع دیتابیس و `NAME` مسیر فایل SQLite است.
- `DEBUG` — `True` فقط برای توسعه؛ در production خطاهای کامل با traceback لو می‌رود.
- `ALLOWED_HOSTS` — دامنه‌های مجاز برای پاسخ به request.
- `STATIC_URL` — URL عمومی فایل‌های استاتیک.

**`project_urls.py`**
- `include("library.urls")` — مسیرهای `library/urls.py` را زیر ریشه `/` می‌چیند؛ یعنی `/books/` در اپ library به `books/` در urls آن resolve می‌شود.
