# جلسه ۳۷ — بین‌المللی‌سازی (i18n/l10n)

## درس

### فعال‌سازی

```python
# settings.py
USE_I18N = True
USE_L10N = True
LANGUAGE_CODE = "fa"          # پیش‌فرض فارسی
LOCALE_PATHS = [BASE_DIR / "locale"]

MIDDLEWARE = [
    "django.middleware.locale.LocaleMiddleware",   # تشخیص زبان از URL/session
    # ...
]
```

### ترجمه در پایتون

```python
from django.utils.translation import gettext as _

def book_list(request):
    title = _("لیست کتاب‌ها")     # gettext → ترجمه در صورت وجود
    return render(request, "...", {"title": title})
```

### ترجمه در قالب

```html
{% load i18n %}
<h1>{% translate "لیست کتاب‌ها" %}</h1>
<p>{% blocktranslate with count=books.count %}{{ count }} کتاب یافت شد{% endblocktranslate %}</p>
```

### ساخت فایل ترجمه

```bash
python manage.py makemessages -l en    # استخراج رشته‌ها → locale/en/LC_MESSAGES/django.po
# ترجمه‌ها را در django.po پر کنید
python manage.py compilemessages      # → django.mo
```

### تغییر زبان با URL

```python
path("en/", include("library.urls", namespace="en")),
# یا: از django.conf.urls.i18n import i18n_patterns
urlpatterns += i18n_patterns(path("", include("library.urls")))
# → /fa/books/ و /en/books/
```

## کد نمونه

بالا کامنت‌گذاری شده — در settings/views/templates پیاده کنید.

## تمرین

برای `book_list` ترجمهٔ «لیست کتاب‌ها» را به انگلیسی اضافه کنید و `makemessages` را اجرا کنید.

## جواب تمرین

```po
# locale/en/LC_MESSAGES/django.po
msgid "لیست کتاب‌ها"
msgstr "Book List"
```

## 📖 شرح فایل `code/settings_i18n.py`

- `USE_I18N`/`USE_L10N` — فعال‌سازی ترجمه و فرمت محلی تاریخ/عدد.
- `LANGUAGE_CODE="fa"` + `LANGUAGES` — پیش‌فرض و زبان‌های مجاز.
- `LOCALE_PATHS` — محل فایل‌های `django.po`/`django.mo`.
- `LocaleMiddleware` — باید بعد از Session و قبل از Common باشد؛ زبان را از session/URL/cookie تشخیص می‌دهد.
- کامنت‌ها: `gettext as _()` در پایتون، `{% translate %}`/`{% blocktranslate %}` در قالب، `makemessages`/`compilemessages`، `i18n_patterns` برای `/fa/` و `/en/` در URL، و تغییر زبان با `translation.activate` + session.
