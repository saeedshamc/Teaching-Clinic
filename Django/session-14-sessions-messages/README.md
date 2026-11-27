# جلسه ۱۴ — Session و Messages

## درس

### Session Framework

سشن دادهٔ کاربر را بین درخواست‌ها نگه می‌دارد (cookie-side یا DB-side). دسترسی: `request.session`.

```python
def set_theme(request):
    request.session["theme"] = "dark"     # ذخیره
    request.session.get("theme", "light") # خواندن
    request.session.save()                # ذخیره اجباری (معمولاً خودکار)
    # حذف: del request.session["theme"] یا request.session.flush()

def counter(request):
    n = request.session.get("count", 0) + 1
    request.session["count"] = n
    return HttpResponse(f"بازدید: {n}")
```

پیکربندی `SESSION_ENGINE`: پیش‌فرض DB؛ گزینهٔ `django.contrib.sessions.backends.signed_cookies` بدون DB.

### Messages Framework

پیام‌های یک‌بارمصرف که در درخواست بعدی نمایش داده می‌شوند:

```python
from django.contrib import messages

messages.success(request, "کتاب با موفقیت ذخیره شد")
messages.error(request, "خطا در پردازش فرم")
messages.info(request, "...")
messages.warning(request, "...")
```

در قالب (base.html):

```html
{% if messages %}
  <ul class="messages">
    {% for msg in messages %}
      <li class="{{ msg.tags }}">{{ msg }}</li>
    {% endfor %}
  </ul>
{% endif %}
```

## کد نمونه

`code/views.py` را ببینید.

## تمرین

یک ویوی `set_language` بنویسید که `request.session["lang"]` را روی "fa" یا "en" تنظیم کند.

## جواب تمرین

```python
def set_language(request, lang):
    if lang in ("fa", "en"):
        request.session["lang"] = lang
        request.session.save()
    return redirect("library:home")
```
