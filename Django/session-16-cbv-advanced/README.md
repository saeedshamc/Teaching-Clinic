# جلسه ۱۶ — mixinها و CRUD کامل با CBV

## درس

### LoginRequiredMixin + PermissionRequiredMixin

```python
from django.contrib.auth.mixins import LoginRequiredMixin, PermissionRequiredMixin

class BookCreateView(LoginRequiredMixin, CreateView):
    login_url = "/login/"          # پیش‌فرض از settings.LOGIN_URL
    # permission_required = "library.add_book"
```

ترتیب مهم است: **Mixin اول، View بعد**.

### mixinهای سفارشی

```python
class AuthorRequiredMixin:
    """فقط کاربری که request.user.author دارد عبور می‌کند."""
    def dispatch(self, request, *args, **kwargs):
        if not hasattr(request.user, "author"):
            return redirect("library:home")
        return super().dispatch(request, *args, **kwargs)

class BookCreateView(AuthorRequiredMixin, LoginRequiredMixin, CreateView): ...
```

### CRUD کامل در یک نگاه

```python
# urls.py
path("books/", BookListView.as_view(), name="book_list"),
path("books/<int:pk>/", BookDetailView.as_view(), name="book_detail"),
path("books/new/", BookCreateView.as_view(), name="book_create"),
path("books/<int:pk>/edit/", BookUpdateView.as_view(), name="book_update"),
path("books/<int:pk>/delete/", BookDeleteView.as_view(), name="book_delete"),
```

## کد نمونه

`code/views.py` را ببینید — CRUD کامل با mixinها.

## تمرین

برای `Author` هم CRUD کامل بسازید (List/Create/Update/Delete) با `LoginRequiredMixin`.

## جواب تمرین

```python
class AuthorListView(LoginRequiredMixin, ListView):
    model = Author
    template_name = "library/author_list.html"
    context_object_name = "authors"

class AuthorCreateView(LoginRequiredMixin, CreateView):
    model = Author
    form_class = AuthorForm
    template_name = "library/author_form.html"
    success_url = reverse_lazy("library:author_list")
# + UpdateView و DeleteView مشابه
```
