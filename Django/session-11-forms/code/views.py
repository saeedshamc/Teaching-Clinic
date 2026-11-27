# views.py — ویوهای فرم (ساخت و ویرایش کتاب)
from django.shortcuts import get_object_or_404, redirect, render

from .forms import BookForm
from .models import Book


def book_create(request):
    """ساخت کتاب جدید — GET فرم خالی، POST پردازش."""
    if request.method == "POST":
        # request.POST داده‌های فرم، request.FILES فایل‌های آپلودی
        form = BookForm(request.POST, request.FILES)
        if form.is_valid():
            book = form.save()   # ModelForm: ساخت instance و ذخیره
            # redirect به صفحهٔ جزئیات همان کتاب (POST/Redirect/GET)
            return redirect("library:book_detail", book.id)
        # اگر نامعتبر باشد، همان فرم با خطاها برمی‌گردد
    else:
        form = BookForm()

    return render(request, "library/book_form.html", {
        "form": form,
        "title": "کتاب جدید",
    })


def book_update(request, book_id):
    """ویرایش کتاب موجود — همان الگو با instance."""
    book = get_object_or_404(Book, pk=book_id)

    if request.method == "POST":
        form = BookForm(request.POST, request.FILES, instance=book)
        if form.is_valid():
            form.save()   # به‌جای INSERT، UPDATE انجام می‌شود
            return redirect("library:book_detail", book.id)
    else:
        form = BookForm(instance=book)   # فرم با مقادیر فعلی پر می‌شود

    return render(request, "library/book_form.html", {
        "form": form,
        "title": f"ویرایش {book.title}",
    })
