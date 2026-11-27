# views.py — ویوهای مبتنی بر کلاس (CBV) با کامنت فارسی
from django.urls import reverse_lazy
from django.views.generic import (
    CreateView, DeleteView, DetailView, ListView, TemplateView, UpdateView,
)

from .forms import BookForm
from .models import Book


class HomeView(TemplateView):
    """ساده‌ترین CBV — فقط یک قالب را رندر می‌کند."""
    template_name = "library/home.html"


class BookListView(ListView):
    """لیست کتاب‌ها — به‌صورت خودکار Book.objects.all() را می‌گیرد."""
    model = Book
    template_name = "library/book_list.html"
    context_object_name = "books"   # نام متغیر در قالب
    ordering = ["title"]
    paginate_by = 10                 # صفحه‌بندی خودکار (در قالب از page_obj استفاده کنید)

    # فیلتر پیشرفته با get_queryset
    def get_queryset(self):
        qs = super().get_queryset()
        q = self.request.GET.get("q")
        if q:
            qs = qs.filter(title__icontains=q)
        return qs


class BookDetailView(DetailView):
    """جزئیات یک کتاب — URL باید <int:pk> داشته باشد."""
    model = Book
    template_name = "library/book_detail.html"
    # context_object_name پیش‌فرض: book


class BookCreateView(CreateView):
    """فرم ساخت کتاب — POST معتبر → ذخیره → redirect به success_url."""
    model = Book
    form_class = BookForm
    template_name = "library/book_form.html"
    success_url = reverse_lazy("library:book_list")  # lazy چون URLها هنوز لود نشده‌اند

    def form_valid(self, form):
        # می‌توانید قبل از ذخیره تغییر دهید، مثلاً assign کردن کاربر
        return super().form_valid(form)


class BookUpdateView(UpdateView):
    model = Book
    form_class = BookForm
    template_name = "library/book_form.html"
    success_url = reverse_lazy("library:book_list")


class BookDeleteView(DeleteView):
    model = Book
    template_name = "library/book_confirm_delete.html"
    success_url = reverse_lazy("library:book_list")
