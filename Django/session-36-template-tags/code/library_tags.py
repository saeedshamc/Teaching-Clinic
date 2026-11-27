# templatetags/library_tags.py — Template Tags و Filter (جلسه ۳۶)
from django import template
from django.utils.safestring import mark_safe

register = template.Library()


# ══════════════════════════════════════════════════════════════════
# simple_tag — تگ ساده که مقدار برمی‌گرداند
# ══════════════════════════════════════════════════════════════════

@register.simple_tag
def total_books():
    """تعداد کل کتاب‌ها — در هر صفحه قابل استفاده."""
    from ..models import Book
    return Book.objects.count()


@register.simple_tag
def book_price(book, currency="تومان"):
    """قیمت کتاب با فرمت — {% book_price book %}"""
    return f"{book.price:,.0f} {currency}"


@register.simple_tag(takes_context=True)
def user_books_count(context):
    """تعداد کتاب‌های کاربر جاری — به context دسترسی دارد."""
    request = context.get("request")
    if request and request.user.is_authenticated:
        return request.user.books.count()
    return 0


# ══════════════════════════════════════════════════════════════════
# inclusion_tag — تگ با قالب داخلی
# ══════════════════════════════════════════════════════════════════

@register.inclusion_tag("library/_tags.html")
def book_tags(book):
    """رندر tagهای کتاب با قالب _tags.html."""
    return {"tags": book.tags.all()}


@register.inclusion_tag("library/_author_card.html")
def author_card(author):
    """کارت نویسنده با آمار کتاب‌ها."""
    return {
        "author": author,
        "book_count": author.books.count(),
        "avg_price": author.books.aggregate(
            avg=models.Avg("price")
        )["avg"] or 0,
    }


# ══════════════════════════════════════════════════════════════════
# filter — تبدیل مقدار
# ══════════════════════════════════════════════════════════════════

@register.filter
def fa_number(value):
    """تبدیل ارقام انگلیسی به فارسی: {{ 1234|fa_number }} → ۱۲۳۴"""
    return str(value).translate(str.maketrans("0123456789", "۰۱۲۳۴۵۶۷۸۹"))


@register.filter
def truncate_words_fa(value, count=5):
    """کوتاه کردن متن به N کلمه: {{ book.description|truncate_words_fa:3 }}"""
    words = str(value).split()
    if len(words) <= count:
        return value
    return " ".join(words[:count]) + "…"


@register.filter
def highlight(text, query):
    """هایلایت کلمهٔ جستجو — با mark_safe."""
    if not query:
        return text
    # Escape برای جلوگیری از XSS
    from django.utils.html import escape
    text_esc = escape(text)
    query_esc = escape(query)
    highlighted = text_esc.replace(
        query_esc,
        f'<mark style="background:yellow">{query_esc}</mark>'
    )
    return mark_safe(highlighted)


# ══════════════════════════════════════════════════════════════════
# استفاده در قالب:
# ══════════════════════════════════════════════════════════════════
# {% load library_tags %}
#
# <p>تعداد کتاب‌ها: {% total_books %}</p>
# <p>قیمت: {% book_price book %}</p>
# <p>کتاب‌های من: {% user_books_count %}</p>
#
# {% book_tags book %}
# {% author_card book.author %}
#
# {{ book.price|fa_number }}
# {{ book.description|truncate_words_fa:5 }}
# {{ book.title|highlight:search_query }}
