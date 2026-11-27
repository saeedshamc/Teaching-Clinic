# جلسه ۲۳ — Formset و ModelFormSet

## درس

Formset = چند فرم یکسان روی یک صفحه (مثلاً افزودن چند کتاب هم‌زمان).

### formset ساده

```python
from django import forms
from django.forms import formset_factory

class BookForm(forms.Form):
    title = forms.CharField()
    pages = forms.IntegerField(min_value=1)

BookFormSet = formset_factory(BookForm, extra=3)   # ۳ فرم خالی اضافه
```

### ModelFormSet — برای مدل

```python
from django.forms import modelformset_factory

BookFormSet = modelformset_factory(
    Book, form=BookForm,
    fields=["title", "author", "pages"],
    extra=2, can_delete=True,
)
```

### Inline FormSet — فرم‌های داخل مدل والد

```python
# ویرایش کتاب + همهٔ tagهایش در یک صفحه
TagFormSet = inlineformset_factory(
    Book, Book.tags.through,
    fields=["tag"], extra=1, can_delete=True,
)
```

### در ویو

```python
def books_bulk(request):
    if request.method == "POST":
        formset = BookFormSet(request.POST)
        if formset.is_valid():
            for form in formset:
                if form.cleaned_data.get("title"):
                    Book.objects.create(**form.cleaned_data)
            return redirect("library:book_list")
    else:
        formset = BookFormSet()
    return render(request, "library/formset.html", {"formset": formset})
```

## کد نمونه

داخل README کامنت‌گذاری شده — در `forms.py`/`views.py` پیاده کنید.

## تمرین

یک `ModelFormSet` برای ویرایش دسته‌ای کتاب‌ها (فقط `title` و `is_available`) بسازید.

## جواب تمرین

```python
BookBulkFormSet = modelformset_factory(
    Book,
    fields=["title", "is_available"],
    extra=0,
    can_delete=True,
)
# در ویو: queryset=Book.objects.all() برای ویرایش موجودها
```

## 📖 شرح فایل‌های `code/`

**`forms.py`**
- `BookForm` — ModelForm پایه با widgets سفارشی.
- `TagFormSet = formset_factory(TagForm, extra=3, can_delete=True)` — ۳ فرم خالی + چک‌باکس حذف برای فرم‌های غیرمدلی.
- `BookFormSet` / `BookCreateFormSet` — `modelformset_factory` برای ویرایش/ایجاد دسته‌ای مدل؛ `extra=0` فقط موجودها، `extra=2` فرم خالی برای ایجاد.
- `BookTagFormSet = inlineformset_factory(Book, Book.tags.through)` — فرم‌های inline داخل مدل والد.

**`views.py`**
- `books_bulk_edit` — POST → `BookFormSet(request.POST, queryset=...)`؛ `formset.save()` همه را یک‌جا ذخیره می‌کند.
- `books_bulk_create` — همان الگو بدون queryset (فقط رکوردهای جدید).
- `book_edit_with_tags` — `BookForm` + `BookTagFormSet` با `instance=book` در یک صفحه.
- `tags_bulk` — formset ساده؛ هر فرم `cleaned_data["name"]` را `get_or_create` می‌کند.
