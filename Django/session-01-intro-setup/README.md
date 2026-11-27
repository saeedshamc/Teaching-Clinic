# جلسه ۱ — معرفی Django و اولین پروژه

## درس

**Django** یک فریم‌ورک وب سطح‌بالا برای Python است که الگوی **MTV** (Model-Template-View) را پیاده می‌کند — مشابه MVC اما با Naming متفاوت. شعار آن «Framework for perfectionists with deadlines» است: ی‌هایincluded، امنیت پیش‌فرض، پنل ادمین رایگان.

### چرا Django؟
- **ORM قدرتمند**: با دیتابیس کار می‌کنید بدون SQL خام
- **Admin خودکار**: از مدل‌هایتان پنل مدیریت می‌سازد
- **امنیت پیش‌فرض**: محافظت در برابر CSRF، SQL Injection، XSS
- **اجتماع بزرگ**: مستندات عالی و پشتیبانی طولانی

### اولین پروژه

```bash
pip install django
django-admin startproject config .   # پروژه را در همین پوشه بساز
django-admin startapp library        # اولین اپ
python manage.py runserver           # اجرا روی 127.0.0.1:8000
```

## کد نمونه

فایل `code/views.py` و `code/urls.py` را ببینید — ساده‌ترین ویو و مسیریابی URL.

## تمرین

در فایل `views.py` یک ویو دوم به نام `about` بسازید که متن «درباره ما» را برگرداند و آن را در `urls.py` به مسیر `/about/` متصل کنید.

## جواب تمرین

```python
# views.py
def about(request):
    return HttpResponse("درباره ما")

# urls.py
path("about/", views.about, name="about"),
```
