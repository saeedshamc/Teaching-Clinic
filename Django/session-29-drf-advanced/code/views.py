# views.py — Pagination، Filtering و Throttling در DRF (جلسه ۲۹)
from rest_framework import viewsets, filters
from rest_framework.pagination import PageNumberPagination, LimitOffsetPagination, CursorPagination
from rest_framework.throttling import UserRateThrottle, AnonRateThrottle, ScopedRateThrottle
from django_filters import rest_framework as django_filters
from .models import Book
from .serializers import BookSerializer
from .filters import BookFilter   # از session-25


# ─── Pagination سفارشی ─────────────────────────────────────────────
class BookPagination(PageNumberPagination):
    """صفحه‌بندی عددی — ?page=2&page_size=5"""
    page_size = 10
    page_size_query_param = "page_size"   # کلاینت می‌تواند اندازه را عوض کند
    max_page_size = 100                    # سقف امنیتی


class BookLimitOffsetPagination(LimitOffsetPagination):
    """جایگزین — ?limit=10&offset=20"""
    default_limit = 10
    max_limit = 100


class BookCursorPagination(CursorPagination):
    """برای dataset خیلی بزرگ — cursor-based، سریع‌تر."""
    page_size = 20
    ordering = "-created_at"   # cursor به ordering نیاز دارد


# ─── Throttling سفارشی ─────────────────────────────────────────────
class BurstThrottle(UserRateThrottle):
    """محدودیت کوتاه‌مدت — مثلاً ۱۰ درخواست در دقیقه."""
    scope = "burst"


class SustainedThrottle(UserRateThrottle):
    """محدودیت بلندمدت — مثلاً ۱۰۰۰ درخواست در روز."""
    scope = "sustained"


# ─── ViewSet با همهٔ قابلیت‌ها ──────────────────────────────────────
class BookViewSet(viewsets.ModelViewSet):
    queryset = Book.objects.select_related("author").prefetch_related("tags").all()
    serializer_class = BookSerializer

    # pagination — یکی را انتخاب کنید
    pagination_class = BookPagination

    # filtering — ترکیب django-filter + search + ordering
    filter_backends = [
        django_filters.DjangoFilterBackend,   # فیلترهای BookFilter
        filters.SearchFilter,                  # ?search=...
        filters.OrderingFilter,                # ?ordering=...
    ]
    filterset_class = BookFilter               # از session-25
    search_fields = ["title", "author__name", "tags__name"]
    ordering_fields = ["price", "created_at", "pages", "title"]
    ordering = ["-created_at"]

    # throttling — روی همهٔ اکشن‌ها
    throttle_classes = [BurstThrottle, SustainedThrottle]

    # یا throttle scoped برای اکشن خاص:
    # @action(detail=False, methods=["post"], throttle_scope="expensive")
    # def bulk_import(self, request): ...


# ══════════════════════════════════════════════════════════════════
# settings.py — تنظیمات DRF کامل
# ══════════════════════════════════════════════════════════════════
# REST_FRAMEWORK = {
#     "DEFAULT_AUTHENTICATION_CLASSES": [
#         "rest_framework.authentication.SessionAuthentication",
#         "rest_framework_simplejwt.authentication.JWTAuthentication",
#     ],
#     "DEFAULT_PERMISSION_CLASSES": [
#         "rest_framework.permissions.IsAuthenticatedOrReadOnly",
#     ],
#     "DEFAULT_PAGINATION_CLASS": "rest_framework.pagination.PageNumberPagination",
#     "PAGE_SIZE": 20,
#     "DEFAULT_FILTER_BACKENDS": [
#         "django_filters.rest_framework.DjangoFilterBackend",
#         "rest_framework.filters.SearchFilter",
#         "rest_framework.filters.OrderingFilter",
#     ],
#     "DEFAULT_THROTTLE_CLASSES": [
#         "rest_framework.throttling.AnonRateThrottle",
#         "rest_framework.throttling.UserRateThrottle",
#     ],
#     "DEFAULT_THROTTLE_RATES": {
#         "anon": "100/hour",
#         "user": "1000/hour",
#         "burst": "10/minute",
#         "sustained": "1000/day",
#         "expensive": "5/minute",
#     },
# }

# ─── نمونهٔ خروجی pagination ──────────────────────────────────────
# GET /api/books/?page=2&page_size=5
# {
#   "count": 47,
#   "next": "http://.../api/books/?page=3&page_size=5",
#   "previous": "http://.../api/books/?page=1&page_size=5",
#   "results": [ {...}, {...}, ... ]
# }
#
# GET /api/books/?search=django&ordering=-price&price__gte=1000
# → ترکیب search + ordering + filter
