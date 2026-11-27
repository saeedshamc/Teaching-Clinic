# views.py — ویوهای تابعی با کامنت فارسی
from django.http import Http404, JsonResponse
from django.shortcuts import get_object_or_404, render

from .models import Book


def book_list(request):
    """لیست همهٔ کتاب‌ها به‌صورت HTML."""
    books = Book.objects.select_related("author").order_by("title")
    return render(request, "library/book_list.html", {"books": books})


def book_detail(request, book_id):
    """جزئیات یک کتاب — book_id از URL می‌آید (type=int در urls.py).

    get_object_or_404: اگر کتاب پیدا نشد، به‌جای DoesNotExist
    یک پاسخ 404 تمیز برمی‌گرداند.
    """
    book = get_object_or_404(Book, pk=book_id)
    return render(request, "library/book_detail.html", {"book": book})


def api_books(request):
    """API ساده — برگرداندن لیست کتاب‌ها به‌صورت JSON.

    JsonResponse(list, safe=False): safe=False لازم است چون
    لیست یک dict نیست.
    """
    data = Book.objects.values("id", "title", "pages", "price")
    return JsonResponse(list(data), safe=False)


def api_book(request, book_id):
    """جزئیات یک کتاب به‌صورت JSON."""
    book = get_object_or_404(Book, pk=book_id)
    data = {
        "id": book.id,
        "title": book.title,
        "pages": book.pages,
        "price": str(book.price),   # Decimal → str برای JSON
    }
    return JsonResponse(data)
