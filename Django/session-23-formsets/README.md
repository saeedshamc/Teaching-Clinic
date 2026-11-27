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
