# جلسه ۹ — قالب‌ها (Templates)

## درس

### render

```python
from django.shortcuts import render

def book_list(request):
    books = Book.objects.all()
    return render(request, "library/book_list.html", {"books": books})
```

`render` فایل `templates/library/book_list.html` را در اپ `library` پیدا می‌کند.

### سینتکس قالب جنگو

```
{{ book.title }}              ← خروجی متغیر (خروجی خودکار escape می‌شود → امن در برابر XSS)
{{ book.title|upper }}        ← filter: حروف بزرگ
{{ book.price|floatformat:2 }}← دو رقم اعشار
{% if book.is_available %} … {% else %} … {% endif %}
{% for book in books %} {{ forloop.counter }} {% empty %} … {% endfor %}
{% url 'library:book_detail' book.id %}   ← لینک امن بدون هاردکود
{% include 'library/_card.html' %}         ← قالب تکه‌ای reusable
{% load humanize %} + {{ num|intcomma }}   ← template tagهای داخلی
```

### ساختار پوشهٔ قالب

```
library/templates/
├── library/
│   ├── base.html          ← قالب پایه با {% block content %}
│   ├── book_list.html
│   └── book_detail.html
```

چرا `library/` داخل `templates/`؟ چون اگر دو اپ پوشهٔ هم‌نام داشته باشند، جنگو اول اپ فعال‌تر را پیدا می‌کند — namespace امن می‌ماند.

## کد نمونه

`code/base.html` و `code/book_list.html` را ببینید.

## تمرین

در `book_list.html` برای هر کتاب یک سطر با عنوان، نویسنده و قیمت بنویسید و اگر ناموجود است برچسب «ناموجود» نشان دهید.

## جواب تمرین

```html
{% for book in books %}
  <li>
    {{ book.title }} — {{ book.author.name }} — {{ book.price }} تومان
    {% if not book.is_available %}<b>(ناموجود)</b>{% endif %}
  </li>
{% empty %}
  <li>کتابی یافت نشد.</li>
{% endfor %}
```
