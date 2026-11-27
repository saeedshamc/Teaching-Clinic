# cache_deep.py — الگوهای Cache پیشرفته (جلسه ۴۸)
from django.core.cache import cache
from django.views.decorators.cache import cache_page, never_cache
from django.utils.decorators import method_decorator
from django.views.generic import TemplateView
from django.db.models.signals import post_save, post_delete
from django.dispatch import receiver
from .models import Book


# ══════════════════════════════════════════════════════════════════
# ۱. Low-level cache — get_or_set
# ══════════════════════════════════════════════════════════════════

def get_bestsellers(limit=10):
    """پرفروش‌ترین کتاب‌ها — ۱ ساعت کش."""
    key = f"bestsellers:{limit}"
    return cache.get_or_set(
        key,
        lambda: list(
            Book.objects.order_by("-pages")[:limit]
            .values("id", "title", "pages", "price")
        ),
        timeout=3600,
    )


def get_book_detail(book_id):
    """جزئیات کتاب — کش با invalidate در signal."""
    key = f"book:{book_id}"
    return cache.get_or_set(
        key,
        lambda: Book.objects.filter(pk=book_id).values(
            "id", "title", "author__name", "pages", "price"
        ).first(),
        timeout=600,
    )


# ══════════════════════════════════════════════════════════════════
# ۲. Cache در template — fragment caching
# ══════════════════════════════════════════════════════════════════
# {% load cache %}
# {% cache 600 book_detail book.id %}
#     {# محتوای سنگین — مثلاًrecommendationها #}
#     {% for rec in book.recommendations.all %}
#         ...
#     {% endfor %}
# {% endcache %}
#
# {% cache 300 "sidebar" %}
#     {# سایدبار که برای همه یکسان است #}
# {% endcache %}


# ══════════════════════════════════════════════════════════════════
# ۳. Per-view cache با کلید داینامیک
# ══════════════════════════════════════════════════════════════════

def book_list(request):
    """کش فقط برای صفحهٔ اول بدون فیلتر."""
    has_filters = any(k in request.GET for k in ("q", "author", "price"))

    if not has_filters and request.GET.get("page", "1") == "1":
        # صفحهٔ اول بدون فیلتر — قابل کش
        key = "book_list_page1"
        html = cache.get(key)
        if html is None:
            from django.template.loader import render_to_string
            books = Book.objects.select_related("author")[:12]
            html = render_to_string(
                "library/book_list.html",
                {"books": books},
                request=request,
            )
            cache.set(key, html, 600)
        from django.http import HttpResponse
        return HttpResponse(html)

    # با فیلتر → بدون کش
    return render(request, "library/book_list.html", {
        "books": Book.objects.all(),
    })


# ══════════════════════════════════════════════════════════════════
# ۴. Invalidation با signal
# ══════════════════════════════════════════════════════════════════

@receiver(post_save, sender=Book)
def invalidate_book_cache(sender, instance, **kwargs):
    """هر save روی Book → کش‌های مرتبط را پاک کن."""
    cache.delete(f"book:{instance.pk}")
    cache.delete("bestsellers:10")
    # برای invalidate pattern با Redis:
    # from django_redis import get_redis_connection
    # get_redis_connection().delete_pattern("book:*")


@receiver(post_delete, sender=Book)
def invalidate_book_cache_on_delete(sender, instance, **kwargs):
    cache.delete(f"book:{instance.pk}")
    cache.delete("bestsellers:10")


# ══════════════════════════════════════════════════════════════════
# ۵. ETag / Conditional GET — برای API
# ══════════════════════════════════════════════════════════════════

from django.views.decorators.http import condition
from django.utils.http import http_date
from django.utils import timezone


def book_last_modified(request):
    """آخرین زمان تغییر هر کتاب — برای ETag."""
    latest = Book.objects.latest("updated_at")
    return latest.updated_at


@condition(last_modified_func=book_last_modified)
def book_list_etag(request):
    """مرورگر If-Modified-Since می‌فرستد → 304 بدون رندر."""
    return render(request, "library/book_list.html", {
        "books": Book.objects.all()
    })


# ══════════════════════════════════════════════════════════════════
# ۶. Cacheops — کش خودکار ORM (اختیاری)
# ══════════════════════════════════════════════════════════════════
# pip install django-cacheops
# INSTALLED_APPS += ["cacheops"]
# CACHES = {"default": {"BACKEND": "django_redis.cache.RedisCache", ...}}
# CACHOPES = {"default": {"timeout": 60 * 60}}
#
# # cacheops خودش کوئری‌ها را کش و invalidate می‌کند
# # نیازی به cache.get/set دستی نیست


# ══════════════════════════════════════════════════════════════════
# ۷. cache.set_many / get_many — batch
# ══════════════════════════════════════════════════════════════════

def warm_cache():
    """گرم کردن کش — مثلاً بعد از deploy."""
    popular = list(Book.objects.order_by("-pages")[:20].values("id", "title"))
    cache.set_many({f"book:{b['id']}": b for b in popular}, timeout=3600)
    cache.set("bestsellers:10", popular[:10], 3600)


# ══════════════════════════════════════════════════════════════════
# ۸. versioned keys — برای invalidate آسان
# ══════════════════════════════════════════════════════════════════
# به‌جای key ثابت، از version استفاده کنید:
#   CACHE_VERSION = "v1"
#   key = f"books:{CACHE_VERSION}:list"
# برای invalidate: CACHE_VERSION = "v2" → همهٔ کلیدهای قدیمی بی‌استفاده می‌شوند


# ══════════════════════════════════════════════════════════════════
# نکات:
# ══════════════════════════════════════════════════════════════════
# - همیشه TTL (timeout) بگذارید — بدون TTL دادهٔ stale می‌ماند
# - کلید را با پارامترهای مؤثر بسازید: f"books:{author_id}:{page}"
# - QuerySet را مستقیم cache نکنید — list() یا values() کنید
# - invalidate را در signal یا بعد از save/delete دستی انجام دهید
# - برای دادهٔ real-time یا user-specific → never_cache
# - stampede/dogpile: اگر کش expire شود و ۱۰۰ request هم‌زمان بیاید → همه کوئری می‌زنند
#   راه‌حل: cache.add با lock یا Django cacheops
