# views.py — FBV در برابر CBV و کلاس View (جلسه ۲۴)
import json
from django.shortcuts import render, redirect
from django.http import JsonResponse, HttpResponse
from django.views import View
from django.views.generic import TemplateView, ListView
from django.utils.decorators import method_decorator
from django.contrib.auth.decorators import login_required
from django.db.models import Avg
from .models import Book


# ══════════════════════════════════════════════════════════════════
# FBV — تابع ساده (برای منطق خاص)
# ══════════════════════════════════════════════════════════════════

def book_list_fbv(request):
    """Function-Based View — مستقیم و خوانا."""
    books = Book.objects.select_related("author").all()
    return render(request, "library/book_list.html", {"books": books})


# ══════════════════════════════════════════════════════════════════
# View دستی — کنترل کامل روی get/post/dispatch
# ══════════════════════════════════════════════════════════════════

class BookSearchView(View):
    """هم GET (جستجو) هم POST (ساخت) — dispatch همه را می‌گیرد."""

    @method_decorator(login_required)   # auth روی همهٔ متدها
    def dispatch(self, request, *args, **kwargs):
        return super().dispatch(request, *args, **kwargs)

    def get(self, request):
        # GET /books/search/?q=django
        q = request.GET.get("q", "").strip()
        if q:
            from django.db.models import Q
            books = Book.objects.filter(
                Q(title__icontains=q) | Q(author__name__icontains=q)
            )
        else:
            books = Book.objects.none()
        return render(request, "library/search.html", {"books": books, "q": q})

    def post(self, request):
        # POST — ساخت کتاب از JSON یا form
        try:
            data = json.loads(request.body)
        except json.JSONDecodeError:
            return JsonResponse({"error": "invalid JSON"}, status=400)
        book = Book.objects.create(
            title=data["title"],
            pages=data.get("pages", 0),
        )
        return JsonResponse({"id": book.id, "title": book.title}, status=201)


# ══════════════════════════════════════════════════════════════════
# TemplateView با context سفارشی
# ══════════════════════════════════════════════════════════════════

class StatsView(TemplateView):
    template_name = "library/stats.html"

    def get_context_data(self, **kwargs):
        # همیشه super() را اول صدا بزن تا context پیش‌فرض حفظ شود
        ctx = super().get_context_data(**kwargs)
        ctx["total_books"] = Book.objects.count()
        ctx["available"] = Book.objects.filter(is_available=True).count()
        ctx["avg_pages"] = Book.objects.aggregate(
            avg=Avg("pages")
        )["avg"] or 0
        return ctx


# ══════════════════════════════════════════════════════════════════
# API ساده با View — GET لیست، POST ساخت
# ══════════════════════════════════════════════════════════════════

class BookAPIView(View):
    """REST-مانند بدون DRF — برای یادگیری concept."""

    def get(self, request):
        books = Book.objects.values("id", "title", "pages", "price")
        return JsonResponse(list(books), safe=False)

    def post(self, request):
        try:
            body = json.loads(request.body)
            # اعتبارسنجی دستی
            if not body.get("title"):
                return JsonResponse({"error": "title required"}, status=400)
            book = Book.objects.create(
                title=body["title"],
                pages=body.get("pages", 0),
                price=body.get("price", 0),
            )
            return JsonResponse({"id": book.id}, status=201)
        except Exception as e:
            return JsonResponse({"error": str(e)}, status=400)

    def put(self, request, pk):
        # ویرایش کامل
        try:
            book = Book.objects.get(pk=pk)
            body = json.loads(request.body)
            for field in ("title", "pages", "price"):
                if field in body:
                    setattr(book, field, body[field])
            book.save()
            return JsonResponse({"id": book.id, "updated": True})
        except Book.DoesNotExist:
            return JsonResponse({"error": "not found"}, status=404)

    def delete(self, request, pk):
        try:
            Book.objects.get(pk=pk).delete()
            return JsonResponse({"deleted": True})
        except Book.DoesNotExist:
            return JsonResponse({"error": "not found"}, status=404)


# ══════════════════════════════════════════════════════════════════
# در urls.py:
# ══════════════════════════════════════════════════════════════════
# path("search/", BookSearchView.as_view(), name="search"),
# path("stats/", StatsView.as_view(), name="stats"),
# path("api/books/", BookAPIView.as_view(), name="api_books"),
# path("api/books/<int:pk>/", BookAPIView.as_view(), name="api_book_detail"),
