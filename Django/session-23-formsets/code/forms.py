# forms.py — Formset و ModelFormSet (جلسه ۲۳)
from django import forms
from django.forms import formset_factory, modelformset_factory, inlineformset_factory
from .models import Book, Tag


# ─── فرم پایه برای یک کتاب ────────────────────────────────────────
class BookForm(forms.ModelForm):
    class Meta:
        model = Book
        fields = ["title", "author", "pages", "price", "is_available"]
        widgets = {
            "title": forms.TextInput(attrs={"placeholder": "عنوان کتاب"}),
            "pages": forms.NumberInput(attrs={"min": 1}),
        }


# ─── formset ساده (بدون مدل) ──────────────────────────────────────
# برای فرم‌های تکراری مثل «افزودن چند Tag هم‌زمان»
class TagForm(forms.Form):
    name = forms.CharField(max_length=50, label="نام تگ")


TagFormSet = formset_factory(
    TagForm,
    extra=3,          # ۳ فرم خالی اضافه نشان بده
    can_delete=True,  # چک‌باکس حذف برای هر فرم
    min_num=1,        # حداقل ۱ فرم
    validate_min=True,
)


# ─── ModelFormSet — ویرایش/حذف دسته‌ای مدل ─────────────────────────
BookFormSet = modelformset_factory(
    Book,
    form=BookForm,
    fields=["title", "author", "pages", "price"],
    extra=0,          # فقط رکوردهای موجود (queryset از ویو می‌آید)
    can_delete=True,
)

# برای ایجاد دسته‌ای (نه ویرایش) extra=2 بگذارید
BookCreateFormSet = modelformset_factory(
    Book,
    form=BookForm,
    fields=["title", "author", "pages", "price"],
    extra=2,
    can_delete=True,
)


# ─── InlineFormSet — فرم‌های داخل مدل والد ─────────────────────────
# ویرایش یک Book + همهٔ Tagهایش در یک صفحه
BookTagFormSet = inlineformset_factory(
    Book,               # والد
    Book.tags.through,  # مدل میانی M2M
    fields=["tag"],
    extra=1,
    can_delete=True,
)


# ─── ویوی استفاده — در views.py بنویسید ────────────────────────────
# def books_bulk(request):
#     if request.method == "POST":
#         formset = BookFormSet(request.POST, queryset=Book.objects.all())
#         if formset.is_valid():
#             formset.save()   # ModelFormSet خودش save می‌کند!
#             return redirect("library:book_list")
#     else:
#         formset = BookFormSet(queryset=Book.objects.all())
#     return render(request, "library/formset.html", {"formset": formset})
