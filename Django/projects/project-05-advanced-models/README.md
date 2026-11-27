# پروژه ۵ — مدل‌های پیشرفته: manager + abstract + formset + جستجو (جلسات ۲۱–۲۵)

## هدف
لایه‌های abstraction و جستجوی حرفه‌ای روی اپ کتابخانه.

## تسک‌ها

1. `BookQuerySet` با `available()`, `expensive(min)`, `by_author(name)` + `BookManager.from_queryset`
2. `TimestampedModel` (abstract) با created_at/updated_at → استفاده در Book و Review
3. `SluggedModel` (abstract) با slug خودکار از title در `save()`
4. `BookFormSet` (modelformset) برای ویرایش دسته‌ای کتاب‌ها
5. `BookFilter` (django-filter) + `Q` objects برای جستجوی ترکیبی

## معیارهای پذیرش

- [ ] `Book.objects.available()` کار می‌کند
- [ ] فیلدهای timestamp بدون تکرار در هر مدل
- [ ] formset حداقل ۳ کتاب را یک‌جا ذخیره می‌کند
- [ ] `/books/?price__gte=100&author=1&q=django` فیلتر می‌کند

## فایل‌های کلیدی

`models.py`, `forms.py`, `views.py`, `filters.py`
