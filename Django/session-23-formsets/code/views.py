# views.py — ویوهای formset (جلسه ۲۳)
import json
from django.shortcuts import render, redirect, get_object_or_404
from django.http import JsonResponse
from django.views import View
from django.views.generic import TemplateView
from django.utils.decorators import method_decorator
from django.contrib.auth.decorators import login_required
from .models import Book
from .forms import BookFormSet, BookCreateFormSet, BookTagFormSet, TagFormSet


@login_required
def books_bulk_edit(request):
    """ویرایش دسته‌ای کتاب‌ها — ModelFormSet."""
    if request.method == "POST":
        formset = BookFormSet(request.POST, queryset=Book.objects.all())
        if formset.is_valid():
            formset.save()          # همهٔ تغییرات یک‌جا ذخیره می‌شوند
            return redirect("library:book_list")
    else:
        formset = BookFormSet(queryset=Book.objects.all())
    return render(request, "library/formset.html", {"formset": formset})


@login_required
def books_bulk_create(request):
    """ایجاد دسته‌ای کتاب‌ها — extra=2 فرم خالی."""
    if request.method == "POST":
        formset = BookCreateFormSet(request.POST)
        if formset.is_valid():
            formset.save()
            return redirect("library:book_list")
    else:
        formset = BookCreateFormSet()
    return render(request, "library/formset_create.html", {"formset": formset})


@login_required
def book_edit_with_tags(request, pk):
    """ویرایش کتاب + tagهایش — InlineFormSet."""
    book = get_object_or_404(Book, pk=pk)
    if request.method == "POST":
        form = BookFormSet.form   # از forms.py: BookForm
        book_form = form(request.POST, instance=book)
        tag_formset = BookTagFormSet(request.POST, instance=book)
        if book_form.is_valid() and tag_formset.is_valid():
            book_form.save()
            tag_formset.save()
            return redirect("library:book_detail", pk=pk)
    else:
        book_form = BookFormSet.form(instance=book)
        tag_formset = BookTagFormSet(instance=book)
    return render(request, "library/book_tags.html", {
        "book_form": book_form,
        "tag_formset": tag_formset,
        "book": book,
    })


@login_required
def tags_bulk(request):
    """افزودن چند تگ هم‌زمان — formset ساده."""
    if request.method == "POST":
        formset = TagFormSet(request.POST)
        if formset.is_valid():
            for form in formset:
                name = form.cleaned_data.get("name")
                if name:
                    from .models import Tag
                    Tag.objects.get_or_create(name=name)
            return redirect("library:tag_list")
    else:
        formset = TagFormSet()
    return render(request, "library/tag_formset.html", {"formset": formset})
