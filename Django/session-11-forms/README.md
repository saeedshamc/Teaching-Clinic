# جلسه ۱۱ — فرم‌ها و ModelForm

## درس

### Form ساده

```python
from django import forms

class BookForm(forms.Form):
    title = forms.CharField(max_length=200, label="عنوان")
    pages = forms.IntegerField(min_value=1, label="تعداد صفحات")
    price = forms.DecimalField(max_digits=8, decimal_places=2)
```

### ModelForm — فرم از روی مدل

```python
class BookForm(forms.ModelForm):
    class Meta:
        model = Book
        fields = ["title", "author", "pages", "price", "is_available"]
        # یا: exclude = ["created_at"]
        widgets = {
            "title": forms.TextInput(attrs={"class": "form-control", "placeholder": "عنوان کتاب"}),
        }
```

### استفاده در ویو

```python
def book_create(request):
    if request.method == "POST":
        form = BookForm(request.POST, request.FILES)  # FILES برای آپلود
        if form.is_valid():
            book = form.save()          # ModelForm → ذخیره در دیتابیس
            return redirect("library:book_detail", book.id)
    else:
        form = BookForm()
    return render(request, "library/book_form.html", {"form": form})
```

### در قالب

```html
<form method="post" enctype="multipart/form-data">
  {% csrf_token %}        <!-- الزامی برای POST — امنیت -->
  {{ form.as_p }}         <!-- یا دستی: {{ form.title }} {{ form.title.errors }} -->
  <button type="submit">ذخیره</button>
</form>
```

## کد نمونه

`code/forms.py` و `code/views.py` را ببینید.

## تمرین

یک `ModelForm` برای مدل `Author` با فقط فیلدهای `name` و `birth_year` بسازید و ویوی ساخت آن را بنویسید.

## جواب تمرین

```python
class AuthorForm(forms.ModelForm):
    class Meta:
        model = Author
        fields = ["name", "birth_year"]

# ویو مشابه book_create با redirect به لیست نویسندگان
```

## 📖 شرح فایل‌های `code/`

**`forms.py`**
- `BookForm(forms.ModelForm)` — `Meta.model` و `Meta.fields` تعیین می‌کنند فرم از کدام فیلدهای مدل ساخته شود.
- `Meta.labels` — برچسب فارسی هر فیلد؛ `Meta.widgets` — HTML attrs مثل class و placeholder.
- `Meta.help_texts` — متن راهنما زیر فیلد.
- `clean_pages()` — اعتبارسنجی یک فیلد خاص؛ خروجی `cleaned_data` را برمی‌گرداند یا `ValidationError` می‌اندازد.
- `clean()` — اعتبارسنجی چندفیلدی؛ `super().clean()` را صدا بزنید وگرنه داده‌ها پاک می‌شوند.
- `AuthorForm` — همان الگو برای مدل Author.

**`views.py`**
- `book_create` — `request.method == "POST"`؟ اگر بله `BookForm(request.POST, request.FILES)`؛ `is_valid()` → `form.save()` → `redirect` (الگوی POST/Redirect/GET). `request.FILES` برای آپلود فایل لازم است.
- `book_update` — همان الگو + `instance=book` → به‌جای INSERT، UPDATE انجام می‌شود. در GET فرم با مقادیر فعلی پر می‌شود.

**`book_form.html`**
- `enctype="multipart/form-data"` — بدون آن فایل آپلود نمی‌شود؛ `novalidate` اعتبارسنجی HTML5 را خاموش می‌کند تا خطاهای Django نمایش داده شوند.
- `{% csrf_token %}` — توکن امنیتی؛ بدون آن POST با 403 رد می‌شود.
- `form.non_field_errors` — خطاهای کلی (مثل clean چندفیلدی)؛ `form.as_p` همهٔ فیلدها را با label+widget+errors رندر می‌کند.
