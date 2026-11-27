# settings_i18n.py — تنظیمات بین‌المللی‌سازی (جلسه ۳۷)
# این بخش را در settings.py اضافه کنید.

# ══════════════════════════════════════════════════════════════════
# settings.py — i18n
# ══════════════════════════════════════════════════════════════════

USE_I18N = True
USE_L10N = True

LANGUAGE_CODE = "fa"               # پیش‌فرض فارسی
LANGUAGES = [
    ("fa", "فارسی"),
    ("en", "English"),
]

LOCALE_PATHS = [
    BASE_DIR / "locale",            # فایل‌های ترجمه اینجا
]

MIDDLEWARE = [
    # LocaleMiddleware باید بعد از SessionMiddleware و قبل از CommonMiddleware باشد
    "django.middleware.locale.LocaleMiddleware",
    "django.contrib.sessions.middleware.SessionMiddleware",
    "django.middleware.common.CommonMiddleware",
    # ... بقیه middlewareها
]

# ══════════════════════════════════════════════════════════════════
# استفاده در پایتون
# ══════════════════════════════════════════════════════════════════
# from django.utils.translation import gettext as _
#
# def book_list(request):
#     title = _("لیست کتاب‌ها")     # gettext → ترجمه در صورت وجود
#     empty = _("کتابی یافت نشد")
#     return render(request, "library/book_list.html", {
#         "title": title,
#         "empty": empty,
#     })

# ══════════════════════════════════════════════════════════════════
# استفاده در قالب
# ══════════════════════════════════════════════════════════════════
# {% load i18n %}
# <h1>{% translate "لیست کتاب‌ها" %}</h1>
#
# {% blocktranslate with count=books.count %}
#     {{ count }} کتاب یافت شد
# {% endblocktranslate %}
#
# {% blocktranslate with name=book.title %}
#     کتاب «{{ name }}» موجود است
# {% endblocktranslate %}

# ══════════════════════════════════════════════════════════════════
# ساخت فایل ترجمه
# ══════════════════════════════════════════════════════════════════
# python manage.py makemessages -l en    # استخراج رشته‌ها → locale/en/LC_MESSAGES/django.po
# # فایل django.po را باز کنید و msgstr را پر کنید:
# #   msgid "لیست کتاب‌ها"
# #   msgstr "Book List"
# python manage.py compilemessages       # → django.mo (باینری برای runtime)

# ══════════════════════════════════════════════════════════════════
# تغییر زبان با URL — i18n_patterns
# ══════════════════════════════════════════════════════════════════
# # config/urls.py
# from django.conf.urls.i18n import i18n_patterns
#
# urlpatterns = [
#     path("admin/", admin.site.urls),
# ] + i18n_patterns(
#     path("", include("library.urls")),
#     # → /fa/books/ و /en/books/
# )

# ══════════════════════════════════════════════════════════════════
# تغییر زبان با session — برای کاربر لاگین‌شده
# ══════════════════════════════════════════════════════════════════
# # views.py
# from django.utils import translation
# from django.utils.translation import gettext as _
#
# def set_language(request):
#     lang = request.POST.get("language")
#     translation.activate(lang)
#     request.session["django_language"] = lang
#     return redirect(request.META.get("HTTP_REFERER", "/"))
#
# # در template:
# # <form method="post" action="{% url 'set_language' %}">
# #   {% csrf_token %}
# #   <select name="language">
# #     <option value="fa">فارسی</option>
# #     <option value="en">English</option>
# #   </select>
# #   <button type="submit">تغییر زبان</button>
# # </form>

# ══════════════════════════════════════════════════════════════════
# نکات:
# ══════════════════════════════════════════════════════════════════
# - {% trans %} برای متن ساده، {% blocktranslate %} برای متن با متغیر
# - pluralization: {% blocktranslate count counter=list|length %}
#       یک کتاب
#   plural
#       {{ counter }} کتاب
#   {% endblocktranslate %}
# - برای تاریخ/عدد: {{ value|date:"Y-m-d" }} و {{ value|intcomma }}
# - LOCALE_PATHS باید قبل از compilemessages تنظیم شود
