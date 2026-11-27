# views.py — DRF ViewSet و Router (جلسه ۲۷)
from rest_framework import viewsets, status
from rest_framework.decorators import action
from rest_framework.response import Response
from django_filters import rest_framework as filters
from django.db.models import Avg, Count
from .models import Book, Author, Review
from .serializers import BookSerializer, ReviewSerializer


class BookViewSet(viewsets.ModelViewSet):
    """
    ModelViewSet = List + Create + Retrieve + Update + PartialUpdate + Destroy
    همهٔ CRUD را یک‌جا می‌دهد — فقط queryset و serializer_class لازم است.
    """

    # select_related → جلوگیری از N+1 روی author
    queryset = Book.objects.select_related("author").prefetch_related("tags").all()
    serializer_class = BookSerializer

    # فیلترها — برای session-29 تکمیل می‌شود
    filter_backends = [filters.DjangoFilterBackend, filters.SearchFilter, filters.OrderingFilter]
    filterset_fields = ["author", "is_available"]
    search_fields = ["title", "author__name"]
    ordering_fields = ["price", "created_at", "pages"]
    ordering = ["-created_at"]

    # ─── اکشن‌های سفارشی ─────────────────────────────────────────

    @action(detail=True, methods=["post"])
    def publish(self, request, pk=None):
        """POST /api/books/{id}/publish/ — موجود کردن کتاب."""
        book = self.get_object()
        book.is_available = True
        book.save()
        return Response({"status": "published", "id": book.id})

    @action(detail=True, methods=["post"])
    def archive(self, request, pk=None):
        """POST /api/books/{id}/archive/ — آرشیو کردن."""
        book = self.get_object()
        book.is_available = False
        book.save()
        return Response({"status": "archived", "id": book.id})

    @action(detail=False, methods=["get"])
    def stats(self, request):
        """GET /api/books/stats/ — آمار کلی (بدون detail)."""
        agg = Book.objects.aggregate(
            total=Count("id"),
            avg_price=Avg("price"),
            total_pages=Avg("pages"),
        )
        return Response(agg)

    # ─── Override برای لاگ ────────────────────────────────────────

    def perform_create(self, serializer):
        # قبل از save — مثلاً set کردن created_by
        serializer.save()

    def get_queryset(self):
        """فیلتر داینامیک — مثلاً کاربر فقط کتاب‌های خودش را ببیند."""
        qs = super().get_queryset()
        if self.request.user.is_authenticated:
            # فرض: فیلتر بر اساس author__user اگر relationship داشته باشیم
            pass
        return qs


class AuthorViewSet(viewsets.ReadOnlyModelViewSet):
    """فقط خواندنی — لیست و جزئیات نویسنده."""
    queryset = Author.objects.annotate(
        book_count=Count("books")
    ).all()
    serializer_class = AuthorSerializer


class ReviewViewSet(viewsets.ModelViewSet):
    """ViewSet برای نظرات — با permission سفارشی."""
    queryset = Review.objects.select_related("book", "user").all()
    serializer_class = ReviewSerializer

    def perform_create(self, serializer):
        # user را خودکار از request می‌گیریم
        serializer.save(user=self.request.user)


# ══════════════════════════════════════════════════════════════════
# در api_urls.py:
# ══════════════════════════════════════════════════════════════════
# from rest_framework.routers import DefaultRouter
# from .views import BookViewSet, AuthorViewSet, ReviewViewSet
#
# router = DefaultRouter()
# router.register("books", BookViewSet, basename="book")
# router.register("authors", AuthorViewSet, basename="author")
# router.register("reviews", ReviewViewSet, basename="review")
#
# urlpatterns = router.urls
#
# Router خودکار این URLها را می‌سازد:
#   GET    /api/books/          → list
#   POST   /api/books/          → create
#   GET    /api/books/{pk}/     → retrieve
#   PUT    /api/books/{pk}/     → update
#   PATCH  /api/books/{pk}/     → partial_update
#   DELETE /api/books/{pk}/     → destroy
#   POST   /api/books/{pk}/publish/  → اکشن سفارشی
#   GET    /api/books/stats/         → اکشن بدون detail
