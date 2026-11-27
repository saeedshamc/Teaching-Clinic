# جلسه ۳۱ — سیستم Cache در Django

## درس

Cache نتایج گران‌قیمت (کوئری، محاسبه) را ذخیره می‌کند تا دوباره اجرا نشوند.

### تنظیمات (پیش‌فرض LocMem — برای production Redis/Memcached)

```python
CACHES = {
    "default": {
        "BACKEND": "django.core.cache.backends.locmem.LocMemCache",
        "LOCATION": "unique-snowflake",
    }
}
```

### Low-level cache API

```python
from django.core.cache import cache

cache.set("total_books", 150, timeout=300)     # ۵ دقیقه
n = cache.get("total_books", 0)                # پیش‌فرض اگر نبود
cache.add("key", "value")                       # فقط اگر نبود
cache.delete("key")
cache.clear()
```

### cache per-view

```python
from django.views.decorators.cache import cache_page

@cache_page(60 * 15)   # ۱۵ دقیقه
def book_list(request): ...
```

### cache در قالب — fragment caching

```html
{% load cache %}
{% cache 500 sidebar %}     <!-- ۵۰۰ ثانیه -->
    {% expensive_query %}
{% endcache %}
```

### cache یک مدل/queryset

```python
def get_popular_books():
    key = "popular_books"
    data = cache.get(key)
    if data is None:
        data = list(Book.objects.order_by("-pages")[:10].values("id", "title"))
        cache.set(key, data, 3600)
    return data
```

## کد نمونه

بالا کامنت‌گذاری شده — در views/services پیاده کنید.

## تمرین

برای `book_list` یک کش ۱۰ دقیقه‌ای بسازید که فقط صفحهٔ اول (بدون `?q`) کش شود.

## جواب تمرین

```python
def book_list(request):
    if "q" not in request.GET and request.GET.get("page") in (None, "1"):
        key = "book_list_page1"
        data = cache.get(key)
        if data is None:
            data = render_to_string("library/book_list.html", {...})
            cache.set(key, data, 600)
        return HttpResponse(data)
    ...
```
