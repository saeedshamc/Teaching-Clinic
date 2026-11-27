# پروژه ۳ — فرم‌ها و احراز هویت (جلسات ۱۱–۱۵)

## هدف
فرم ساخت/ویرایش کتاب + سیستم ورود/خروج + session و middleware.

## تسک‌ها

1. `BookForm(ModelForm)` با clean() سفارشی (قیمت > ۵۰۰۰۰۰ → خطا)
2. ویوهای `book_create`/`book_update` با POST/redirect/GET
3. `login_view`/`logout_view` با `authenticate`/`login`/`logout` + `login_required`
4. `request.session["theme"]` برای تم روشن/تاریک + `messages.success/error`
5. middleware `TimingMiddleware` که `X-Response-Time` هدر اضافه کند

## معیارهای پذیرش

- [ ] بدون لاگین، create/update به login ریدایرکت می‌شود
- [ ] فرم نامعتبر خطای فارسی نشان می‌دهد
- [ ] تم در session ذخیره و در base.html اعمال می‌شود
- [ ] پیام‌ها یک‌بار نمایش داده می‌شوند

## فایل‌های کلیدی

`forms.py`, `views.py`, `urls.py`, `middleware.py`, `templates/registration/login.html`, `templates/library/book_form.html`
