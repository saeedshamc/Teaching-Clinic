# جلسه ۱۰ — فایل‌های Static و Media

## درس

### Static files (CSS/JS/عکس‌های قالب)

- در هر اپ: `library/static/library/style.css`
- در قالب: `{% load static %}` سپس `<link href="{% static 'library/style.css' %}">`
- در production باید `collectstatic` اجرا شود تا فایل‌ها در یک پوشه جمع شوند

### Media files (فایل‌های آپلودی کاربر)

- در مدل: `avatar = models.ImageField(upload_to="avatars/")`
- در `settings.py`:

```python
MEDIA_URL = "/media/"
MEDIA_ROOT = BASE_DIR / "media"
```

- در `config/urls.py` (فقط DEBUG):

```python
from django.conf import settings
from django.conf.urls.static import static

if settings.DEBUG:
    urlpatterns += static(settings.MEDIA_URL, document_root=settings.MEDIA_ROOT)
```

- در قالب: `<img src="{{ book.cover.url }}">`

## کد نمونه

`code/models.py` (فیلد FileField)، `code/urls.py` و `code/base_head.html` را ببینید.

## تمرین

یک فیلد `cover = models.ImageField(upload_to="covers/")` به `Book` اضافه کنید و در قالب جزئیات کتاب آن را نمایش دهید.

## جواب تمرین

```python
# models.py
cover = models.ImageField(upload_to="covers/", null=True, blank=True)

# book_detail.html
{% if book.cover %}
  <img src="{{ book.cover.url }}" alt="جلد {{ book.title }}">
{% endif %}
```

## 📖 شرح فایل‌های `code/`

**`models.py`**
- `cover = ImageField(upload_to="covers/")` — فایل در `media/covers/` ذخیره می‌شود؛ `null/blank` یعنی اختیاری. `ImageField` همان `FileField` + اعتبارسنجی تصویر است.
- `sample_pdf = FileField(upload_to="samples/%Y/%m/")` — `%Y/%m` از strftime استفاده می‌کند → فایل‌ها بر اساس سال/ماه پوشه‌بندی می‌شوند.

**`urls.py`**
- `static(settings.MEDIA_URL, document_root=settings.MEDIA_ROOT)` — فقط در `DEBUG=True` فایل‌های `media/` را سرو می‌کند؛ در production Nginx این کار را می‌کند.

**`base_head.html`**
- `{% load static %}` — لود کردن تگ‌های static.
- `{% static 'library/style.css' %}` — URL نهایی فایل استاتیک (در production با hash نسخه‌گذاری می‌شود).
- `{{ book.cover.url }}` — آدرس عمومی فایل آپلودی؛ اگر فیلد خالی باشدurl خطا می‌دهد برای همین `{% if %}` چک می‌کنیم.
