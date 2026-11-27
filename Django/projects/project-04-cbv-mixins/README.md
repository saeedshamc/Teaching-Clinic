# پروژه ۴ — CRUD کامل با CBV و mixinها (جلسات ۱۶–۲۰)

## هدف
همهٔ CRUD را با Class-Based Views + mixinها بازنویسی کنید و پروژه را کامل کنید.

## تسک‌ها

1. `BookListView`, `BookDetailView`, `BookCreateView`, `BookUpdateView`, `BookDeleteView` با `LoginRequiredMixin`
2. `AuthorListView/Create/Update/Delete` با `AuthorRequiredMixin` سفارشی
3. `signals.py`: `post_save` روی Book → ساخت Profile اگر author تغییر کرد
4. حداقل ۵ تست: model (str, filter) + view (list 200, detail 404, create login)
5. بهینه‌سازی: `select_related("author")` در ListView + `paginate_by=12`

## معیارهای پذیرش

- [ ] همهٔ ۸ ویو با CBV کار می‌کنند
- [ ] کاربر لاگین‌نشده به login ریدایرکت می‌شود
- [ ] `python manage.py test` سبز است
- [ ] N+1 روی لیست کتاب‌ها وجود ندارد (با Debug Toolbar چک کنید)

## فایل‌های کلیدی

`views.py`, `urls.py`, `signals.py`, `apps.py`, `tests.py`, `admin.py`
