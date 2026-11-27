# جلسه ۲۰ — پروژهٔ نهایی: اپ «کتابخانه»

## درس

در این جلسه همهٔ آموخته‌ها را در یک اپ کامل جمع می‌کنیم: مدل با روابط، Form/ModelForm، CBV با mixin، auth، admin و template inheritance.

### معماری اپ library

```
library/
├── models.py       # Author, Book, Tag, Profile
├── forms.py        # BookForm, AuthorForm
├── views.py        # CBVهای CRUD + mixinها
├── urls.py         # همهٔ مسیرها با name
├── admin.py        # ModelAdmin سفارشی
├── signals.py      # post_save برای ساخت Profile
├── apps.py         # ready() برای لود سیگنال
└── templates/library/
    ├── base.html
    ├── book_list.html / book_detail.html / book_form.html
    └── author_list.html / author_form.html
```

### چک‌لیست فیچرها

- [x] لیست و جزئیات کتاب با pagination
- [x] ساخت/ویرایش/حذف کتاب (فقط کاربر لاگین‌شده)
- [x] جستجوی کتاب با `?q=` در ListView
- [x] CRUD نویسنده با `AuthorRequiredMixin`
- [x] admin با search/filter/actions
- [x] سیگنال post_save برای Profile
- [x] template inheritance با base.html
- [x] messages برای بازخورد کاربر

## کد نمونه

`code/models.py` — مدل‌های نهایی پروژه را ببینید.

## تمرین نهایی

یک ویوی `search` اضافه کنید که نتایج جستجو را در `book_list.html` با هایلایت کلمهٔ جستجو نشان دهد. از `mark_safe` یا template filter استفاده نکنید مگر necessity داشته باشید — ترجیحاً `{{ book.title }}` را دست نزنید و فقط CSS برای `.highlight` اضافه کنید.

## جواب تمرین

```python
# views.py — داخل get_queryset از BookListView
qs = qs.filter(title__icontains=q)
# در قالب: از {% if query %}<mark>{{ query }}</mark>{% endif %} استفاده نکنید
# چون title از قبل escape شده؛ ساده‌ترین راه: CSS + JS یا template filter سفارشی
```
