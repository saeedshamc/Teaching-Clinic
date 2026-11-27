# جلسه ۴۸ — Cache عمیق و بهینه‌سازی

## درس

### سطوح caching

```python
# ۱. Template fragment caching
{% load cache %}
{% cache 600 book_detail book.id %}...{% endcache %}

# ۲. View-level
@cache_page(60 * 15)
def book_list(request): ...

# ۳. Low-level (per-object)
from django.core.cache import cache
books = cache.get_or_set("books_list", lambda: list(Book.objects.values(...)), 300)

# ۴. Cache در ORM — فقط کوئری را کش کنید نه کل صفحه
cached_books = cache.get("books_qs")
if cached_books is None:
    cached_books = list(Book.objects.select_related("author")[:50])
    cache.set("books_qs", cached_books, 600)
```

### cacheops — کش خودکار کوئری (اختیاری)

```bash
pip install django-cacheops
# settings: INSTALLED_APPS += "cacheops" و CACHES = {"default": {"BACKEND": "django_redis.cache.RedisCache", ...}}
# CACHOPES = {"default": {"timeout": 60*60}}
# سپس migrate → جدول‌های cacheops ساخته می‌شوند و کوئری‌ها خودکار invalidate می‌شوند
```

### ETag / Conditional GET

```python
from django.views.decorators.http import condition
from django.utils.http import http_date
from django.utils import timezone

def last_modified(request):
    return Book.objects.latest("updated_at").updated_at

@condition(last_modified_func=last_modified)
def book_detail(request, pk):
    ...
# مرورگر If-Modified-Since می‌فرستد → 304 بدون رندر مجدد
```

### نکات طلایی

- کلید کش را با پارامترهای مؤثر بسازید: `f"books:{author_id}:{page}"`
- کش را بعد از update/delete invalidate کنید: `cache.delete_pattern("books_*")` (با redis)
- TTL مناسب: کوتاه برای دادهٔ متغیر، بلند برای دادهٔ ثابت

## تمرین

یک `get_or_set` برای «پرفروش‌ترین کتاب‌ها» بنویسید که ۱ ساعت کش شود و بعد از `post_save` روی Book invalidate شود.

## جواب تمرین

```python
@shared_task
def get_bestsellers():
    return cache.get_or_set(
        "bestsellers",
        lambda: list(Book.objects.order_by("-pages")[:10].values("id", "title")),
        3600,
    )

@receiver(post_save, sender=Book)
def invalidate_bestsellers(sender, **kwargs):
    cache.delete("bestsellers")
```

## 📖 شرح فایل `code/cache_deep.py`

- `get_bestsellers`/`get_book_detail` — `cache.get_or_set` با lambda محاسبه؛ کلید با پارامتر (`bestsellers:10`, `book:{id}`).
- کامنت fragment caching — `{% cache 600 key %}...{% endcache %}` برای بخش‌های سنگین قالب.
- `book_list` — کش HTML رندرشده فقط برای صفحهٔ اول بدون فیلتر (`render_to_string` + `HttpResponse`).
- `invalidate_book_cache`/`_on_delete` — `post_save`/`post_delete` روی Book → `cache.delete` کلیدهای مرتبط.
- `book_last_modified` + `@condition` — ETag/conditional GET؛ مرورگر `If-Modified-Since` می‌فرستد و 304 بدون رندر می‌گیرد.
- `warm_cache` — `set_many` برای گرم کردن کش بعد از deploy؛ کامنت versioned keys برای invalidate آسان.
