# forms.py — Form و ModelForm با کامنت فارسی
from django import forms

from .models import Author, Book


class BookForm(forms.ModelForm):
    """فرم ساخت/ویرایش کتاب — فیلدها از روی مدل ساخته می‌شوند."""

    class Meta:
        model = Book
        # فیلدهایی که در فرم می‌آیند — id و created_at نمی‌آیند
        fields = ["title", "author", "pages", "price", "is_available", "tags"]
        # فیلدهای اختیاری می‌توانند در اینجا سفارشی شوند
        labels = {
            "title": "عنوان کتاب",
            "author": "نویسنده",
            "pages": "تعداد صفحات",
            "price": "قیمت (تومان)",
            "is_available": "موجود است",
            "tags": "برچسب‌ها",
        }
        # widgets برای کنترل HTML خروجی
        widgets = {
            "title": forms.TextInput(attrs={
                "class": "form-control",
                "placeholder": "مثلاً: جنگ و صلح",
            }),
            "pages": forms.NumberInput(attrs={"min": "1"}),
            "price": forms.NumberInput(attrs={"step": "0.01"}),
        }
        # help_text زیر هر فیلد نمایش داده می‌شود
        help_texts = {
            "tags": "با Ctrl+کلیک چند برچسب انتخاب کنید",
        }

    # اعتبارسنجی سفارشی یک فیلد — clean_<fieldname>
    def clean_pages(self):
        pages = self.cleaned_data["pages"]
        if pages > 5000:
            raise forms.ValidationError("کتابی با بیش از ۵۰۰۰ صفحه غیرممکن است!")
        return pages

    # اعتبارسنجی روی چند فیلد — clean()
    def clean(self):
        cleaned = super().clean()
        price = cleaned.get("price")
        pages = cleaned.get("pages")
        if price and pages and price > pages * 1000:
            raise forms.ValidationError("قیمت مشکوک است!")
        return cleaned


class AuthorForm(forms.ModelForm):
    """فرم سادهٔ نویسنده."""

    class Meta:
        model = Author
        fields = ["name", "birth_year"]
        labels = {"name": "نام", "birth_year": "سال تولد"}
