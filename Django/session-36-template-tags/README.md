# جلسه ۳۶ — Template Tags و Filterهای سفارشی

## درس

### simple_tag — تگ ساده

```python
# library/templatetags/library_tags.py
from django import template

register = template.Library()

@register.simple_tag
def total_books():
    from .models import Book
    return Book.objects.count()

@register.simple_tag
def book_price(book, currency="تومان"):
    return f"{book.price:,.0f} {currency}"
```

استفاده در قالب:

```html
{% load library_tags %}
<p>تعداد کتاب‌ها: {% total_books %}</p>
<p>قیمت: {% book_price book %}</p>
```

### inclusion_tag — تگ با قالب داخلی

```python
@register.inclusion_tag("library/_tags.html")
def book_tags(book):
    return {"tags": book.tags.all()}
```

`_tags.html`:

```html
{% for tag in tags %}<span class="tag">{{ tag.name }}</span>{% endfor %}
```

### filter سفارشی

```python
@register.filter
def fa_number(value):
    """تبدیل ارقام انگلیسی به فارسی."""
    return str(value).translate(str.maketrans("0123456789", "۰۱۲۳۴۵۶۷۸۹"))
```

استفاده: `{{ book.price|fa_number }}`

## کد نمونه

بالا کامنت‌گذاری شده — در `templatetags/library_tags.py` پیاده کنید.

## تمرین

یک filter به نام `truncate_words_fa` بنویسید که متن را به ۵ کلمهٔ فارسی کوتاه کند.

## جواب تمرین

```python
@register.filter
def truncate_words_fa(value, count=5):
    words = str(value).split()
    if len(words) <= count:
        return value
    return " ".join(words[:count]) + "…"
```
