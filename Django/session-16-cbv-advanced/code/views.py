# views.py — CRUD کامل با mixinها و کنترل دسترسی
from django.contrib.auth.mixins import LoginRequiredMixin, PermissionRequiredMixin
from django.urls import reverse_lazy
from django.views.generic import (
    CreateView, DeleteView, DetailView, ListView, UpdateView,
)

from .forms import AuthorForm, BookForm
from .models import Author, Book


class AuthorRequiredMixin:
    """فقط کاربران دارای پروفایل author اجازهٔ ورود دارند.

    dispatch قبل از هر متد (get/post/...) صدا زده می‌شود —
    اگر شرط برقرار نباشد، request هرگز به ویو نمی‌رسد.
    """
    def dispatch(self, request, *args, **kwargs):
        if not hasattr(request.user, "author"):
            # messages.error(request, "دسترسی ندارید")
            return redirect("library:home")
        return super().dispatch(request, *args, **kwargs)


# ---------- Book CRUD ----------
class BookListView(ListView):
    model = Book
    template_name = "library/book_list.html"
    context_object_name = "books"
    ordering = ["title"]
    paginate_by = 12


class BookDetailView(DetailView):
    model = Book
    template_name = "library/book_detail.html"


class BookCreateView(LoginRequiredMixin, CreateView):
    """فقط کاربر لاگین‌شده — LoginRequiredMixin باید قبل از CreateView بیاید."""
    model = Book
    form_class = BookForm
    template_name = "library/book_form.html"
    success_url = reverse_lazy("library:book_list")

    def form_valid(self, form):
        # می‌توانید قبل از ذخیره فیلد اضافه کنید
        return super().form_valid(form)


class BookUpdateView(LoginRequiredMixin, PermissionRequiredMixin, UpdateView):
    """ویرایش — نیازمند لاگین + پرمیشن change_book."""
    model = Book
    form_class = BookForm
    template_name = "library/book_form.html"
    success_url = reverse_lazy("library:book_list")
    permission_required = "library.change_book"


class BookDeleteView(LoginRequiredMixin, DeleteView):
    model = Book
    template_name = "library/book_confirm_delete.html"
    success_url = reverse_lazy("library:book_list")


# ---------- Author CRUD (فقط نویسندگان مجاز) ----------
class AuthorListView(AuthorRequiredMixin, ListView):
    model = Author
    template_name = "library/author_list.html"
    context_object_name = "authors"


class AuthorCreateView(AuthorRequiredMixin, CreateView):
    model = Author
    form_class = AuthorForm
    template_name = "library/author_form.html"
    success_url = reverse_lazy("library:author_list")


class AuthorUpdateView(AuthorRequiredMixin, UpdateView):
    model = Author
    form_class = AuthorForm
    template_name = "library/author_form.html"
    success_url = reverse_lazy("library:author_list")


class AuthorDeleteView(AuthorRequiredMixin, DeleteView):
    model = Author
    template_name = "library/author_confirm_delete.html"
    success_url = reverse_lazy("library:author_list")
