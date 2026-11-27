# جلسه ۸ — URLها و ویوهای تابعی

## درس

### UrlPattern

`path()` در `urls.py` سه آرگومان می‌گیرد: **route** (الگوی مسیر)، **view**، و **name** اختیاری.

```python
path("books/<int:book_id>/", views.book_detail, name="book_detail")
# → /books/5/ با book_id=5 به ویو می‌رسد
```

### Converters پرکاربرد

`str` (پیش‌فرض، بدون /)، `int`، `slug`، `uuid`، `path` (شامل /)

### ویوهای تابعی

ویو تابعی یک تابع Python است که `request` می‌گیرد و `HttpResponse` برمی‌گرداند:

```python
from django.http import HttpResponse, Http404, JsonResponse
from django.shortcuts import get_object_or_404, render

def book_detail(request, book_id):
    book = get_object_or_404(Book, pk=book_id)   # اگر نبود → 404
    return render(request, "library/book_detail.html", {"book": book})

def api_books(request):
    books = Book.objects.values("id", "title")
    return JsonResponse(list(books), safe=False)  # safe=False برای list
```

### reverse و {% url %}

به‌جای هاردکد کردن مسیر، از نام استفاده کنید تا تغییر مسیرها آسان شود:

```python
from django.urls import reverse
reverse("book_detail", args=[5])   # → '/books/5/'
# در قالب: {% url 'book_detail' book.id %}
```

## کد نمونه

`code/views.py` و `code/urls.py` را ببینید.

## تمرین

یک ویو `book_list` بنویسید که همهٔ کتاب‌ها را با `JsonResponse` به‌صورت JSON برگرداند.

## جواب تمرین

```python
def book_list(request):
    data = Book.objects.values("id", "title", "pages", "price")
    return JsonResponse(list(data), safe=False)
```

## 📖 شرح فایل‌های `code/`

**`urls.py`**
- `app_name = "library"` — namespace؛ در قالب و reverse باید `'library:book_detail'` بنویسید.
- `path("books/<int:book_id>/", ...)` — converter `int` فقط عدد می‌پذیرد و آن را به‌صورت int به ویو می‌دهد؛ نام converter هر چیزی می‌تواند باشد (`book_id`) و به‌عنوان kwarg به ویو پاس می‌شود.

**`views.py`**
- `book_list` — `select_related("author")` برای جلوگیری از N+1؛ `render` قالب + context dict.
- `book_detail` — `get_object_or_404(Book, pk=book_id)` به‌جای try/except؛ اگر نبود 404 می‌دهد.
- `api_books` — `values()` خروجی دیکشنری می‌دهد (سبک‌تر از مدل)؛ `JsonResponse(list, safe=False)` برای serialize لیست.
- `api_book` — `str(book.price)` چون `Decimal` به‌صورت خودکار JSON serialize نمی‌شود.
