# cache_helpers.py — الگوهای caching (جلسه ۳۱)
from django.core.cache import cache
from django.shortcuts import render
from django.http import JsonResponse
from django.views.decorators.cache import cache_page, never_cache, cache_control
from django.utils.decorators import method_decorator
from django.views.generic import TemplateView
from .models import Book


# ─── Low-level API ─────────────────────────────────────────────────
def get_total_books():
    """مثال: کش کردن محاسبهٔ گران."""
    count = cache.get("total_books")
    if count is None:
        count = Book.objects.count()
        cache.set("total_books", count, timeout=300)   # ۵ دقیقه
    return count


def get_popular_books(limit=10, timeout=3600):
    """کش کردن queryset — نکته: QuerySet را cache نکنید، لیست dict کنید."""
    key = f"popular_books:{limit}"
    data = cache.get(key)
    if data is None:
        data = list(
            Book.objects.order_by("-pages")[:limit].values("id", "title", "pages")
        )
        cache.set(key, data, timeout)
    return data


# ─── get_or_set — الگوی تمیزتر ─────────────────────────────────────
def get_or_compute(key, compute_func, timeout=300):
    """اگر key نبود، compute_func را اجرا و نتیجه را کش کن."""
    data = cache.get(key)
    if data is None:
        data = compute_func()
        cache.set(key, data, timeout)
    return data


# ─── Per-view cache ────────────────────────────────────────────────
@cache_page(60 * 15)   # ۱۵ دقیقه
def book_list(request):
    return render(request, "library/book_list.html", {"books": Book.objects.all()})


@method_decorator(cache_page(60 * 5), name="dispatch")
class CachedStatsView(TemplateView):
    template_name = "library/stats.html"

    def get_context_data(self, **kwargs):
        ctx = super().get_context_data(**kwargs)
        ctx["total"] = Book.objects.count()
        return ctx


# ─── never_cache — برای صفحات خصوصی ──────────────────────────────
@never_cache
def user_dashboard(request):
    # این صفحه هرگز کش نمی‌شود — دادهٔ کاربر-specific
    return render(request, "library/dashboard.html", {"user": request.user})


# ─── cache_control — کنترل header ────────────────────────────────
@cache_control(max_age=3600, public=True)   # ۱ ساعت، عمومی
def public_api(request):
    return JsonResponse({"data": "cached for 1 hour"})


# ─── cache.delete_pattern — invalidate (فقط با Redis/Memcached) ───
# from django_redis import get_redis_connection
# def invalidate_books():
#     get_redis_connection("default").delete_pattern("books_*")


# ─── settings.py ───────────────────────────────────────────────────
# CACHES = {
#     "default": {
#         "BACKEND": "django.core.cache.backends.locmem.LocMemCache",
#         "LOCATION": "unique-snowflake",   # برای LocMem باید یکتا باشد
#         # "TIMEOUT": 300,                  # پیش‌فرض ۵ دقیقه
#     }
# }
#
# برای production:
#   pip install django-redis
#   CACHES = {"default": {"BACKEND": "django_redis.cache.RedisCache",
#                        "LOCATION": "redis://127.0.0.1:6379/1"}}
