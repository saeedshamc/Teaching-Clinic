# پروژه ۲ — CRUD وب برای کتاب‌ها (جلسات ۶–۱۰)

## هدف
روی مدل‌های پروژه ۱، کوئری ORM + ادمین + ویو + قالب + static/media بسازید.

## تسک‌ها

1. کوئری‌های ORM: لیست کتاب‌های موجود با بیش از ۲۰۰ صفحه، میانگین قیمت، کتاب‌های هر نویسنده
2. `BookAdmin` با list_display/filter/search/ordering + اکشن «ناموجود کردن»
3. ویوهای `book_list` و `book_detail` با `render` + template inheritance
4. قالب `base.html` + `book_list.html` + `book_detail.html` با `{% url %}` و `{{ }}`
5. `cover = ImageField` + `MEDIA_URL/ROOT` + نمایش در قالب

## معیارهای پذیرش

- [ ] `/books/` لیست را با قالب نمایش می‌دهد
- [ ] `/books/<id>/` جزئیات را نشان می‌دهد
- [ ] `/admin/` لیست/ویرایش/جستجو کار می‌کند
- [ ] آپلود جلد در ادمین و نمایش در قالب

## فایل‌های کلیدی

`models.py`, `views.py`, `urls.py`, `admin.py`, `templates/library/*.html`, `static/library/style.css`
