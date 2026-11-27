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
