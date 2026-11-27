# راه‌اندازی محیط کار Django

## ۱. نصب Python

Django به Python 3.10 یا بالاتر نیاز دارد. نسخه را بررسی کنید:

```bash
python --version
```

## ۲. ساخت محیط مجازی (توصیه‌شده)

```bash
python -m venv .venv
```

فعال‌سازی:

- **Windows (PowerShell):** `.venv\Scripts\Activate.ps1`
- **Windows (CMD):** `.venv\Scripts\activate.bat`
- **Linux/macOS:** `source .venv/bin/activate`

## ۳. نصب Django

```bash
pip install django
python -c "import django; print(django.get_version())"
```

## ۴. ساخت اولین پروژه

```bash
# در پوشه کاری خود:
django-admin startproject config .
django-admin startapp library
```

- `config/` → تنظیمات پروژه (settings, urls, wsgi)
- `library/` → اپلیکیشن شما (models, views, templates, ...)

## ۵. اجرای پروژه

```bash
python manage.py runserver
```

مرورگر را باز کنید: `http://127.0.0.1:8000`

## ۶. دستورات پرکاربرد

```bash
python manage.py makemigrations   # ساخت فایل‌های migration از روی مدل‌ها
python manage.py migrate          # اعمال migrationها روی دیتابیس
python manage.py createsuperuser  # ساخت کاربر ادمین
python manage.py shell            # شل تعاملی Django (برای تست QuerySet)
python manage.py check            # بررسی سلامت پروژه
```

## نکته برای ویندوز

اگر `Activate.ps1` خطای Execution Policy داد:

```powershell
Set-ExecutionPolicy -ExecutionPolicy RemoteSigned -Scope CurrentUser
```

یا از CMD استفاده کنید.
