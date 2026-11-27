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

## 📖 شرح فایل‌های `code/`

**`views.py`**
- `HomeView(TemplateView)` — ساده‌ترین CBV؛ فقط `template_name` می‌خواهد.
- `BookListView(ListView)` — `model=Book` یعنی queryset خودکار `Book.objects.all()` است؛ `context_object_name="books"` نام متغیر قالب را تعیین می‌کند (پیش‌فرض `book_list`). `paginate_by=10` صفحه‌بندی خودکار می‌سازد و در قالب `page_obj` در دسترس است. `get_queryset` را override کردیم تا با `?q=` فیلتر کند.
- `BookDetailView(DetailView)` — URL باید `<int:pk>` داشته باشد چون DetailView از `pk` استفاده می‌کند؛ `context_object_name` پیش‌فرض نام مدل lowercase است (`book`).
- `BookCreateView(CreateView)` — `form_class=BookForm`؛ POST معتبر → `form_valid` → ذخیره → `success_url`. `reverse_lazy` چون urls هنوز لود نشده‌اند.
- `BookUpdateView(UpdateView)` — همان CreateView + `instance` از URL (`pk`) خودکار پر می‌شود.
- `BookDeleteView(DeleteView)` — GET صفحهٔ تأیید (`book_confirm_delete.html`) و POST حذف می‌کند.

**`urls.py`** — همهٔ CBVها با `.as_view()` به path وصل می‌شوند؛ `as_view()` یک تابع ویو از کلاس می‌سازد.
