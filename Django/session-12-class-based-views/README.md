# جلسه ۱۲ — ویوهای مبتنی بر کلاس (CBV)

## درس

Django مجموعه‌ای از **Class-Based Views** آماده دارد که کارهای رایج را generic می‌کنند:

| کلاس | کار |
|---|---|
| `TemplateView` | نمایش یک قالب ثابت |
| `ListView` | لیست اشیاء از مدل |
| `DetailView` | جزئیات یک شیء |
| `CreateView` | ساخت شیء جدید (با Form) |
| `UpdateView` | ویرایش شیء |
| `DeleteView` | حذف شیء |
| `FormView` / `FormMixin` | فرم سفارشی |

### مثال ListView + DetailView

```python
from django.views.generic import ListView, DetailView
from .models import Book

class BookListView(ListView):
    model = Book
    template_name = "library/book_list.html"
    context_object_name = "books"   # نام متغیر قالب (پیش‌فرض: book_list)
    paginate_by = 10                 # صفحه‌بندی خودکار
    ordering = ["title"]

class BookDetailView(DetailView):
    model = Book
    template_name = "library/book_detail.html"
    # context_object_name پیش‌فرض: book
```

### در urls.py

```python
path("books/", views.BookListView.as_view(), name="book_list"),
path("books/<int:pk>/", views.BookDetailView.as_view(), name="book_detail"),
```

نکته: `DetailView` به‌جای `book_id` از **`pk`** استفاده می‌کند.

### CreateView

```python
class BookCreateView(CreateView):
    model = Book
    form_class = BookForm
    template_name = "library/book_form.html"
    success_url = reverse_lazy("library:book_list")
```

## کد نمونه

`code/views.py` و `code/urls.py` را ببینید.

## تمرین

با استفاده از `CreateView` ویوی ساخت کتاب را بازنویسی کنید و در `urls.py` وصل کنید.

## جواب تمرین

```python
class BookCreateView(CreateView):
    model = Book
    form_class = BookForm
    template_name = "library/book_form.html"
    success_url = reverse_lazy("library:book_list")

# urls.py: path("books/new/", views.BookCreateView.as_view(), name="book_create")
```
